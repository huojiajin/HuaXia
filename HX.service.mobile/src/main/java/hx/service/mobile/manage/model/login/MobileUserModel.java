package hx.service.mobile.manage.model.login;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName MobileUserModel
 * @Description 移动端登录用户Model
 * @Author HuoJiaJin
 * @Date 2020/6/26 13:49
 * @Version 1.0
 **/
public class MobileUserModel extends BaseEntity {

    private String token;//登录凭证
    private String user_id;//用户Id
    private String mobile;//手机号
    private String openid;//用户关注华夏微信服务号后产生的唯一标识(用户未与华夏微信服务号绑定则返回空)
    private String branch_type;//渠道编码
    private String name;//姓名
    private String sex;//性别
    private String id_no;//证件号
    private String email;//邮箱
    private String unionid;//微信开放平台unionid
    private String fhagent_grade;//凤凰社星级
    private String avatar;//头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可（branch_type不为99时返回）
    private String position_code;//员工职级编码
    private String position;//员工职级名称
    private String employee_code;//员工工号
    private String employee_type;//员工类型，0-内勤、1-外勤、2-预工号（branch_type不为99时返回）
    private String employee_date;//入职日期（branch_type不为99时返回）
    private String leave_date;//离职日期（branch_type不为99时返回）
    private String employee_state;//在职状态，0-离职、1-在职（branch_type不为99时返回）
    private String branch_type_name;//渠道名称（branch_type不为99时返回）
    private String employee_group;//员工展业机构代码（branch_type不为99时返回）
    private String employee_group_name;//员工展业机构名称（branch_type不为99时返回）
    private String manage_com;//员工管理机构代码（branch_type不为99时返回）
    private String manage_name;//员工管理机构（branch_type不为99时返回）
    private String branch_no;//分公司代码（branch_type不为99时返回）
    private String branch_name;//分公司名称（branch_type不为99时返回）
    private String center_branch;//中心支公司代码（branch_type不为99时返回）
    private String center_branch_name;//中心支公司名称（branch_type不为99时返回）
    private String employee_major_com;//总监区编码（branch_type不为99时返回）
    private String employee_major_com_name;//业务（总监）区名称（branch_type不为99时返回）
    private String employee_part_com;//业务部编码（branch_type不为99时返回）
    private String employee_part_com_name;//业务部名称（branch_type不为99时返回）
    private String employee_group_com;//业务组编码（branch_type不为99时返回）
    private String employee_group_com_name;//业务组名称（branch_type不为99时返回）
    private String branch_attr;//员工展业机构外部代码（branch_type不为99时返回）
    private String branch_attr_area;//业务区展业机构外部编码（branch_type不为99时返回）
    private String branch_attr_part;//业务部展业机构外部编码（branch_type不为99时返回）
    private String branch_attr_group;//业务组展业机构外部编码（branch_type不为99时返回）
    private String branch_manager;//业务组经理代码（branch_type不为99时返回）
    private String branch_manager_name;//业务组经理名称（branch_type不为99时返回）
    private String mdrt_flag;//MDRT会员标识，Y-是，N-不是（branch_type不为99时返回）
    private String ida_flag;//IDA会员标识，Y-是，N-不是（branch_type不为99时返回）
    private String dept_code;//内勤部门编码（branch_type不为99时返回）
    private String dept_code_name;//内勤部门名称（branch_type不为99时返回）

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBranch_type() {
        return branch_type;
    }

