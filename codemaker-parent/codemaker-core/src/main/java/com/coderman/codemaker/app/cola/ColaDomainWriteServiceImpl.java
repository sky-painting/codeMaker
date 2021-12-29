package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.CommonWriteService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.GlobalConstant;
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
@Component(value = "colaDomainWriteService")
public class ColaDomainWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private CommonWriteService commonWriteService;


    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleDomainPath());

        //写domain.bo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            classContentBean.setChildPackageName("domain.bo");
            writeRoute(classContentBean);
        }

        //写model.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("bo") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"BO");
            classContentBean.setChildPackageName("model.bo");
            classContentBean.setClassSuffix("BO.java");
            writeClassFile(classContentBean);
        }

        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            classContentBean.setChildPackageName("domain.enums");
            writeRoute(classContentBean);
        }

        //写domain.valueobject
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VALUE_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("domain.valueobject");
            writeRoute(classContentBean);
        }
        //写domain.msgbody
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MESSAGE_BODY.getTempFileName())){
            classContentBean.setChildPackageName("domain.msgbody");
            writeRoute(classContentBean);
        }

        //写domain.event
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EVENT_BODY.getTempFileName())){
            classContentBean.setChildPackageName("domain.event");
            writeRoute(classContentBean);
        }


        //写domain.gataway
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY.getTempFileName())){
            classContentBean.setChildPackageName("domain.gataway");
            writeRoute(classContentBean);
        }
        //写domain.repository
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.REPOSITORY.getTempFileName())){
            classContentBean.setChildPackageName("domain.repository");
            writeRoute(classContentBean);
        }
        //写domain.factory
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACTORY.getTempFileName())){
            classContentBean.setChildPackageName("domain.factory");
            writeRoute(classContentBean);
        }

        //写service
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE.getTempFileName())){
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("service") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"Service");
            writeClassFile(classContentBean);
        }

        //写BaseEvent
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName())){
            commonWriteService.writeBaseEvent(writeContentBean.getContent(),projectTemplateColaConfig.getModuleDomainPath());
        }

        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }

    }

}
