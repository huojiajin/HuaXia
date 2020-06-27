package hx.base.core.dao.repo.request.common;

/**
 *@name: DefaultJpaPageableDataRequest
 *@description: 默认分页查询实现
 *@author: huojiajin
 *@time: 2020/5/26 11:11
**/
public class DefaultJpaPageableDataRequest<E> extends JpaPageableDataRequest<E>
{
	public DefaultJpaPageableDataRequest(Class<E> clazz)
	{
		super(clazz);
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName());
		return hql;
	}

}
