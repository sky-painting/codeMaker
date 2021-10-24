package com.coderman.codemaker.enums;

/**
 * description: TemplateFileEnum <br>
 * date: 2020/7/7 23:44 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 设置要生成的模板文件的类型
 */
public enum  TemplateFileEnum {
    ENTITY("entity"),
    MAPPER("mapper"),
    POM("pom"),
    CONTROLLER("controller"),
    MAPPER_XML("mapperxml"),
    SERVICE("service"),
    SERVICE_IMPL("serviceImpl"),
    VO("vo"),
    BASE_CONTROLLER("baseController"),
    SPRING_APPLICATION_CONTEXT("SpringApplicationContext"),
    TEST("test"),
    APPLICATION("application"),
    DTO("dto"),
    FACADE("facade"),
    DATA_OBJECT("do"),
    FACADE_IMPL("facadeImpl"),
    BUSINESS_OBJECT("bo"),
    CONVERT("convert"),
    DTOBO_CONVERT("dtoboconvert"),
    DOBO_CONVERT("doboconvert"),
    FACADE_AOP("facadeaop"),
    GATAWAY("gataway"),
    REPOSITORY("repository"),
    REPOSITORY_IMPL("repositoryimpl"),
    FACTORY("factory"),
    ENUM("enum"),
    API_ENUM("apienum"),
    MESSAGE_BODY("msgbody"),
    EVENT_BODY("event"),
    GATAWAY_IMPL("gatawayimpl"),
    ACL("acl"),
    ACL_IMPL("aclimpl"),
    ACL_REQ("aclreq"),
    ACL_RES("aclres"),
    ACL_PARAM("aclparam"),
    CMD("cmd"),
    EXE("exe"),
    EXE_IMPL("exeimpl"),
    EXE_ABSTRACT("abstractexe"),
    VALUE_OBJECT("valueobject"),
    BUSINESS_OBJECT_DDD("boddd"),
    FACADE_DDD("facadeddd"),
    FACADE_IMPL_DDD("facadeimplddd"),
    CONTROLLER_DDD("controllerddd"),
    VO_DDD("voddd"),
    VOBO_CONVERT("voboconvert"),
    DTO_DDD("dtoddd"),
    MQ_CONSUMER("mqconsumer"),
    MQ_HANDLER("mqhandler"),
    MQ_LISTENER("mqlistener"),
    MQ_PRODUCER("mqproducer"),
    ;
    private String tempFileName;
    TemplateFileEnum(String tempFileName){
        this.tempFileName = tempFileName;
    }

    public String getTempFileName() {
        return tempFileName;
    }
}
