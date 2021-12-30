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
    APP_LISTENER("mqlistener","监听器"),
    MQ_CONSUMER("mqconsumer","消息消费者"),
    MQ_PRODUCER("mqproducer","消息生产者"),
    MQ_HANDLER("mqhandler","消息处理器"),
    CACHE("cache","缓存"),

    GATAWAY_IMPL("gatawayimpl","领域网关服务实现"),
    REPOSITORY_IMPL("repositoryimpl","领域仓库实现"),
    ACL_IMPL("aclimpl","适配器防腐层实现"),
    ADAPTER_IMPL("aclimpl","适配器防腐层实现"),
    ADAPTER_ACL_IMPL("adapterimpl,aclimpl","适配器防腐层实现"),

    //这里的mapper只是为了辅助代码生成，严格来说不算领域元素
    DYNAMIC_MAPPER("mapper","动态mapper"),



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
