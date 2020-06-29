package hx.service.mobile.manage.test;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.PapersStatus;
import hx.base.core.dao.entity.test.papers.Papers;
import hx.base.core.dao.entity.test.papers.PapersPush;
import hx.base.core.dao.repo.jpa.test.papers.PapersPushRepo;
import hx.base.core.dao.repo.jpa.test.papers.PapersRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.test.paper.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName PaperManagerImpl
 * @Description 试卷相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2020/6/27 21:56
 * @Version 1.0
 **/
@Service
public class PaperManagerImpl extends AbstractMobileManager implements PaperManager {

    @Autowired
    private PapersRepo papersRepo;
    @Autowired
    private PapersPushRepo pushRepo;

    @Override
    public String list(PaperListRequest request){
        CommonResponse response = new CommonResponse();
        PaperListResponse data = new PaperListResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        List<PapersPush> pushList = pushRepo.listByAgentCode(user.getEmployee_code());
        Map<String, PapersPush> pushMap = pushList.stream().collect(Collectors.toMap(PapersPush::getPapersId, Function.identity()));
        List<String> papersId = pushList.stream().map(PapersPush::getPapersId).collect(Collectors.toList());
        List<Papers> paperList = papersRepo.listByIds(papersId);

        List<PaperListModel> result = Lists.newArrayList();
        for (Papers papers : paperList) {
            String id = papers.getId();
            PaperListModel model = new PaperListModel();
            model.setId(id);
            model.setName(papers.getName());
            model.setEndTime(MyTimeTools.timeToStr(papers.getEndTime()));
            model.setNum(papers.getSubjectNum());
            PapersPush push = pushMap.get(id);
            model.setAnswerType(push.getAnswerType().getCode());
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String detail(PaperDetailRequest request){
        CommonResponse response = new CommonResponse();
        PaperDetailResponse data = new PaperDetailResponse();
        Optional<Papers> op = papersRepo.findById(request.getPaperId());
        if (op.isEmpty()){
            return response.setError(ErrorType.NOPAPERS);
        }
        Papers papers = op.get();
        if (papers.getStatus() != PapersStatus.YTS){
            return response.setError(ErrorType.HASEXPIRE);
        }



        return response.toJson();
    }
}
