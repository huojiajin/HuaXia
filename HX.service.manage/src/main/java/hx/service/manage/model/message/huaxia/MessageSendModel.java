package hx.service.manage.model.message.huaxia;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: MessageSendModel
 * @description: 微信消息发送通用Model
 * @author: huojiajin
 * @time: 2021/3/20 20:12
 */
public class MessageSendModel extends BaseEntity {

    private String touser = "";//成员ID列表；员工工号
    private String toparty = "";//部门ID列表；
    private String totag = "";//标签ID列表；
    private String msgtype = "text";//消息类型;固定值：text
    private String agentid = "107";//目前固定为《消息中心》，测试环境取值1000024，生产环境取值107

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }
}
