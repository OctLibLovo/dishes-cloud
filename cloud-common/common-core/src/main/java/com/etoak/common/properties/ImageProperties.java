package com.etoak.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "image")
@Component
public class ImageProperties {

    private String prefix; // image.prefix

    private String location;

    private List<String> typeList;

}
