package com.coderman.infosys.auth;


import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Description:应用启动入口
* @Author:shenshuai
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@DubboComponentScan(basePackages = "com.coderman.infosys.auth")
@EnableDubbo //开启Dubbo的注解支持
@SpringBootApplication(scanBasePackages = {"com.coderman.infosys.auth"})
@MapperScan(basePackages = "com.coderman.infosys.auth.common.infrast")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
