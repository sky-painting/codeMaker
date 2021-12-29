package com.coderman.codemaker.app.springcloud;

import com.coderman.codemaker.app.CommonWriteService;
import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.*;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateSpringCloudConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.dberpicture.DBErPictureService;
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
 * date: 2021/11/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "sCFeignProviderWriteService")
public class SCFeignProviderWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateSpringCloudConfig projectTemplateSpringCloudConfig;


    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private DBErPictureService erPictureService;


    @Autowired
    private CommonWriteService commonWriteService;


    @Override
    public void writeContent(WriteContentBean writeContentBean) {

        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateSpringCloudConfig.getModuleFeginProviderPath());

        //写entity class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENTITY.getTempFileName())){
            classContentBean.setChildPackageName("entity");
            classContentBean.setClassSuffix("Entity.java");
            writeClassFile(classContentBean);
        }

        //写do class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DATA_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("infrast.dao.dataobject");
            classContentBean.setClassSuffix("DO.java");
            // writeDO(classContentBean);
            writeClassFile(classContentBean);
        }
        //写mapper class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER.getTempFileName())){
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
            classContentBean.setChildPackageName("vo");
            classContentBean.setClassSuffix("VO.java");
            writeClassFile(classContentBean);
        }

        //写vo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VO_DDD.getTempFileName())){
            classContentBean.setChildPackageName("adapter.vo");
            classContentBean.setClassSuffix("VO.java");
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
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            writeClassFile(classContentBean);
        }

        //写serviceImpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("service.impl");
            classContentBean.setClassSuffix("ServiceImpl.java");
            writeClassFile(classContentBean);
        }

        //写controller
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER.getTempFileName())){
            classContentBean.setChildPackageName("controller");
            classContentBean.setClassSuffix("Controller.java");
            writeClassFile(classContentBean);
        }

        //写controller-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONTROLLER_DDD.getTempFileName())){
            classContentBean.setChildPackageName("adapter.controller");
            classContentBean.setClassSuffix("Controller.java");
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
            commonWriteService.writeSpringApplicationContext(writeContentBean.getContent(),projectTemplateSpringCloudConfig.getModuleFeginProviderPath());
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BASE_CONTROLLER.getTempFileName())){
            writeBaseController(writeContentBean.getContent());
        }

        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APPLICATION.getTempFileName())){
            writeApplication(writeContentBean.getContent());
        }
        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName())){
            commonWriteService.writeAppEventPublisher(writeContentBean.getContent(),projectTemplateSpringCloudConfig.getModuleFeginProviderPath());
        }

        //写BaseEvent
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName())){
            commonWriteService.writeBaseEvent(writeContentBean.getContent(),projectTemplateSpringCloudConfig.getModuleFeginProviderPath());
        }


        //写model.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("bo");
            classContentBean.setClassSuffix("BO.java");
            writeClassFile(classContentBean);
        }
        //写domain.bo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            classContentBean.setChildPackageName("domain.bo");
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
            writeClassFileV2(classContentBean);
        }
        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            classContentBean.setChildPackageName("domain.enums");
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
            classContentBean.setChildPackageName("domain.valueobject");
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
            classContentBean.setChildPackageName("domain.event");
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
            classContentBean.setChildPackageName("domain.msgbody");
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
            classContentBean.setChildPackageName("domain.gataway");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.repository
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.REPOSITORY.getTempFileName())){
            classContentBean.setChildPackageName("domain.repository");
            writeRoute(classContentBean);
        }

        //写domain.gataway.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("gataway.impl");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }


        //写domain.repository.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.REPOSITORY_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("repositoryimpl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName()+".repositoryimpl");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.repositoryimpl");
                writeClassFileV2(classContentBean);
            }
        }


        //写infrast.acl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL.getTempFileName())){
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter");
            }
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
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl.impl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter.impl");
            }

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
            writeClassFileV2(classContentBean);
        }

        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            classContentBean.setChildPackageName("app.command");
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
            classContentBean.setChildPackageName("app.executor");
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
            classContentBean.setChildPackageName("domain.factory");

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
            classContentBean.setChildPackageName("app.listener");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写infrast.cache
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CACHE.getTempFileName())){
            classContentBean.setChildPackageName("infrast.cache");
            writeRoute(classContentBean);
        }


        //写infras.mq.consumer
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_CONSUMER.getTempFileName())){
            classContentBean.setChildPackageName("infrast.mq.consumer");
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
            classContentBean.setChildPackageName("infrast.mq.handler");
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
            classContentBean.setChildPackageName("infrast.mq.producer");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.producer");
                writeClassFileV2(classContentBean);
            }
        }

        //写infrast.dao.mapper
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER_DDD.getTempFileName())){
            classContentBean.setChildPackageName("infrast.dao.mapper");
            writeRoute(classContentBean);
        }

        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }
    }

    /**
     * 写mapper xml文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapperXml(String content, String humpClassName) {
        String fileName = humpClassName + "Mapper.xml";
        String filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath() + Constant.MAPPER + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath() + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath()  + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath()  + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath() + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath() + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath()  + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath()  + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath() + filePath + "/" + fileName;
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
        filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath()  + filePath + "/" + fileName;
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
        commonWriteService.writeSpringApplicationContext(SpringApplicationContextContent,projectTemplateSpringCloudConfig.getModuleFeginProviderPath());

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
        String filePath = projectTemplateSpringCloudConfig.getModuleFeginProviderPath() + Constant.ER_PICTURE + "/" + projectTemplateSpringCloudConfig.getDbName() + ".puml";
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
        return projectTemplateSpringCloudConfig.getModuleFeginProviderPath()  + packagePath + "/" + fileName;
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
