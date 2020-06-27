package hx.base.core.dao.repo.request.common;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

/**
 *@name: JpaSortDataRequest
 *@description: JPA数据排序基础类
 *@author: huojiajin
 *@time: 2020/5/26 11:08
**/
public abstract class JpaSortDataRequest<E> extends JpaDataRequest<E>
{
	protected String orderBy;// 排序的字段
	protected boolean desc;// 是否逆序
	protected Sort otherSort;// 需要多个排序的情况

	protected JpaSortDataRequest()
	{

	}

	protected JpaSortDataRequest(Class<E> resultClass)
	{
		super(resultClass);
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public boolean isDesc()
	{
		return desc;
	}

	public Sort getOtherSort()
	{
		return otherSort;
	}

	public void setOtherSort(Sort otherSort)
	{
		this.otherSort = otherSort;
	}

	public JpaSortDataRequest<E> setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
		return this;
	}

	public JpaSortDataRequest<E> setDesc(boolean desc)
	{
		this.desc = desc;
		return this;
	}

	/**
	 * 调用此方法增加排序属性
	 * 
	 * @param property
	 * @param direction
	 * @return {@link #toSort()}
	 */
	public JpaSortDataRequest<E> appendOrder(String property, Direction direction)
	{
		Sort append = Sort.by(direction, property);
		otherSort = otherSort == null ? append : otherSort.and(append);
		return this;
	}

	public Sort toSort()
	{
		// 优先的排序未指定
		if (!StringUtils.hasText(orderBy)) return otherSort;
		Direction d = desc ? Direction.DESC : Direction.ASC;
		Sort rs = Sort.by(d, orderBy);
		return otherSort == null ? rs : rs.and(otherSort);
	}
}
