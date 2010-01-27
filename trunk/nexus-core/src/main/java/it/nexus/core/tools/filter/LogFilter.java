package it.nexus.core.tools.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {

	protected FilterConfig filterConfig;

	public void destroy() {
		filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		ServletContext context = filterConfig.getServletContext();
		long befTime = System.currentTimeMillis();
		chain.doFilter(request, response); // no chain parameter needed here
		long aftTime = System.currentTimeMillis();
		context.log("Request to "
				+ ((HttpServletRequest) request).getRequestURI() + ": "
				+ (aftTime - befTime));
	}

	public void init(FilterConfig config) throws ServletException {
		filterConfig = config;
		System.out.println("初始化日志过滤器");
	}

}
