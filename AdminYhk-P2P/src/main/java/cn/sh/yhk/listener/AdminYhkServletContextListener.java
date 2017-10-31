package cn.sh.yhk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AdminYhkServletContextListener implements ServletContextListener {
	protected static Logger logger = LoggerFactory.getLogger(AdminYhkServletContextListener.class);



	public void contextInitialized(ServletContextEvent sce) {
		
		logger.info("ServletContex初始化");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
		logger.info("ServletContex销毁");
	}

}
