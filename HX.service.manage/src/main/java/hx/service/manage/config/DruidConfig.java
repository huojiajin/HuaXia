package hx.service.manage.config;

import hx.service.manage.dao.repo.jpa.common.MyJpaRespositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @name: DruidConfig
 * @description: druid连接池配置
 * @author: huojiajin
 * @time: 2020/5/25 10:27
 */
@Configuration
@EntityScan(value = "hx.service.manage.dao.entity")
@EnableJpaRepositories(basePackages ={"hx.service.manage.dao.repo.jpa"},
        repositoryFactoryBeanClass = MyJpaRespositoryFactoryBean.class)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
public class DruidConfig {


}
