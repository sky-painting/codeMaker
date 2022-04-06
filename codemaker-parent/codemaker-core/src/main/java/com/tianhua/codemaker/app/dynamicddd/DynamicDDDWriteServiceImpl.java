package com.tianhua.codemaker.app.dynamicddd;

import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.ProjectTemplateDynamicDDDConfig;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.app.IWriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "dynamicDDDWriteServiceImpl")
public class DynamicDDDWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateDynamicDDDConfig projectTemplateDynamicDDDConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateDynamicDDDConfig.getOutPath());

        //写domain.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            classContentBean.setChildPackageName("domain.bo");
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
        //写domain.gataway.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("domain.gataway.impl");
            writeRoute(classContentBean);
        }
        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            classContentBean.setChildPackageName("domain.enums");
            writeRoute(classContentBean);
        }

        //写domain.factory
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACTORY.getTempFileName())){
            classContentBean.setChildPackageName("domain.factory");
            writeRoute(classContentBean);
        }

        //写infrast.acl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL.getTempFileName())){
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter");
            }
            writeRoute(classContentBean);
        }

        //写infrast.acl.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_IMPL.getTempFileName())){
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl.impl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter.impl");
            }
            writeRoute(classContentBean);
        }

        //写acl.param
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_PARAM.getTempFileName())){
            writeClassFileV2(classContentBean);
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
    }
}
