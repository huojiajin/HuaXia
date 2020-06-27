package hx.base.core.manage.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 *@name: CommonAbstract
 *@description: 通用抽象类
 *@author: huojiajin
 *@time: 2020/5/26 11:02
**/
public abstract class CommonAbstract
{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static String subContent(String content)
	{
		return subContent(content, 200);
	}

	public static String subContent(String content, int max)
	{
		if (!StringUtils.hasText(content) || content.length() < (max + 1)) return content;
		return content.substring(0, max) + "...(has more)";
	}

	protected static final boolean hasText(CharSequence str)
	{
		return StringUtils.hasText(str);
	}

	protected static final boolean isEmpty(Object[] array)
	{
		return ObjectUtils.isEmpty(array);
	}

	protected static final boolean isEmpty(Collection<?> collection)
	{
		return CollectionUtils.isEmpty(collection);
	}

	protected static final boolean isEmpty(Map<?, ?> map)
	{
		return CollectionUtils.isEmpty(map);
	}

	public static void closeQuietly(Closeable closeable){
		if(closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
