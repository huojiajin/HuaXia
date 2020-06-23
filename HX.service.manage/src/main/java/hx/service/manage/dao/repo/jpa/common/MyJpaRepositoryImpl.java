package hx.service.manage.dao.repo.jpa.common;

import com.google.common.collect.Maps;
import hx.service.manage.dao.entity.common.BaseEntity;
import hx.service.manage.dao.repo.request.common.*;
import hx.service.manage.manage.tools.CollectionTools;
import org.hibernate.engine.jdbc.ContextualLobCreator;
import org.hibernate.engine.jdbc.LobCreator;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@NoRepositoryBean
public class MyJpaRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements AbstractJpaRepo<T, ID>
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private final EntityManager entityManager;

	public MyJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager)
	{
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	public MyJpaRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager)
	{
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void persistAll(List<T> list)
	{
		for (T entity : list)
		{
			entityManager.persist(entity);
		}
		entityManager.flush();
	}

	@Override
	@Transactional
	public void persist(T entity)
	{
		entityManager.persist(entity);
	}

	@Override
	@Transactional
	public T merge(T entity)
	{
		return entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void remove(T entity)
	{
		entityManager.remove(entity);
	}

	@Override
	@Transactional
	public void blobSave(T entity, String blobPropName, InputStream in)
	{
		Assert.notNull(in, "inputstream can not be null.");
		try
		{
			entity.setPropertyValue(blobPropName, input2Blob(in));
			save(entity);
			entityManager.flush();
		}
		finally
		{
			closeInput(in);
		}
	}

	@Override
	public Long deleteByIdIn(Iterable<ID> ids)
	{
		Assert.notNull(ids, "ids can not be null.");
		String hql = "delete from " + getDomainClass().getName() + " where id in :ids";
		Map<String, Object> params = Maps.newHashMap();
		params.put("ids", ids);
		int count = executeHql(hql, params);
		return Long.valueOf(count);
	}

	@Override
	public long count(JpaCountDataRequest qm)
	{
		HqlBuilder hql = qm.toSelectHql();
		return querySize(hql.toString(), hql.getParams());
	}

	@Override
	public Pagination pageAll(Sort sort, int pageNo, int pageSize)
	{
		DefaultJpaPageableDataRequest<T> req = new DefaultJpaPageableDataRequest<>(getDomainClass());
		req.setOtherSort(sort);
		return page(req.setPageNo(pageNo).setPageSize(pageSize));
	}

	@Override
	public <E> Pagination page(Long totalCount, JpaPageableDataRequest<E> qm)
	{
		HqlBuilder hql = qm.toSelectHql();
		Long total = totalCount;
		if (total == null) total = querySize(qm.toCountHql(), hql.getParams());
		appendSort(hql, qm.toSort());
		Pagination pg = newPagination(qm.getPageNo(), total, qm.getPageSize());
		pg.setResult(pageList(qm.getClazz(), hql.toString(), hql.getParams(), pg.getCurrentPage(), qm.getPageSize()));
		return pg;
	}

	@Override
	public <E> List<E> list(JpaDataRequest<E> req, int maxSize)
	{
		HqlBuilder hql = req.toSelectHql();
		if (req instanceof JpaSortDataRequest) appendSort(hql, ((JpaSortDataRequest<E>) req).toSort());
		return pageList(req.getClazz(), hql.toString(), hql.getParams(), -1, maxSize);
	}

	protected final void appendSort(HqlBuilder hql, Sort sort)
	{
		if (sort == null) return;
		hql.append(" order by ");
		Iterator<Order> orders = sort.iterator();
		while (orders.hasNext())
		{
			Order order = orders.next();
			hql.append(order.getProperty() + " " + order.getDirection());
			if (orders.hasNext()) hql.append(",");
		}
	}

	protected final long querySize(String hql, Map<String, Object> values)
	{
		if (hql == null) return 0;
		List<Long> list = pageList(Long.class, hql, values, -1, -1);
		return list.get(0);
	}

	/**
	 * 分页查询
	 * 
	 * @param eleClass
	 *            返回列表的元素类型
	 * @param hql
	 *            查询的hql语句
	 * @param values
	 *            名字占位符(ex :column)
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return 查询结果
	 */
	protected final <E> List<E> pageList(Class<E> eleClass, String hql, Map<String, Object> values, int pageNo,
			int pageSize)
	{
		long begin = System.currentTimeMillis();
		if (pageSize <= 0 && getDomainClass().equals(eleClass) && CollectionTools.isEmpty(values))
			logger.info("从表中获取所有数据,请确认是否需要查询所有数据:{}", hql);
		TypedQuery<E> query = entityManager.createQuery(hql, eleClass);
		prepareTypedQuery(query, values, pageNo, pageSize);
		List<E> rs = query.getResultList();
		if (rs.size() > 1000) logger.warn("单次查询数量超过1000,hql:{}", hql);
		long exec = System.currentTimeMillis() - begin;
		if (exec > 5000) logger.info("本次查询耗时超过5秒,耗时为:{}ms,hql:{}", exec, hql);
		return rs;
	}

	protected <E> void prepareTypedQuery(TypedQuery<E> query, Map<String, Object> values, int pageNo, int pageSize)
	{
		int firstResult = pageNo < 1 ? 0 : pageSize * (pageNo - 1);
		if (firstResult >= 0) query.setFirstResult(firstResult);
		if (pageSize > 0) query.setMaxResults(pageSize);
		if (values != null)
		{
			for (Entry<String, Object> en : values.entrySet())
			{
				query.setParameter(en.getKey(), en.getValue());
			}
		}
	}

	@Transactional
	protected int executeHql(String hql, Map<String, Object> values)
	{
		long begin = System.currentTimeMillis();
		Query query = entityManager.createQuery(hql);
		if (values != null)
		{
			for (Entry<String, Object> en : values.entrySet())
			{
				query.setParameter(en.getKey(), en.getValue());
			}
		}
		int count = query.executeUpdate();
		entityManager.flush();
		long exec = System.currentTimeMillis() - begin;
		if (exec > 5000) logger.info("本次更新耗时超过5秒,耗时为:{}ms,hql:{}", exec, hql);
		return count;
	}

	protected final LobCreator getLobCreator()
	{
		return new ContextualLobCreator(entityManager.unwrap(SessionImpl.class));
	}

	/**
	 * 使用当前的Lobreator将input转换成blob
	 * 
	 * @param in
	 * @return
	 */
	protected final Blob input2Blob(InputStream in)
	{
		return input2Blob(in, -1);
	}

	protected final Blob input2Blob(InputStream in, long length)
	{
		Assert.notNull(in, "inputstream can not be null.");
		try
		{
			long myLength = length < 0 ? in.available() : length;
			Blob blob = getLobCreator().createBlob(in, myLength);
			return blob;
		}
		catch (IOException e)
		{
			logger.error("", e);
			throw new RuntimeException("inputstream 转成blob出错");
		}
		// hibernate 4.1.9以后，不能在此处close input,在具体Dao的blobSave方法中persist后close
		// finally
		// {
		// closeInputSilence(in);
		// }
	}

	protected void closeInput(InputStream in)
	{
		try
		{
			if (in != null) in.close();
		}
		catch (IOException e)
		{
			logger.error("", e);
		}
	}

	private final Pagination newPagination(int pageNumber, long totalCount, int pageSize)
	{
		return new Pagination(pageNumber, pageSize, totalCount).compute();
	}
}
