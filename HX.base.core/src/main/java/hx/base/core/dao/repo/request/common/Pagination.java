package hx.base.core.dao.repo.request.common;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.manage.tools.CollectionTools;

import java.util.ArrayList;
import java.util.List;

/**
 *@name: Pagination
 *@description: 数据库查询分页类
 *@author: huojiajin
 *@time: 2020/5/26 10:39
**/
public class Pagination extends BaseEntity
{
	private int currentPage;
	private int pageSize = 16;
	private long pageCount;
	private long totalCount;
	private List<?> result;

	public Pagination()
	{

	}

	public Pagination(int curretPage, int pageSize)
	{
		this.currentPage = curretPage;
		this.pageSize = pageSize;
	}

	public Pagination(int currentPage, int pageSize, long totalCount)
	{
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	/**
	 * 设置完成totalcount和pagesize以后调用此方法自动计算其他值
	 * 
	 * @return
	 */
	public Pagination compute()
	{
		if (totalCount < 0 || pageSize <= 0) throw new RuntimeException("totalCount和pageSize设置之前不能进行计算");
		if (currentPage <= 0) currentPage = 1;// 当前页不能为负数
		pageCount = getPageCount(totalCount, pageSize);
		int count = Long.valueOf(pageCount).intValue();
		if (currentPage > count) currentPage = count;// 如页码过大，返回最后一页
		return this;
	}

	public Pagination(List<?> result)
	{
		this.result = result;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public Pagination setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
		return this;
	}

	public long getPageCount()
	{
		return pageCount;
	}

	public Pagination setPageCount(long pageCount)
	{
		this.pageCount = pageCount;
		return this;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public Pagination setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
		return this;
	}

	public List<?> getResult()
	{
		return result;
	}

	public Pagination setResult(List<?> result)
	{
		this.result = result;
		return this;
	}

	public long getTotalCount()
	{
		return totalCount;
	}

	public Pagination setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getResult(Class<T> clazz)
	{
		return (List<T>) result;
	}

	public static long getPageCount(long totalCount, int pageSize)
	{
		if (totalCount < 0 || pageSize <= 0) return 0;
		return (totalCount % pageSize == 0) ? totalCount / pageSize : (totalCount / pageSize + 1);
	}

	@SuppressWarnings("unchecked")
	public <F, T> Pagination convertResult(ResultConverter<F, T> converter)
	{
		result = CollectionTools.convertList((List<F>) result, converter);
		return this;
	}

	public static Pagination emptyInstance()
	{
		return new Pagination().compute().setResult(new ArrayList<Object>());
	}

	@Override
	public String toString()
	{
		return "Pagination [currentPage=" + currentPage + ", pageCount=" + pageCount + ", pageSize=" + pageSize
				+ ", result.size()=" + result.size() + ", totalCount=" + totalCount + "]";
	}
}
