package com.coderman.codemaker.service.invoker.handler;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import com.coderman.codemaker.service.invoker.ConvertInvokeService;
import com.coderman.codemaker.service.invoker.InvokeHandler;
import org.apache.commons.lang3.StringUtils;
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
    private ConvertInvokeService convertInvokeService;

    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {

        //写场景,对调用方的方法参数vo->bo
         if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.WRITE.getCode())){
            InterfaceBean bovoConvertInterfaceBean = convertInvokeService.getBOVOConvert(invokeBean);
            if(bovoConvertInterfaceBean == null){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

             MethodBean convertMethod = convertInvokeService.getVO2BOConvertMethod(bovoConvertInterfaceBean,invokeBean.getInvokerMethodBean());
             if(convertMethod == null){
                 invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                 return;
             }

             InvokeRowBean convertInvokeRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod, bovoConvertInterfaceBean.getClassName());
             String convertInvokeRow = convertInvokeRowBean.buildInvokeContent();
             String finalConvertInvokeRow = convertInvokeRow;
             Optional<String> oldRow = invokeBean.getInvokerMethodBean().getInvokeMethodList().stream().filter(str->str.equals(finalConvertInvokeRow)).findFirst();
             if(oldRow.isPresent()){
                 invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                 return;
             }

             invokeBean.getInvokerMethodBean().addInvokeRowContent(convertInvokeRow,convertInvokeRowBean);
             String currentInvokeRowContent = invokeBean.getCurrentInvokeRowBean().refreshInvokeContent(convertInvokeRowBean, invokeBean.getPlantUmlContextBean());
             invokeBean.getInvokerMethodBean().addInvokeRowContent(currentInvokeRowContent,invokeBean.getCurrentInvokeRowBean());

            //需要引用convert接口的时候注册引用包
            if(CollectionUtils.isEmpty(invokeBean.getInvokerClassBean().getDynamicImportPackageList())){
                invokeBean.getInvokerClassBean().setDynamicImportPackageList(new ArrayList<>());
            }
            ClassBean returnClassBean = invokeBean.getPlantUmlContextBean().getClassBeanMap().get(convertMethod.getReturnClass());
            if(returnClassBean == null){
                returnClassBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(convertMethod.getReturnClass());
            }
            if(returnClassBean != null){
                invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(returnClassBean.getPackageName()+"."+returnClassBean.getClassName());
            }
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(bovoConvertInterfaceBean.getPackageName()+"."+bovoConvertInterfaceBean.getClassName());
        }

         //读场景,对被调用方的返回值bo->vo
        if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.READ.getCode())){
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

            InterfaceBean bovoConvertInterfaceBean = convertInvokeService.getBOVOConvert(invokeBean);
            if(bovoConvertInterfaceBean == null){
                return;
            }

            MethodBean convertMethod = convertInvokeService.getBO2VOConvertMethod(bovoConvertInterfaceBean,invokeBean.getInvokerMethodBean());
            if(convertMethod == null){
                return;
            }

            InvokeRowBean invokeRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod, bovoConvertInterfaceBean.getClassName());
            String invokeRow = invokeRowBean.buildInvokeContent();
            Optional<String> oldRow = invokeBean.getInvokerMethodBean().getInvokeMethodList().stream().filter(str->str.equals(invokeRow)).findFirst();
            if(oldRow.isPresent()){
                return;
            }
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeRow,invokeRowBean);
            //需要引用convert接口的时候注册引用包
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(bovoConvertInterfaceBean.getPackageName()+"."+bovoConvertInterfaceBean.getClassName());
         }
        else {
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
        }
    }

}
