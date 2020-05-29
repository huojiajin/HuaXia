package hx.service.manage.dao.repo.request.common;

/**
 *@name: JpaCountDataRequest
 *@description: JPA计数查询基类
 *@author: huojiajin
 *@time: 2020/5/26 11:04
**/
public abstract class JpaCountDataRequest extends JpaDataRequest<Long>
{
	protected JpaCountDataRequest()
	{
		super(Long.class);
	}
}
