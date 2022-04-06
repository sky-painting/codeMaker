package com.tianhua.codemaker.enums;

/**
 * description: TemplateFileEnum <br>
 * date: 2020/7/7 23:44 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 设置要生成的模板文件的类型
 */
public enum  TemplateFileEnum {
    ENTITY("entity","dao.dataobject",".java","infrast"),
    MAPPER("mapper","dao.mapper",".java","infrast"),
    POM("pom","",".xml","resources"),
    CONTROLLER("controller","controller",".java","adapter"),
    MAPPER_XML("mapperxml","",".xml","resource"),
    SERVICE("service","service",".java","app,domain"),
    SERVICE_DDD("serviceddd","service",".java","app,domain"),
    SERVICE_IMPL_DDD("serviceimplddd","serviceimpl",".java","app,domain,infrast"),
    SERVICE_IMPL("serviceImpl","serviceimpl",".java","app,domain,infrast"),
    VO("vo","vo",".java","adapter"),
    BASE_CONTROLLER("baseController","",".java","adapter"),
    SPRING_APPLICATION_CONTEXT("SpringApplicationContext","",".java","support"),
    TEST("test","",".java","test"),
    TEST_DDD("testddd","",".java","test"),
    APPLICATION("application","",".java","start"),
    DTO("dto","api.dto",".java","client,feignapi,api"),
    FACADE("facade","api.facade",".java","client,api"),
    DATA_OBJECT("do","dao.dataobject",".java","infrast"),
    FACADE_IMPL("facadeImpl","facadeimpl",".java","app"),
    BUSINESS_OBJECT("bo","bo",".java","domain"),
    CONVERT("convert","convert",".java","adapter,app,infrast"),
    DTOBO_CONVERT("dtoboconvert","convert",".java","app"),
    DTO2DTO_CONVERT("dto2dtoconvert","convert",".java","app"),
    DOBO_CONVERT("doboconvert","convert",".java","infrast"),
    FACADE_AOP("facadeaop","",".java","app"),
    GATAWAY("gataway","gataway",".java","domain"),
    REPOSITORY("repository","repository",".java","domain"),
    REPOSITORY_IMPL("repositoryimpl","repositoryimpl",".java","infrast"),
    FACTORY("factory","factory",".java","app,domain,infrast"),
    ENUM("enum","enum",".java","domain,client"),
    API_ENUM("apienum","api.enum",".java","api,feignapi"),
    MESSAGE_BODY("msgbody","bo.msgbody",".java","domain"),
    EVENT_BODY("event","bo.event",".java","domain"),
    GATAWAY_IMPL("gatawayimpl","repositoryimpl",".java","infrast"),
    ACL("acl","acl",".java","infrast"),
    ACL_IMPL("aclimpl","acl.aclimpl",".java","infrast"),
    ACL_REQ("aclreq","acl.param.req",".java","infrast"),
    ACL_RES("aclres","acl.param.res",".java","infrast"),
    ACL_PARAM("aclparam","acl.param",".java","infrast"),
    CMD("cmd","command",".java","app"),
    EXE("exe","executor",".java","app"),
    EXE_IMPL("exeimpl","exeimpl",".java","app"),
    EXE_ABSTRACT("abstractexe","executor",".java","app"),
    VALUE_OBJECT("valueobject","bo.valueobject",".java","domain"),
    BUSINESS_OBJECT_DDD("boddd","bo",".java","domain"),
    FACADE_DDD("facadeddd","api.facade",".java","client,api"),
    FACADE_IMPL_DDD("facadeimplddd","facadeimpl",".java","client,api"),
    CONTROLLER_DDD("controllerddd","adapter.controller",".java","adapter,feignprovider"),
    VO_DDD("voddd","adapter.vo",".java","adapter"),
    VOBO_CONVERT("voboconvert","convert",".java","adapter"),
    DTO_DDD("dtoddd","api.dto",".java","client,api"),
    MQ_CONSUMER("mqconsumer","mq.consumer",".java","infrast"),
    MQ_HANDLER("mqhandler","mq.handler",".java","infrast"),
    MQ_LISTENER("mqlistener","mq.listener",".java","infrast"),
    MQ_PRODUCER("mqproducer","mq.producer",".java","infrast"),
    APP_EVENT_PUBLISHER("AppEventPublisher","",".java","domain"),
    BASE_EVENT("BaseEvent","",".java",""),
    MAPPER_DDD("mapperddd","dao.mapper",".java","infrast"),
    MAPPER_XML_DDD("mapperxmlddd","",".xml","resources"),
    API_HTTP("http","http",".md","api-doc"),
    API_RPC("rpc","rpc",".md","api-doc"),
    SQL("sql","sql",".sql","sql-doc"),
    CACHE("cache","cache",".java","infrast"),

