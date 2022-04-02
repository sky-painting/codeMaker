package com.tianhua.codemaker.app;

import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component(value = "abstractWriteService")
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
     * 写路由
     * @param classContentBean
     */
    protected void writeRoute(ClassContentBean classContentBean){
        //走默认的包生成方式
        if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
            writeClassFile(classContentBean);
        }else {
            //走文档里的package包生成方式
            writeClassFileV2(classContentBean);
        }
    }

    /**
     * 写 api doc 文件
     *
     * @param content
     * @param moduleName
     */
    public void writeApiDoc(String content, String moduleName) {
        String apiDocOutPath = appServiceConfig.getApiDocOutPath();
        String filePath = apiDocOutPath + "/" + moduleName+".md";
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     * 写sql文件
     * @param classContentBean
     */
    public void writeSql(ClassContentBean classContentBean){
        String modulePath = classContentBean.getModulePath();
        String filePath = modulePath + "/sql-doc/" + appServiceConfig.getDbName() + ".sql";
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写项目配置文件
     * @param classContentBean
     */
    public void writeConfig(ClassContentBean classContentBean){
        String modulePath = classContentBean.getModulePath();
        String filePath = modulePath + "/src/main/resources/" + classContentBean.getHumpClassName();
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
