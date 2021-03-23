package hx.base.core.dao.entity.acl;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @name: MobileUser
 * @description: 手机用户相关信息表
 * @author: huojiajin
 * @time: 2020/7/13 0:50
 */
@Entity
@Table(name = "hx_mobile_user", indexes = {
        @Index(columnList = "agent_code", name = "hx_mobile_user_index")
})
public class MobileUser extends AbstractInsertTimeEntity {

    private String agentCode;//员工编号
    private String openid;//用户关注华夏微信服务号后产生的唯一标识(用户未与华夏微信服务号绑定则返回空)
    private String unionid;//微信开放平台unionid
    private String avatar;//头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可（branch_type不为99时返回）
    private String mdrt_flag;//MDRT会员标识，Y-是，N-不是（branch_type不为99时返回）
    private String mobile;//手机号

    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "mdrt_flag")
    public String getMdrt_flag() {
        return mdrt_flag;
    }

    public void setMdrt_flag(String mdrt_flag) {
        this.mdrt_flag = mdrt_flag;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
