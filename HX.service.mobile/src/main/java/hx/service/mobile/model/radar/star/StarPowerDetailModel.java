package hx.service.mobile.model.radar.star;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.service.mobile.model.radar.StadpremMonthModel;

import java.util.List;

/**
 * @ClassName StarPowerDetailModel
 * @Description 星级人力详情Model
 * @Author HuoJiaJin
 * @Date 2020/6/27 16:06
 * @Version 1.0
 **/
public class StarPowerDetailModel extends BaseEntity {

    private String name;//姓名
    private String group;//组名称
    private int star;//星级
    private List<StadpremMonthModel> stadpremList;//标保月份集合

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public List<StadpremMonthModel> getStadpremList() {
        return stadpremList;
    }

    public void setStadpremList(List<StadpremMonthModel> stadpremList) {
        this.stadpremList = stadpremList;
    }
}
