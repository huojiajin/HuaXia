package hx.service.manage.dao.repo.request.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *@name: JpaPageableDataRequest
 *@description: JPA分页基础类
 *@author: huojiajin
 *@time: 2020/5/26 11:05
**/
public abstract class JpaPageableDataRequest<E> extends JpaSortDataRequest<E>
{
	protected int pageNo = 1;
	protected int pageSize = 16;

	protected JpaPageableDataRequest()
	{

	}

	protected JpaPageableDataRequest(Class<E> clazz)
	{
		super(clazz);
		this.pageSize = 16;
	}

	/**
	 * 用于查询总数的hql。 子类可以通过覆盖本方法实现自定义count
	 * 
	 * @return
	 */
	public String toCountHql()
	{
		return COUNT_ID + toSelectHql().toString();
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public JpaPageableDataRequest<E> setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
		return this;
	}

	public JpaPageableDataRequest<E> setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
		return this;
	}

	public Pageable toPageable()
	{
		return PageRequest.of(pageNo - 1, pageSize, toSort());
	}
}
