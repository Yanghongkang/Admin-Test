package cn.sh.yhk.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 設置welcome首頁
* @ClassName: DefaultView 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author AdminYHK(Yanghongkang)
* @date 2017年5月19日 上午5:08:48 
*
 */
@Configuration
public class DefaultView extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        //registry.addViewController( "/" ).setViewName( "forward:/login.html" );
        //registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    } 
}
