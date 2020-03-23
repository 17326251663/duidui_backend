package com.czxy.changgou3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author gukaipeng
 * @email gukaipeng@czrj.fun
 * @date 2020/3/22
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class CGZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGZuulApplication.class,args);
    }
}
