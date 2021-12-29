package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.CommonWriteService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
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
@Component(value = "colaAppWriteService")
public class ColaAppWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private AppServiceConfig appServiceConfig;
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private CommonWriteService commonWriteService;


    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleAppPath());

        //写FacadeImpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE_IMPL.getTempFileName())){
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("facadeimpl") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"FacadeImpl");
            classContentBean.setChildPackageName("app.facadeimpl");
            writeRoute(classContentBean);
        }

        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            classContentBean.setChildPackageName("app.command");
            writeRoute(classContentBean);
        }
        //写app.exe
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EXE.getTempFileName())){
            classContentBean.setChildPackageName("app.executor");
            writeRoute(classContentBean);
        }

        //写convert
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONVERT.getTempFileName())){
            classContentBean.setClassPackageName(appServiceConfig.getPackage()+".app.convert");
            writeClassFileV2(classContentBean);
        }

        //写app.listener
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_LISTENER.getTempFileName())){
            classContentBean.setChildPackageName("app.listener");
            writeRoute(classContentBean);
        }

       /* //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())){
            commonWriteService.writeSpringApplicationContext(writeContentBean.getContent(),projectTemplateColaConfig.getModuleAppPath());
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName())){
            commonWriteService.writeAppEventPublisher(writeContentBean.getContent(),projectTemplateColaConfig.getModuleAppPath());
        }*/

        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }
    }
}
