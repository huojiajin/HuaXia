package hx.service.manage.manage.tools.httpclient;

import hx.service.manage.manage.common.AbstractManager;
import org.apache.http.HttpResponse;

public abstract class AbstractResponseHandler extends AbstractManager
{
	protected final boolean isContentGziped(HttpResponse response)
	{
		return HttpClientHelper.isContentGziped(response);
	}

}
