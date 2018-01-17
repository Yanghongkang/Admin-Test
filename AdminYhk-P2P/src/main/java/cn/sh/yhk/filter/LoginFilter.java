package cn.sh.yhk.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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

import com.alibaba.fastjson.JSON;

import cn.sh.yhk.admin.mapper.AdminRoleMapper;
import cn.sh.yhk.admin.mapper.AdminUserMapper;
import cn.sh.yhk.admin.model.AdminUser;
import cn.sh.yhk.commone.AdminYhkCommonConstant;
import cn.sh.yhk.commone.AdminYhkResponseData;
import cn.sh.yhk.commone.plugins.sso.AdminYhkSsoObject;
import cn.sh.yhk.commone.plugins.sso.AdminYhkSsoResultMap;
import cn.sh.yhk.util.MD5Util;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/login")
public class LoginFilter implements Filter {

	protected static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Autowired
	private AdminUserMapper adminUserMapper;

	@Autowired
	AdminRoleMapper adminRoleMapper;

	public static final String LOGIN_ERROR_NOUSER = "用户名未注册";

	public static final String LOGIN_ERROR_PWD = "密码错误";

	public static final String LOGIN_ERROR_LOCKUSER = "用户已锁住";

	public static final String LOGIN_ERROR_USERLOGINED = "用户已登陆";

	@Autowired
	private AdminYhkSsoResultMap adminYhkssoResultMap;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpResponse.setCharacterEncoding("UTF-8");

		AdminYhkResponseData loginresponse = new AdminYhkResponseData();

		String contextPath = (String) ((HttpServletRequest) request).getContextPath();
		String ncode = request.getParameter("Vcode");
		String vcode = (String) httpRequest.getSession().getAttribute(AdminYhkCommonConstant.SESSION_LOGIN_VCODE);

		PrintWriter write = httpResponse.getWriter();

		if (!ncode.equals(vcode)) {
			loginresponse.setSuccess(false);
			loginresponse.setMsg("验证码错误");
			write.println(JSON.toJSONString(loginresponse));
			write.close();
			return;
		}
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		AdminUser user = adminUserMapper.queryAdminUserByName(name);
		Enumeration<String> ss = httpRequest.getSession().getAttributeNames();
		System.out.println(ss.nextElement());
		try {
			if (user != null) {
				if (MD5Util.md5Encode(password).trim().equals(user.getUserPwd().trim())) {
					// SSO
					String key = user.getUserNo() + user.getId() + user.getUserPwd().substring(10);
					AdminYhkSsoObject ysessionObj = adminYhkssoResultMap.getLoginUserMap(key);
					if (ysessionObj != null) {
						if (!ysessionObj.getSessionId().equals(httpRequest.getSession().getId())) {// 不同sessionID
							// 去除先前登陆信息
							ysessionObj.getSession().invalidate();
							// 删除原session信息
							adminYhkssoResultMap.removeLoginUserMap(key);
							// 添加新session
							adminYhkssoResultMap.setLoginUserMap(user, httpRequest.getSession(), httpRequest
									.getSession().getAttribute(AdminYhkCommonConstant.SESSION_LOGIN_VCODE).toString());
						}
					} else {
						adminYhkssoResultMap.setLoginUserMap(user, httpRequest.getSession(), httpRequest.getSession()
								.getAttribute(AdminYhkCommonConstant.SESSION_LOGIN_VCODE).toString());
					}
					// SSO結束
					// role
					user.setRole(adminRoleMapper.queryAdminRoleByUserId(user.getId()));
					httpRequest.getSession().setAttribute(AdminYhkCommonConstant.SESSION_SYS_USER, user);
					httpResponse.sendRedirect("/html/main.html");
					return;
				} else {
					loginresponse.setSuccess(false);
					loginresponse.setMsg("密码错误");
					write.println(JSON.toJSONString(loginresponse));
					write.flush();
					write.close();
					return;
				}

			} else {
				loginresponse.setSuccess(false);
				loginresponse.setMsg("没有找到该用户");
				write.println(JSON.toJSONString(loginresponse));
				write.flush();
				write.close();
				return;
			}
		} catch (Exception e) {
			write.flush();
			write.close();
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
