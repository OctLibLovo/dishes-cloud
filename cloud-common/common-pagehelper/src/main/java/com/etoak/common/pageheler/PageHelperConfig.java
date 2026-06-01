package com.etoak.common.pageheler;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 手动配置PageHelper
 */
@Configuration
public class PageHelperConfig {
    @Bean
    public PageInterceptor pageInterceptor(){
        Properties properties = new Properties();

        properties.setProperty("reasonable","true");

        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);

        return pageInterceptor;
    }
}
