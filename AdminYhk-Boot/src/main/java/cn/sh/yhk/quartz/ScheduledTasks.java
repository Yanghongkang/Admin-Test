package cn.sh.yhk.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
	
	protected static Logger logger=LoggerFactory.getLogger(ScheduledTasks.class); 
    //每1分钟执行一次
    //@Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron(){
    	logger.info("日志文件转移");
    }

    
}