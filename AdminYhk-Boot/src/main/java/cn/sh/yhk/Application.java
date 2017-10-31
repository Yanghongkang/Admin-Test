package cn.sh.yhk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ServletComponentScan
@Configuration
@EnableAutoConfiguration
//@EnableAdminServer
public class Application implements EmbeddedServletContainerCustomizer {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("asas");
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8888);
	}
	
}
