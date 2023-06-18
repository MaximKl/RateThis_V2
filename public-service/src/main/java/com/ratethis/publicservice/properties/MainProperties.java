package com.ratethis.publicservice.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Component
@ConfigurationProperties(prefix = "products.main")
@Data
@Validated
public class MainProperties {

    private int pageSize = 0;
}
