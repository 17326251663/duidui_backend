package com.czxy.duidui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/2/1
 * @infos
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class imageApplication {
    public static void main(String[] args) {
        SpringApplication.run(imageApplication.class,args);
    }
}
