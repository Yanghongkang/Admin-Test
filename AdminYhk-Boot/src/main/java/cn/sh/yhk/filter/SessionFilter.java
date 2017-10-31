package cn.sh.yhk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

	 protected static Logger logger=LoggerFactory.getLogger(SessionFilter.class); 
	 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		//if(httpRequest.ge)
			
			//response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("过滤器初始化");
	}
}
