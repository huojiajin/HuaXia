package hx.service.mobile.manage.test;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.PapersStatus;
import hx.base.core.dao.entity.test.papers.Papers;
import hx.base.core.dao.entity.test.papers.PapersOption;
import hx.base.core.dao.entity.test.papers.PapersPush;
import hx.base.core.dao.entity.test.papers.PapersSubject;
import hx.base.core.dao.repo.jpa.test.papers.PapersOptionRepo;
import hx.base.core.dao.repo.jpa.test.papers.PapersPushRepo;
import hx.base.core.dao.repo.jpa.test.papers.PapersRepo;
import hx.base.core.dao.repo.jpa.test.papers.PapersSubjectRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.test.paper.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions;
import org.springframework.stereotype.Service;

import java.util.*;
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
    @Autowired
    private PapersSubjectRepo subjectRepo;
    @Autowired
    private PapersOptionRepo optionRepo;

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
        //保存试卷信息
        data.setId(papers.getId());
        data.setName(papers.getName());
        data.setAnswerTime(papers.getAnswerTime());
        //保存题目信息
        List<PapersSubject> subjects = subjectRepo.listByPapersId(papers.getId());
        Collections.shuffle(subjects);//随机顺序
        List<String> subjectids = subjects.stream().map(PapersSubject::getId).collect(Collectors.toList());
        List<PapersOption> options = optionRepo.listBySubjectIds(subjectids);
        Map<String, List<PapersOption>> optionsMap = options.stream().collect(Collectors.groupingBy(PapersOption::getSubjectId));
        List<PaperDetailSubjectModel> subjectModelList = Lists.newArrayList();
        int subjectIndex = 1;
        for (PapersSubject subject : subjects) {
            PaperDetailSubjectModel model = new PaperDetailSubjectModel();
            model.setId(subject.getId());
            model.setIndex(subjectIndex);
            model.setStem(subject.getSubject());
            model.setType(subject.getType().getCode());
            List<PapersOption> optionList = optionsMap.get(subject.getId());
            optionList.sort(Comparator.comparing(PapersOption::getList));
            //处理选项信息
            List<PaperDetailOptionModel> optionModelList = optionList.stream().map(o -> {
                PaperDetailOptionModel optionModel = new PaperDetailOptionModel();
                optionModel.setIndex(o.getList());
                optionModel.setOption(o.getContent());
                return optionModel;
            }).collect(Collectors.toList());
            model.setOptionList(optionModelList);
            subjectModelList.add(model);
        }
        data.setSubjectList(subjectModelList);
        response.setData(data);
        return response.toJson();
    }



}
