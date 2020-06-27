package hx.service.mobile.manage.test;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.entity.test.integral.Integral;
import hx.base.core.dao.entity.test.integral.IntegralSignIn;
import hx.base.core.dao.repo.jpa.test.integral.IntegralRepo;
import hx.base.core.dao.repo.jpa.test.integral.IntegralSignInRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.common.MobileCommonRequest;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.test.integral.IntegralRankModel;
import hx.service.mobile.manage.model.test.integral.IntegralRankResponse;
import hx.service.mobile.manage.model.test.integral.SignInResponse;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @ClassName SignInManagerImpl
 * @Description 签到相关Manager
 * @Author HuoJiaJin
 * @Date 2020/6/27 20:10
 * @Version 1.0
 **/
@Service
public class SignInManagerImpl extends AbstractMobileManager implements SignInManager {

    @Autowired
    private IntegralSignInRepo signInRepo;
    @Autowired
    private IntegralRepo integralRepo;


    @Override
    public String signIn(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        SignInResponse data = new SignInResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        IntegralSignIn signIn = new IntegralSignIn();
        String agentCode = user.getEmployee_code();
        signIn.setAgentCode(agentCode);
        LocalDate now = LocalDate.now();
        signIn.setSignInDate(now);
        List<LocalDate> week = MyTimeTools.getWeek(now);
        boolean isSeries = false;
        String weekSign = "";
        for (LocalDate date : week) {
            IntegralSignIn signInDate = signInRepo.findBySignInDate(date, agentCode);
            if (signInDate == null){
                weekSign += "0|";
            }else {
                weekSign += "1|";
            }
            if (now.minusDays(1).isEqual(date)){
                isSeries = true;
            }
        }
        int integral = isSeries ? 10 : 5;
        signIn.setIntegral(integral);
        updateIntegral(signIn, user);
        data.setIntegral(integral);
        data.setWeekSign(weekSign);
        response.setData(data);
        return response.toJson();
    }

    @Transactional
    private void updateIntegral(IntegralSignIn signIn, MobileUserModel user){
        signInRepo.persist(signIn);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        String month = df.format(LocalDate.now());
        Integral integral = integralRepo.findByAgentCode(month, user.getEmployee_code());
        if (integral == null){
            integral = new Integral();
            integral.setAgentCode(user.getEmployee_code());
            integral.setName(user.getName());
            integral.setMonth(month);
            integral.setAllNum(signIn.getIntegral());
            integral.setSignInNum(signIn.getIntegral());
            integralRepo.persist(integral);
        }else {
            integralRepo.updateSignIn(month, user.getEmployee_code(), signIn.getIntegral());
        }
    }

    @Override
    public String integralRank(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        IntegralRankResponse data = new IntegralRankResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        String month = df.format(LocalDate.now());
        List<Integral> integralList = integralRepo.listRank(month);
        //获取本人排名
        Integral myIntegral = integralRepo.findByAgentCode(month, user.getEmployee_code());
        int index = integralList.indexOf(myIntegral);
        data.setName(user.getName());
        data.setIntegral(myIntegral.getAllNum());
        data.setIndex(index + 1);
        //获取排行榜
        List<IntegralRankModel> rankModelList = Lists.newArrayList();
        for (int i = 0; i < 6; i++) {
            Integral integral = integralList.get(i);
            IntegralRankModel model = new IntegralRankModel();
            model.setName(integral.getName());
            model.setIntegral(integral.getAllNum());
            model.setIndex(i + 1);
            rankModelList.add(model);
        }
        data.setIntegralList(rankModelList);
        response.setData(data);
        return response.toJson();
    }
}
