package hx.base.core.dao.repo.request.staff;

import hx.base.core.dao.entity.staff.Train;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;

import java.time.LocalDateTime;

/**
 * @name: MarketingManpowerPageRequest
 * @description: 人力清单分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class TrainPageRequest extends JpaPageableDataRequest<Train> {

    private String name;//培训名称
    private LocalDateTime trainTimeStart;//培训时间开始
    private LocalDateTime trainTimeEnd;//培训时间结束
    private boolean delete;

    public TrainPageRequest() {
        this.orderBy = "updateTime";
        this.desc = true;
    }

    @Override
    public String toCountHql()
    {
        return "select count(*) " + toSelectHql().toString();
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and name like :name", like(name));
        hql.append(" and trainTime >= :trainTimeStart", trainTimeStart);
        hql.append(" and trainTime < :trainTimeEnd", trainTimeEnd);
        hql.append(" and delete = :delete", delete);
        return hql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTrainTimeStart() {
        return trainTimeStart;
    }

    public void setTrainTimeStart(LocalDateTime trainTimeStart) {
        this.trainTimeStart = trainTimeStart;
    }

    public LocalDateTime getTrainTimeEnd() {
        return trainTimeEnd;
    }

    public void setTrainTimeEnd(LocalDateTime trainTimeEnd) {
        this.trainTimeEnd = trainTimeEnd;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
