package cn.sh.yhk.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

import cn.sh.yhk.dao.redis.RedisService;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Autowired
	private RedisService redisService;
	private static Long Server_Count_Num = 0L;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//redisService.set("Server_Count_Num", (Server_Count_Num++).toString());
		System.out.println("Session 被创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//redisService.set("Server_Count_Num",  (Server_Count_Num--).toString());
		System.out.println("Session 被销毁");
	}

}
