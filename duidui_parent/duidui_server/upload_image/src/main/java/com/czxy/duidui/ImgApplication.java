package com.czxy.duidui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 207798583@qq.com
 * @version 1.0
 * @date 2020/1/16
 * @infos
 */
@EnableEurekaClient
@SpringBootApplication
public class ImgApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImgApplication.class,args);
    }
}
