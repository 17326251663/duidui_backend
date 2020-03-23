package com.czxy.duidui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/17
 * @infos
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableFeignClients
public class MaterialApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaterialApplication.class,args);
    }
}
