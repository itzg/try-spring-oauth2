package me.itzg.tryspringoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Refer to https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/view-controller.html
        registry.addViewController("/app/**").setViewName("/app.html");
    }
}
