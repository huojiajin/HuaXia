package hx.base.core.dao.repo.jpa.common;

import hx.base.core.dao.entity.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

/**
 *@name: MyJpaRespositoryFactoryBean
 *@description: jpa实现工厂类
 *@author: huojiajin
 *@time: 2020/5/26 11:18
**/
public class MyJpaRespositoryFactoryBean<R extends JpaRepository<T, ID>, T extends BaseEntity, ID extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, ID>
{
	public MyJpaRespositoryFactoryBean(Class<? extends R> repositoryInterface)
	{
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager)
	{
		return new MyJpaRepositoryFactory(entityManager);
	}

	private static class MyJpaRepositoryFactory extends JpaRepositoryFactory
	{
		public MyJpaRepositoryFactory(EntityManager entityManager)
		{
			super(entityManager);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata)
		{
			boolean dslQuery = QUERY_DSL_PRESENT
					&& QuerydslPredicateExecutor.class.isAssignableFrom(metadata.getRepositoryInterface());
			return dslQuery ? QuerydslJpaPredicateExecutor.class : MyJpaRepositoryImpl.class;
		}
	}
}