    public void setBranch_type(String branch_type) {
        this.branch_type = branch_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getFhagent_grade() {
        return fhagent_grade;
    }

    public void setFhagent_grade(String fhagent_grade) {
        this.fhagent_grade = fhagent_grade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public String getEmployee_date() {
        return employee_date;
    }

    public void setEmployee_date(String employee_date) {
        this.employee_date = employee_date;
    }

    public String getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(String leave_date) {
        this.leave_date = leave_date;
    }

    public String getEmployee_state() {
        return employee_state;
    }

    public void setEmployee_state(String employee_state) {
        this.employee_state = employee_state;
    }

    public String getBranch_type_name() {
        return branch_type_name;
    }

    public void setBranch_type_name(String branch_type_name) {
        this.branch_type_name = branch_type_name;
    }

    public String getEmployee_group() {
        return employee_group;
    }

    public void setEmployee_group(String employee_group) {
        this.employee_group = employee_group;
    }

    public String getEmployee_group_name() {
        return employee_group_name;
    }

    public void setEmployee_group_name(String employee_group_name) {
        this.employee_group_name = employee_group_name;
    }

    public String getManage_com() {
        return manage_com;
    }

    public void setManage_com(String manage_com) {
        this.manage_com = manage_com;
    }

    public String getManage_name() {
        return manage_name;
    }

    public void setManage_name(String manage_name) {
        this.manage_name = manage_name;
    }

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getCenter_branch() {
        return center_branch;
    }

    public void setCenter_branch(String center_branch) {
        this.center_branch = center_branch;
    }

    public String getCenter_branch_name() {
        return center_branch_name;
    }

    public void setCenter_branch_name(String center_branch_name) {
        this.center_branch_name = center_branch_name;
    }

    public String getEmployee_major_com() {
        return employee_major_com;
    }

    public void setEmployee_major_com(String employee_major_com) {
        this.employee_major_com = employee_major_com;
    }

    public String getEmployee_major_com_name() {
        return employee_major_com_name;
    }

    public void setEmployee_major_com_name(String employee_major_com_name) {
        this.employee_major_com_name = employee_major_com_name;
    }

    public String getEmployee_part_com() {
        return employee_part_com;
    }

    public void setEmployee_part_com(String employee_part_com) {
        this.employee_part_com = employee_part_com;
    }

    public String getEmployee_part_com_name() {
        return employee_part_com_name;
    }

    public void setEmployee_part_com_name(String employee_part_com_name) {
        this.employee_part_com_name = employee_part_com_name;
    }

    public String getEmployee_group_com() {
        return employee_group_com;
    }

    public void setEmployee_group_com(String employee_group_com) {
        this.employee_group_com = employee_group_com;
    }

    public String getEmployee_group_com_name() {
        return employee_group_com_name;
    }

    public void setEmployee_group_com_name(String employee_group_com_name) {
        this.employee_group_com_name = employee_group_com_name;
    }

    public String getBranch_attr() {
        return branch_attr;
    }

    public void setBranch_attr(String branch_attr) {
        this.branch_attr = branch_attr;
    }

    public String getBranch_attr_area() {
        return branch_attr_area;
    }

    public void setBranch_attr_area(String branch_attr_area) {
        this.branch_attr_area = branch_attr_area;
    }

    public String getBranch_attr_part() {
        return branch_attr_part;
    }

    public void setBranch_attr_part(String branch_attr_part) {
        this.branch_attr_part = branch_attr_part;
    }

    public String getBranch_attr_group() {
        return branch_attr_group;
    }

    public void setBranch_attr_group(String branch_attr_group) {
        this.branch_attr_group = branch_attr_group;
    }

    public String getBranch_manager() {
        return branch_manager;
    }

    public void setBranch_manager(String branch_manager) {
        this.branch_manager = branch_manager;
    }

    public String getBranch_manager_name() {
        return branch_manager_name;
    }

    public void setBranch_manager_name(String branch_manager_name) {
        this.branch_manager_name = branch_manager_name;
    }

    public String getMdrt_flag() {
        return mdrt_flag;
    }

    public void setMdrt_flag(String mdrt_flag) {
        this.mdrt_flag = mdrt_flag;
    }

    public String getIda_flag() {
        return ida_flag;
    }

    public void setIda_flag(String ida_flag) {
        this.ida_flag = ida_flag;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getDept_code_name() {
        return dept_code_name;
    }

    public void setDept_code_name(String dept_code_name) {
        this.dept_code_name = dept_code_name;
    }
}
