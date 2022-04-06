package com.tianhua.codemaker.app;

import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class CommonWriteService {

    @Autowired
    private AppServiceConfig appServiceConfig;
    /**
     * 写pom文件
     * @param classContentBean
     */
    public void writePom(ClassContentBean classContentBean){
        String modulePath = classContentBean.getModulePath();
        String filePath = modulePath + "/" + "pom.xml";
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 写路由
     * @param classContentBean
     */
    public void writeRoute(ClassContentBean classContentBean){
        //走默认的包生成方式
        if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
            writeClassFile(classContentBean);
        }else {
            //走文档里的package包生成方式
            writeClassFileV2(classContentBean);
        }
    }

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
        String filePath = getClassPackageFilePath(classContentBean);
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            log.error("error",e);
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


    /**
     * 写Mapper.xml文件
     * @param classContentBean
     */
    public void writeMapperXml(ClassContentBean classContentBean) {
        String filePath = classContentBean.getModulePath() +  Constant.MAPPER + "/" + classContentBean.getHumpClassName() + classContentBean.getClassSuffix();

        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写class文件
     * @param classContentBean
     */
    public void writeClassTestFile(ClassContentBean classContentBean) {

        String filePath = getTestFilePath( classContentBean.getHumpClassName(), classContentBean.getClassSuffix(), classContentBean.getModulePath());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写测试文件
     * @param humpClassName
     * @param classSuffix
     * @return
     */
    public String getTestFilePath(String humpClassName, String classSuffix, String modulePath) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        packagePath = Constant.TEST_JAVA + "/" + packagePath + ".test";
        String fileName = humpClassName+"Test" + classSuffix;
        return modulePath  + packagePath + "/" + fileName;
    }
}
