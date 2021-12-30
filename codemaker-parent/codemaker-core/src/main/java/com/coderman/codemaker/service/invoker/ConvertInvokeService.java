package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.utils.StringHandleUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description:bo,vo,dto之间的转换调用处理器
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ConvertInvokeService {

    /**
     * 获取bovoconvert 接口类
     * @param invokeBean
     * @return
     */
    public InterfaceBean getBOVOConvert(InvokeContextBean invokeBean){
        //根据调用方的返回值进行查找---适用于读场景和写场景返回具体vo,dto的情况
        String returnClassStr = invokeBean.getInvokerMethodBean().getReturnClass();
        String [] returnClassArr = returnClassStr.split("<");
        ClassBean classBean = null;
        for (String returnClass : returnClassArr){
            String classStr = returnClass.replace(">","");
            classBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(classStr);
            if(classBean == null){
                classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(classStr);
            }
            if(classBean != null){
                break;
            }
        }

        //根据调用方的参数进行查找---适用于写场景的入参是具体vo,dto的情况,还有读场景是具体查询xxvo,xxdto的情况
        if(classBean == null){
            String [] paramArr = invokeBean.getInvokerMethodBean().getParamArr();
            if(paramArr == null || paramArr.length == 0){
                return null;
            }
            for (String param : paramArr){
                String classStr = param;
                if(classStr.contains(" ")){
                    classStr = classStr.split(" ")[0];
                }
                if(classStr.contains("<")){
                    classStr = classStr.split("<")[1].replace(">","");
                }
                classBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(classStr);
                if(classBean == null){
                    classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(classStr);
                }
            }
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
     * @return
     */
    public MethodBean getDTO2BOConvertMethod(InterfaceBean convertInterfaceBean, InvokeContextBean invokeBean){
        //写场景根据调用方的方法参数寻找合适的转换方法
        String [] paramArr = invokeBean.getInvokerMethodBean().getParamArr();

        AtomicReference<MethodBean> methodBean = new AtomicReference<>();

        convertInterfaceBean.getMethodBeanList().stream().forEach(convertMethod -> {
            for (String param : paramArr){
                if(convertMethod.paramMatchOne(param)){
                    methodBean.set(convertMethod);
                }
            }
        });
        return methodBean.get();
    }


    /**
     * 从转换接口中过滤符合转换条件参数的转换方法
     * @param convertInterfaceBean
     * @param invokeBean
     * @return
     */
    public MethodBean getConvertMethod(InterfaceBean convertInterfaceBean, InvokeContextBean invokeBean){
        //读场景根据调用方的返回值寻找合适的转换方法
        String returnClass = invokeBean.getInvokerMethodBean().getReturnClass();
        boolean isList = false;
        if(returnClass.toLowerCase().contains("page") || returnClass.toLowerCase().contains("list")){
            isList = true;
        }

        String returnClassModelType = invokeBean.getInvokerMethodBean().getReturnClassTypeModel();
        AtomicReference<MethodBean> methodBean = new AtomicReference<>();
        boolean finalIsList = isList;
        convertInterfaceBean.getMethodBeanList().stream().forEach(convertMethod -> {
            if(finalIsList && convertMethod.getReturnClass().trim().toLowerCase().contains("list") && convertMethod.getReturnClass().trim().toLowerCase().contains(returnClassModelType.toLowerCase())) {
                methodBean.set(convertMethod);
            }
            if(!finalIsList  && convertMethod.getReturnClass().toLowerCase().equals(returnClassModelType.toLowerCase())) {
                methodBean.set(convertMethod);
            }
        });

        return methodBean.get();
    }



    /**
     * 从转换接口中过滤符合转换条件参数的转换方法
     * @param convertInterfaceBean
     * @param invokerMethodBean
     * @return
     */
    public MethodBean getVO2BOConvertMethod(InterfaceBean convertInterfaceBean, MethodBean invokerMethodBean){
        //写场景根据调用方的方法参数寻找合适的转换方法
        String [] paramArr = invokerMethodBean.getParamArr();

        AtomicReference<MethodBean> methodBean = new AtomicReference<>();

        convertInterfaceBean.getMethodBeanList().stream().forEach(convertMethod -> {
            for (String param : paramArr){
                if(convertMethod.paramMatchOne(param)){
                    methodBean.set(convertMethod);
                }
            }
        });
        return methodBean.get();
    }

    /**
     * 从转换接口中过滤符合转换条件参数的转换方法
     * bo->vo
     * 主要用于读场景进行转换
     * @param convertInterfaceBean
     * @param invokerMethodBean
     * @return
     */
    public MethodBean getBO2VOConvertMethod(InterfaceBean convertInterfaceBean, MethodBean invokerMethodBean){
        //读场景根据调用方的返回值寻找合适的转换方法
        String returnClass = invokerMethodBean.getReturnClass();
        boolean isList = false;
        if(returnClass.toLowerCase().contains("page") || returnClass.toLowerCase().contains("list")){
            isList = true;
        }

        String returnClassModelType = invokerMethodBean.getReturnClassTypeModel();
        AtomicReference<MethodBean> methodBean = new AtomicReference<>();
        boolean finalIsList = isList;
        convertInterfaceBean.getMethodBeanList().stream().forEach(convertMethod -> {
            if(finalIsList && convertMethod.getReturnClass().trim().toLowerCase().contains("list") && convertMethod.getReturnClass().trim().toLowerCase().contains(returnClassModelType.toLowerCase())) {
                methodBean.set(convertMethod);
            }
            if(!finalIsList  && convertMethod.getReturnClass().toLowerCase().equals(returnClassModelType.toLowerCase())) {
                methodBean.set(convertMethod);
            }
        });

        return methodBean.get();
    }

    /**
     * 从调用方的方法名称中扣出BO类 className
     * 并且找到bodoConvert类
     * @param invokeBean
     * @return
     */
    public InterfaceBean getBODOConvertInterfaceBean(InvokeContextBean invokeBean){
        String paramContent = invokeBean.getInvokerMethodBean().getMethodName().split("\\(")[1].replace(")","");
        String [] paramArr = paramContent.split(",");
        InterfaceBean interfaceBean = null;
        for (String param : paramArr){
            String [] arr = param.trim().split(" ");
            if(!arr[0].toLowerCase().endsWith("bo")){
                continue;
            }
            ClassBean boClassBeanElement = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(arr[0]);
            if(boClassBeanElement == null || StringUtils.isEmpty(boClassBeanElement.getBodoConvertInterface())){
                continue;
            }
            interfaceBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(boClassBeanElement.getBodoConvertInterface());
        }
        if(interfaceBean != null){
            return interfaceBean;
        }

        String classModel = invokeBean.getInvokerMethodBean().getReturnClassTypeModel();
        if(classModel.toLowerCase().endsWith(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            ClassBean boClassBeanElement = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(classModel);
            interfaceBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(boClassBeanElement.getBodoConvertInterface());
        }
        return interfaceBean;
    }

    /**
     * 构建变量名
     * @param returnClassName
     * @return
     */
    public String buildReturnVar(String returnClassName){
        if(returnClassName.contains(".")){
            String [] arr = returnClassName.split("\\.");
            String returnClass = arr[arr.length - 1].replace(">","");
            return returnClass.substring(0,1).toLowerCase() + returnClass.substring(1);
        }
        if(returnClassName.contains("<")){
            if(returnClassName.toLowerCase().contains("list")){
                //List<List<xxxx>>
                String [] classArr = returnClassName.split("<");
                String modelClass = "";
                for (String str : classArr){
                    String className = str.replace(">","");
                    if(className.toLowerCase().endsWith("bo") || className.toLowerCase().endsWith("dto") || className.toLowerCase().endsWith("vo") || className.toLowerCase().endsWith("do")){
                        modelClass = className;
                        break;
                    }
                }

                if(StringUtils.isEmpty(modelClass)){
                    return "list";
                }

                String returnVar = modelClass+"List";
                return returnVar.substring(0,1).toLowerCase() + returnVar.substring(1);
            }
            if(returnClassName.endsWith("<")){
                return returnClassName.replace("<","");
            }
        }
        return returnClassName.substring(0,1).toLowerCase() + returnClassName.substring(1);
    }


    /**
     * 构建参数转换调用内容
     * @param invokeBean
     * @param convertMethod
     * @param convertInterfaceName
     * @return
     */
    public InvokeRowBean buildInvokeConvertRow(InvokeContextBean invokeBean,MethodBean convertMethod,String convertInterfaceName){
        String [] paramArr = invokeBean.getInvokerMethodBean().getParamArr();
        String convertParamType = convertMethod.getParamArr()[0];
        if(convertParamType.contains(" ")){
            convertParamType = convertParamType.split(" ")[0];
        }
        String convertParamStr = "null";
        for (String param : paramArr){
            String paramType = param;
            String paramVar = param;
            if(paramType.contains(" ")){
                paramType = paramType.split(" ")[0];
                paramVar = paramVar.split(" ")[1];
            }else {
                paramVar = paramVar.substring(0,1).toUpperCase()+paramVar.substring(1);
            }

            if(paramType.trim().equals(convertParamType.trim())){
                convertParamStr = paramVar;
            }
        }

        /**
         * 如果调用方的方法参数无法当转换类接口方法的方法参数
         * 则尝试从调用行内容找到合适的方法参数
         */
        if(convertParamStr.equals("null")){
            List<InvokeRowBean> invokeRowBeanList = invokeBean.getInvokerMethodBean().getInvokeRowBeanList();
            if(CollectionUtils.isNotEmpty(invokeRowBeanList)){
                for (InvokeRowBean invokeRowBean : invokeRowBeanList){
                    String convertParamClass = convertParamType.trim().split(" ")[0];
                    if(! convertParamClass.equals(invokeRowBean.getReturnClassName())){
                        continue;
                    }
                    convertParamStr = invokeRowBean.getReturnClassValue();
                }
            }
        }


        String returnClassName = convertMethod.getReturnClass();

        InvokeRowBean invokeRowBean = new InvokeRowBean();
        invokeRowBean.setProviderClassName(convertInterfaceName);
        invokeRowBean.setProviderClassNameVar(convertInterfaceName+".INSTANCE");
        invokeRowBean.setProviderMethodName(convertMethod.getSimplMethodName());
        invokeRowBean.setReturnClassName(returnClassName);
        invokeRowBean.setReturnClassValue(this.buildReturnVar(returnClassName));
        invokeRowBean.setProviderMethodParamTypeArr(convertMethod.getParamArr());
        invokeRowBean.setProviderMethodParamValueArr(new String[]{convertParamStr});
        return invokeRowBean;
    }


    /**
     * 通过返回值找对应的转换方法
     * @param interfaceBean
     * @param returnClassName
     * @return
     */
    public MethodBean getDOBOConvertMethod( InterfaceBean interfaceBean,String returnClassName){
        for(MethodBean methodBean : interfaceBean.getMethodBeanList()){
            for (String paramStr : methodBean.getParamArr()){
                if(paramStr.startsWith(returnClassName)){
                    return methodBean;
                }
            }
        }
        return null;
    }

    /**
     * 构建一个dto到dto的转换类-->调用下游的参数转换--参数请求转换
     * @param invokeBean
     * @return
     */
    public MethodBean getDTO2DTOConvertMethodParamRequest(InvokeContextBean invokeBean){
        if(invokeBean.getInvokerMethodBean().getReturnClass().equals("void")){
            return null;
        }

        String[] params = invokeBean.getInvokerMethodBean().getParamArr();
        String params1 = params[0];

        String paramType = params1.split(" ")[0];
        if(StringHandleUtils.isBasicType(paramType)){
            return null;
        }

        String  [] returnClassTypeArr = invokeBean.getProviderClassMethod().getGenericParamList().get(0).split("\\.");
        String returnClassType = returnClassTypeArr[returnClassTypeArr.length - 1].replace(">","");
        String paramName = paramType.replace("DTO","").replace("Dto","")
                .replace("Request","")
                .replace("Query","");
        paramName = paramName.substring(0,1).toLowerCase()+paramName.substring(1);
        MethodBean convertMethod = new MethodBean();
        if(returnClassType.equals(paramType)){
            convertMethod.setReturnClass(invokeBean.getProviderClassMethod().getGenericParamList().get(0));
            ClassBean paramBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(paramType+"acl");

            convertMethod.setMethodName(paramName+"ReqConvert"+"("+paramBean.getPackageName()+"."+params1+")");
        }else {
            String [] arr = returnClassType.split("\\.");
            convertMethod.setReturnClass(arr[arr.length - 1].replace(">",""));
            convertMethod.setMethodName(paramName+"ReqConvert"+"("+params1+")");
        }


        convertMethod.buildParamArr();
        return convertMethod;
    }

    /**
     * 构建一个dto到dto的转换类-->调用下游的参数转换--参数请求转换
     * @param invokeBean
     * @return
     */
    public MethodBean getDTO2DTOConvertMethodParamResponse(InvokeContextBean invokeBean){
        if(invokeBean.getInvokerMethodBean().getReturnClass().equals("void")){
            return null;
        }

        String invokeMethodReturnClass = invokeBean.getInvokerMethodBean().getReturnClass();


        MethodBean convertMethod = new MethodBean();

        String  provideMethodReturnClass = invokeBean.getProviderClassMethod().getReturnClass();
        provideMethodReturnClass = StringHandleUtils.getParamTypeNoWrapper(provideMethodReturnClass);
        convertMethod.setReturnClass(invokeMethodReturnClass);
        String paramName = invokeBean.getInvokerMethodBean().getSimplMethodName()
                .replace("query","")
                .replace("get","")
                .replace("List","")
                .replace("find","");


        convertMethod.setMethodName(paramName+"ResConvert"+"("+provideMethodReturnClass+" "+StringHandleUtils.getParamVar(provideMethodReturnClass)+")");

        convertMethod.buildParamArr();
        return convertMethod;
    }
}
