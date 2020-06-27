package hx.base.core.dao.repo.request.common;

import hx.base.core.manage.common.CommonAbstract;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@name: HqlBuilder
 *@description: hql语句生成类
 *@author: huojiajin
 *@time: 2020/5/26 11:02
**/
public class HqlBuilder extends CommonAbstract
{
	// 非空白字符包含括号等，不合适.用单词进行捕获更准确
	private static final Pattern pattern = Pattern.compile(":(\\w+)");

	private StringBuilder hql;
	private Map<String, Object> params = new HashMap<String, Object>();

	public HqlBuilder(String hql)
	{
		super();
		this.hql = new StringBuilder(hql);
	}

	public String toString()
	{
		return hql.toString();
	}

	public Map<String, Object> getParams()
	{
		return params;
	}

	public HqlBuilder append(String clause, Object value)
	{
		if (!needAppend(value)) return this;
		hql.append(clause);
		Matcher matcher = pattern.matcher(clause);
		while (matcher.find())
		{
			String key = matcher.group(1);
			if (params.containsKey(key))
				throw new IllegalArgumentException(key + " has already used in previous clause.");
			params.put(key, value);
		}
		return this;
	}

	public HqlBuilder append(String append)
	{
		hql.append(append);
		return this;
	}

	private static boolean needAppend(Object value)
	{
		if (value == null) return false;
		if (value instanceof Collection) return !CommonAbstract.isEmpty((Collection<?>) value);
		if (!(value instanceof CharSequence)) return true;
		return CommonAbstract.hasText((CharSequence) value);
	}
}
