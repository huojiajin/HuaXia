package hx.base.core.dao.entity.staff;

import hx.base.core.dao.dict.TrainType;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Train
 * @Description 培训场次表
 * @Author HuoJiaJin
 * @Date 2020/11/8 2:24
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_train")
public class Train extends AbstractInsertTimeEntity {

    private String name;//培训名称
    private LocalDateTime trainTime;//培训时间
    private int peopleNum;//培训人数
    private TrainType status;//培训场次状态
    private boolean delete;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "train_time")
    public LocalDateTime getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(LocalDateTime trainTime) {
        this.trainTime = trainTime;
    }

    @Column(name = "people_num")
    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public TrainType getStatus() {
        return status;
    }

    public void setStatus(TrainType status) {
        this.status = status;
    }

    @Column(name = "delete")
    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
