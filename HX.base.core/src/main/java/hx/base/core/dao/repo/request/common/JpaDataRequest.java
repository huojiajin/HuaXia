package hx.base.core.dao.repo.request.common;

import org.springframework.util.StringUtils;

/**
 *@name: JpaDataRequest
 *@description: Jpa请求的基类，泛型的类型是查询结果的类型
 *@author: huojiajin
 *@time: 2020/5/26 11:01
**/
public abstract class JpaDataRequest<E> extends TypedDataRequest<E>
{
	protected static final String COUNT_ID = "select count(id) ";

	protected JpaDataRequest()
	{

	}

	protected JpaDataRequest(Class<E> resultClass)
	{
		super(resultClass);
	}

	public final String like(String rs)
	{
		return StringUtils.hasText(rs) ? "%" + rs + "%" : null;
	}

	public final String leftLike(String rs)
	{
		return StringUtils.hasText(rs) ? "%" + rs : null;
	}

	public final String rightLike(String rs)
	{
		return StringUtils.hasText(rs) ? rs + "%" : null;
	}

	/**
	 * 不包含Order的查询hql
	 * 
	 * @return
	 */
	public abstract HqlBuilder toSelectHql();
}
