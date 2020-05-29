package hx.service.manage.dao.repo.request.common;

import hx.service.manage.dao.entity.common.BaseEntity;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;

/**
 *@name: TypedDataRequest
 *@description: 泛型请求的基类，泛型的类型是查询结果的类型
 *@author: huojiajin
 *@time: 2020/5/26 11:02
**/
public abstract class TypedDataRequest<E> extends BaseEntity
{
	protected final Class<E> clazz;// result class

	@SuppressWarnings("unchecked")
	protected TypedDataRequest()
	{
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		clazz = (Class<E>) type.getActualTypeArguments()[0];
		Assert.notNull(clazz, "无法获得泛型的class");
	}

	protected TypedDataRequest(Class<E> clazz)
	{
		this.clazz = clazz;
		Assert.notNull(clazz, "clazz can not be null.");
	}

	public Class<E> getClazz()
	{
		return clazz;
	}

}
