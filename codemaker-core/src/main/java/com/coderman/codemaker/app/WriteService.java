package com.coderman.codemaker.app;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.utils.Constant;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public abstract class WriteService {
    @Autowired
    private AppServiceConfig appServiceConfig;
    /**
     * 写class文件
     * @param classContentBean
     */
    public void writeClassFile(ClassContentBean classContentBean) {
        String filePath = getFilePath(classContentBean);
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param classContentBean
     * @return
     */
    private String getFilePath(ClassContentBean classContentBean) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/") ;
        packagePath = Constant.JAVA + "/" + packagePath + "/" + classContentBean.getChildPackageName();
        String fileName = classContentBean.getHumpClassName() + ".java";
        return classContentBean.getModulePath()  + packagePath + "/" + fileName;
    }


    /**
     * 写class文件
     * @param classContentBean
     */
    public void writeClassFileV2(ClassContentBean classContentBean) {
        log.info("classContentBean = "+ JSON.toJSONString(classContentBean));
        String filePath = getClassPackageFilePath(classContentBean);
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param classContentBean
     * @return
     */
    private String getClassPackageFilePath(ClassContentBean classContentBean) {
        String packagePath = classContentBean.getClassPackageName().replace(".", "/") ;
        packagePath = Constant.JAVA + "/" + packagePath;
        String fileName = classContentBean.getHumpClassName() + ".java";
        return classContentBean.getModulePath()  + packagePath + "/" + fileName;
    }
}
