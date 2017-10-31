package cn.sh.yhk.commone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.sh.yhk.filter.SessionFilter;
/**
 * 
 * spring bean 初始化 预处理
* @ClassName: BeanPostPrcessorImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author AdminYHK(Yanghongkang)
* @date 2016年11月17日 上午9:36:37 
*
 */
@Component
public class AdminYhkBeanPostPrcessor implements BeanPostProcessor {
	
	 protected static Logger logger=LoggerFactory.getLogger(SessionFilter.class); 
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		logger.info(bean.getClass().getName());
		return bean;
	}

}
