package it.nexus.core.tools.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	protected FilterConfig filterConfig;
	private String targetEncoding;

	public EncodingFilter() {
		targetEncoding = "UTF-8";
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		filterConfig = filterconfig;
		targetEncoding = filterconfig.getInitParameter("aaaaaa");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpservletrequest = (HttpServletRequest) request;
		httpservletrequest.setCharacterEncoding(targetEncoding);
		System.out.println("使用" + targetEncoding + "对请求进行编码过滤");
		chain.doFilter(request, response);
	}

	public void setFilterConfig(FilterConfig filterconfig) {
		filterConfig = filterconfig;
	}

	public void destroy() {
		filterConfig = null;
	}
}
