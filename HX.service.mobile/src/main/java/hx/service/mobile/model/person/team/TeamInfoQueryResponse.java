package hx.service.mobile.model.person.team;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName: TeamInfoQueryResponse
 * @Description: 团队信息查询Response
 * @Author HuoJiaJin
 * @Date 2021/2/24 23:33
 * @Version 1.0
 **/
public class TeamInfoQueryResponse extends BaseEntity {

    private List<TeamInfoQueryModel> result;//结果集合
    private List<TeamInfoQueryMonthModel> summary;//月份汇总数据

    public List<TeamInfoQueryModel> getResult() {
        return result;
    }

    public void setResult(List<TeamInfoQueryModel> result) {
        this.result = result;
    }

    public List<TeamInfoQueryMonthModel> getSummary() {
        return summary;
    }

    public void setSummary(List<TeamInfoQueryMonthModel> summary) {
        this.summary = summary;
    }
}
