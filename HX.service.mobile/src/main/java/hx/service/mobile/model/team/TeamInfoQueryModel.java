package hx.service.mobile.model.team;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: TeamInfoQueryModel
 * @Description: 团队信息查询结果Model
 * @Author HuoJiaJin
 * @Date 2021/2/24 23:33
 * @Version 1.0
 **/
public class TeamInfoQueryModel extends BaseEntity {

    private String name;//名称
    private String code;//代码
    private int type;//数据类型
    private Integer star;//星级
    private List<TeamInfoQueryMonthModel> monthList;//月份数据集合
    private int health;//健康人力
    private String gradeName;//职级名称
    private String entryTime;//入职时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public List<TeamInfoQueryMonthModel> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<TeamInfoQueryMonthModel> monthList) {
        this.monthList = monthList;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
