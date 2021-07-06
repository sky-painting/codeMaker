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
    FACADE_AOP("facadeaop"),
    GATAWAY("gataway"),
    REPOSITORY("repository"),
    REPOSITORY_IMPL("repositoryimpl"),
    FACTORY("factory"),
    ENUM("enum"),
    MESSAGE_BODY("msgbody"),
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
    ;
    private String tempFileName;
    TemplateFileEnum(String tempFileName){
        this.tempFileName = tempFileName;
    }

    public String getTempFileName() {
        return tempFileName;
    }
}
