package com.by.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/tolistpage").setViewName("user/list");
        registry.addViewController("/role/tolistpage").setViewName("role/list");
        registry.addViewController("/permission/tolistpage").setViewName("permission/list");
    }
}
