package hx.service.mobile.config;

import hx.base.core.dao.repo.jpa.common.MyJpaRespositoryFactoryBean;
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
@EntityScan(basePackages = "hx.base.core.dao.entity")
@EnableJpaRepositories(basePackages ={"hx.base.core.dao.repo.jpa"},
        repositoryFactoryBeanClass = MyJpaRespositoryFactoryBean.class)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
public class DruidConfig {


}
