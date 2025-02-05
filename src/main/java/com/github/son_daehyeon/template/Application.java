package com.github.son_daehyeon.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.son_daehyeon.template.common.property.JwtProperty;
import com.github.son_daehyeon.template.common.property.RedisProperty;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperty.class, RedisProperty.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
