package hx.service.manage.dao.entity;

import hx.service.manage.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @name: Role
 * @description: 角色表（电脑端）
 * @author: huojiajin
 * @time: 2020/5/25 15:38
 */
@Entity
@Table(name = "hx_role")
public class Role extends AbstractInsertTimeEntity {

    private String name;// 角色名称
    private String info;// 描述
    private Integer list;// 排序
    private boolean stop;//是否删除

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "info", nullable = false)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Column(name = "list", nullable = false)
    public Integer getList() {
        return list;
    }

    public void setList(Integer list) {
        this.list = list;
    }

    @Column(name = "is_delete", nullable = false)
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
