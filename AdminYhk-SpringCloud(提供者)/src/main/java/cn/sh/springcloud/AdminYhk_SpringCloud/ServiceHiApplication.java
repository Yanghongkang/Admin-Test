package cn.sh.springcloud.AdminYhk_SpringCloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *http://localhost:8762/hi?name=%E6%9D%A8%E9%B8%BF%E5%BA%B7
 * @ClassName: EurekaserverApplication
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author AdminYHK(Yanghongkang)
 * @date 2017年6月15日 上午9:53:00
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication  {
	public static void main(String[] args) {
		SpringApplication.run(ServiceHiApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "hi " + name + ",i am from port:" + port;
	}
	@RequestMapping("/adminYhk")
	public String age(@RequestParam String age) {
		return "hi " + age + ",i am from port:" + port;
	}
}
