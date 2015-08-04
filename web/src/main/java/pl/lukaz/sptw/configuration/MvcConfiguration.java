package pl.lukaz.sptw.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lzenczuk 03/08/2015
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Because at this moment we don't have login controller we have specify view
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/jsonLogin").setViewName("jsonLogin");
        //registry.addViewController("/jsonLogout").setViewName("jsonLogout");
    }
}
