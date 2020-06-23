package hx.service.manage.manage.model.test.course;

import hx.service.manage.manage.model.CommonRequest;

import java.util.List;

/**
 * @ClassName CoursePushRequest
 * @Description 学习资料推送Request
 * @Author HuoJiaJin
 * @Date 2020/6/22 23:25
 * @Version 1.0
 **/
public class CoursePushRequest extends CommonRequest {

    private String courseId;//
    private List<String> rankCodeList;//职级代码集合

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getRankCodeList() {
        return rankCodeList;
    }

    public void setRankCodeList(List<String> rankCodeList) {
        this.rankCodeList = rankCodeList;
    }
}
