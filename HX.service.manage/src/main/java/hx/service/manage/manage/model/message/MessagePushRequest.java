package hx.service.manage.manage.model.message;

import java.util.List;

/**
 * @ClassName: MessagePushRequest
 * @Description: 自定义消息推送Request
 * @Author HuoJiaJin
 * @Date 2021/1/31 23:15
 * @Version 1.0
 **/
public class MessagePushRequest extends MessageIdRequest{

    private int pushType;//推送类型
    private List<String> codeList;//代码集合

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
