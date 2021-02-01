package hx.service.manage.model.message;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName: MessageLogQueryResponse
 * @Description: 自定义消息日志查询Response
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:17
 * @Version 1.0
 **/
public class MessageLogQueryResponse extends BaseEntity {

    private int pushType;//推送类型
    private String pushName;//推送职级/营服
    private String pushTime;//推送时间

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }
}
