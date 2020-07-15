package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PapersImportSubjectModel
 * @Description 试卷导入-题目Model
 * @Author HuoJiaJin
 * @Date 2020/6/21 20:52
 * @Version 1.0
 **/
public class PapersImportSubjectModel extends BaseEntity {

    private boolean success = true;//是否读取成功
    private String errorMsg;//错误信息
    private int list;//排序
    private String subject;//题目
    private int type;//题目类型
    private int score;//分值
    private String correctNum;//答案序号
    private List<PapersImportOptionModel> optionModels;//选项List

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(String correctNum) {
        this.correctNum = correctNum;
    }

    public List<PapersImportOptionModel> getOptionModels() {
        return optionModels;
    }

    public void setOptionModels(List<PapersImportOptionModel> optionModels) {
        this.optionModels = optionModels;
    }

    public PapersImportSubjectModel setError(String errorMsg){
        this.setSuccess(false);
        this.setErrorMsg(errorMsg);
        return this;
    }
}
