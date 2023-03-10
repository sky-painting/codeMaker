package com.tianhua.codemaker.enums;


import com.tianhua.codemaker.bean.GlobalConstant;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Description: 项目模块枚举
 * date: 2021/6/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum  ModuleEnum {
    /**
     *
     */
    SPRING_BOOT_WEB("springboot","springboot",
            Sets.newHashSet(
                    TemplateFileEnum.MAPPER_XML.getTempFileName(),
                    TemplateFileEnum.MAPPER.getTempFileName(),
                    TemplateFileEnum.MAPPER_DDD.getTempFileName(),
                    TemplateFileEnum.MAPPER_XML_DDD.getTempFileName(),
                    TemplateFileEnum.CONTROLLER.getTempFileName(),
                    TemplateFileEnum.CONTROLLER_DDD.getTempFileName(),
                    TemplateFileEnum.SERVICE_IMPL.getTempFileName(),
                    TemplateFileEnum.SERVICE.getTempFileName(),
                    TemplateFileEnum.SERVICE_DDD.getTempFileName(),
                    TemplateFileEnum.SERVICE_IMPL_DDD.getTempFileName(),
                    TemplateFileEnum.VO.getTempFileName(),
                    TemplateFileEnum.VO_DDD.getTempFileName(),
                    TemplateFileEnum.DTO_DDD.getTempFileName(),
                    TemplateFileEnum.DTO.getTempFileName(),
                    TemplateFileEnum.ENTITY.getTempFileName(),
                    TemplateFileEnum.DATA_OBJECT.getTempFileName(),
                    TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),
                    TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(),

                    TemplateFileEnum.TEST.getTempFileName(),
                    TemplateFileEnum.TEST_DDD.getTempFileName(),
                    TemplateFileEnum.VALUE_OBJECT.getTempFileName(),
                    TemplateFileEnum.ENUM.getTempFileName(),
                    TemplateFileEnum.CMD.getTempFileName(),
                    TemplateFileEnum.EXE.getTempFileName(),
                    TemplateFileEnum.EXE_IMPL.getTempFileName(),
                    TemplateFileEnum.EXE_ABSTRACT.getTempFileName(),
                    TemplateFileEnum.GATAWAY.getTempFileName(),
                    TemplateFileEnum.REPOSITORY.getTempFileName(),
                    TemplateFileEnum.GATAWAY_IMPL.getTempFileName(),
                    TemplateFileEnum.REPOSITORY_IMPL.getTempFileName(),
                    TemplateFileEnum.ACL.getTempFileName(),
                    TemplateFileEnum.ACL_IMPL.getTempFileName(),
                    TemplateFileEnum.ACL_PARAM.getTempFileName(),
                    TemplateFileEnum.DTOBO_CONVERT.getTempFileName(),
                    //TemplateFileEnum.BASE_CONTROLLER.getTempFileName(),
                    TemplateFileEnum.VOBO_CONVERT.getTempFileName(),
                    TemplateFileEnum.FACTORY.getTempFileName(),
                    TemplateFileEnum.MESSAGE_BODY.getTempFileName(),
                    TemplateFileEnum.EVENT_BODY.getTempFileName(),
                    TemplateFileEnum.CONVERT.getTempFileName(),
                    TemplateFileEnum.DOBO_CONVERT.getTempFileName(),

                    TemplateFileEnum.MQ_CONSUMER.getTempFileName(),
                    TemplateFileEnum.MQ_PRODUCER.getTempFileName(),
                    TemplateFileEnum.MQ_LISTENER.getTempFileName(),
                    TemplateFileEnum.MQ_HANDLER.getTempFileName(),
                    TemplateFileEnum.PARENT_POM.getTempFileName(),
                    TemplateFileEnum.MQ_HANDLER.getTempFileName(),
                    TemplateFileEnum.CACHE.getTempFileName(),
                    GlobalConstant.SINGLE_CLASS_COMMON)
                    ),





    /**
     * springcloud feign
     */
    SC_FEIGN_API("springcloud","springcloud-api", Sets.newHashSet(
            TemplateFileEnum.FEIGN.getTempFileName(),
            TemplateFileEnum.FEIGN_DDD.getTempFileName(),
            TemplateFileEnum.DTO_DDD.getTempFileName(),
            TemplateFileEnum.DTO.getTempFileName(),
            TemplateFileEnum.API_ENUM.getTempFileName(),
            TemplateFileEnum.API_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON
    )),

    /**
     *
     */
    SC_FEIGN_PROVIDER("springcloud","springcloud-provider",
            Sets.newHashSet(
                    TemplateFileEnum.MAPPER_XML.getTempFileName(),
                    TemplateFileEnum.MAPPER.getTempFileName(),
                    TemplateFileEnum.MAPPER_DDD.getTempFileName(),
                    TemplateFileEnum.MAPPER_XML_DDD.getTempFileName(),

                    TemplateFileEnum.CONTROLLER.getTempFileName(),
                    TemplateFileEnum.CONTROLLER_DDD.getTempFileName(),
                    TemplateFileEnum.SERVICE_IMPL.getTempFileName(),
                    TemplateFileEnum.SERVICE.getTempFileName(),
                    TemplateFileEnum.SERVICE_DDD.getTempFileName(),
                    TemplateFileEnum.SERVICE_IMPL_DDD.getTempFileName(),
                    TemplateFileEnum.DTO_DDD.getTempFileName(),
                    TemplateFileEnum.DTO.getTempFileName(),
                    TemplateFileEnum.ENTITY.getTempFileName(),
                    TemplateFileEnum.DATA_OBJECT.getTempFileName(),
                    TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),
                    TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(),
                    TemplateFileEnum.TEST_DDD.getTempFileName(),
                    TemplateFileEnum.TEST.getTempFileName(),
                    TemplateFileEnum.VALUE_OBJECT.getTempFileName(),
                    TemplateFileEnum.ENUM.getTempFileName(),
                    TemplateFileEnum.CMD.getTempFileName(),
                    TemplateFileEnum.EXE.getTempFileName(),
                    TemplateFileEnum.EXE_IMPL.getTempFileName(),
                    TemplateFileEnum.EXE_ABSTRACT.getTempFileName(),
                    TemplateFileEnum.GATAWAY.getTempFileName(),
                    TemplateFileEnum.REPOSITORY.getTempFileName(),
                    TemplateFileEnum.GATAWAY_IMPL.getTempFileName(),
                    TemplateFileEnum.REPOSITORY_IMPL.getTempFileName(),
                    TemplateFileEnum.ACL.getTempFileName(),
                    TemplateFileEnum.ACL_IMPL.getTempFileName(),
                    TemplateFileEnum.ACL_PARAM.getTempFileName(),
                    TemplateFileEnum.DTOBO_CONVERT.getTempFileName(),
                    TemplateFileEnum.FACTORY.getTempFileName(),
                    TemplateFileEnum.MESSAGE_BODY.getTempFileName(),
                    TemplateFileEnum.EVENT_BODY.getTempFileName(),
                    TemplateFileEnum.CONVERT.getTempFileName(),
                    TemplateFileEnum.DOBO_CONVERT.getTempFileName(),

                    TemplateFileEnum.MQ_CONSUMER.getTempFileName(),
                    TemplateFileEnum.MQ_PRODUCER.getTempFileName(),
                    TemplateFileEnum.MQ_LISTENER.getTempFileName(),
                    TemplateFileEnum.MQ_HANDLER.getTempFileName(),
                    TemplateFileEnum.MQ_HANDLER.getTempFileName(),
                    TemplateFileEnum.CACHE.getTempFileName(),

                    TemplateFileEnum.FEIGN.getTempFileName(),
                    TemplateFileEnum.FEIGN_DDD.getTempFileName(),
                    TemplateFileEnum.FEIGN_CONTROLLER.getTempFileName(),
                    TemplateFileEnum.FEIGN_CONTROLLER_DDD.getTempFileName(),
                    TemplateFileEnum.PROVIDER_POM.getTempFileName(),

                    //TemplateFileEnum.BASE_EVENT.getTempFileName(),
                    //TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName(),
                    GlobalConstant.SINGLE_CLASS_COMMON
                    //TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName()
                    )),



    /**
     *
     */
    DUBBO_API("dubbo","dubbo-api", Sets.newHashSet(
            TemplateFileEnum.FACADE.getTempFileName(),
            TemplateFileEnum.FACADE_DDD.getTempFileName(),
            TemplateFileEnum.DTO_DDD.getTempFileName(),
            TemplateFileEnum.DTO.getTempFileName(),
            TemplateFileEnum.API_ENUM.getTempFileName(),
            TemplateFileEnum.API_POM.getTempFileName(),

            GlobalConstant.SINGLE_CLASS_COMMON
    )),
    /**
     *
     */
    DUBBO_COMMON("dubbo","dubbo-common", Sets.newHashSet(
            TemplateFileEnum.MAPPER_XML.getTempFileName(),
            TemplateFileEnum.MAPPER.getTempFileName(),
            TemplateFileEnum.MAPPER_DDD.getTempFileName(),
            TemplateFileEnum.MAPPER_XML_DDD.getTempFileName(),

            TemplateFileEnum.DATA_OBJECT.getTempFileName(),
            TemplateFileEnum.COMMON_POM.getTempFileName(),

            GlobalConstant.SINGLE_CLASS_COMMON
    )),
    /**
     *
     */
    DUBBO_CORE("dubbo","dubbo-core", Sets.newHashSet(
            TemplateFileEnum.SERVICE.getTempFileName(),
            TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),
            TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(),

            TemplateFileEnum.CONVERT.getTempFileName(),
            TemplateFileEnum.SERVICE_IMPL.getTempFileName(),
            TemplateFileEnum.TEST.getTempFileName(),
            TemplateFileEnum.TEST_DDD.getTempFileName(),
            TemplateFileEnum.FACADE_IMPL.getTempFileName(),
            TemplateFileEnum.FACADE_IMPL_DDD.getTempFileName(),
            TemplateFileEnum.SERVICE_DDD.getTempFileName(),
            TemplateFileEnum.SERVICE_IMPL_DDD.getTempFileName(),
            TemplateFileEnum.VALUE_OBJECT.getTempFileName(),
            TemplateFileEnum.ENUM.getTempFileName(),
            TemplateFileEnum.CMD.getTempFileName(),
            TemplateFileEnum.EXE.getTempFileName(),
            TemplateFileEnum.EXE_IMPL.getTempFileName(),
            TemplateFileEnum.EXE_ABSTRACT.getTempFileName(),
            TemplateFileEnum.GATAWAY_IMPL.getTempFileName(),
            TemplateFileEnum.GATAWAY.getTempFileName(),
            TemplateFileEnum.REPOSITORY.getTempFileName(),
            TemplateFileEnum.FACTORY.getTempFileName(),
            TemplateFileEnum.REPOSITORY_IMPL.getTempFileName(),
            TemplateFileEnum.ACL.getTempFileName(),
            TemplateFileEnum.ACL_IMPL.getTempFileName(),
            TemplateFileEnum.DTOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.DOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.MESSAGE_BODY.getTempFileName(),
            TemplateFileEnum.EVENT_BODY.getTempFileName(),
            TemplateFileEnum.ACL_PARAM.getTempFileName(),
            TemplateFileEnum.MQ_CONSUMER.getTempFileName(),
            TemplateFileEnum.MQ_PRODUCER.getTempFileName(),
            TemplateFileEnum.MQ_LISTENER.getTempFileName(),
            TemplateFileEnum.MQ_HANDLER.getTempFileName(),
            TemplateFileEnum.CACHE.getTempFileName(),
            TemplateFileEnum.CORE_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON

            )),


    /**
     *
     */
    COLA_FEIGN_API("cola","cola-feignapi", Sets.newHashSet(
            TemplateFileEnum.FEIGN.getTempFileName(),
            TemplateFileEnum.FEIGN_DDD.getTempFileName(),
            TemplateFileEnum.DTO_DDD.getTempFileName(),
            TemplateFileEnum.DTO.getTempFileName(),
            TemplateFileEnum.API_ENUM.getTempFileName(),
            TemplateFileEnum.FEIGN_API_POM.getTempFileName(),

            GlobalConstant.SINGLE_CLASS_COMMON
    )),

    /**
     *
     */
    COLA_ADAPTER("cola","cola-adapter", Sets.newHashSet(
            TemplateFileEnum.CONTROLLER.getTempFileName(),
            //TemplateFileEnum.BASE_CONTROLLER.getTempFileName(),
            TemplateFileEnum.VO.getTempFileName(),
            TemplateFileEnum.CONTROLLER_DDD.getTempFileName(),
            TemplateFileEnum.VOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.CONVERT.getTempFileName(),
            TemplateFileEnum.VO_DDD.getTempFileName(),
            TemplateFileEnum.ADAPTER_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON

    )),

    /**
     *
     */
    COLA_DOMAIN("cola","cola-domain", Sets.newHashSet(
            TemplateFileEnum.SERVICE.getTempFileName(),
            TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),
            TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(),
            TemplateFileEnum.SERVICE.getTempFileName(),
            TemplateFileEnum.GATAWAY.getTempFileName(),
            TemplateFileEnum.FACTORY.getTempFileName(),
            TemplateFileEnum.REPOSITORY.getTempFileName(),
            TemplateFileEnum.VALUE_OBJECT.getTempFileName(),
            TemplateFileEnum.MESSAGE_BODY.getTempFileName(),
            TemplateFileEnum.EVENT_BODY.getTempFileName(),
            TemplateFileEnum.ENUM.getTempFileName(),
            TemplateFileEnum.DOMAIN_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON,
            TemplateFileEnum.SERVICE_DDD.getTempFileName()

            )),
    /**
     *
     */
    COLA_CLIENT("cola","cola-client", Sets.newHashSet(
            TemplateFileEnum.DTO.getTempFileName(),
            TemplateFileEnum.DTO_DDD.getTempFileName(),
            TemplateFileEnum.FACADE.getTempFileName(),
            TemplateFileEnum.FACADE_DDD.getTempFileName(),
            TemplateFileEnum.API_ENUM.getTempFileName(),
            TemplateFileEnum.ENUM.getTempFileName(),
            TemplateFileEnum.CLIENT_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON
            )),

    /**
     *
     */
    COLA_INFRAST("cola","cola-infrast", Sets.newHashSet(
            TemplateFileEnum.GATAWAY_IMPL.getTempFileName(),
            TemplateFileEnum.REPOSITORY_IMPL.getTempFileName(),
            TemplateFileEnum.MAPPER_XML.getTempFileName(),
            TemplateFileEnum.MAPPER_XML_DDD.getTempFileName(),
            TemplateFileEnum.CONVERT.getTempFileName(),
            TemplateFileEnum.MAPPER.getTempFileName(),
            TemplateFileEnum.MAPPER_DDD.getTempFileName(),
            TemplateFileEnum.DATA_OBJECT.getTempFileName(),
            TemplateFileEnum.DOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.SERVICE_IMPL.getTempFileName(),
            TemplateFileEnum.SERVICE_IMPL_DDD.getTempFileName(),
            TemplateFileEnum.ACL.getTempFileName(),
            TemplateFileEnum.ACL_IMPL.getTempFileName(),
            TemplateFileEnum.ACL_PARAM.getTempFileName(),

            TemplateFileEnum.CACHE.getTempFileName(),

            TemplateFileEnum.MQ_CONSUMER.getTempFileName(),
            TemplateFileEnum.MQ_PRODUCER.getTempFileName(),
            TemplateFileEnum.MQ_HANDLER.getTempFileName(),
            TemplateFileEnum.INFRAST_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON
            )),

    /**
     *
     */
    COLA_APP("cola","cola-app", Sets.newHashSet(
            //todo test
            //TemplateFileEnum.TEST.getTempFileName(),

            TemplateFileEnum.FACADE_IMPL.getTempFileName(),
            TemplateFileEnum.FACADE_IMPL_DDD.getTempFileName(),
            TemplateFileEnum.CMD.getTempFileName(),
            TemplateFileEnum.CONVERT.getTempFileName(),
            TemplateFileEnum.EXE_ABSTRACT.getTempFileName(),
            TemplateFileEnum.EXE.getTempFileName(),
            TemplateFileEnum.EXE_IMPL.getTempFileName(),
            TemplateFileEnum.DTOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.MQ_LISTENER.getTempFileName(),
            TemplateFileEnum.APP_POM.getTempFileName(),
            GlobalConstant.SINGLE_CLASS_COMMON
            )),


    /**
     *
     */
    COLA_API_DOC("cola","api-doc", Sets.newHashSet(
            TemplateFileEnum.API_HTTP.getTempFileName(),
            TemplateFileEnum.PARENT_POM.getTempFileName(),
            TemplateFileEnum.API_RPC.getTempFileName()
    )),

    /**
     *
     */
    COLA_START("cola","cola-start", Sets.newHashSet(
            TemplateFileEnum.START_POM.getTempFileName(),
            TemplateFileEnum.TEST_DDD.getTempFileName(),

            GlobalConstant.SINGLE_CLASS_COMMON
    )),


    /**
     *
     */
    DUBBO_API_DOC("dubbo","api-doc", Sets.newHashSet(
            TemplateFileEnum.API_RPC.getTempFileName(),
            TemplateFileEnum.PARENT_POM.getTempFileName()

            )),

    /**
     *
     */
    SPRINGBOOT_API_DOC("springboot","api-doc", Sets.newHashSet(
            TemplateFileEnum.API_HTTP.getTempFileName(),
            TemplateFileEnum.PARENT_POM.getTempFileName()

    )),

    /**
     *
     */
    SC_API_DOC("springcloud","api-doc", Sets.newHashSet(
            TemplateFileEnum.API_HTTP.getTempFileName(),
            TemplateFileEnum.API_RPC.getTempFileName(),
            TemplateFileEnum.PARENT_POM.getTempFileName()

    )),

    ;
    private String appName;
    private String moduleName;
    private Set<String> templateFileSet;
    ModuleEnum(String appName, String moduleName, Set<String> templateFileSet){
        this.appName = appName;
        this.moduleName = moduleName;
        this.templateFileSet = templateFileSet;
    }

    public String getAppName() {
        return appName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Set<String> getTemplateFileSet() {
        return templateFileSet;
    }

    /**
     * 增加自定义代码元素
     */
    public static void addCustomTemplateFile(String appName,String moduleName, String templateCode){
        String module = appName + "-" + moduleName;
        for (ModuleEnum moduleEnum : ModuleEnum.values()){
            if(moduleEnum.getAppName().equals(appName) && moduleEnum.getModuleName().equals(module)){
                moduleEnum.getTemplateFileSet().add(templateCode);
            }
        }
    }

}
