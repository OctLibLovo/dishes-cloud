package com.etoak.config;

import cn.hutool.core.util.StrUtil;
import com.etoak.common.properties.ImageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
    @Autowired
    ImageProperties properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String prefix = properties.getPrefix().endsWith(StrUtil.SLASH) ? properties.getPrefix() + "**" : properties.getPrefix() + "/**";

        String location = properties.getLocation().endsWith(StrUtil.SLASH) ? "file:" + properties.getLocation() : "file:" + properties.getLocation() + "/";
        registry.addResourceHandler(prefix)
                .addResourceLocations(location);

    }
}
