package com.tianhua.codemaker.enums.dynamic;

import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description: 调用层有哪些调用入口枚举
 * date: 2021/10/16
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum  InvokeLayerTypeEnum {
    APP_RPC("app_rpc", Sets.newHashSet(TemplateFileEnum.FACADE.getTempFileName(),
            TemplateFileEnum.EXE.getTempFileName(),
            TemplateFileEnum.EXE_ABSTRACT.getTempFileName(),
            TemplateFileEnum.EXE_IMPL.getTempFileName(),
            TemplateFileEnum.MQ_LISTENER.getTempFileName(),
            "app_rpc"
            )),

    APP_HTTP("app_http", Sets.newHashSet(
            TemplateFileEnum.CONTROLLER.getTempFileName(),
            "app_http")
    ),

    DOMAIN("domain", Sets.newHashSet(
            TemplateFileEnum.GATAWAY.getTempFileName(),
            TemplateFileEnum.REPOSITORY.getTempFileName(),
            TemplateFileEnum.SERVICE.getTempFileName(),
            TemplateFileEnum.FACTORY.getTempFileName(),
            "domain"
            )),
    INFRAST("infrast", Sets.newHashSet(
            TemplateFileEnum.MQ_PRODUCER.getTempFileName(),
            TemplateFileEnum.MAPPER.getTempFileName(),
            DomainElementEnum.ADAPTER.getElement(),
            TemplateFileEnum.MQ_PRODUCER.getTempFileName(),
            TemplateFileEnum.MQ_HANDLER.getTempFileName(),
            TemplateFileEnum.MQ_CONSUMER.getTempFileName(),
            "infrast"
            )),

    ACL_API("aclapi",Sets.newHashSet("aclapi"))

    ;
    private String code;
    private Set<String> classTypeSet;
    InvokeLayerTypeEnum(String code, Set<String> classTypeSet){
        this.code = code;
        this.classTypeSet = classTypeSet;
    }


    public String getCode() {
        return code;
    }

    public Set<String> getClassTypeSet() {
        return classTypeSet;
    }

    /**
     * 根据类名获取类所在层
     * @param className
     * @return
     */
    public static String getLayerCode(String className){
        AtomicReference<String> layerName = new AtomicReference<>("");
        for (InvokeLayerTypeEnum layerTypeEnum : InvokeLayerTypeEnum.values()){
            layerTypeEnum.getClassTypeSet().forEach(classType->{
                if(className.toLowerCase().endsWith(classType)){
                    layerName.set(layerTypeEnum.code);
                }
            });
        }
        return layerName.get();
    }



    /**
     * 根据类名获取类所在层
     * @param invokeLayerStr
     * @return
     */
    public static String getInvokeLayerCode(String invokeLayerStr){
        AtomicReference<String> layerName = new AtomicReference<>("");
        for (InvokeLayerTypeEnum layerTypeEnum : InvokeLayerTypeEnum.values()){
            layerTypeEnum.getClassTypeSet().forEach(classType->{
                if(invokeLayerStr.toLowerCase().startsWith(classType)){
                    layerName.set(layerTypeEnum.code);
                }
            });
        }
        return layerName.get();
    }

    /**
     * 根据类名获取类所在层
     * @param invokeLayerStr
     * @return
     */
    public static String getProvideLayerCode(String invokeLayerStr){
        AtomicReference<String> layerName = new AtomicReference<>("");
        for (InvokeLayerTypeEnum layerTypeEnum : InvokeLayerTypeEnum.values()){
            layerTypeEnum.getClassTypeSet().forEach(classType->{
                if(invokeLayerStr.toLowerCase().endsWith(classType)){
                    layerName.set(layerTypeEnum.code);
                }
            });
        }
        return layerName.get();
    }
}
