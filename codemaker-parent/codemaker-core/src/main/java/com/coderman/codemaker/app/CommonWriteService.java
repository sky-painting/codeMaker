package com.coderman.codemaker.app;

import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class CommonWriteService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    /**
     * 写工具类文件
     * @param content
     * @param modulePath
     */
    public void writeSpringApplicationContext(String content,String modulePath) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/utils";
        String fileName = "SpringApplicationContext.java";
        filePath = modulePath  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写工具类文件
     * @param content
     * @param modulePath
     */
    public void writeAppEventPublisher(String content, String modulePath) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/utils";
        String fileName = "AppEventPublisher.java";
        filePath = modulePath  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写工具类文件
     *
     * @param content
     */
    public void writeBaseEvent(String content, String modulePath) {
        String packageName = appServiceConfig.getPackage()+".domain.event";
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath;
        String fileName = "BaseEvent.java";
        filePath = modulePath  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写BaseController文件
     *
     * @param content
     */
    public void writeBaseController(String content, String modulePath) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/controller";
        String fileName = "BaseController.java";
        filePath = modulePath  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
