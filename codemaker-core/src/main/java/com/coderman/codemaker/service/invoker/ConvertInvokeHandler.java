package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ConvertInvokeHandler {

    /**
     * 获取bovoconvert 接口类
     * @param invokeBean
     * @return
     */
    public InterfaceBean getBOVOConvert(InvokeContextBean invokeBean){
        String returnClassType = invokeBean.getMethodBean().buildReturnClassType();
        ClassBean classBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(returnClassType);
        if(classBean == null){
            classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(returnClassType);
        }
        if(classBean == null){
            return null;
        }
        return invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean()
                .getInterfaceBeanMap()
                .get(classBean.getBovoConvertInterface());
    }

    /**
     * 从转换接口中过滤符合转换条件参数的转换方法
     * @param convertInterfaceBean
     * @param invokeBean
     * @param methodTag
     * @return
     */
    public MethodBean getConvertMethod(InterfaceBean convertInterfaceBean, InvokeContextBean invokeBean,String methodTag){
        List<MethodBean> methodBeanList = convertInterfaceBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().toLowerCase().startsWith(methodTag)).collect(Collectors.toList());
        if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.READ.getCode()) && CollectionUtils.isNotEmpty(methodBeanList)){
            return methodBeanList.get(0);
        }
        String [] invokerParamArr = invokeBean.getMethodBean().getParamArr();

        for (MethodBean methodBean : methodBeanList){
            String [] providerConvertParamArr = methodBean.getParamArr();
            for (int i = 0;i < providerConvertParamArr.length;i ++ ){
                for (int j = 0;j< invokerParamArr.length;j ++){
                    String providerConvertParamType = providerConvertParamArr[i].trim().split(" ")[0];
                    String invokerParamType = invokerParamArr[j].trim().split(" ")[0];
                    if(invokerParamType.equals(providerConvertParamType)){
                        return methodBean;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 从转换接口中过滤符合转换条件参数的转换方法
     * @param convertInterfaceBean
     * @param invokerMethodBean
     * @param methodTag
     * @return
     */
    public MethodBean getBO2VOConvertMethod(InterfaceBean convertInterfaceBean, MethodBean invokerMethodBean,String methodTag){
        List<MethodBean> methodBeanList = convertInterfaceBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().toLowerCase().startsWith(methodTag)).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(methodBeanList)){
            return methodBeanList.get(0);
        }
        return null;
    }
    /**
     * 从调用方的方法名称中扣出BO类 className
     * 并且找到bodoConvert类
     * @param invokeBean
     * @return
     */
    public InterfaceBean getBODOConvertInterfaceBean(InvokeContextBean invokeBean){
        String paramContent = invokeBean.getMethodBean().getMethodName().split("\\(")[1].replace(")","");
        String [] paramArr = paramContent.split(",");
        for (String param : paramArr){
            String [] arr = param.trim().split(" ");
            if(!arr[0].toLowerCase().endsWith("bo")){
                continue;
            }
            ClassBean boClassBeanElement = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(arr[0]);
            if(StringUtils.isEmpty(boClassBeanElement.getBodoConvertInterface())){
                continue;
            }
            return invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(boClassBeanElement.getBodoConvertInterface());
        }
        return null;
    }


}
