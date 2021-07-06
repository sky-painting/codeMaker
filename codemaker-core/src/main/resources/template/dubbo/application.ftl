package ${package}.core;


import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Description:应用启动入口
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@DubboComponentScan(basePackages = "${package}.core")
@EnableDubbo //开启Dubbo的注解支持
@SpringBootApplication(scanBasePackages = {"${package}"})
@MapperScan(basePackages = "${package}.common")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
