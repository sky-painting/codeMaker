package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.enums.VisibilityEnum;
import com.coderman.codemaker.enums.dynamic.InvokeLayerTypeEnum;
import com.coderman.codemaker.enums.dynamic.InvokeSceneTypeEnum;
import com.coderman.codemaker.service.ImportPackageService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.Optional;

/**
 * Description:
 * date: 2021/10/16
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class MethodInvokeService {

    @Resource(name = "appRpcInvokeHandler")
    private InvokeHandler appRpcInvokeHandler;

    @Resource(name = "appHttpInvokeHandler")
    private InvokeHandler appHttpInvokeHandler;

    @Resource(name = "domainInvokeHandler")
    private InvokeHandler domainInvokeHandler;

    @Resource(name = "infrastInvokeHandler")
    private InvokeHandler infrastInvokeHandler;


    @Autowired
    private ImportPackageService importPackageService;

    @Autowired
    private ConvertInvokeService convertInvokeService;

    @Autowired
    private ReturnBodyFactoryService returnBodyFactoryService;

    /**
     * 处理方法调用内容
     * @param invokeBean
     */
    public void dealInvokeContent(InvokeContextBean invokeBean){

        if(!invokeBean.getInvokerMethodBean().getMethodName().toLowerCase().startsWith(invokeBean.getInvokerMethod().toLowerCase())){
            return;
        }

        String  providerClassMethod = getProviderClassMethod(invokeBean);
        String returnStr = invokeBean.getProviderClassMethod().getReturnClass();
        String invokeRowReturn = buildReturnBody(returnStr,invokeBean.getInvokerMethodBean());
        if (StringUtils.isNotEmpty(invokeRowReturn)) {
            invokeRowReturn = invokeRowReturn + " = ";
        }

        String providerClassVar = invokeBean.getProviderClassName().substring(0,1).toLowerCase()+invokeBean.getProviderClassName().substring(1);
        String invokeRowContent = invokeRowReturn + providerClassVar +"."+providerClassMethod;
        invokeBean.buildCurrentInvokeRow(invokeRowContent);
        //注册调用层,根据调用层间关系判断是否需要增加do,bo,vo之间的转换接口
        registConvertLayer(invokeBean);
        //动态构建调用方的方法返回值
        returnBodyFactoryService.refreshReturnBody(invokeBean);
    }


    /**
     * 动态绘制调用方法详情
     * @param invokeBean
     * @return
     */
    private String getProviderClassMethod(InvokeContextBean invokeBean){
        String  providerClassMethod = invokeBean.getProviderClassMethod().getMethodName();
        if(!providerClassMethod.contains("(")){
            providerClassMethod = providerClassMethod+"()";
        }
        String methodName = providerClassMethod.substring(0,providerClassMethod.indexOf("("));
        String params = providerClassMethod.substring(providerClassMethod.indexOf("(")+1).replace(")","");
        providerClassMethod = methodName+"("+getProviderClassMethodParamStr(invokeBean.getInvokerMethodBean(), params)+")";
        return providerClassMethod;
    }

    /**
     * 根据调用链路内容动态绘制方法参数
     * @param invokeMethod
     * @param providerMethodParams
     * @return
     */
    private String getProviderClassMethodParamStr(MethodBean invokeMethod,String providerMethodParams){
        if(StringUtils.isEmpty(providerMethodParams)){
            return "";
        }
        //如果调用内容为空，则校验接口参数调用者与被调用者是否存在一致，存在则保持一致
        if(CollectionUtils.isEmpty(invokeMethod.getInvokeMethodList())){
            if(invokeMethod.getMethodName().contains("()")){
                return "";
            }
            String [] invokerMethodParamArr = invokeMethod.getMethodName().replace(")","").split("\\(")[1].split(",");
            String [] providerMethodParamArr = providerMethodParams.split(",");
            StringBuilder paramBuilder = new StringBuilder();
            for (int i = 0;i < providerMethodParamArr.length;i++){
                String providerParamType = "";
                String providerParamVar = "";
                if(providerMethodParamArr[i].contains(" ")){
                    providerParamType = providerMethodParamArr[i].trim().split(" ")[0];
                    providerParamVar = providerMethodParamArr[i].trim().split(" ")[1];
                }else {
                    providerParamType = providerMethodParamArr[i];
                    providerParamVar = invokeMethod.getParamVar(providerParamType);
                }


                boolean find = false;
                for (int j = 0;j < invokerMethodParamArr.length;j++){
                    String invokerParamType = invokerMethodParamArr[j].trim().split(" ")[0];
                    String invokerParamVar = invokerMethodParamArr[j].trim().split(" ")[1];
                    if(providerParamType.equals(invokerParamType) && providerParamVar.equals(invokerParamVar)){
                        find = true;
                        break;
                    }
                }
                if(find){
                    paramBuilder.append(providerParamVar+", ");
                }else {
                    paramBuilder.append("null, ");
                }
            }
            if(paramBuilder.length() == 0 || StringUtils.isEmpty(paramBuilder.toString())){
                return "";
            }
            return paramBuilder.substring(0,paramBuilder.length() - 2);
        }


        String [] paramArr = providerMethodParams.split(",");
        StringBuilder paramBuilder = new StringBuilder();
        for (String param : paramArr){
            String paramType = param.trim().split(" ")[0];
            String paramVar = param.trim().split(" ")[1];
            boolean find = false;
            for (String invokeContent : invokeMethod.getInvokeMethodList()){
                if(!invokeContent.contains(paramType)){
                    continue;
                }
                find = true;
            }

            if(find){
                paramBuilder.append(paramVar+", ");
            }else {
                paramBuilder.append("null, ");
            }
        }

        if(paramBuilder.length() == 0 || StringUtils.isEmpty(paramBuilder.toString())){
            return "";
        }
        return paramBuilder.substring(0,paramBuilder.length() - 2);
    }


    /**
     * 构建调用方法返回体
     * @param returnStr
     * @param invokeMethodBean
     * @return
     */
    private String buildReturnBody(String returnStr,MethodBean invokeMethodBean){
        String invokeRowReturn = "";
        if(returnStr.contains("void")){
            return invokeRowReturn;
        }

        if(returnStr.toLowerCase().contains("Integer") && !returnStr.contains("<")){
            invokeRowReturn = "Integer integerVar";
            //return invokeRowReturn;
        }

        else if(returnStr.toLowerCase().contains("int")){
            invokeRowReturn = "int intVar";
            //return invokeRowReturn;
        }


        else if(returnStr.toLowerCase().contains("short")){
            invokeRowReturn = "short shortVar";
            //return invokeRowReturn;
        }

        else if(returnStr.toLowerCase().contains("String") && !returnStr.contains("<")){
            invokeRowReturn = "String strVar";
           //return invokeRowReturn;
        }

        else if(returnStr.toLowerCase().contains("boolean")  && !returnStr.contains("<")){
            invokeRowReturn = "boolean booleanVar";
            //return invokeRowReturn;
        }


        else if(returnStr.contains("Long") && !returnStr.contains("<")){
            invokeRowReturn = "Long longVar";
            //return invokeRowReturn;
        }

        else if(returnStr.contains("long")){
            invokeRowReturn = "long longVar";
            //return invokeRowReturn;
        }


        else if(!returnStr.contains("void") && !returnStr.contains("<")){
            String returnVar = returnStr.substring(0,1).toLowerCase()+returnStr.substring(1);
            invokeRowReturn = returnStr + " " + returnVar;
        }

        else if(returnStr.trim().toLowerCase().contains("<")){
            invokeRowReturn = returnStr + " " +convertInvokeService.buildReturnVar(returnStr);
        }

        String finalInvokeRowReturn = invokeRowReturn;
        Optional<InvokeRowBean> optional = invokeMethodBean.getInvokeRowBeanList().stream().filter(invokeRowBean -> finalInvokeRowReturn.endsWith(invokeRowBean.getReturnClassValue())).findFirst();
        if(optional.isPresent()){
            return invokeRowReturn + invokeMethodBean.getInvokeRowBeanList().size();
        }
        return invokeRowReturn;
    }

    /**
     * 注册被调用方作为调用方的属性
     * @param invokerClassBean
     * @param providerClassBean
     */
    public void registField(AbstractClassBean invokerClassBean,AbstractClassBean providerClassBean){
        String targetClassName = providerClassBean.getClassName();
        //注册被调用方的field
        if(CollectionUtils.isEmpty(invokerClassBean.getFieldBeanList())){
            invokerClassBean.setFieldBeanList(Lists.newArrayList());
        }
        FieldBean fieldBean = new FieldBean();
        fieldBean.setVisibility(VisibilityEnum.PRIVATE.getVisibility());
        fieldBean.setFieldName(targetClassName+ " " +targetClassName.substring(0,1).toLowerCase() + targetClassName.substring(1));
        Optional<FieldBean> fieldBeanOptional = invokerClassBean.getFieldBeanList().stream().filter(fieldBean1 -> fieldBean1.getFieldName().toLowerCase().contains(targetClassName.toLowerCase())).findFirst();
        if(fieldBeanOptional.isPresent()){
            return;
        }
        invokerClassBean.getFieldBeanList().add(fieldBean);
        invokerClassBean.getDynamicImportPackageList().add(providerClassBean.getPackageName()+"."+targetClassName);
    }

    /**
     * 注册被调用方的方法返回值所属包作为调用方的引用包
     * @param invokerClassBean
     * @param plantUmlContextBean
     * @param importClassName
     */
    public void registImportPackageByClass(AbstractClassBean invokerClassBean, PlantUmlContextBean plantUmlContextBean, String importClassName){
        if(StringUtils.isEmpty(importClassName) || importClassName.contains("void")){
            return;
        }

        if(CollectionUtils.isEmpty(invokerClassBean.getDynamicImportPackageList())){
            invokerClassBean.setDynamicImportPackageList(Lists.newArrayList());
        }

        importClassName = importClassName.trim();
        if(importClassName.contains("<")){
            String [] arr = importClassName.split("<");
            for (String content : arr){
                String tempContent = content.replace(">","").trim();
                String packageName = importPackageService.getMatchPackageDefault(tempContent);

                if(StringUtils.isEmpty(packageName)){
                    importPackageService.dealImportPackage(invokerClassBean,plantUmlContextBean,tempContent);
                    continue;
                }
                invokerClassBean.getDynamicImportPackageList().add(packageName);
            }
            return;
        }
        importPackageService.dealImportPackage(invokerClassBean,plantUmlContextBean,importClassName);
    }

    /**
     * 根据场景动态判断是否需要调用转换层进行数据转换
     * @param invokeBean
     */
    private void  registConvertLayer(InvokeContextBean invokeBean){
        String invokeSceneType = invokeBean.getInvokeSceneType();
        //是应用层app_rpc作为调用方
        if(invokeSceneType.startsWith(InvokeLayerTypeEnum.APP_RPC.getCode())
                ||invokeSceneType.equals(InvokeSceneTypeEnum.APP_RPC_TO_SELF.getCode())){
            appRpcInvokeHandler.dealInvoke(invokeBean);
        }
        //是应用层app_http作为调用方
        else if(invokeSceneType.startsWith(InvokeLayerTypeEnum.APP_HTTP.getCode())
            ||invokeSceneType.equals(InvokeSceneTypeEnum.APP_HTTP_TO_SELF.getCode())){
            appHttpInvokeHandler.dealInvoke(invokeBean);
        }
        //是领域层domain作为调用方
        else if(invokeBean.getInvokeSceneType().startsWith(InvokeLayerTypeEnum.DOMAIN.getCode())) {
            domainInvokeHandler.dealInvoke(invokeBean);
        }
        //是基础设施层infrast作为调用方
        else if(invokeBean.getInvokeSceneType().startsWith(InvokeLayerTypeEnum.INFRAST.getCode())) {
            infrastInvokeHandler.dealInvoke(invokeBean);
        }
    }

}
