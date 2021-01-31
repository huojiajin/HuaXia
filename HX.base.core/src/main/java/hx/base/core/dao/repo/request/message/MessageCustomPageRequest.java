package hx.base.core.dao.repo.request.message;

import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.request.common.HqlBuilder;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;
import org.springframework.data.domain.Sort;

/**
 * @name: MessageCustomPageRequest
 * @description: 自定义消息分页查询
 * @author: huojiajin
 * @time: 2020/7/3 11:42
 */
public class MessageCustomPageRequest extends JpaPageableDataRequest<MessageCustom> {

    private String name;//消息名称

    public MessageCustomPageRequest() {
        this.otherSort = Sort.by(Sort.Direction.DESC, "updateTime", "createTime");
    }

    @Override
    public HqlBuilder toSelectHql() {
        HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
        hql.append(" and stop = 0");
        hql.append(" and name like :name", like(name));
        return hql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
