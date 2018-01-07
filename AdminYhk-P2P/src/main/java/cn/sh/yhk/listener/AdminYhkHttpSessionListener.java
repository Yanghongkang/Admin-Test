package cn.sh.yhk.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sh.yhk.commone.AdminYhkCommonConstant;
import cn.sh.yhk.commone.plugins.sso.AdminYhkSsoResultMap;
import cn.sh.yhk.p2p.model.admin.User;

@WebListener
public class AdminYhkHttpSessionListener implements HttpSessionListener {
	protected static Logger logger = LoggerFactory.getLogger(AdminYhkHttpSessionListener.class);

	@Autowired
	private AdminYhkSsoResultMap sooResultMap;
	
//	@Autowired
//	AdminYhkSessionContext adminYhkSessionContext;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// redisService.set("Server_Count_Num",
		// (Server_Count_Num++).toString());
		//adminYhkSessionContext.AddSession(se.getSession());
		logger.info("Session 被创建"+se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//adminYhkSessionContext.DelSession(se.getSession());

		User user = (User) se.getSession().getAttribute(AdminYhkCommonConstant.SESSION_SYS_USER);
		if (user != null) {
			String key = user.getUserNo() + user.getId() + user.getUserPwd().substring(10);
			sooResultMap.removeLoginUserMap(key);
			se.getSession().invalidate();
		}
		logger.info("Session 被销毁"+se.getSession().getId());

	}

}
