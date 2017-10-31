package cn.sh.yhk;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class WindupApplication implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger log = Logger.getLogger(WindupApplication.class);

	private static boolean init = true;
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			String[] s = event.getApplicationContext().getBeanDefinitionNames();
			for (String string : s) {
				log.info("SpringBeanFactory :" + string);
			}
			log.info("SpringMVC Controller Url");
			log.info("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
			this.searchUrl(event);
		}
		/*
		 * if (init) { QueueReceived queue = new QueueReceived(); Thread t1 =
		 * new Thread(queue); t1.start(); init = false; }
		 */
	}

	final private void searchUrl(ContextRefreshedEvent event) {
		Map map = this.handlerMapping.getHandlerMethods();
		Iterator<?> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String uri = entry.getKey().toString();
			String url = uri.substring(2, uri.indexOf(".do")) + ".do";
			log.info("ActionUrl :" + url);
		}
	}

}
