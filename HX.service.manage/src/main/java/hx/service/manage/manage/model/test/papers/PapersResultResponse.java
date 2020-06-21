package hx.service.manage.manage.model.test.papers;

import hx.service.manage.dao.entity.common.BaseEntity;

/**
 * @ClassName PapersResultResponse
 * @Description 试卷答题结果查看
 * @Author HuoJiaJin
 * @Date 2020/6/22 0:45
 * @Version 1.0
 **/
public class PapersResultResponse extends BaseEntity {

    private String resultFile;//结果文件Base64

    public String getResultFile() {
        return resultFile;
    }

    public void setResultFile(String resultFile) {
        this.resultFile = resultFile;
    }
}
