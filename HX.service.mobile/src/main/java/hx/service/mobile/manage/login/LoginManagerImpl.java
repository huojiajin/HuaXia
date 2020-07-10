package hx.service.mobile.manage.login;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.collect.Maps;
import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.PositionsClass;
import hx.base.core.dao.dict.PositionsType;
import hx.base.core.dao.dict.SectionType;
import hx.base.core.dao.entity.acl.MobileRoleResource;
import hx.base.core.dao.entity.radar.RadarGrade;
import hx.base.core.dao.entity.test.integral.Integral;
import hx.base.core.dao.entity.test.integral.IntegralSignIn;
import hx.base.core.dao.repo.jpa.acl.MobileRoleResourceRepo;
import hx.base.core.dao.repo.jpa.radar.RadarGradeRepo;
import hx.base.core.dao.repo.jpa.test.integral.IntegralRepo;
import hx.base.core.dao.repo.jpa.test.integral.IntegralSignInRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.MyTimeTools;
import hx.base.core.manage.tools.SecurityUtil;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.MyMecachedPrefix;
import hx.service.mobile.manage.model.common.HXCommonResponse;
import hx.service.mobile.manage.model.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ClassName LoginManagerImpl
 * @Description 登录Manager
 * @Author HuoJiaJin
 * @Date 2020/6/23 21:46
 * @Version 1.0
 **/
@Service
public class LoginManagerImpl extends AbstractMobileManager implements LoginManager {

    @Value("${redirectUrl}")
    private String redirectUrl;
    @Autowired
    private IntegralRepo integralRepo;
    @Autowired
    private MobileRoleResourceRepo resourceRepo;
    @Autowired
    private IntegralSignInRepo signInRepo;
    @Autowired
    private RadarGradeRepo radarGradeRepo;

    @Override
    public String loginInfo() {
        CommonResponse response = new CommonResponse();
        LoginInfoResponse infoResponse = new LoginInfoResponse();
        infoResponse.setApp_id(appId);
        //生成十位随机数
        String nonce = "";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            nonce += String.valueOf(random.nextInt(10));
        }
        infoResponse.setNonce(nonce);
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        infoResponse.setTimestamp(timestamp);
        LoginInfoDataModel data = new LoginInfoDataModel();
        data.setRedirect_url(redirectUrl);
        //存放有序处理的dataJson
        infoResponse.setData(getDataJson(data));
        setSign(infoResponse);
        response.setData(infoResponse);
        return response.toJson();
    }

    private void setSign(LoginInfoResponse info){
        String encryptStr = appId + info.getData() + info.getNonce() + secretKey + info.getSign_type() + info.getTimestamp();
        byte[] hashBytes = SecurityUtil.hash(encryptStr.getBytes(), SecurityUtil.HashType.MD5);
        String sign = new BigInteger(1, hashBytes).toString(16);
        info.setSign(sign);
    }

    private String getDataJson(LoginInfoDataModel data){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("redirect_url",data.getRedirect_url());
        map.put("user_type",data.getUser_type());
        map.put("first_regist",data.getFirst_regist());
        map.put("attach",data.getAttach());
        return JSONUtils.toJSONString(map);
    }

    @Override
    public String login(LoginRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel mobileUser = getUser(request.getToken());
        if (mobileUser == null) {
            Map<String, String> params = Maps.newHashMap();
            params.put("token", request.getToken());
            try {
                String responseStr = hxDoGet(url + "/api/v2/uc/user/query", params);
                HXCommonResponse<MobileUserModel> commonResponse = JsonTools.json2Object(responseStr, HXCommonResponse.class, MobileUserModel.class);
                if (!commonResponse.getCode().equals("0")) {
                    logger.error("======请求华夏API报错：{}-{}", commonResponse.getCode(), commonResponse.getMessage());
                    response.setSuccess(false);
                    response.setMessage(commonResponse.getMessage());
                    response.setErrCode(7000);
                    return response.toJson();
                } else {
                    mobileUser = commonResponse.getData();
                }
            } catch (IOException e) {
                return response.setError(ErrorType.CONVERT);
            }
            if (mobileUser.getBranch_type().equals("99")) {
                return response.setError(ErrorType.NOEMPLAYEE);
            }
            //保存登录信息
            memcachedClient.set(MyMecachedPrefix.mobileLoginTokenPrefix + mobileUser.getToken(), 7*24*60*60 ,mobileUser.toJson());
        }else{
            memcachedClient.touch(MyMecachedPrefix.mobileLoginTokenPrefix + mobileUser.getToken(), 7*24*60*60);
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmployeeNum(mobileUser.getEmployee_code());
        loginResponse.setName(mobileUser.getName());
        loginResponse.setAvatar(mobileUser.getAvatar());
        loginResponse.setEnryTme(mobileUser.getEmployee_date());

        if (mobileUser.getEmployee_type().equals("1")){//当员工为外勤时
            try {
                setOutWorker(mobileUser, loginResponse);
            } catch (InterruptedException e) {
                logger.error("", e);
                return response.setError(ErrorType.CONVERT);
            }
        }else if (mobileUser.getEmployee_type().equals("0")){//TODO 当员工为内勤时

        }
        response.setData(loginResponse);
        return response.toJson();
    }


    private void setOutWorker(MobileUserModel mobileUser, LoginResponse loginResponse) throws InterruptedException {
        loginResponse.setGrade(mobileUser.getPosition());
        loginResponse.setStar(mobileUser.getFhagent_grade());
        String month = MyTimeTools.timeToStr(LocalDateTime.now(), "yyyy-MM");
        //获取积分
        Integral integral = integralRepo.findByAgentCode(month, mobileUser.getEmployee_code());
        loginResponse.setIntegral(integral == null ? 0 : integral.getAllNum());
        PositionsClass positionsClass = PositionsClass.valueOf(mobileUser.getPosition_code());
        PositionsType positionsType = PositionsType.fromClass(positionsClass);
        List<MobileRoleResource> resourceList = resourceRepo.listByClass(positionsType);
        List<Integer> resourceCodeList = resourceList.stream().map(r -> r.getResourceCode()).collect(Collectors.toList());
        loginResponse.setResourceCodeList(resourceCodeList);
        if (positionsType != PositionsType.FZG){
            loginResponse.setType(1);
        }else {
            loginResponse.setType(2);
        }
        //获取部、组评级
        if (positionsType != PositionsType.FZG){
            if (positionsType != PositionsType.BC) {
                String sectionCode = mobileUser.getEmployee_part_com();
                RadarGrade radarGrade = radarGradeRepo.findByCode(sectionCode, month, SectionType.SECTION);
                loginResponse.setSectionRateGrade(radarGrade.getRateType().getValue() + SectionType.SECTION.getName());
            }
            String groupCode = mobileUser.getEmployee_group_com();
            RadarGrade radarGrade = radarGradeRepo.findByCode(groupCode, month, SectionType.GROUP);
            loginResponse.setGroupRateGrade(radarGrade.getRateType().getValue() + SectionType.GROUP.getName());
        }
        //当天是否已签到
        IntegralSignIn signIn = signInRepo.findBySignInDate(LocalDate.now(), mobileUser.getEmployee_code());
        loginResponse.setHasSignIn(signIn != null);
    }
}
