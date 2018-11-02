package com.demo.bolian.security.demo.web.config;

import com.demo.bolian.security.demo.web.filter.TimeFilter;
import com.demo.bolian.security.demo.web.intercetper.TimeIntercetper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeIntercetper timeIntercetper;

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeIntercetper);
    }

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);


        return registrationBean;
    }

}
