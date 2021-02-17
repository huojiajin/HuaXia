package hx.service.mobile.manage.honor;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.entity.honor.Honor;
import hx.base.core.dao.entity.honor.HonorPeople;
import hx.base.core.dao.repo.jpa.honor.HonorPeopleRepo;
import hx.base.core.dao.repo.jpa.honor.HonorRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.honor.WallQueryModel;
import hx.service.mobile.model.honor.WallQueryRequest;
import hx.service.mobile.model.honor.WallQueryResponse;
import hx.service.mobile.model.login.MobileUserModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: WallManagerImpl
 * @Description: 荣誉墙ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/6 3:18
 * @Version 1.0
 **/
@Service
public class WallManagerImpl extends AbstractMobileManager implements WallManager {

    @Autowired
    private HonorPeopleRepo peopleRepo;
    @Autowired
    private HonorRepo honorRepo;

    @Override
    public String query(WallQueryRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null){
            return response.setError(ErrorType.NOLOGIN);
        }
        WallQueryResponse queryResponse = new WallQueryResponse();
        List<WallQueryModel> modelList = Lists.newArrayList();
        List<HonorPeople> peopleList = peopleRepo.listByAgentCode(user.getEmployee_code());
        for (HonorPeople people : peopleList) {
            WallQueryModel model = new WallQueryModel();
            Honor honor = honorRepo.findById(people.getHonorId()).orElse(null);
            if (honor != null && !honor.isStop()){
                model.setHonorName(honor.getName());
                model.setObtainTime(MyTimeTools.timeToStr(people.getInsertTime(), "yyyy-MM-dd"));
                model.setHonorImage(new String(Base64.encodeBase64(honor.getIcon())));
                modelList.add(model);
            }
        }
        queryResponse.setResult(modelList);
        response.setData(queryResponse);
        return response.toJson();
    }
}
