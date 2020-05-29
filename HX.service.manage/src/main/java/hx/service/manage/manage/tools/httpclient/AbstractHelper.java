package hx.service.manage.manage.tools.httpclient;

import hx.service.manage.manage.common.AbstractManager;
import org.apache.commons.lang3.SystemUtils;

public abstract class AbstractHelper extends AbstractManager
{
	public static final int BUFFER = 1024;
	protected static final String DEFAULT_ENCODING = SystemUtils.IS_OS_WINDOWS ? "GBK" : "UTF-8";
}
