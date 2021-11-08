package com.coderman.infosys.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
* @Description:应用启动入口
* @Author:shenshuai
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.coderman.infosys.auth.**"})
public class Application {
  
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
