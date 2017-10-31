package cn.sh.yhk.filter;

import java.io.IOException;
import java.util.Arrays;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import cn.sh.yhk.commone.AdminYhkCommonConstant;
import cn.sh.yhk.commone.plugins.sso.AdminYhkSsoResultMap;

@WebFilter(filterName = "SessionFilter", urlPatterns = "*")
@Order(2)
public class SessionFilter implements Filter {

	protected static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	@Value("${AdminYhk.not.filter.url}")
	private String NeedtLogurls;

	@Value("${AdminYhk.static.source.suffix}")
	private String sourceSuffix;

	@Autowired
	private AdminYhkSsoResultMap sooResultMap;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String contextPath = (String) ((HttpServletRequest) request).getContextPath();
		String ServletPath = (String) ((HttpServletRequest) request).getServletPath();
		HttpServletRequest hRequest = (HttpServletRequest) request;
		//SSO
//		User user =  (User)hRequest.getSession().getAttribute(AdminYhkCommonConstant.SESSION_SYS_USER);
//		String key = user.getUserNo() + user.getId() + user.getUserPwd().substring(10);
//		String val = sooResultMap.getLoginUserMap(key);
//		String[] vals = val.split("@");
//		String session = vals[1];
//		if(!hRequest.getSession().getId().equals(session)){
//			((HttpServletResponse) response).sendRedirect(contextPath + "/gologin?sessionid="+null);
//		}
		//SSO
		// 静态资源直接过
		String[] sourceSuffixs = sourceSuffix.split(";");
		String suffix = ServletPath.substring(ServletPath.lastIndexOf(".") + 1, ServletPath.length());
		if(Arrays.asList(sourceSuffixs).contains(suffix)){
			chain.doFilter(hRequest, response);
			return;
		}
		// 静态资源直接过
		boolean needtLog = false;
		String[] needLogurls = NeedtLogurls.split(";");
		if(Arrays.asList(needLogurls).contains(ServletPath)){
			chain.doFilter(hRequest, response);
			return;
		}
		if (hRequest.getSession().getAttribute(AdminYhkCommonConstant.SESSION_SYS_USER) != null) {
			chain.doFilter(hRequest, response);
			return;
		} else {
			if (needtLog) {
				chain.doFilter(hRequest, response);
				return;
			}
			if (StringUtils.isEmpty(hRequest.getParameter(AdminYhkCommonConstant.SESSION_SYS_USER))) {
				((HttpServletResponse) response).sendRedirect(contextPath + "/gologin?Nologcode=1&sessionid="+hRequest.getSession().getId());
			}
		}
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
