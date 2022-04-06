package ${package};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
* @Description:应用启动入口
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@SpringBootApplication
@MapperScan(value = "${package}.infrast.dao")
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
