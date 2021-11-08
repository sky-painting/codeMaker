package com.coderman.codemaker.service.invoker.handler;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import com.coderman.codemaker.service.invoker.ConvertInvokeService;
import com.coderman.codemaker.service.invoker.InvokeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Description:应用层app_rpc调用方 方法绘制处理器
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service(value = "appRpcInvokeHandler")
public class AppRpcInvokeHandler implements InvokeHandler {
    @Autowired
    private ConvertInvokeService convertInvokeService;

    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {
        //写场景,对调用方的方法参数dto->bo
        if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.WRITE.getCode())){
            InterfaceBean bodtoConvertInterfaceBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(invokeBean.getInvokerClassBean().getBodtoConvertInterface());
            if(bodtoConvertInterfaceBean == null){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            MethodBean convertMethod = convertInvokeService.getDTO2BOConvertMethod(bodtoConvertInterfaceBean,invokeBean);

            if(convertMethod == null){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }
            InvokeRowBean convertInvokeRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod, bodtoConvertInterfaceBean.getClassName());
            String convertInvokeRow = convertInvokeRowBean.buildInvokeContent();
            String finalConvertInvokeRow = convertInvokeRow;
            Optional<String> oldRow = invokeBean.getInvokerMethodBean().getInvokeMethodList().stream().filter(str->str.equals(finalConvertInvokeRow)).findFirst();
            if(oldRow.isPresent()){
                invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
                return;
            }

            invokeBean.getInvokerMethodBean().addInvokeRowContent(convertInvokeRow,convertInvokeRowBean);
            String currentInvokeRowContent = invokeBean.getCurrentInvokeRowBean().refreshInvokeContent(convertInvokeRowBean,invokeBean.getPlantUmlContextBean());
            invokeBean.getInvokerMethodBean().addInvokeRowContent(currentInvokeRowContent,invokeBean.getCurrentInvokeRowBean());

            //需要引用convert接口的时候注册引用包
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(bodtoConvertInterfaceBean.getPackageName()+"."+bodtoConvertInterfaceBean.getClassName());

        }
        //读场景,对可能的被调用方的方法返回值进行bo->dto
        else if(invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.READ.getCode())){
            InterfaceBean bodtoConvertInterfaceBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(invokeBean.getInvokerClassBean().getBodtoConvertInterface());
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
            if(bodtoConvertInterfaceBean == null){
                return;
            }
            MethodBean convertMethod = convertInvokeService.getConvertMethod(bodtoConvertInterfaceBean,invokeBean);
            if(convertMethod == null){
                return;
            }

            InvokeRowBean convertRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod, bodtoConvertInterfaceBean.getClassName());
            String convertInvokeRow = convertRowBean.buildInvokeContent();
            Optional<String> oldConvertInvokeRow = invokeBean.getInvokerMethodBean().getInvokeMethodList().stream().filter(str->str.equals(convertInvokeRow)).findFirst();

            if(oldConvertInvokeRow.isPresent()){
                return;
            }
            invokeBean.getInvokerMethodBean().addInvokeRowContent(convertInvokeRow,convertRowBean);
            //需要引用convert接口的时候注册引用包
            invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(bodtoConvertInterfaceBean.getPackageName()+"."+bodtoConvertInterfaceBean.getClassName());
        }
        else {
            invokeBean.getInvokerMethodBean().getInvokeMethodList().add(invokeBean.getCurrentInvokeRowContent());
        }






    }
}
