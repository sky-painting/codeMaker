package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateColaConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/7/6
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaClientWriteService")
public class ColaClientWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleClientPath());

        //写api.dto-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DTO_DDD.getTempFileName())){

            classContentBean.setChildPackageName("api.dto");
            classContentBean.setClassSuffix("DTO.java");
            writeRoute(classContentBean);
        }
        //写api.dto
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DTO.getTempFileName())){
            classContentBean.setChildPackageName("dto");
            classContentBean.setClassSuffix("DTO.java");
            writeDTO(classContentBean);
        }

        //写api.facade
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE.getTempFileName())){
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("facade") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"Facade");
            classContentBean.setChildPackageName("api.facade");
            classContentBean.setClassSuffix("Facade.java");
            writeRoute(classContentBean);
        }

        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            classContentBean.setChildPackageName("api.enums");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".api.enums");
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
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
        String packagePath = packageName.replace(".", "/") + "/api";
        packagePath = Constant.JAVA + "/" + packagePath + "/" + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateColaConfig.getModuleClientPath()  + packagePath + "/" + fileName;
    }

}
