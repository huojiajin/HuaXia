package hx.base.core.dao.entity.hualife;

import hx.base.core.dao.entity.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @name: Attendance
 * @description: 考勤信息表
 * @author: huojiajin
 * @time: 2020/6/17 14:52
 */
@Entity
@Table(name = "LTATTENDANCE", indexes = {
        @Index(columnList = "STAFFCODE", name = "LTATTENDANCE_INDEX"),
        @Index(columnList = "ATTENDATE", name = "LTATTENDANCE_INDEX1"),
})
public class Attendance extends BaseEntity {

    private String attendanceNo;//序列号
    private String qrcodeId;//二维码ID
    private String attendanceType;//考勤类型
    private String trainCode;//学员编码
    private String staffCode;//营销员或工号
    private String manageCom;//管理机构
    private String classCode;//班级编码
    private LocalDate attenDate;//考勤日期
    private String signinTime;//签到时间
    private String signoutTime;//签退时间
    private String p1;//备用1
    private String p2;//备用2
    private String p3;//备用3

    @Id
    @Column(name = "ATTENDANCENO", nullable = false, length = 50)
    public String getAttendanceNo() {
        return attendanceNo;
    }

    public void setAttendanceNo(String attendanceNo) {
        this.attendanceNo = attendanceNo;
    }

    @Column(name = "QRCODEID", nullable = false, length = 20)
    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    @Column(name = "ATTENDANCETYPE", nullable = false, length = 10)
    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Column(name = "TRAINCODE", length = 50)
    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    @Column(name = "STAFFCODE", length = 20)
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Column(name = "MANAGECOM", length = 10)
    public String getManageCom() {
        return manageCom;
    }

    public void setManageCom(String manageCom) {
        this.manageCom = manageCom;
    }

    @Column(name = "CLASSCODE", length = 40)
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Column(name = "ATTENDATE", nullable = false)
    public LocalDate getAttenDate() {
        return attenDate;
    }

    public void setAttenDate(LocalDate attenDate) {
        this.attenDate = attenDate;
    }

    @Column(name = "SIGNINTIME", length = 8)
    public String getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(String signinTime) {
        this.signinTime = signinTime;
    }

    @Column(name = "SIGNOUTTIME", length = 8)
    public String getSignoutTime() {
        return signoutTime;
    }

    public void setSignoutTime(String signoutTime) {
        this.signoutTime = signoutTime;
    }

    @Column(name = "P1", length = 20)
    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    @Column(name = "P2", length = 20)
    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    @Column(name = "P3", length = 20)
    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }
}
