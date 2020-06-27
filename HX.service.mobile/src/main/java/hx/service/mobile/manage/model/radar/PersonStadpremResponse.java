package hx.service.mobile.manage.model.radar;

import hx.service.manage.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName PersonStadpremResponse
 * @Description 个人标保返回信息
 * @Author HuoJiaJin
 * @Date 2020/6/27 9:34
 * @Version 1.0
 **/
public class PersonStadpremResponse extends BaseEntity {

    private List<PersonStadpremModel> result;

    public List<PersonStadpremModel> getResult() {
        return result;
    }

    public void setResult(List<PersonStadpremModel> result) {
        this.result = result;
    }
}
