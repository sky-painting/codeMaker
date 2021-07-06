package com.coderman.codemaker.enums;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum  DomainElementEnum {
    VALUEOBJECT("valueobject,config,vo","值对象"),
    CONFIG("config","值对象"),
    ENUM("enum","值对象"),
    BO("bo","领域实体"),
    SERVICE("service","领域服务"),
    GATAWAY("gataway","领域网关服务"),
    MSGBODY("msgbody","事件消息体"),
    EVENT("event","事件"),
    FACTORY("factory","领域工厂"),
    REPOSITORY("repository","领域仓库"),
    ACL("acl","适配器防腐层"),
    ADAPTER("adapter","适配器防腐层"),
    ADAPTER_SERVICE("adapterservice","适配器防腐层"),
    COMMAND("command,cmd","命令"),
    EXECUTOR("executor,exe,exeservice,exehandler","执行器"),


    ;
    private String element;
    private String desc;
    DomainElementEnum(String element, String desc){
        this.element = element;
        this.desc = desc;
    }


    public String getElement() {
        return element;
    }

    public String getDesc() {
        return desc;
    }
}
