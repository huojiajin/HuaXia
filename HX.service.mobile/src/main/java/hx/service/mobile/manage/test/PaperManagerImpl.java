package hx.service.mobile.manage.test;

import hx.base.core.dao.dict.ErrorType;
import hx.base.core.dao.dict.PapersStatus;
import hx.base.core.dao.dict.PapersType;
import hx.base.core.dao.entity.test.integral.IntegralTest;
import hx.base.core.dao.entity.test.papers.*;
import hx.base.core.dao.repo.jpa.test.integral.IntegralRepo;
import hx.base.core.dao.repo.jpa.test.integral.IntegralTestRepo;
import hx.base.core.dao.repo.jpa.test.papers.*;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.AbstractMobileManager;
import hx.service.mobile.manage.model.login.MobileUserModel;
import hx.service.mobile.manage.model.test.paper.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private PapersPushAnswerRepo pushAnswerRepo;
    @Autowired
    private IntegralRepo integralRepo;
    @Autowired
    private IntegralTestRepo integralTestRepo;

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
        paperList = paperList.stream().filter(p -> {
            int type = request.getType();
            if (type != 5){
                if (p.getType().getCode() == type){
                    return true;
                }
            }else{
                if (PapersType.isZZRZType(p.getType())){
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<PaperListModel> result = Lists.newArrayList();
        for (Papers papers : paperList) {
            String id = papers.getId();
            PaperListModel model = new PaperListModel();
            model.setId(id);
            model.setName(papers.getName());
            model.setEndTime(MyTimeTools.timeToStr(papers.getEndTime()));
            model.setNum(papers.getSubjectNum());
            //判断是否已截止
            if (papers.getEndTime().isBefore(LocalDateTime.now())){
                if (papers.getStatus() != PapersStatus.YJZ){
                    papersRepo.updateStatus(id, PapersStatus.YJZ, papers.getEndTime());
                }
                model.setAnswerType(3);
            }else {
                PapersPush push = pushMap.get(id);
                model.setAnswerType(push.getAnswerType().getCode());
            }
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
            subjectIndex++;
        }
        data.setSubjectList(subjectModelList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String submit(PaperSubmitRequest request){
        CommonResponse response = new CommonResponse();
        PaperSubmitResponse data = new PaperSubmitResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        PapersPush papersPush = pushRepo.findByPapersId(request.getPaperId(), user.getEmployee_code());
        List<PapersPushAnswer> answers = pushAnswerRepo.listByPushId(papersPush.getId());
        if (!isEmpty(answers)){
            return response.setError(ErrorType.HASCOMPLETED);
        }
        //获取题目答案
        List<PapersSubject> subjectList = subjectRepo.listByPapersId(request.getPaperId());
        Map<String, PapersSubject> subjectMap = subjectList.stream()
                .collect(Collectors.toMap(PapersSubject::getId, Function.identity()));
        //拼装答案实体
        int score = 0;
        List<PapersPushAnswer> pushAnswerList = Lists.newArrayList();
        for (PaperSubmitSubjectModel model : request.getSubjectList()) {
            PapersPushAnswer entity = new PapersPushAnswer();
            entity.setPushId(papersPush.getId());
            entity.setSubjectId(model.getSubjectId());
            entity.setAnswer(model.getAnswer());
            PapersSubject subject = subjectMap.get(model.getSubjectId());
            if (model.getAnswer().equals(subject.getCorrectNum())){
                score += subject.getScore();
            }
            pushAnswerList.add(entity);
        }
        //根据分值计算所得积分
        int integral = 0;
        if (score >= 70 && score < 90){
            integral = 8;
        }else if (score >= 90) {
            integral = 15;
        }

        //拼装考试积分表实体
        IntegralTest integralTest = new IntegralTest();
        integralTest.setAgentCode(user.getEmployee_code());
        integralTest.setPaperId(request.getPaperId());
        integralTest.setIntegral(integral);
        integralTest.setInsertTime(LocalDateTime.now());

        //保存相关信息
        updateAnswer(pushAnswerList, integralTest, score, integral, user.getEmployee_code(), request.getPaperId());

        //拼装返回信息
        data.setScore(score);
        data.setIntegral(integral);
        response.setData(data);
        return response.toJson();
    }

    @Transactional(rollbackFor=Exception.class)
    private void updateAnswer(List<PapersPushAnswer> pushAnswerList, IntegralTest integralTest, int score, int integral,
                              String agentCode, String papersId){
        pushAnswerRepo.persistAll(pushAnswerList);
        LocalDateTime now = LocalDateTime.now();
        pushRepo.UpdateAnswer(agentCode, papersId, score, integral, now);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        integralRepo.updatePaper(df.format(now), agentCode, integral);
        integralTestRepo.persist(integralTest);
    }

    @Override
    public String getCompletedList(PaperCompletedListRequest request){
        CommonResponse response = new CommonResponse();
        PaperCompletedListResponse data = new PaperCompletedListResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        List<PapersPush> papersPushList = pushRepo.listComplete(user.getEmployee_code());
        //根据试卷ID和类型查找试卷
        List<String> papersIds = papersPushList.stream().map(PapersPush::getPapersId).collect(Collectors.toList());
        List<PapersType> types = Lists.newArrayList();
        if (request.getType() != 5){
            try {
                PapersType type = PapersType.fromCode(request.getType());
                types.add(type);
            } catch (InterruptedException e) {
                logger.error("", e);
                return response.setError(ErrorType.CONVERT);
            }
        }else{
            types = PapersType.getZZRZ();
        }
        List<Papers> papers = papersRepo.listComplete(papersIds, types);
        List<PaperCompletedListModel> result = Lists.newArrayList();
        //将推送信息根据试卷ID分组
        Map<String, PapersPush> pushMap = papersPushList.stream()
                .collect(Collectors.toMap(PapersPush::getPapersId, Function.identity()));
        for (Papers paper : papers) {
            PaperCompletedListModel model = new PaperCompletedListModel();
            model.setId(paper.getId());
            model.setName(paper.getName());
            model.setNum(paper.getSubjectNum());
            PapersPush push = pushMap.get(paper.getId());
            model.setScore(push.getScore());
            model.setCompleteTime(MyTimeTools.timeToStr(push.getCompleteTime()));
            result.add(model);
        }
        data.setResult(result);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getCompletedDetail(PaperCompletedDetailRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) return response.setError(ErrorType.NOLOGIN);
        PaperCompletedDetailResponse data = new PaperCompletedDetailResponse();
        String paperId = request.getPaperId();
        Optional<Papers> op = papersRepo.findById(paperId);
        if (op.isEmpty()){
            return response.setError(ErrorType.NOPAPERS);
        }
        Papers papers = op.get();
        data.setName(papers.getName());
        List<PapersSubject> subjectList = subjectRepo.listByPapersId(paperId);
        List<String> subjectIds = subjectList.stream().map(PapersSubject::getId).collect(Collectors.toList());
        List<PapersOption> optionList = optionRepo.listBySubjectIds(subjectIds);
        Map<String, List<PapersOption>> optionMaps = optionList.stream().collect(Collectors.groupingBy(PapersOption::getSubjectId));

        //获取员工答题结果,根据题目ID分组
        PapersPush push = pushRepo.findByPapersId(paperId, user.getEmployee_code());
        List<PapersPushAnswer> pushAnswerList = pushAnswerRepo.listByPushId(push.getId());
        Map<String, PapersPushAnswer> pushAnswerMaps = pushAnswerList.stream()
                .collect(Collectors.toMap(PapersPushAnswer::getSubjectId, Function.identity()));
        //处理题目列表
        List<PaperCompletedDetailSubjectModel> subjectModelList = Lists.newArrayList();
        int index = 1;
        for (PapersSubject subject : subjectList) {
            PaperCompletedDetailSubjectModel model = new PaperCompletedDetailSubjectModel();
            String subjectId = subject.getId();
            model.setId(subjectId);
            model.setIndex(index);
            model.setStem(subject.getSubject());
            model.setType(subject.getType().getCode());
            PapersPushAnswer pushAnswer = pushAnswerMaps.get(subjectId);
            String[] answerArr = new String[]{};
            if (pushAnswer != null){
                answerArr = pushAnswer.getAnswer().split("\\|");
            }
            String[] correctArr = subject.getCorrectNum().split("\\|");
            //处理选项
            List<PapersOption> options = optionMaps.get(subjectId);
            List<PaperCompletedDetailOptionModel> optionModelList = Lists.newArrayList();
            for (PapersOption option : options) {
                PaperCompletedDetailOptionModel optionModel = new PaperCompletedDetailOptionModel();
                int list = option.getList();
                optionModel.setIndex(list);
                optionModel.setOption(option.getContent());
                //判断是否选择
                boolean isSelect = false;
                for (String answer : answerArr) {
                    if (list == Integer.valueOf(answer)){
                        isSelect = true;
                    }
                }
                optionModel.setSelect(isSelect);
                //判断是否正确选项
                boolean isCorrect = false;
                for (String correct : correctArr) {
                    if (list == Integer.valueOf(correct)){
                        isCorrect = true;
                    }
                }
                optionModel.setCorrect(isCorrect);

                optionModelList.add(optionModel);
            }
            model.setOptionList(optionModelList);
            subjectModelList.add(model);
            index++;
        }
        data.setSubjectList(subjectModelList);
        response.setData(data);
        return response.toJson();
    }
}
