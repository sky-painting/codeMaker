package com.coderman.codemaker.app.springboot;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateSpringbootConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.DBErPictureService;
import com.coderman.codemaker.service.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import com.coderman.codemaker.utils.FreemarkerUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "springBootWriteService")
public class SpringBootWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateSpringbootConfig projectTemplateConfig;


    @Autowired
    private AppServiceConfig appServiceConfig;
    @Autowired
    private DBErPictureService erPictureService;


    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        //写api.dto-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DTO_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());

            classContentBean.setChildPackageName("api.dto");
            classContentBean.setClassSuffix("DTO.java");
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写api.dto
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DTO.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("dto");
            classContentBean.setClassSuffix("DTO.java");
            writeDTO(classContentBean);
        }


        //写entity class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENTITY.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("entity");
            classContentBean.setClassSuffix("Entity.java");
            writeClassFile(classContentBean);
        }

        //写do class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DATA_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("dao.dataobject");
            classContentBean.setClassSuffix("DO.java");
            writeDO(classContentBean);
        }
        //写mapper class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("dao.mapper");
            classContentBean.setClassSuffix("Mapper.java");
            writeClassFile(classContentBean);
        }

        //写mapper.xml
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER_XML.getTempFileName())){
            writeMapperXml(writeContentBean.getContent(), writeContentBean.getHumpClassName());
        }

        //写vo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VO.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("vo");
            classContentBean.setClassSuffix("VO.java");
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
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }


        //写service
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            writeClassFile(classContentBean);
        }

        //写serviceImpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("service.impl");
            classContentBean.setClassSuffix("ServiceImpl.java");
            writeClassFile(classContentBean);
        }

        //写controller
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER.getTempFileName())){

            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("controller");
            classContentBean.setClassSuffix("Controller.java");
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
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写test
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.TEST.getTempFileName())){
            writeTest(writeContentBean.getContent(), writeContentBean.getHumpClassName());
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())){
            writeSpringApplicationContext(writeContentBean.getContent());
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BASE_CONTROLLER.getTempFileName())){
            writeBaseController(writeContentBean.getContent());
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APPLICATION.getTempFileName())){
            writeApplication(writeContentBean.getContent());
        }



        //写model.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("model.bo");
            classContentBean.setClassSuffix("BO.java");
            writeClassFile(classContentBean);
        }
        //写domain.bo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.bo");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
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
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            writeClassFileV2(classContentBean);
        }
        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.enums");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.valueobject
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VALUE_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.valueobject");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.event
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EVENT_BODY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.event");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }

        }


        //写domain.msgbody
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MESSAGE_BODY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.msgbody");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }

        }

        //写domain.gataway
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.gataway");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.gataway.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("gataway.impl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写infrast.acl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter");
            }
            classContentBean.setClassSuffix("");
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写infrast.acl.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl.impl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter.impl");
            }
            classContentBean.setClassSuffix("");
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写acl.param
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_PARAM.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            writeClassFileV2(classContentBean);
        }

        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setChildPackageName("app.command");
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

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
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.factory
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACTORY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.factory");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }


        //写app.listener
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_LISTENER.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("app.listener");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }



        //写infras.mq.consumer
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_CONSUMER.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("infrast.mq.consumer");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.consumer");

                writeClassFileV2(classContentBean);
            }
        }

        //写infras.mq.handler
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_HANDLER.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("infrast.mq.handler");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.handler");
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写infras.mq.producer
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_PRODUCER.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("infrast.mq.producer");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.producer");
                writeClassFileV2(classContentBean);
            }
        }


    }

    @Override
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast) {
        writeAll(humpClassName, varMap, fast);
    }

    @Override
    public void writeCommonContent(Map<String, Object> varMap, String fast) {
        writeCommon(varMap, fast);
    }


    /**
     * 写mapper xml文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapperXml(String content, String humpClassName) {
        String fileName = humpClassName + "Mapper.xml";
        String filePath = projectTemplateConfig.getOutPath() + Constant.MAPPER + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeEntity(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/entity";
        String fileName = humpClassName + "Entity.java";
        filePath = projectTemplateConfig.getOutPath() + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeVO(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/vo";
        String fileName = humpClassName + "VO.java";
        filePath = projectTemplateConfig.getOutPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写mapper class文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapper(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/mapper";
        String fileName = humpClassName + "Mapper.java";
        filePath = projectTemplateConfig.getOutPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写service文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeService(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/service";
        String fileName = humpClassName + "Service.java";
        filePath = projectTemplateConfig.getOutPath() + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写serviceImpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeServiceImpl(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/service/impl";
        String fileName = humpClassName + "ServiceImpl.java";
        filePath = projectTemplateConfig.getOutPath() + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        filePath = projectTemplateConfig.getOutPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        filePath = projectTemplateConfig.getOutPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写测试文件
     *
     * @param content
     */
    public void writeTest(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.TEST_JAVA + "/" + packagePath + "/service/test";
        String fileName = humpClassName + "ServiceTest.java";
        filePath = projectTemplateConfig.getOutPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeApplication(String content) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "";
        String fileName = "Application.java";
        filePath = projectTemplateConfig.getOutPath() + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写controller文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeController(String content, String humpClassName) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        String filePath = Constant.JAVA + "/" + packagePath + "/controller";
        String fileName = humpClassName + "Controller.java";
        filePath = projectTemplateConfig.getOutPath()  + filePath + "/" + fileName;
        try {
            FileUtils.write(new File(filePath), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    public void writeAll(String humpClassName, Map<String, Object> varMap, String fast) {
        String entityContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.ENTITY.getTempFileName(), varMap);
        this.writeEntity(entityContent, humpClassName);

        String serviceContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.SERVICE.getTempFileName(), varMap);
        this.writeService(serviceContent, humpClassName);

        String serviceImplContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.SERVICE_IMPL.getTempFileName(), varMap);
        this.writeServiceImpl(serviceImplContent, humpClassName);

        String mapperXmlContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.MAPPER_XML.getTempFileName(), varMap);
        this.writeMapperXml(mapperXmlContent, humpClassName);

        String mapperContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.MAPPER.getTempFileName(), varMap);
        this.writeMapper(mapperContent, humpClassName);

        String controllerContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.CONTROLLER.getTempFileName(), varMap);
        this.writeController(controllerContent, humpClassName);

        String voContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.VO.getTempFileName(), varMap);
        this.writeVO(voContent, humpClassName);

        String testContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.TEST.getTempFileName(), varMap);
        this.writeTest(testContent, humpClassName);
    }




    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    public void writeCommon(Map<String, Object> varMap, String fast) {
        String baseControllerContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.BASE_CONTROLLER.getTempFileName(), varMap);
        this.writeBaseController(baseControllerContent);

        String SpringApplicationContextContent = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(), varMap);
        this.writeSpringApplicationContext(SpringApplicationContextContent);

        String application = FreemarkerUtils.parseTpl(fast+ TemplateFileEnum.APPLICATION.getTempFileName(), varMap);
        this.writeApplication(application);

    }

    /**
     * 整合e-r图生成工具
     *
     * @param tableBeanMap
     * @param columnBeanListMap
     */
    public void writeERPicture(Map<String, TableBean> tableBeanMap, Map<String, List<ColumnBean>> columnBeanListMap) {
        String filePath = projectTemplateConfig.getOutPath() + Constant.ER_PICTURE + "/" + projectTemplateConfig.getDbName() + ".puml";
        List<com.coderman.codemaker.dbergenerate.bean.TableBean> tableBeanList = new ArrayList<>();
        tableBeanMap.forEach((k, v) -> {
            com.coderman.codemaker.dbergenerate.bean.TableBean tableBean = new com.coderman.codemaker.dbergenerate.bean.TableBean();
            tableBean.setTableComment(v.getTableComment());
            tableBean.setTableName(v.getTableName());
            List<ColumnBean> columnBeanList = columnBeanListMap.get(k);
            List<com.coderman.codemaker.dbergenerate.bean.ColumnBean> columnBeanList1 = new ArrayList<>();
            columnBeanList.forEach(columnBean -> {
                com.coderman.codemaker.dbergenerate.bean.ColumnBean columnBean1 = new com.coderman.codemaker.dbergenerate.bean.ColumnBean();
                columnBean1.setColumnComment(columnBean.getColumnComment());
                columnBean1.setColumnKey(columnBean.getColumnKey());
                columnBean1.setColumnName(columnBean.getColumnName());
                columnBean1.setTableName(columnBean.getTableName());
                columnBean1.setColumnType(columnBean.getColumnType());
                columnBean1.setDataType(columnBean.getDataType());
                columnBeanList1.add(columnBean1);
            });
            tableBean.setColumnBeanList(columnBeanList1);
            tableBeanList.add(tableBean);
        });
        erPictureService.getErPicture(filePath, tableBeanList);
    }


    /**
     * 写class文件
     * @param classContentBean
     */
    public void writeClassFile(ClassContentBean classContentBean) {
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
        String packagePath = packageName.replace(".", "/") ;
        packagePath = Constant.JAVA + "/" + packagePath + "/" + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateConfig.getOutPath()  + packagePath + "/" + fileName;
    }

    /**
     * 写DO文件
     * @param classContentBean
     */
    public void writeDO(ClassContentBean classContentBean) {
        String filePath = getFilePath(classContentBean.getChildPackageName(), classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
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
}
