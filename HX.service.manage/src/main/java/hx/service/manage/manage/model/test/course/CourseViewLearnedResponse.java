package hx.service.manage.manage.model.test.course;

import hx.service.manage.manage.model.CommonResponse;

/**
 * @ClassName CourseViewLearnedResponse
 * @Description 学习资料查看已学人员response
 * @Author HuoJiaJin
 * @Date 2020/6/22 23:36
 * @Version 1.0
 **/
public class CourseViewLearnedResponse extends CommonResponse {

    private String learnedFile;//已学人员名单

    public String getLearnedFile() {
        return learnedFile;
    }

    public void setLearnedFile(String learnedFile) {
        this.learnedFile = learnedFile;
    }
}
