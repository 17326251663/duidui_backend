package com.czxy.changgou3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/3/22
 */
@SpringBootApplication
@EnableEurekaServer
public class CGEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGEurekaApplication.class,args);
    }
}
