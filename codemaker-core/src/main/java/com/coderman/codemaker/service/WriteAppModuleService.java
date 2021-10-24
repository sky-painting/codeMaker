package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.config.AppServiceConfig;

import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 新版本的应用模块代码写服务，屏蔽底层写文件的细节，通过app和module隔离api和底层实现
 * 做到一套api支持多种应用框架风格的代码生成。
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

@Component
public class WriteAppModuleService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private DBErPictureService erPictureService;

    @Autowired
    private FreemarkerService freemarkerService;

    @Autowired
    private WriteDynamicDDDModuleService writeDynamicDDDModuleService;

    /**
     * 写mapper xml文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapperXml(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MAPPER_XML.getTempFileName());
        if (writeFileService == null) {
            return;
        }

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.MAPPER_XML.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写entity文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeEntity(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ENTITY.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.ENTITY.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写do文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeDO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DATA_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.DATA_OBJECT.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写VO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeVO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.VO.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.VO.getTempFileName())
                .humpClassName(humpClassName).build();

        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写DTO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeDTO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DTO.getTempFileName());
        if (writeFileService == null) {
            return;
        }

        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.DTO.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写BO文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeBO(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写BO文件
     * @param writeContentBean
     */
    public void writeBO(WriteContentBean writeContentBean) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写BO文件
     * @param classBeanList
     */
    public void writeBO(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeBO(classBeanList,writeFileService,null);
    }

    /**
     * 写msgbody文件
     * @param classBeanList
     */
    public void writeMsgBody(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MESSAGE_BODY.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeMsgBody(classBeanList,writeFileService,null);
    }

    /**
     * 写event文件
     * @param classBeanList
     */
    public void writeDomainEvent(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.EVENT_BODY.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDomainEvent(classBeanList,writeFileService,null);
    }

    /**
     * 写domaingataway文件
     * @param classBeanList
     */
    public void writeDomainGataway(List<InterfaceBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.GATAWAY.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeGataWay(classBeanList,writeFileService,null);
    }
    /**
     * 写domaingatawayimpl文件
     * @param gatawayImplBeanList
     */
    public void writeGatawayImpl(List<ClassBean> gatawayImplBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.GATAWAY_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeGatawayImpl(gatawayImplBeanList,writeFileService,null);
    }

    /**
     * 写domainrepositoryimpl文件
     * @param repositoryImplBeanList
     */
    public void writeRepositoryImpl(List<ClassBean> repositoryImplBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.REPOSITORY_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeRepositoryImpl(repositoryImplBeanList,writeFileService,null);
    }

    /**
     * 写command文件
     * @param classBeanList
     */
    public void writeCommand(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CMD.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAppCommand(classBeanList,writeFileService,null);
    }

    /**
     * 写factory文件
     * @param classBeanList
     */
    public void writeFactory(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACTORY.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeFactory(classBeanList,writeFileService,null);
    }

    /**
     * 写applistener文件
     * @param classBeanList
     */
    public void writeAppListener(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MQ_LISTENER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAppListner(classBeanList,writeFileService,null);
    }

    /**
     * 写mqproducer文件
     * @param classBeanList
     */
    public void writeMqProducer(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MQ_PRODUCER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeMqProducer(classBeanList,writeFileService,null);
    }

    /**
     * 写mqconsumer文件
     * @param classBeanList
     */
    public void writeMqConsumer(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MQ_CONSUMER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeMqConsumer(classBeanList,writeFileService,null);
    }

    /**
     * 写mqhandler文件
     * @param classBeanList
     */
    public void writeMqHandler(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MQ_HANDLER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeMqHandler(classBeanList,writeFileService,null);
    }



    /**
     * 写AppExeImpl文件
     * @param classBeanList
     */
    public void writeAppExeImpl(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CMD.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAppExeImpl(classBeanList,writeFileService,null);
    }

    /**
     * 写AppExeInterface文件
     * @param interfaceBeanList
     */
    public void writeAppExeInterface(List<InterfaceBean> interfaceBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.EXE.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAppExeInterface(interfaceBeanList,writeFileService,null);
    }


    /**
     * 写dtoboconvert文件
     * @param dtoboConvertBeanList
     */
    public void writeDTOBOConvert(List<InterfaceBean> dtoboConvertBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DTOBO_CONVERT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDtoBOConvert(dtoboConvertBeanList,writeFileService);
    }

    /**
     * 写voboconvert文件
     * @param dtoboConvertBeanList
     */
    public void writeVOBOConvert(List<InterfaceBean> dtoboConvertBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.VOBO_CONVERT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeVoBOConvert(dtoboConvertBeanList,writeFileService);
    }

    /**
     * 写doboconvert文件
     * @param doboConvertBeanList
     */
    public void writeDOBOConvert(List<InterfaceBean> doboConvertBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DOBO_CONVERT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDoBOConvert(doboConvertBeanList,writeFileService);
    }


    /**
     * 写acl.param文件
     * @param classBeanList
     */
    public void writeAclInterfaceParam(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ACL_PARAM.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeACLParam(classBeanList,writeFileService,null);
    }

    /**
     * 写AppExeInterface文件
     * @param interfaceBeanList
     */
    public void writeAclInterface(List<InterfaceBean> interfaceBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ACL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAclInterface(interfaceBeanList,writeFileService,null);
    }


    /**
     * 写InfrastAclImpl文件
     * @param classBeanList
     */
    public void writeAclInterfaceImpl(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ACL_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAclInterfaceImpl(classBeanList,writeFileService,null);
    }

    /**
     * 写valueobject文件
     * @param classBeanList
     */
    public void writeValueObject(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.VALUE_OBJECT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeValueObject(classBeanList,writeFileService,null);
    }

    /**
     * 写enum文件
     * @param classBeanList
     */
    public void writeEnum(List<EnumBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.ENUM.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeEnum(classBeanList,writeFileService,null);
    }


    /**
     * 写apienum文件
     * @param classBeanList
     */
    public void writeAPIEnum(List<EnumBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.API_ENUM.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeEnum(classBeanList,writeFileService,null);
    }

    /**
     * 写DTO文件
     * @param classBeanList
     */
    public void writeDTO(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.DTO.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDTO(classBeanList,writeFileService);
    }

    /**
     * 写VO文件
     * @param classBeanList
     */
    public void writeVO(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.VO.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeVO(classBeanList,writeFileService);
    }

    /**
     * 写controller文件
     * @param classBeanList
     */
    public void writeController(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CONTROLLER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeController(classBeanList,writeFileService);
    }

    /**
     * 写facade文件
     * @param classBeanList
     */
    public void writeFacade(List<InterfaceBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE_DDD.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeFacade(classBeanList,writeFileService);
    }

    /**
     * 写facadeimpl文件
     * @param classBeanList
     */
    public void writeFacadeImpl(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE_IMPL_DDD.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeFacadeImpl(classBeanList,writeFileService);
    }

    /**
     * 写mapper class文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeMapper(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MAPPER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.MAPPER.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写service文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeService(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.SERVICE.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写facade文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeFacade(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.FACADE.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写facadeimpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeFacadeImpl(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.FACADE_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.FACADE_IMPL.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写serviceImpl文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeServiceImpl(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SERVICE_IMPL.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.SERVICE_IMPL.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写BaseController文件
     *
     * @param content
     */
    public void writeBaseController(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.BASE_CONTROLLER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.BASE_CONTROLLER.getTempFileName())
                .humpClassName("").build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写工具类文件
     *
     * @param content
     */
    public void writeSpringApplicationContext(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())
                .humpClassName("SpringApplicationContext.java").build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写测试文件
     *
     * @param content
     */
    public void writeTest(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.TEST.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.TEST.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeApplication(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.APPLICATION.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.APPLICATION.getTempFileName())
                .humpClassName("Application.java").build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写应用启动类
     *
     * @param content
     */
    public void writeFacadeAop(String content) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.APPLICATION.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.FACADE_AOP.getTempFileName())
                .humpClassName("FacadeServiceAop.java").build();
        writeFileService.writeContent(writeContentBean);
    }


    /**
     * 写controller文件
     *
     * @param content
     * @param humpClassName
     */
    public void writeController(String content, String humpClassName) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CONTROLLER.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        WriteContentBean writeContentBean = WriteContentBean.builder().content(content)
                .templateName(TemplateFileEnum.CONTROLLER.getTempFileName())
                .humpClassName(humpClassName).build();
        writeFileService.writeContent(writeContentBean);
    }

    /**
     * 一次性生成单表需要的所有模块代码
     *
     * @param humpClassName
     * @param varMap
     */
    public void writeAll(String humpClassName, Map<String, Object> varMap, String fast) {
        if (StringUtils.isEmpty(fast)) {
            fast = "/" + appServiceConfig.getApplicationType() + "/";
        }
        String entityContent = freemarkerService.parseTpl(fast + TemplateFileEnum.ENTITY.getTempFileName(), varMap);
        this.writeEntity(entityContent, humpClassName);

        String serviceContent = freemarkerService.parseTpl(fast + TemplateFileEnum.SERVICE.getTempFileName(), varMap);
        this.writeService(serviceContent, humpClassName);

        String serviceImplContent = freemarkerService.parseTpl(fast + TemplateFileEnum.SERVICE_IMPL.getTempFileName(), varMap);
        this.writeServiceImpl(serviceImplContent, humpClassName);

        String mapperXmlContent = freemarkerService.parseTpl(fast + TemplateFileEnum.MAPPER_XML.getTempFileName(), varMap);
        this.writeMapperXml(mapperXmlContent, humpClassName);

        String mapperContent = freemarkerService.parseTpl(fast + TemplateFileEnum.MAPPER.getTempFileName(), varMap);
        this.writeMapper(mapperContent, humpClassName);

        String controllerContent = freemarkerService.parseTpl(fast + TemplateFileEnum.CONTROLLER.getTempFileName(), varMap);
        this.writeController(controllerContent, humpClassName);

        String voContent = freemarkerService.parseTpl(fast + TemplateFileEnum.VO.getTempFileName(), varMap);
        this.writeVO(voContent, humpClassName);

        String testContent = freemarkerService.parseTpl(fast + TemplateFileEnum.TEST.getTempFileName(), varMap);
        this.writeTest(testContent, humpClassName);

        String boContent = freemarkerService.parseTpl(fast + TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
        this.writeBO(boContent, humpClassName);

        String dtoContent = freemarkerService.parseTpl(fast + TemplateFileEnum.DTO.getTempFileName(), varMap);
        this.writeDTO(dtoContent, humpClassName);


        String facadeContent = freemarkerService.parseTpl(fast + TemplateFileEnum.FACADE.getTempFileName(), varMap);
        this.writeFacade(facadeContent, humpClassName);


        String facadeImplContent = freemarkerService.parseTpl(fast + TemplateFileEnum.FACADE_IMPL.getTempFileName(), varMap);
        this.writeFacadeImpl(facadeImplContent, humpClassName);

        String doContent = freemarkerService.parseTpl(fast + TemplateFileEnum.DATA_OBJECT.getTempFileName(), varMap);
        this.writeDO(doContent, humpClassName);


    }

    /**
     * 一次性生成所有表的模块代码，同时解析plantUML的类图结合起来
     * @param allMetaDataMap
     */
    public void writeAllWithDDD(Map<String,Object> allMetaDataMap){
        List<ClassBean> boClassBeanList = (List<ClassBean>)allMetaDataMap.get("domainbo");
        this.writeBO(boClassBeanList);

        List<ClassBean> valueObjectBeanList = (List<ClassBean>)allMetaDataMap.get("domainvalueobject");
        List<EnumBean> enumBeanList = (List<EnumBean>)allMetaDataMap.get("valueobjectenum");
        this.writeValueObject(valueObjectBeanList);
        this.writeEnum(enumBeanList);



        List<EnumBean> apiEnumBeanList = (List<EnumBean>)allMetaDataMap.get("apienum");
        this.writeAPIEnum(apiEnumBeanList);




        List<ClassBean> msgClassBeanList = (List<ClassBean>)allMetaDataMap.get("domainmsg");
        this.writeMsgBody(msgClassBeanList);


        List<ClassBean> eventClassBeanList = (List<ClassBean>)allMetaDataMap.get("domainevent");
        this.writeDomainEvent(eventClassBeanList);


        List<InterfaceBean> gatawayBeanList = (List<InterfaceBean>)allMetaDataMap.get("gataway");
        List<InterfaceBean> repositoryBeanList = (List<InterfaceBean>)allMetaDataMap.get("repository");
        gatawayBeanList.addAll(repositoryBeanList);
        this.writeDomainGataway(gatawayBeanList);

        List<ClassBean> gatawayImplBeanList = (List<ClassBean>)allMetaDataMap.get("gatawayimpl");
        this.writeGatawayImpl(gatawayImplBeanList);

        List<ClassBean> repositoryImplBeanList = (List<ClassBean>)allMetaDataMap.get("repositoryimpl");
        this.writeRepositoryImpl(repositoryImplBeanList);



        List<InterfaceBean> aclBeanList = (List<InterfaceBean>)allMetaDataMap.get("infrastacl");
        List<ClassBean> aclParamBeanList = (List<ClassBean>)allMetaDataMap.get("infrastaclparam");
        List<ClassBean> aclImplBeanList = (List<ClassBean>)allMetaDataMap.get("infrastaclimpl");

        this.writeAclInterface(aclBeanList);
        this.writeAclInterfaceParam(aclParamBeanList);
        this.writeAclInterfaceImpl(aclImplBeanList);



        List<ClassBean> commandBeanList = (List<ClassBean>)allMetaDataMap.get("cmd");
        this.writeCommand(commandBeanList);

        List<ClassBean> exeBeanList = (List<ClassBean>)allMetaDataMap.get("exeClass");
        List<InterfaceBean> exeInterfaceBeanList = (List<InterfaceBean>)allMetaDataMap.get("exeInterface");
        this.writeAppExeImpl(exeBeanList);
        this.writeAppExeInterface(exeInterfaceBeanList);


        List<ClassBean> factoryBeanList = (List<ClassBean>)allMetaDataMap.get("domainfactory");
        this.writeFactory(factoryBeanList);

        List<ClassBean> appListenerBeanList = (List<ClassBean>)allMetaDataMap.get("applistener");
        this.writeAppListener(appListenerBeanList);

        List<ClassBean> mqProducerBeanList = (List<ClassBean>)allMetaDataMap.get("mqproducer");
        this.writeMqProducer(mqProducerBeanList);

        List<ClassBean> mqConsumerBeanList = (List<ClassBean>)allMetaDataMap.get("mqconsumer");
        this.writeMqConsumer(mqConsumerBeanList);

        List<ClassBean> mqHandlerBeanList = (List<ClassBean>)allMetaDataMap.get("mqhandler");
        this.writeMqHandler(mqHandlerBeanList);






        List<ClassBean> voClassBeanList = (List<ClassBean>)allMetaDataMap.get("adaptervo");
        this.writeVO(voClassBeanList);

        List<ClassBean> classBeanList = (List<ClassBean>)allMetaDataMap.get("controller");
        this.writeController(classBeanList);


        List<InterfaceBean> derivefacadeInterfaceBeanList = (List<InterfaceBean>)allMetaDataMap.get("derivefacade");
        this.writeFacade(derivefacadeInterfaceBeanList);

        List<ClassBean> derivefacadeimplclassBeanList = (List<ClassBean>)allMetaDataMap.get("derivefacadeimpl");
        this.writeFacadeImpl(derivefacadeimplclassBeanList);

        List<ClassBean> dtoClassBeanList = (List<ClassBean>)allMetaDataMap.get("derivedto");
        this.writeDTO(dtoClassBeanList);

        List<InterfaceBean> dtoboconvertBeanList = (List<InterfaceBean>)allMetaDataMap.get("dtoboconvert");
        this.writeDTOBOConvert(dtoboconvertBeanList);

        List<InterfaceBean> voboconvertBeanList = (List<InterfaceBean>)allMetaDataMap.get("voboconvert");
        this.writeVOBOConvert(voboconvertBeanList);

        List<InterfaceBean> doboconvertBeanList = (List<InterfaceBean>)allMetaDataMap.get("doboconvert");
        this.writeDOBOConvert(doboconvertBeanList);

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)allMetaDataMap.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)allMetaDataMap.get("columns");
        Map<String,Object> varMap = new HashMap<>();

        tableBeanMap.forEach((k,v)->{
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", allMetaDataMap.get("package"));
            varMap.put("author", allMetaDataMap.get("author"));
            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.DATA_OBJECT.getTempFileName(),varMap);
            this.writeDO(templateContent,v.getHumpClassName());

            templateContent = freemarkerService.parseTpl(TemplateFileEnum.MAPPER.getTempFileName(),varMap);
            this.writeMapper(templateContent,v.getHumpClassName());

            templateContent = freemarkerService.parseTpl(TemplateFileEnum.MAPPER_XML.getTempFileName(),varMap);
            this.writeMapperXml(templateContent,v.getHumpClassName());

            templateContent = freemarkerService.parseTpl(TemplateFileEnum.TEST.getTempFileName(),varMap);
            this.writeTest(templateContent,v.getHumpClassName());
        });
        //写公共服务类
        this.writeCommon(varMap,"");
        //渲染e-r图
        this.writeERPicture(tableBeanMap,columnBeanListMap);

    }

    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    public void writeCommon(Map<String, Object> varMap, String fast) {


        String baseControllerContent = freemarkerService.parseTpl(fast + TemplateFileEnum.BASE_CONTROLLER.getTempFileName(), varMap);
        this.writeBaseController(baseControllerContent);

        String SpringApplicationContextContent = freemarkerService.parseTpl(fast + TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(), varMap);
        this.writeSpringApplicationContext(SpringApplicationContextContent);

        String application = freemarkerService.parseTpl(fast + TemplateFileEnum.APPLICATION.getTempFileName(), varMap);
        this.writeApplication(application);

        String facadeAop = freemarkerService.parseTpl(fast + TemplateFileEnum.FACADE_AOP.getTempFileName(), varMap);
        this.writeFacadeAop(facadeAop);
    }

    /**
     * 整合e-r图生成工具
     *
     * @param tableBeanMap
     * @param columnBeanListMap
     */
    public void writeERPicture(Map<String, TableBean> tableBeanMap, Map<String, List<ColumnBean>> columnBeanListMap) {
        String filePath = appServiceConfig.getErPictureOutPath() + Constant.ER_PICTURE + "/" + appServiceConfig.getDbName() + ".puml";
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
     * 写dynamicddd模块代码生成
     *
     * @param dynamicDDDMap
     */
    public void writeDynamicDDD(Map<String, Object> dynamicDDDMap) {

        IWriteFileService writeFileService = appServiceConfig.getDynamicDDDWriteService();
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDynamicDDD(dynamicDDDMap, writeFileService);
    }
}
