package hx.base.core.dao.entity.honor;

import hx.base.core.dao.dict.honor.HonorStatus;
import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.*;

/**
 * @ClassName: Honor
 * @Description: 荣誉实体
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:07
 * @Version 1.0
 **/
@Entity
@Table(name = "hx_honor")
public class Honor extends AbstractInsertTimeEntity {

    private String name;//荣誉名称
    private HonorStatus status = HonorStatus.WDR;//状态
    private byte[] icon;//荣誉图标
    private boolean stop = false;//是否已删除

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public HonorStatus getStatus() {
        return status;
    }

    public void setStatus(HonorStatus status) {
        this.status = status;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "icon")
    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Column(name = "is_stop")
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
