package hx.service.mobile.manage.model.radar;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName RadarResponse
 * @Description 主管经营雷达图Response
 * @Author HuoJiaJin
 * @Date 2020/6/27 2:46
 * @Version 1.0
 **/
public class RadarResponse extends BaseEntity {

    private String grade;//当前等级
    private Integer stadpremMax;//月均标保极值
    private Integer stadpremNum;//月均标保
    private Integer stadpremGap;//月均标保差距
    private Integer personStarMax;//个人星级极值
    private Integer personStarNum;//个人星级
    private Integer personStarGap;//个人星级差距
    private Integer starPowerMax;//星级人力极值
    private Integer starPowerNum;//星级人力
    private Integer starPowerGap;//星级人力差距
    private Integer rateMax;//继续率极值
    private Integer rate;//继续率
    private Integer rateGap;//继续率差距
    private Integer attendPowerMax;//出勤人力极值
    private Integer attendPowerNum;//出勤人力
    private Integer attendPowerGap;//出勤人力差距

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getStadpremMax() {
        return stadpremMax;
    }

    public void setStadpremMax(Integer stadpremMax) {
        this.stadpremMax = stadpremMax;
    }

    public Integer getStadpremNum() {
        return stadpremNum;
    }

    public void setStadpremNum(Integer stadpremNum) {
        this.stadpremNum = stadpremNum;
    }

    public Integer getStadpremGap() {
        return stadpremGap;
    }

    public void setStadpremGap(Integer stadpremGap) {
        this.stadpremGap = stadpremGap;
    }

    public Integer getPersonStarMax() {
        return personStarMax;
    }

    public void setPersonStarMax(Integer personStarMax) {
        this.personStarMax = personStarMax;
    }

    public Integer getPersonStarNum() {
        return personStarNum;
    }

    public void setPersonStarNum(Integer personStarNum) {
        this.personStarNum = personStarNum;
    }

    public Integer getPersonStarGap() {
        return personStarGap;
    }

    public void setPersonStarGap(Integer personStarGap) {
        this.personStarGap = personStarGap;
    }

    public Integer getStarPowerMax() {
        return starPowerMax;
    }

    public void setStarPowerMax(Integer starPowerMax) {
        this.starPowerMax = starPowerMax;
    }

    public Integer getStarPowerNum() {
        return starPowerNum;
    }

    public void setStarPowerNum(Integer starPowerNum) {
        this.starPowerNum = starPowerNum;
    }

    public Integer getStarPowerGap() {
        return starPowerGap;
    }

    public void setStarPowerGap(Integer starPowerGap) {
        this.starPowerGap = starPowerGap;
    }

    public Integer getRateMax() {
        return rateMax;
    }

    public void setRateMax(Integer rateMax) {
        this.rateMax = rateMax;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRateGap() {
        return rateGap;
    }

    public void setRateGap(Integer rateGap) {
        this.rateGap = rateGap;
    }

    public Integer getAttendPowerMax() {
        return attendPowerMax;
    }

    public void setAttendPowerMax(Integer attendPowerMax) {
        this.attendPowerMax = attendPowerMax;
    }

    public Integer getAttendPowerNum() {
        return attendPowerNum;
    }

    public void setAttendPowerNum(Integer attendPowerNum) {
        this.attendPowerNum = attendPowerNum;
    }

    public Integer getAttendPowerGap() {
        return attendPowerGap;
    }

    public void setAttendPowerGap(Integer attendPowerGap) {
        this.attendPowerGap = attendPowerGap;
    }
}
