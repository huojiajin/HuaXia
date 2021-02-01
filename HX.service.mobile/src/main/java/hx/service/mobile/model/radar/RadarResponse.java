package hx.service.mobile.model.radar;

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
    private Double stadpremMax;//月均标保极值
    private Double stadpremNum;//月均标保
    private Double stadpremGap;//月均标保差距
    private Double stadpremLastGap;//月均标保上月差距
    private Integer personStarMax;//个人星级极值
    private Integer personStarNum;//个人星级
    private Integer personStarGap;//个人星级差距
    private Integer personStarLastGap;//个人星级上月差距
    private Integer starPowerMax;//星级人力极值
    private Integer starPowerNum;//星级人力
    private Integer starPowerGap;//星级人力差距
    private Integer starPowerLastGap;//星级人力上月差距
    private Double rateMax;//继续率极值
    private Double rate;//继续率
    private Double rateGap;//继续率差距
    private Double rateLastGap;//继续率上月差距
    private Integer attendPowerMax;//出勤人力极值
    private Integer attendPowerNum;//出勤人力
    private Integer attendPowerGap;//出勤人力差距
    private Integer attendPowerLastGap;//出勤人力上月差距

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getStadpremMax() {
        return stadpremMax;
    }

    public void setStadpremMax(Double stadpremMax) {
        this.stadpremMax = stadpremMax;
    }

    public Double getStadpremNum() {
        return stadpremNum;
    }

    public void setStadpremNum(Double stadpremNum) {
        this.stadpremNum = stadpremNum;
    }

    public Double getStadpremGap() {
        return stadpremGap;
    }

    public void setStadpremGap(Double stadpremGap) {
        this.stadpremGap = stadpremGap;
    }

    public Double getStadpremLastGap() {
        return stadpremLastGap;
    }

    public void setStadpremLastGap(Double stadpremLastGap) {
        this.stadpremLastGap = stadpremLastGap;
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

    public Integer getPersonStarLastGap() {
        return personStarLastGap;
    }

    public void setPersonStarLastGap(Integer personStarLastGap) {
        this.personStarLastGap = personStarLastGap;
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

    public Integer getStarPowerLastGap() {
        return starPowerLastGap;
    }

    public void setStarPowerLastGap(Integer starPowerLastGap) {
        this.starPowerLastGap = starPowerLastGap;
    }

    public Double getRateMax() {
        return rateMax;
    }

    public void setRateMax(Double rateMax) {
        this.rateMax = rateMax;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRateGap() {
        return rateGap;
    }

    public void setRateGap(Double rateGap) {
        this.rateGap = rateGap;
    }

    public Double getRateLastGap() {
        return rateLastGap;
    }

    public void setRateLastGap(Double rateLastGap) {
        this.rateLastGap = rateLastGap;
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

    public Integer getAttendPowerLastGap() {
        return attendPowerLastGap;
    }

    public void setAttendPowerLastGap(Integer attendPowerLastGap) {
        this.attendPowerLastGap = attendPowerLastGap;
    }
}
