package com.coderman.codemaker.listener;

import com.coderman.codemaker.config.DefaultPackageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Description:项目启动监听器，做数据初始化使用
 * date: 2020/9/29 10:21 上午
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Order(value = 1000)
@Component
public class RefrashConfigListener implements ApplicationListener<ApplicationStartedEvent> {
    private Logger logger = LoggerFactory.getLogger(RefrashConfigListener.class);

    @Autowired
    private DefaultPackageConfig defaultPackageConfig;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            logger.info("应用启动成功,准备刷新初始化适配依赖配置.......................");
            defaultPackageConfig.addAdapterPackages();
            logger.info("应用启动成功,刷新初始化适配依赖配置完成.......................");

        } catch (Exception e) {
            logger.error("刷新初始化适配依赖配置失败",e);
        }


    }
}
