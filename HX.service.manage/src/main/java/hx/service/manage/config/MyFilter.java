package hx.service.manage.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @name: MyFilters
 * @description: 自定义过滤器
 * @author: huojiajin
 * @time: 2020/5/29 11:31
 */
@WebFilter(filterName="MyFilter",urlPatterns="/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper=null;
        if(request instanceof HttpServletRequest) {
            requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest)request);
        }
        if(requestWrapper == null) {
            chain.doFilter(request, response);
        }else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {
        // do nothing
    }

}