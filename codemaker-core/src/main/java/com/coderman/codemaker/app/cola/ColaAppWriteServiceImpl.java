package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateColaConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Description:
 * date: 2021/7/6
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaAppWriteService")
public class ColaAppWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private AppServiceConfig appServiceConfig;
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        //写FacadeImpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("facadeimpl") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"FacadeImpl");
            classContentBean.setChildPackageName("app.facadeimpl");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAppPath());
            writeClassFile(classContentBean);

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //lassContentBean.setClassPackageName(appServiceConfig.getPackage()+"/app/facadeimpl");
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }


        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setChildPackageName("app.command");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAppPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写app.exe
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EXE.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setChildPackageName("app.executor");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAppPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写convert
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONVERT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAppPath());
            classContentBean.setClassPackageName(appServiceConfig.getPackage()+".app.convert");

            writeClassFileV2(classContentBean);
        }

        //写app.listener
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_LISTENER.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("app.listener");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAppPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }



        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())){
            writeSpringApplicationContext(writeContentBean.getContent());
        }

    }

    @Override
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast) {

    }

    @Override
    public void writeCommonContent(Map<String, Object> varMap, String fast) {

    }


    /**
     * 写工具类文件
     *
     * @param content
     */
    public void writeSpringApplicationContext(String content) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/utils";
        String fileName = "SpringApplicationContext.java";
        filePath = projectTemplateColaConfig.getModuleAppPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
