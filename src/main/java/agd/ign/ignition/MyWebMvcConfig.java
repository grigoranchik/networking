package agd.ign.ignition;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author aillusions
 */
/*@Configuration
@EnableWebMvc
@ComponentScan*/
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

  /*  @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }*/

   /* @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ignition/").setViewName("forward:/static/html/index.html");
    }*/

}
