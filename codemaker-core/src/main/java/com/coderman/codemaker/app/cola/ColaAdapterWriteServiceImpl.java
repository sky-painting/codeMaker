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
@Component(value = "colaAdapterWriteService")
public class ColaAdapterWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        //写vo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VO.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().endsWith("VO") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"VO");
            classContentBean.setChildPackageName("vo");
            classContentBean.setClassSuffix("VO.java");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAdapterPath());

            writeClassFile(classContentBean);
        }

        //写vo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VO_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());

            classContentBean.setChildPackageName("adapter.vo");
            classContentBean.setClassSuffix("VO.java");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAdapterPath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写controller
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().endsWith("Controller") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"Controller");
            classContentBean.setChildPackageName("controller");
            classContentBean.setClassSuffix("Controller.java");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAdapterPath());
            writeClassFile(classContentBean);
        }

        //写controller-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());

            classContentBean.setChildPackageName("adapter.controller");
            classContentBean.setClassSuffix("Controller.java");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAdapterPath());
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
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleAdapterPath());
            writeClassFileV2(classContentBean);
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BASE_CONTROLLER.getTempFileName())){
            writeBaseController(writeContentBean.getContent());
        }

    }

    @Override
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast) {

    }

    @Override
    public void writeCommonContent(Map<String, Object> varMap, String fast) {

    }

    /**
     * 写BaseController文件
     *
     * @param content
     */
    public void writeBaseController(String content) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/controller";
        String fileName = "BaseController.java";
        filePath = projectTemplateColaConfig.getModuleAdapterPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
