package hx.service.manage.dao.entity.common;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @name: StringUUID
 * @description: 主键ID共有类
 * @author: huojiajin
 * @time: 2020/5/25 15:22
 */
@MappedSuperclass
public class StringUUIDEntity extends BaseEntity{

    protected String id;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId()
    {
        if (id == null) id = UUID.randomUUID().toString().replace("-", "");
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof StringUUIDEntity)) return false;
        StringUUIDEntity other = (StringUUIDEntity) obj;
        if (id == other.getId()) return true;
        if (id == null || other.getId() == null) return false;
        return id.equals(other.getId());
    }

    @Override
    public int hashCode()
    {
        if (id == null) return super.hashCode();
        return id.hashCode();
    }
}
