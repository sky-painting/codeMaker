package com.coderman.codemaker.enums;


import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Description: 项目模块枚举
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum  ModuleEnum {
    /**
     *
     */
    SPRING_BOOT_WEB("springboot","codemaker-springboot",
            Sets.newHashSet(TemplateFileEnum.APPLICATION.getTempFileName(),
                    TemplateFileEnum.MAPPER_XML.getTempFileName(),
                    TemplateFileEnum.MAPPER.getTempFileName(),
                    TemplateFileEnum.CONTROLLER.getTempFileName(),
                    TemplateFileEnum.CONTROLLER_DDD.getTempFileName(),
                    TemplateFileEnum.SERVICE_IMPL.getTempFileName(),
                    TemplateFileEnum.SERVICE.getTempFileName(),
                    TemplateFileEnum.VO.getTempFileName(),
                    TemplateFileEnum.VO_DDD.getTempFileName(),
                    TemplateFileEnum.DTO_DDD.getTempFileName(),
                    TemplateFileEnum.DTO.getTempFileName(),
                    TemplateFileEnum.ENTITY.getTempFileName(),
                    TemplateFileEnum.DATA_OBJECT.getTempFileName(),
                    TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(),
                    TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(),

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
                    TemplateFileEnum.BASE_CONTROLLER.getTempFileName(),
                    TemplateFileEnum.VOBO_CONVERT.getTempFileName(),
                    TemplateFileEnum.FACTORY.getTempFileName(),
                    TemplateFileEnum.MESSAGE_BODY.getTempFileName(),
                    TemplateFileEnum.CONVERT.getTempFileName(),
                    TemplateFileEnum.DOBO_CONVERT.getTempFileName(),

                    TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())),
    /**
     *
     */
    DUBBO_API("dubbo","dubbo-api", Sets.newHashSet(
            TemplateFileEnum.FACADE.getTempFileName(),
            TemplateFileEnum.FACADE_DDD.getTempFileName(),
            TemplateFileEnum.DTO_DDD.getTempFileName(),
            TemplateFileEnum.DTO.getTempFileName()
    )),
    /**
     *
     */
    DUBBO_COMMON("dubbo","dubbo-common", Sets.newHashSet(
            TemplateFileEnum.MAPPER_XML.getTempFileName(),
            TemplateFileEnum.MAPPER.getTempFileName(),
            TemplateFileEnum.DATA_OBJECT.getTempFileName()
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
            TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(),
            TemplateFileEnum.FACADE_IMPL.getTempFileName(),
            TemplateFileEnum.FACADE_IMPL_DDD.getTempFileName(),

            TemplateFileEnum.APPLICATION.getTempFileName(),
            TemplateFileEnum.FACADE_AOP.getTempFileName(),
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
            TemplateFileEnum.ACL_PARAM.getTempFileName()


            )),


    /**
     *
     */
    COLA_ADAPTER("cola","cola-adapter", Sets.newHashSet(
            TemplateFileEnum.CONTROLLER.getTempFileName(),
            TemplateFileEnum.BASE_CONTROLLER.getTempFileName(),
            TemplateFileEnum.VO.getTempFileName(),
            TemplateFileEnum.CONTROLLER_DDD.getTempFileName(),
            TemplateFileEnum.VOBO_CONVERT.getTempFileName(),

            TemplateFileEnum.VO_DDD.getTempFileName()

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
            TemplateFileEnum.ENUM.getTempFileName()

            )),
    /**
     *
     */
    COLA_CLIENT("cola","cola-client", Sets.newHashSet(
            TemplateFileEnum.DTO.getTempFileName(),
            TemplateFileEnum.DTO_DDD.getTempFileName(),
            TemplateFileEnum.FACADE.getTempFileName(),
            TemplateFileEnum.FACADE_DDD.getTempFileName()
            )),

    /**
     *
     */
    COLA_INFRAST("cola","cola-infrast", Sets.newHashSet(
            TemplateFileEnum.GATAWAY_IMPL.getTempFileName(),
            TemplateFileEnum.REPOSITORY_IMPL.getTempFileName(),
            TemplateFileEnum.MAPPER_XML.getTempFileName(),
            TemplateFileEnum.MAPPER.getTempFileName(),
            TemplateFileEnum.DATA_OBJECT.getTempFileName(),
            TemplateFileEnum.DOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.SERVICE_IMPL.getTempFileName(),
            TemplateFileEnum.ACL.getTempFileName(),
            TemplateFileEnum.ACL_IMPL.getTempFileName(),
            TemplateFileEnum.ACL_PARAM.getTempFileName()
            )),

    /**
     *
     */
    COLA_APP("cola","cola-app", Sets.newHashSet(
            TemplateFileEnum.TEST.getTempFileName(),

            TemplateFileEnum.FACADE_IMPL.getTempFileName(),
            TemplateFileEnum.FACADE_IMPL_DDD.getTempFileName(),
            TemplateFileEnum.CMD.getTempFileName(),
            TemplateFileEnum.CONVERT.getTempFileName(),
            TemplateFileEnum.EXE_ABSTRACT.getTempFileName(),
            TemplateFileEnum.EXE.getTempFileName(),
            TemplateFileEnum.EXE_IMPL.getTempFileName(),
            TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName(),
            TemplateFileEnum.DTOBO_CONVERT.getTempFileName(),
            TemplateFileEnum.FACADE_AOP.getTempFileName()
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
}
