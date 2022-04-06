package com.tianhua.codemaker.service.invoker.handler;

import com.tianhua.codemaker.bean.invoke.InvokeContextBean;
import com.tianhua.codemaker.bean.invoke.InvokeRowBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.enums.dynamic.InvokeSceneTypeEnum;
import com.tianhua.codemaker.enums.dynamic.ReadWriteTypeEnum;
import com.tianhua.codemaker.service.invoker.ConvertInvokeService;
import com.tianhua.codemaker.service.invoker.InvokeHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Description:领域层调用方 方法绘制处理器
 * date: 2021/10/21
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service(value = "domainInvokeHandler")
public class DomainInvokeHandler implements InvokeHandler {
    @Autowired
    private ConvertInvokeService convertInvokeService;

    @Autowired
    private DynamicConvertHandler dynamicConvertHandler;

    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {
        /**
         * 如果读写场景为空的话，就重新根据被调用方的方法读写情况判断整体的读写情况
         * 这里可能产生一定的误差：
         * 由于调用方的方法判断读写情况不准确，只能根据被调用方的方法判断，如果都判断不出来则不进行convert转换接口绘制
         */
        if(invokeBean.getInvokeSceneType()
                .equals(InvokeSceneTypeEnum.DOMAIN_TO_INFRAST.getCode()) && StringUtils.isEmpty(invokeBean.getMethodRWType())){
            String rwType = ReadWriteTypeEnum.getCodeByMethod(invokeBean.getProviderClassMethod().getSimplMethodName());
            invokeBean.setMethodRWType(rwType);
        }

        //写场景
        if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.WRITE.getCode())){
            InterfaceBean doboConvertInterfaceBean = convertInvokeService.getBODOConvertInterfaceBean(invokeBean);
            if(doboConvertInterfaceBean == null){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            Optional<MethodBean> convertMethod = doboConvertInterfaceBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().startsWith("bo2do(")).findFirst();
            if(!convertMethod.isPresent()){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }


            InvokeRowBean invokeRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod.get(), doboConvertInterfaceBean.getClassName());
            String invokeRow = invokeRowBean.buildInvokeContent();
            String finalInvokeRow = invokeRow;
            Optional<String> oldRow = invokeBean.getInvokerMethodBean().getInvokeMethodList().stream().filter(str->str.equals(finalInvokeRow)).findFirst();
            if(oldRow.isPresent()){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            invokeBean.getInvokerMethodBean().getInvokeMethodList().add(invokeRow);
            invokeRow = invokeBean.getCurrentInvokeRowBean().refreshInvokeContent(invokeRowBean, invokeBean.getPlantUmlContextBean());
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeRow,invokeBean.getCurrentInvokeRowBean());

            //需要引用convert接口的时候注册引用包
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
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());

            InterfaceBean doboConvertInterfaceBean = convertInvokeService.getBODOConvertInterfaceBean(invokeBean);
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
            if(doboConvertInterfaceBean == null){
                return;
            }
            MethodBean convertMethod = convertInvokeService.getConvertMethod(doboConvertInterfaceBean,invokeBean);
            if(convertMethod == null){
                return;
            }
            InvokeRowBean convertRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod, doboConvertInterfaceBean.getClassName());
            String convertRow = convertRowBean.buildInvokeContent();
            Optional<String> oldConvertRow = invokeBean.getInvokerMethodBean().getInvokeMethodList().stream().filter(str->str.equals(convertRow)).findFirst();

            if(!oldConvertRow.isPresent()){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(convertRow,convertRowBean);
                //需要引用convert接口的时候注册引用包
                invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(doboConvertInterfaceBean.getPackageName()+"."+doboConvertInterfaceBean.getClassName());
            }
        }
        else {
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
            //invokeBean.getInvokerMethodBean().getInvokeMethodList().add(invokeBean.getCurrentInvokeRowContent());
        }

        //对当前调用行背后的相关变量做潜在的对象类型转换，进一步降低书写对象转换的频率
        dynamicConvertHandler.dealInvoke(invokeBean);
    }


}
