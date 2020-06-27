package hx.base.core.dao.repo.jpa.common;

import hx.base.core.dao.repo.request.common.JpaCountDataRequest;
import hx.base.core.dao.repo.request.common.JpaDataRequest;
import hx.base.core.dao.repo.request.common.JpaPageableDataRequest;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.entity.common.BaseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 *@name: AbstractJpaRepo
 *@description: Repo基础类
 *@author: huojiajin
 *@time: 2020/5/26 11:09
**/
@NoRepositoryBean
public interface AbstractJpaRepo<T extends BaseEntity, ID extends Serializable>
		extends JpaRepository<T, ID>
{

	/**
	 * 保存entity，只是保存
	 * @param entity
	 */
	public void persist(T entity);

	/**
	 * 保存一组entities
	 * @param list
	 */
	public void persistAll(List<T> list);

	public T merge(T entity);

	public void remove(T entity);

	public Long deleteByIdIn(Iterable<ID> ids);

	public void blobSave(T entity, String blobPropName, InputStream in);

	/**
	 * 按条件查询
	 * 
	 * @param req
	 * @return
	 */
	default public <E> List<E> list(JpaDataRequest<E> req)
	{
		return list(req, -1);
	}

	/**
	 * 按条件查询
	 * 
	 * @param req
	 * @param maxSize
	 *            最多取多少条
	 * @return
	 */
	public <E> List<E> list(JpaDataRequest<E> req, int maxSize);

	/**
	 * 统计总数
	 * 
	 * @param req
	 * @return
	 */
	public long count(JpaCountDataRequest req);

	/**
	 * 分页查询 使用{@link JpaPageableDataRequest#toCountHql()}查询总数
	 * 
	 * @param req
	 * @return
	 */
	default public <E> Pagination page(JpaPageableDataRequest<E> req)
	{
		return page(null, req);
	}

	/**
	 * 分页查询
	 * 
	 * @param totalCount
	 *            指定总数时直接使用指定的总数，不再使用{@link JpaPageableDataRequest#toCountHql()}查询总数
	 * @param req
	 * @return
	 */
	public <E> Pagination page(Long totalCount, JpaPageableDataRequest<E> req);

	public Pagination pageAll(Sort sort, int pageNo, int pageSize);

	default public Pagination pageAll(int pageNo, int pageSize)
	{
		return pageAll(null, pageNo, pageSize);
	}
}
