package hx.service.manage.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import javax.persistence.Table;

/**
 * @name: HXNamingStrategy
 * @description: 华夏用表名处理
 * @author: huojiajin
 * @time: 2020/7/11 16:43
 */
public class HXNamingStrategy extends SpringPhysicalNamingStrategy {

    // 定义包名
    private static final String packageName = "hx.base.core.dao.entity.";

    /**
     * 重写父类生成表名的方法
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        try {
            // 获取实体类
            Class entityClass = Class.forName(packageName + name.getText());
            // 判断类上是否有Table注解
            Boolean hasAnnotation = entityClass.isAnnotationPresent(Table.class);
            // 存在Table注解
            if (hasAnnotation) {
                // 获取Table注解实例
                Table table = (Table) entityClass.getAnnotation(Table.class);
                // 如果注解中的name字段不为空
                if (!table.name().equals("")) {
                    // 不对名称进行处理
                    return name;
                }
            }
            // 表示这是一个类名，按父类操作进行处理
            return super.toPhysicalTableName(name, jdbcEnvironment);
        } catch (ClassNotFoundException e) {
            // 找不到实体类，说明肯定是@Table注解中的名称
            return name;
        }
    }
}
