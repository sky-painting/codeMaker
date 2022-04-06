package com.tianhua.codemaker.enums.dynamic;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:调用场景枚举
 * date: 2021/10/16
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum InvokeSceneTypeEnum {

    APP_RPC_TO_DOMAIN("app_rpc2domain","应用层->领域层"),
    APP_HTTP_TO_DOMAIN("app_http2domain","应用层->领域层"),
    APP_RPC_TO_SELF("app_rpc2self","应用层->应用层"),
    APP_HTTP_TO_SELF("app_http2self","应用层->应用层"),
    APP_RPC_TO_INFRAST("app_rpc2infrast","应用层->基础设施层"),
    APP_HTTP_TO_INFRAST("app_http2infrast","应用层->基础设施层"),

    DOMAIN_TO_INFRAST("domain2infrast","领域层->基础设施层"),
    DOMAIN_TO_SELF("domain2self","领域层->领域层"),

    INFRAST_TO_SELF("infrast2self","基础设施层->依赖服务"),
    INFRAST_TO_API("infrast2api","基础设施层->依赖服务层"),

    ;
    private String code;
    private String desc;
    InvokeSceneTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    /**
     * 根据调用类和被调用类判断调用场景
     * @param invokeClassName
     * @param providerClassName
     * @return
     */
    public static String getInvokeScene(String invokeClassName,String providerClassName){
        String invokerLayer = InvokeLayerTypeEnum.getLayerCode(invokeClassName);
        String providerLayer = InvokeLayerTypeEnum.getLayerCode(providerClassName);
        //这里增加一个infrast 到下游调用方的调用层类型判断
        if(invokerLayer.equals(InvokeLayerTypeEnum.INFRAST.getCode()) && StringUtils.isEmpty(providerLayer)){
            return InvokeSceneTypeEnum.INFRAST_TO_API.code;
        }
        if(StringUtils.isEmpty(invokerLayer) || StringUtils.isEmpty(providerLayer)){
            return null;
        }

        for (InvokeSceneTypeEnum invokeSceneTypeEnum : InvokeSceneTypeEnum.values()){
            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.APP_RPC.getCode())){
                return InvokeSceneTypeEnum.APP_RPC_TO_SELF.getCode();
            }

            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.APP_HTTP.getCode())){
                return InvokeSceneTypeEnum.APP_HTTP_TO_SELF.getCode();
            }

            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.DOMAIN.getCode())){
                return InvokeSceneTypeEnum.DOMAIN_TO_SELF.getCode();
            }

            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.INFRAST.getCode())){
                return InvokeSceneTypeEnum.INFRAST_TO_SELF.getCode();
            }

            if(invokeSceneTypeEnum.getCode().contains(invokerLayer) && invokeSceneTypeEnum.getCode().contains(providerLayer)){
                return invokeSceneTypeEnum.getCode();
            }
        }


        return null;
    }

    /**
     * 根据调用方向判断调用场景
     * @param invokeSceneStr
     * @param
     * @return
     */
    public static String getInvokeSceneV2(String invokeSceneStr){

        String invokerLayer = InvokeLayerTypeEnum.getInvokeLayerCode(invokeSceneStr);
        String providerLayer = InvokeLayerTypeEnum.getProvideLayerCode(invokeSceneStr);

        if(invokerLayer.equals(InvokeLayerTypeEnum.INFRAST.getCode()) && StringUtils.isEmpty(providerLayer)){
            return InvokeSceneTypeEnum.INFRAST_TO_API.code;
        }



        if(StringUtils.isEmpty(invokerLayer) || StringUtils.isEmpty(providerLayer)){
            return null;
        }

        for (InvokeSceneTypeEnum invokeSceneTypeEnum : InvokeSceneTypeEnum.values()){
            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.APP_RPC.getCode())){
                return InvokeSceneTypeEnum.APP_RPC_TO_SELF.getCode();
            }

            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.APP_HTTP.getCode())){
                return InvokeSceneTypeEnum.APP_HTTP_TO_SELF.getCode();
            }

            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.DOMAIN.getCode())){
                return InvokeSceneTypeEnum.DOMAIN_TO_SELF.getCode();
            }

            if(invokerLayer.equals(providerLayer) && providerLayer.equals(InvokeLayerTypeEnum.INFRAST.getCode())){
                return InvokeSceneTypeEnum.INFRAST_TO_SELF.getCode();
            }

            if(invokeSceneTypeEnum.getCode().contains(invokerLayer) && invokeSceneTypeEnum.getCode().contains(providerLayer)){
                return invokeSceneTypeEnum.getCode();
            }
        }


        return null;
    }


}
