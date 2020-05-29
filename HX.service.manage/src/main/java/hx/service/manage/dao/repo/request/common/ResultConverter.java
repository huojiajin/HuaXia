package hx.service.manage.dao.repo.request.common;

/**
 *@name: ResultConverter
 *@description: 结果转换
 *@author: huojiajin
 *@time: 2020/5/26 10:32
**/
public interface ResultConverter<F, T>
{
	public T convert(F from);
}
