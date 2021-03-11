package hx.base.core.dao.entity.share;

import hx.base.core.dao.dict.share.ShareType;
import hx.base.core.dao.entity.common.StringUUIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name: ShareData
 * @description: 手机端电脑端共享数据
 * @author: huojiajin
 * @time: 2021/3/11 15:29
 */
@Entity
@Table(name = "hx_share_data")
public class ShareData extends StringUUIDEntity {

    private ShareType type;
    private String data;

    @Column(name = "type", unique = true)
    public ShareType getType() {
        return type;
    }

    public void setType(ShareType type) {
        this.type = type;
    }

    @Column(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
