package com.tianhua.codemaker.enums;

/**
 * description: TemplateFileEnum <br>
 * date: 2020/7/7 23:44 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 代码元素类型枚举
 */
public enum CodeElementEnum {
    ENTITY("entity", ClassEnum.CLASS.getClassType()),
    MAPPER("mapper", ClassEnum.INTERFACE.getClassType()),
    POM("pom",""),
    CONTROLLER("controller",ClassEnum.CLASS.getClassType()),
    MAPPER_XML("mapperxml",""),
    SERVICE_CLASS("serviceclasss",ClassEnum.CLASS.getClassType()),
    SERVICE_INTERFACE("serviceinterface",ClassEnum.INTERFACE.getClassType()),

    SERVICE_IMPL("serviceImpl",ClassEnum.CLASS.getClassType()),
    VO("vo",ClassEnum.CLASS.getClassType()),
    TEST("test",ClassEnum.CLASS.getClassType()),
    DTO("dto",ClassEnum.CLASS.getClassType()),
    FACADE("facade",ClassEnum.INTERFACE.getClassType()),
    DATA_OBJECT("do",ClassEnum.CLASS.getClassType()),
    FACADE_IMPL("facadeImpl",ClassEnum.CLASS.getClassType()),
    BUSINESS_OBJECT("bo",ClassEnum.CLASS.getClassType()),
    CONVERT("convert",ClassEnum.INTERFACE.getClassType()),
    DTOBO_CONVERT("dtoboconvert",ClassEnum.INTERFACE.getClassType()),
    DTO2DTO_CONVERT("dto2dtoconvert",ClassEnum.INTERFACE.getClassType()),
    DOBO_CONVERT("doboconvert",ClassEnum.INTERFACE.getClassType()),
    GATAWAY("gataway",ClassEnum.INTERFACE.getClassType()),
    REPOSITORY("repository",ClassEnum.INTERFACE.getClassType()),
    REPOSITORY_IMPL("repositoryimpl",ClassEnum.CLASS.getClassType()),
    FACTORY("factory",ClassEnum.CLASS.getClassType()),
    ENUM("enum",ClassEnum.ENUM.getClassType()),
    API_ENUM("apienum",ClassEnum.ENUM.getClassType()),
    MESSAGE_BODY("msgbody",ClassEnum.CLASS.getClassType()),
    EVENT_BODY("event",ClassEnum.CLASS.getClassType()),
    GATAWAY_IMPL("gatawayimpl",ClassEnum.CLASS.getClassType()),
    ACL_IMPL("aclimpl",ClassEnum.CLASS.getClassType()),
    ACL_REQ("aclreq",ClassEnum.CLASS.getClassType()),
    ACL_RES("aclres",ClassEnum.CLASS.getClassType()),
    ACL_PARAM("aclparam",ClassEnum.CLASS.getClassType()),
    CMD("cmd",ClassEnum.CLASS.getClassType()),
    EXE("exe",ClassEnum.INTERFACE.getClassType()),
    EXE_IMPL("exeimpl",ClassEnum.CLASS.getClassType()),
    EXE_ABSTRACT("abstractexe" ,ClassEnum.CLASS.getClassType()),
    VALUE_OBJECT("valueobject", ClassEnum.CLASS.getClassType()),
    VOBO_CONVERT("voboconvert",ClassEnum.INTERFACE.getClassType()),

    MQ_CONSUMER("mqconsumer",ClassEnum.CLASS.getClassType()),
    MQ_HANDLER("mqhandler",ClassEnum.CLASS.getClassType()),
    MQ_LISTENER("mqlistener",ClassEnum.CLASS.getClassType()),
    MQ_PRODUCER("mqproducer",ClassEnum.CLASS.getClassType()),



    FEIGN("feign",ClassEnum.INTERFACE.getClassType()),


    ;
    private String code;

    private String classType;

    CodeElementEnum(String code,String classType){
        this.code = code;
        this.classType = classType;
    }

    public String getCode() {
        return code;
    }

    public String getClassType() {
        return classType;
    }
}
