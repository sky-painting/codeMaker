package com.tianhua.codemaker;

import com.coderman.codemaker.dberparse.ERPlantUMLParseService;
import com.coderman.codemaker.dberparse.ERPlantUMLParseServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * description: Main <br>
 * date: 2020/7/6 23:07 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@SpringBootApplication
@MapperScan(value = "com.tianhua.codemaker.dao")
@EnableConfigurationProperties
@ComponentScan({"com.tianhua.*"})
public class Main {

    /**
     * 将plantuml e-r图解析服务注入到springbean中
     * @return
     */
    @Bean
    public ERPlantUMLParseService getErplantUMLParseService(){
        return new ERPlantUMLParseServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
