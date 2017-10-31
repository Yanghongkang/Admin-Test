package cn.sh.yhk.commone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 統一異常處理
 * 
 * @ClassName: AdminYhkGlobalExceptionHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author AdminYHK(Yanghongkang)
 * @date 2017年7月3日 下午3:54:38
 *
 */
@ControllerAdvice
public class AdminYhkGlobalExceptionHandler {
	// @ExceptionHandler(value = Exception.class)
	protected static Logger logger = LoggerFactory.getLogger(AdminYhkBeanPostPrcessor.class);
}
