package cn.sh.springcloud.AdminYhk_SpringCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ribbon访问
 * @ClassName: ServiceRibbonApplication
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author AdminYHK(Yanghongkang)
 * @date 2017年6月15日 上午9:53:00
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRibbonApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

@Service
class HelloService {

	@Autowired
	RestTemplate restTemplate;

	public String hiService(String name) {
		return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
	}

}

@RestController
class HelloControler {

	@Autowired
	HelloService helloService;

	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name) {
		return helloService.hiService(name);
	}

}