package com.coderman.codemaker.app.dubbo;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateDubboConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 *
 * 写api模块服务
 */
@Component(value = "dubboApiWriteFileService")
public class DubboApiWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateDubboConfig.getModuleApiPath());

        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DTO.getTempFileName())){
            classContentBean.setChildPackageName("api.dto");
            classContentBean.setClassSuffix("DTO.java");
            writeDTO(classContentBean);
        }

        //写api.dto-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DTO_DDD.getTempFileName())){
            classContentBean.setChildPackageName("api.dto");
            classContentBean.setClassSuffix("DTO.java");
            writeRoute(classContentBean);
        }
        //写facade
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE.getTempFileName())){
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("facade") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"Facade");
            classContentBean.setChildPackageName("api.facade");
            classContentBean.setClassSuffix("Facade.java");
            writeRoute(classContentBean);
        }
        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            classContentBean.setChildPackageName("domain.enums");
            writeRoute(classContentBean);
        }
    }

    /**
     * 写dto文件
     * @param classContentBean
     */
    public void writeDTO(ClassContentBean classContentBean) {
        String filePath = getFilePath(classContentBean.getChildPackageName(), classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param childPackageName 最后一级子包名称
     * @param humpClassName 驼峰式类名
     * @param classSuffix   文件后缀 egg：DTO.java
     * @return
     */
    private String getFilePath(String childPackageName, String humpClassName, String classSuffix) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        packagePath = Constant.JAVA + "/" + packagePath + "/" + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateDubboConfig.getModuleApiPath()  + packagePath + "/" + fileName;
    }


}
