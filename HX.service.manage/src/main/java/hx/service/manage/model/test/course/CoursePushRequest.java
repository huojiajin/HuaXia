package hx.service.manage.model.test.course;

import hx.service.manage.model.common.CommonRequest;

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
    private int pushType;//推送类型
    private List<String> codeList;//职级代码集合

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
