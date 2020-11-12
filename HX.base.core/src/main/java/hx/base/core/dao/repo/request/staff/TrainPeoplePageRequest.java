package hx.base.core.dao.repo.request.staff;

import hx.base.core.dao.entity.staff.TrainPeople;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;
import org.springframework.data.domain.Sort;

/**
 * @name: MarketingManpowerPageRequest
 * @description: 人力清单分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class TrainPeoplePageRequest extends JpaPageableDataRequest<TrainPeople> {

    private String trainId;//培训场次ID
    private String name;//人员名称
    private String campName;//营服名称
    private String campCode;//营服代码
    private String sectionName;//部名称
    private String sectionCode;//部名称
    private String groupName;//组名称
    private String groupCode;//组名称

    public TrainPeoplePageRequest() {
        this.otherSort = Sort.by("campCode", "sectionCode", "groupCode");
    }

    @Override
    public String toCountHql()
    {
        return "select count(*) " + toSelectHql().toString();
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and trainId = :trainId", trainId);
        hql.append(" and name = :name", name);
        hql.append(" and campName like :campName", like(campName));
        hql.append(" and campCode = :campCode", campCode);
        hql.append(" and sectionName like :sectionName", like(sectionName));
        hql.append(" and sectionCode = :sectionCode", sectionCode);
        hql.append(" and groupName like :groupName", like(groupName));
        hql.append(" and groupCode = :groupCode", groupCode);
        return hql;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
