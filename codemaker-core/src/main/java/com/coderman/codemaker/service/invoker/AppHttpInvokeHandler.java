package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.enums.dynamic.InvokeLayerTypeEnum;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Description: 应用层app_http调用方 方法绘制处理器
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service(value = "appHttpInvokeHandler")
public class AppHttpInvokeHandler implements InvokeHandler {
    @Autowired
    private ConvertInvokeHandler convertInvokeHandler;

    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {

        //写场景,对调用方的方法参数vo->bo
         if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.WRITE.getCode())){

            InterfaceBean bovoConvertInterfaceBean = convertInvokeHandler.getBOVOConvert(invokeBean);
            if(bovoConvertInterfaceBean == null){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

                return;
            }

            MethodBean convertMethod = convertInvokeHandler.getBO2VOConvertMethod(bovoConvertInterfaceBean,invokeBean.getMethodBean(),"vo2bo(");
            if(convertMethod == null){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

                return;
            }

            String [] paramArr = invokeBean.getMethodBean().getParamArr();
            String convertParamType = convertMethod.getParamArr()[0];
            String convertParamStr = "null";
            for (String param : paramArr){
                String paramClassType = param.trim().split(" ")[0];
                String convertClassType = convertParamType.trim().split(" ")[0];
                if(paramClassType.equals(convertClassType)){
                    convertParamStr = param.trim().split(" ")[1];
                }
            }
            String returnClassName = convertMethod.getReturnClass();
            String invokeRow = returnClassName +" "
                    + returnClassName.substring(0,1).toLowerCase()
                    + returnClassName.substring(1)
                    + " = " + bovoConvertInterfaceBean.getClassName() + ".INSTANCE."+convertMethod.getSimplMethodName()+"("+convertParamStr+")";
            Optional<String> oldRow = invokeBean.getMethodBean().getInvokeMethodList().stream().filter(str->str.equals(invokeRow)).findFirst();
            if(oldRow.isPresent()){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }
            invokeBean.getMethodBean().getInvokeMethodList().add(invokeRow);
            invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

            //需要引用convert接口的时候注册引用包
            if(CollectionUtils.isEmpty(invokeBean.getInvokerClassBean().getDynamicImportPackageList())){
                invokeBean.getInvokerClassBean().setDynamicImportPackageList(new ArrayList<>());
            }


            ClassBean returnClassBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(returnClassName);
            if(returnClassBean == null){
                returnClassBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(returnClassName);
            }
            if(returnClassBean != null){
                invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(returnClassBean.getPackageName()+"."+returnClassBean.getClassName());
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(bovoConvertInterfaceBean.getPackageName()+"."+bovoConvertInterfaceBean.getClassName());
        }

         //读场景,对被调用方的返回值bo->vo
         else if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.READ.getCode())){
            InterfaceBean bodtoConvertInterfaceBean = convertInvokeHandler.getBOVOConvert(invokeBean);
            if(bodtoConvertInterfaceBean == null){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }
            MethodBean convertMethod = convertInvokeHandler.getBO2VOConvertMethod(bodtoConvertInterfaceBean,invokeBean.getMethodBean(),"bo2vo(");
            if(convertMethod == null){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            String [] paramArr = invokeBean.getMethodBean().getParamArr();
            String convertParamType = convertMethod.getParamArr()[0];
            String convertParamStr = "null";
            for (String param : paramArr){
                if(param.startsWith(convertParamType)){
                    convertParamStr = param.trim().split(" ")[1];
                }
            }
            String returnClassName = convertMethod.getReturnClass();
            String invokeRow = returnClassName +" "
                    + returnClassName.substring(0,1).toLowerCase()
                    + returnClassName.substring(1)
                    + " = " + bodtoConvertInterfaceBean.getClassName() + ".INSTANCE."+convertMethod.getSimplMethodName()+"("+convertParamStr+")";
            Optional<String> oldRow = invokeBean.getMethodBean().getInvokeMethodList().stream().filter(str->str.equals(invokeRow)).findFirst();
            if(oldRow.isPresent()){
                invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }
             invokeBean.getMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
            invokeBean.getMethodBean().getInvokeMethodList().add(invokeRow);
            //需要引用convert接口的时候注册引用包
            if(CollectionUtils.isEmpty(invokeBean.getInvokerClassBean().getDynamicImportPackageList())){
                invokeBean.getInvokerClassBean().setDynamicImportPackageList(new ArrayList<>());
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(bodtoConvertInterfaceBean.getPackageName()+"."+bodtoConvertInterfaceBean.getClassName());
         }
         else {
             invokeBean.getMethodBean().getInvokeMethodList().add(invokeBean.getCurrentInvokeRowContent());
         }

    }
}
