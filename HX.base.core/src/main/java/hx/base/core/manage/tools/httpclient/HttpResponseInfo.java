package hx.base.core.manage.tools.httpclient;

import hx.base.core.dao.entity.common.BaseEntity;
import hx.base.core.manage.tools.NumberTools;

public class HttpResponseInfo extends BaseEntity
{
	private boolean gzip;
	private long contentLength;

	public HttpResponseInfo()
	{
		super();
	}

	public HttpResponseInfo(boolean gzip, long contentLength)
	{
		super();
		this.gzip = gzip;
		this.contentLength = contentLength;
	}

	public boolean isGzip()
	{
		return gzip;
	}

	public long getContentLength()
	{
		return contentLength;
	}

	public HttpResponseInfo setGzip(boolean gzip)
	{
		this.gzip = gzip;
		return this;
	}

	public HttpResponseInfo setContentLength(long contentLength)
	{
		this.contentLength = contentLength;
		return this;
	}

	public String getContentLengthStr()
	{
		return NumberTools.bytesAsHumanStr(contentLength);
	}

	public static HttpResponseInfo fromJson(String jsonStr)
	{
		return fromJson(jsonStr, HttpResponseInfo.class);
	}
}
