package com.tianhua.codemaker.service.invoker.handler;

import com.tianhua.codemaker.bean.invoke.InvokeContextBean;
import com.tianhua.codemaker.bean.invoke.InvokeRowBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.enums.dynamic.InvokeSceneTypeEnum;
import com.tianhua.codemaker.service.invoker.ConvertInvokeService;
import com.tianhua.codemaker.service.invoker.InvokeHandler;
import com.tianhua.codemaker.service.invoker.MethodInvokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Description:基础设施层infrast调用方 方法绘制处理器
 * date: 2021/10/21
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service(value = "infrastInvokeHandler")
@Slf4j
public class InfrastInvokeHandler implements InvokeHandler {
    @Autowired
    private MethodInvokeService methodInvokeService;

    @Autowired
    private ConvertInvokeService convertInvokeService;

    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {
        if (invokeBean.getInvokeSceneType().equals(InvokeSceneTypeEnum.INFRAST_TO_SELF.getCode())) {
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(), invokeBean.getCurrentInvokeRowBean());
        } else if (invokeBean.getInvokeSceneType().equals(InvokeSceneTypeEnum.INFRAST_TO_API.getCode())) {

            //手动构建转换类
            buildConvertInterface(invokeBean);
            //注册被调用方的field
            methodInvokeService.registField(invokeBean.getInvokerClassBean(), invokeBean.getProviderClassBean(), "Reference");
            //注册被调用方的方法返回值引用包
            methodInvokeService.registImportPackageByClass(invokeBean.getInvokerClassBean(), invokeBean.getPlantUmlContextBean(), invokeBean.getProviderClassMethod().getReturnClass());


        }
    }


    /**
     * 构建infrast层的converter
     *
     * @param invokeBean
     */
    private void buildConvertInterface(InvokeContextBean invokeBean) {
        //引入注解包-这里先写死，后续再优化
        invokeBean.getInvokerClassBean().addImportClass("org.apache.dubbo.config.annotation.Reference");
        String interfaceName = invokeBean.getProviderClassBean().getClassName()
                .replace("Facade", "")
                .replace("Operation", "")
                .replace("Api", "")
                .replace("Service", "")
                .replace("SPI", "")
                .replace("Query", "") + "ConvertorDTO";
        InterfaceBean convertInterface = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(interfaceName);
        if (convertInterface == null) {
            convertInterface = new InterfaceBean();
            convertInterface.setPlantUMLPackage("infrast.acl");
            convertInterface.setPackageName(invokeBean.getInvokerClassBean().getPackageName() + ".convert");
            convertInterface.setClassName(interfaceName);
            convertInterface.setMethodBeanList(new ArrayList<>());
            invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getInterfaceBeanMap().put(interfaceName, convertInterface);
        }
        //处理请求参数转换
        dealRequestParamConvert(invokeBean, convertInterface);

        //处理响应参数转换
        dealResponseParamConvert(invokeBean, convertInterface);
    }

    /**
     * 构建需要引入的包
     *
     * @param genericReturnType
     * @return
     */
    private String getSimpleReturnClass(String genericReturnType) {
        String[] arr = genericReturnType.split("<");
        return arr[arr.length - 1].replace(">", "");
    }


    /**
     * 处理防腐层调用外部组件需要的请求参数转换
     *
     * @param invokeBean
     * @param convertInterface
     */
    private void dealRequestParamConvert(InvokeContextBean invokeBean, InterfaceBean convertInterface) {
        MethodBean requestConvertMethod = convertInvokeService.getDTO2DTOConvertMethodParamRequest(invokeBean);
        if (requestConvertMethod != null) {
            convertInterface.getMethodBeanList().add(requestConvertMethod);
            convertInterface.addImportClass(invokeBean.getProviderClassMethod().getGenericParamList().get(0));
            //引入convert接口
            invokeBean.getInvokerClassBean().addImportClass(convertInterface.getPackageName() + "." + convertInterface.getClassName().replace("DTO", ""));

            //引入下游接口的参数类和返回类型
            invokeBean.getInvokerClassBean().addImportClass(getSimpleReturnClass(invokeBean.getProviderClassMethod().getGenericReturnType()));
            //这里将转换方法的入参与调用方的参数融合一起
            InvokeRowBean requestConvertRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean, requestConvertMethod, convertInterface.getClassName().replace("DTO", ""));
            String paramTypeStr = requestConvertRowBean.getProviderMethodParamTypeArr()[0];
            String paramType = paramTypeStr.split(" ")[0];
            if (paramType.contains(".")) {
                String[] arr = paramType.split("\\.");
                String paramTypex = arr[arr.length - 1];
                ClassBean classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(paramTypex);
                if (classBean == null) {
                    classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(paramTypex + "acl");
                }
                if ((classBean.getPackageName() + "." + classBean.getClassName().replace("acl", "")).equals(paramType)) {
                    requestConvertRowBean.getProviderMethodParamValueArr()[0] = invokeBean.getInvokerMethodBean().getParamArr()[0].split(" ")[1];
                }
            } else {

            }

            String convertRow = requestConvertRowBean.buildInvokeContent();

            //1.增加转换代码行
            invokeBean.getInvokerMethodBean().addInvokeRowContent(convertRow, requestConvertRowBean);
            //2.增加当前被调用的代码行
            //invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(),invokeBean.getCurrentInvokeRowBean());
            //3.刷新转换的代码行
            String currentInvokeRowContent = invokeBean.getCurrentInvokeRowBean().refreshInvokeContent(requestConvertRowBean, invokeBean.getPlantUmlContextBean());

            //4.重新增加
            invokeBean.getInvokerMethodBean().addInvokeRowContent(currentInvokeRowContent, invokeBean.getCurrentInvokeRowBean());
        }
        else {
            invokeBean.getInvokerMethodBean().addInvokeRowContent(invokeBean.getCurrentInvokeRowContent(), invokeBean.getCurrentInvokeRowBean());
        }
    }

    /**
     * 处理防腐层调用外部组件需要的响应参数转换
     *
     * @param invokeBean
     * @param convertInterface
     */
    private void dealResponseParamConvert(InvokeContextBean invokeBean, InterfaceBean convertInterface) {
        //对调用返回值构建mp转换方法
        MethodBean responseConvertMethod = convertInvokeService.getDTO2DTOConvertMethodParamResponse(invokeBean);
        if (responseConvertMethod == null) {
            return;
        }
        //引入convert接口
        invokeBean.getInvokerClassBean().addImportClass(convertInterface.getPackageName() + "." + convertInterface.getClassName().replace("DTO", ""));

        convertInterface.getMethodBeanList().add(responseConvertMethod);

        String invokeMethodReturnClass = invokeBean.getInvokerMethodBean().getReturnClass();
        if (invokeMethodReturnClass.contains("<")) {
            String[] invokeMethodReturnClassArr = invokeMethodReturnClass.split("<");
            invokeMethodReturnClass = invokeMethodReturnClassArr[invokeMethodReturnClassArr.length - 1].replace(">", "");
        }
        ClassBean classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(invokeMethodReturnClass);
        if (classBean == null) {
            classBean = invokeBean.getPlantUmlContextBean().getDerivedPlantUmlContextBean().getClassBeanMap().get(invokeMethodReturnClass + "acl");
        }

        convertInterface.addImportClass(classBean.getPackageName() + "." + invokeMethodReturnClass);

        InvokeRowBean responseConvertRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean, responseConvertMethod, convertInterface.getClassName().replace("DTO", ""));
        String responseConvertRow = responseConvertRowBean.buildInvokeContent();
        //1.增加转换代码行
        invokeBean.getInvokerMethodBean().addInvokeRowContent(responseConvertRow, responseConvertRowBean);

    }
}
