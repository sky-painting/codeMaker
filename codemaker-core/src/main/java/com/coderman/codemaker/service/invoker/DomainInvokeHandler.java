package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.enums.dynamic.InvokeSceneTypeEnum;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description:领域层调用方 方法绘制处理器
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service(value = "domainInvokeHandler")
public class DomainInvokeHandler implements InvokeHandler{
    @Autowired
    private ConvertInvokeHandler convertInvokeHandler;


    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {
        /**
         * 如果读写场景为空的话，就重新根据被调用方的方法读写情况判断整体的读写情况
         * 这里可能产生一定的误差：
         * 由于调用方的方法判断读写情况不准确，只能根据被调用方的方法判断，如果都判断不出来则不进行convert转换接口绘制
         */
        if(invokeBean.getInvokeSceneType()
                .equals(InvokeSceneTypeEnum.DOMAIN_TO_INFRAST.getCode()) && StringUtils.isEmpty(invokeBean.getMethodRWType())){
            String rwType = ReadWriteTypeEnum.getCodeByMethod(invokeBean.getProviderClassMethod());
            invokeBean.setMethodRWType(rwType);
        }

        //写场景
        if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.WRITE.getCode())){
            InterfaceBean doboConvertInterfaceBean = convertInvokeHandler.getBODOConvertInterfaceBean(invokeBean);
            if(doboConvertInterfaceBean == null){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            Optional<MethodBean> convertMethod = doboConvertInterfaceBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().startsWith("bo2do(")).findFirst();
            if(!convertMethod.isPresent()){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

                return;
            }

            String paramContent = invokeBean.getMethodBean().getMethodName().split("\\(")[1].replace(")","");
            String [] paramArr = paramContent.split(",");
            String convertParam = convertMethod.get().getMethodName().split("\\(")[1].replace(")","").trim();
            String convertClassName = convertParam.split(" ")[0];
            String convertParamStr = "null";
            for (String param : paramArr){
                if(param.startsWith(convertClassName)){
                    convertParamStr = param.trim().split(" ")[1];
                }
            }

            String returnClassName = convertMethod.get().getReturnClass();
            String invokeRow = returnClassName +" "
                    + returnClassName.substring(0,1).toLowerCase()
                    + returnClassName.substring(1)
                    + " = " + doboConvertInterfaceBean.getClassName() + ".INSTANCE.bo2do("+convertParamStr+")";
            Optional<String> oldRow = invokeBean.getMethodBean().getInvokeMethodList().stream().filter(str->str.equals(invokeRow)).findFirst();
            if(oldRow.isPresent()){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            invokeBean.getMethodBean().getInvokeMethodList().add(invokeRow);
            invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

            if(CollectionUtils.isEmpty(invokeBean.getInvokerClassBean().getDynamicImportPackageList())){
                invokeBean.getInvokerClassBean().setDynamicImportPackageList(new ArrayList<>());
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(doboConvertInterfaceBean.getPackageName()+"."+doboConvertInterfaceBean.getClassName());

            String providerMethodReturnClassName = convertMethod.get().getReturnClass();
            ClassBean returnClassBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(providerMethodReturnClassName);
            if(returnClassBean == null){
                returnClassBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(providerMethodReturnClassName);
            }
            if(returnClassBean == null){
                return;
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(returnClassBean.getPackageName()+"."+returnClassBean.getClassName());
        }


        //读场景
        else if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.READ.getCode())){
            InterfaceBean doboConvertInterfaceBean = convertInvokeHandler.getBODOConvertInterfaceBean(invokeBean);
            if(doboConvertInterfaceBean == null){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

                return;
            }

            Optional<MethodBean> convertMethod = doboConvertInterfaceBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().startsWith("bo2do(")).findFirst();
            if(!convertMethod.isPresent()){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

                return;
            }

            String paramContent = invokeBean.getMethodBean().getMethodName().split("\\(")[1].replace(")","");
            String [] paramArr = paramContent.split(",");
            String convertParam = convertMethod.get().getMethodName().split("\\(")[1].replace(")","").trim();
            String convertClassName = convertParam.split(" ")[0];
            String convertParamStr = "null";
            for (String param : paramArr){
                if(param.startsWith(convertClassName)){
                    convertParamStr = param.trim().split(" ")[1];
                }
            }

            String returnClassName = convertMethod.get().getReturnClass();
            String invokeRow = returnClassName +" "
                    + returnClassName.substring(0,1).toLowerCase()
                    + returnClassName.substring(1)
                    + " = " + doboConvertInterfaceBean.getClassName() + ".INSTANCE.bo2do("+convertParamStr+")";
            Optional<String> oldRow = invokeBean.getMethodBean().getInvokeMethodList().stream().filter(str->str.equals(invokeRow)).findFirst();
            if(oldRow.isPresent()){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

                return;
            }
            invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
            invokeBean.getMethodBean().getInvokeMethodList().add(invokeRow);

            if(CollectionUtils.isEmpty(invokeBean.getInvokerClassBean().getDynamicImportPackageList())){
                invokeBean.getInvokerClassBean().setDynamicImportPackageList(new ArrayList<>());
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(doboConvertInterfaceBean.getPackageName()+"."+doboConvertInterfaceBean.getClassName());

            String providerMethodReturnClassName = convertMethod.get().getReturnClass();
            ClassBean returnClassBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(providerMethodReturnClassName);
            if(returnClassBean == null){
                returnClassBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(providerMethodReturnClassName);
            }
            if(returnClassBean == null){
                return;
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(returnClassBean.getPackageName()+"."+returnClassBean.getClassName());
        }
        else {
            invokeBean.getMethodBean().getInvokeMethodList().add(invokeBean.getCurrentInvokeRowContent());
        }

    }
}
