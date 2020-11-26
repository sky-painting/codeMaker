package com.lightsnail.app.user.crm.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.coderman.utils.bean.CglibConvertService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:应用启动入口
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:54
* @version v1.0
*/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan({"com.lightsnail.**","com.alibaba.nacos","com.coderman.**"})
public class Application {

    @Bean
    public CglibConvertService cglibConvertService(){
        return new CglibConvertService();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
