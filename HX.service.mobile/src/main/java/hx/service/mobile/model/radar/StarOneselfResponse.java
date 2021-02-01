package hx.service.mobile.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName StarOneselfResponse
 * @Description 个人星级Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 18:10
 * @Version 1.0
 **/
public class StarOneselfResponse extends BaseEntity {

    private String name;//姓名
    private int star;//星级
    private int type;//0为部，1为组
    private List<StadpremMonthModel> stadpremList;//标保月份集合

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<StadpremMonthModel> getStadpremList() {
        return stadpremList;
    }

    public void setStadpremList(List<StadpremMonthModel> stadpremList) {
        this.stadpremList = stadpremList;
    }
}
