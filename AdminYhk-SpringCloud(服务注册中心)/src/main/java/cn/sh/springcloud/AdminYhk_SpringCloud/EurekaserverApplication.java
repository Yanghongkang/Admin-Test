package cn.sh.springcloud.AdminYhk_SpringCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 服务注册中心 
 *类似zookpeer Admin
 *http://localhost:8761/ 
* @ClassName: EurekaserverApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author AdminYHK(Yanghongkang)
* @date 2017年6月15日 上午9:53:00 
*
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}
}