    FEIGN("feign","api.feign",".java","feignapi"),
    FEIGN_DDD("feignddd","api.feign",".java","feignapi"),
    FEIGN_CONTROLLER("feigncontroller","api.feign.controller",".java","feignprovider"),
    FEIGN_CONTROLLER_DDD("feigncontrollerddd","api.feign.controller",".java","feignprovider"),


    CLIENT_POM("clientpom","",".xml","client"),
    DOMAIN_POM("domainpom", "",".xml","domain"),
    INFRAST_POM("infrastpom","",".xml","infrast"),
    API_POM("apipom","",".xml","api"),
    CORE_POM("corepom","",".xml","core"),
    COMMON_POM("commonpom","",".xml","common"),
    APP_POM("apppom","",".xml","app"),
    PARENT_POM("parentpom","",".xml","parent"),
    START_POM("startpom","",".xml","start"),
    ADAPTER_POM("adapterpom","",".xml","adapter"),
    FEIGN_API_POM("feignapipom","",".xml","feignapi"),
    PROVIDER_POM("providerpom","",".xml","provider"),

    ;
    private String tempFileName;
    /**
     * 最后一级子包名，也是代码元素别名
     */
    private String childPackageName;

    /**
     * 类后缀
     */
    private String classSuffix;

    /**
     * 所属层
     * todo
     */
    private String layerCodes;

    TemplateFileEnum(String tempFileName, String childPackageName, String classSuffix, String layerCodes){
        this.tempFileName = tempFileName;
        this.childPackageName = childPackageName;
        this.classSuffix = classSuffix;
        this.layerCodes = layerCodes;
    }

    public String getTempFileName() {
        return tempFileName;
    }

    public String getChildPackageName() {
        return childPackageName;
    }

    public String getClassSuffix() {
        return classSuffix;
    }

    /**
     * 判断类名是否是bo,vo,dto,entity
     * @param className
     * @return
     */
    public static boolean isClassModel(String className){
        String classType = className.toLowerCase();
        return classType.endsWith(TemplateFileEnum.VO.getTempFileName())
                || classType.endsWith(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())
                || classType.endsWith(TemplateFileEnum.DTO.getTempFileName())
                || classType.endsWith(TemplateFileEnum.DATA_OBJECT.getTempFileName())
                || classType.endsWith(TemplateFileEnum.ENTITY.getTempFileName());
    }

    public static String getChildPackageName(String templateCode){
        for (TemplateFileEnum templateFileEnum : TemplateFileEnum.values()){
            if(templateFileEnum.getTempFileName().equals(templateCode)){
                return templateFileEnum.getChildPackageName();
            }
        }
        return templateCode;
    }


    /**
     * 获取模板文件名后缀
     * @param templateCode
     * @return
     */
    public static String getClassSuffix(String templateCode){
        for (TemplateFileEnum templateFileEnum : TemplateFileEnum.values()){
            if(templateFileEnum.getTempFileName().equals(templateCode)){
                return templateFileEnum.getClassSuffix();
            }
        }
        return null;
    }
}
