package hx.service.manage.model.message.huaxia;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: MessagePicContentModel
 * @description: 发送文本信息-图片内容Model
 * @author: huojiajin
 * @time: 2021/3/11 18:03
 */
public class MessagePicContentModel extends BaseEntity {

    private String title;//标题，不超过128个字节，超过会自动截断
    private String description;//描述，不超过512个字节，超过会自动截断
    private String url;//点击后跳转的链接
    private String picurl;//图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
