package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.CommonWriteService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.ProjectTemplateColaConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.app.IWriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private CommonWriteService commonWriteService;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleAdapterPath());

        //写vo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VO.getTempFileName())){
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().endsWith("VO") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"VO");
            classContentBean.setChildPackageName("vo");
            classContentBean.setClassSuffix("VO.java");
            writeClassFile(classContentBean);
        }

        //写vo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VO_DDD.getTempFileName())){
            classContentBean.setChildPackageName("adapter.vo");
            classContentBean.setClassSuffix("VO.java");
            writeRoute(classContentBean);
        }

        //写controller
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER.getTempFileName())){

            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().endsWith("Controller") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"Controller");
            classContentBean.setChildPackageName("controller");
            classContentBean.setClassSuffix("Controller.java");
            writeClassFile(classContentBean);
        }

        //写controller-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER_DDD.getTempFileName())){
            classContentBean.setChildPackageName("adapter.controller");
            classContentBean.setClassSuffix("Controller.java");
            writeRoute(classContentBean);
        }

        //写convert
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONVERT.getTempFileName())){
            writeClassFileV2(classContentBean);
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BASE_CONTROLLER.getTempFileName())){
            commonWriteService.writeBaseController(writeContentBean.getContent(),projectTemplateColaConfig.getModuleAdapterPath());
        }
    }

}
