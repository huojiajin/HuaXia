package hx.service.manage.manage.model.test.papers;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName PaperTemplateResponse
 * @Description 试卷模板下载Response
 * @Author HuoJiaJin
 * @Date 2020/6/21 17:44
 * @Version 1.0
 **/
public class PaperTemplateResponse extends BaseEntity {

    private String template;//模板文件base64

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
