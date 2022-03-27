package com.highio.shoping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.ArrayList;
import java.util.List;

@MapperScan("com.highio.shoping.mapper")
@SpringBootApplication
public class ShopingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopingApplication.class, args);
    }


}
