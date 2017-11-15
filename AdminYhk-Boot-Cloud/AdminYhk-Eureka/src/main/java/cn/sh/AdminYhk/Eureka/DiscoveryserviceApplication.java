package cn.sh.AdminYhk.Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @ClassName: DiscoveryserviceApplication
 * @Description: TODO* Eureka注册中心
 * @author AdminYHK
 * @date 2017年11月15日 上午10:35:40
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryserviceApplication.class, args);
	}
}
