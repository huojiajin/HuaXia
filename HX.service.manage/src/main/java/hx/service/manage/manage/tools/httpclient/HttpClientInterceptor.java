/*
 * Date: 2014年4月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package hx.service.manage.manage.tools.httpclient;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年4月1日 下午5:26:24<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 * @see {@link cn.com.taiji.common.manager.net.http.HttpClientHelper#httpRequest(HttpUriRequest, boolean, int, HttpResponseHandler, int, cn.com.taiji.common.manager.net.http.HttpClientInterceptor)}
 */
public interface HttpClientInterceptor
{
	/**
	 * 发送http请求前做些操作，比如增加header等
	 * 
	 * @param request
	 */
	public void process(final HttpUriRequest request);
}
