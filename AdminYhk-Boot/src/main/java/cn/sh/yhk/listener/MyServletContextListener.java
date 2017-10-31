package cn.sh.yhk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sh.yhk.dao.redis.RedisService;
import cn.sh.yhk.filter.SessionFilter;

@WebListener
public class MyServletContextListener implements ServletContextListener {
	protected static Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);



	public void contextInitialized(ServletContextEvent sce) {
		
		logger.info("ServletContex初始化");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
		logger.info("ServletContex销毁");
	}

}
