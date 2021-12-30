package com.coderman.codemaker.service.invoker.handler;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import com.coderman.codemaker.service.invoker.ConvertInvokeService;
import com.coderman.codemaker.service.invoker.InvokeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * 1.在一个对象关联多个对象进行写的时候给多个对象进行dto->bo的转换，或者bo->do的转换
 * 2.在一个对象关联多个对象进行读的时候，比如调用方service要调用多个mapper进行组装的时候，进行
 * do->bo，bo->dto,bo->vo的转换
 *
 * date: 2021/11/5
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class DynamicConvertHandler implements InvokeHandler {

    @Autowired
    private ConvertInvokeService convertInvokeService;

    @Override
    public void dealInvoke(InvokeContextBean invokeBean) {
        List<InvokeRowBean> invokeRowBeanList = invokeBean.getInvokerMethodBean().getInvokeRowBeanList();
        if(CollectionUtils.isEmpty(invokeRowBeanList)){
            return;
        }
        InvokeRowBean invokeRowBean = invokeRowBeanList.get(invokeRowBeanList.size()-1);

        if(!providerIsReadMapper(invokeBean) || invokeRowBean.getProviderClassName().toLowerCase().endsWith(TemplateFileEnum.CONVERT.getTempFileName())){
            return;
        }
        InvokeRowBean lastInvokeRow = invokeRowBeanList.get(invokeRowBeanList.size()-1);
        String returnClassName = lastInvokeRow.getReturnClassName();
        String classDOPreFix = getHumpClassDOName(returnClassName);
        if(StringUtils.isEmpty(classDOPreFix)){
            return;
        }
        InterfaceBean convertInterfaceBean = getBODOConvertInterfaceBean(classDOPreFix,invokeBean.getPlantUmlContextBean());
        if(convertInterfaceBean == null){
            return;
        }

        MethodBean convertMethod = convertInvokeService.getDOBOConvertMethod(convertInterfaceBean,lastInvokeRow.getReturnClassName());
        log.info("methodBean = "+ JSON.toJSONString(convertMethod));

        InvokeRowBean convertRowBean = convertInvokeService.buildInvokeConvertRow(invokeBean,convertMethod, convertInterfaceBean.getClassName());
        String convertRow = convertRowBean.buildInvokeContent();

        invokeBean.getInvokerMethodBean().addInvokeRowContent(convertRow,convertRowBean);
        //需要引用convert接口的时候注册引用包
        invokeBean.getInvokerClassBean().getDynamicImportPackageList().add(convertInterfaceBean.getPackageName()+"."+convertInterfaceBean.getClassName());
    }

    /**
     * 判断被调用方是否是mapper的读方法
     * @param invokeBean
     * @return
     */
    private Boolean providerIsReadMapper(InvokeContextBean invokeBean){
        String providerClassName = invokeBean.getProviderClassName();
        return providerClassName.toLowerCase().endsWith(TemplateFileEnum.MAPPER.getTempFileName()) && invokeBean.getMethodRWType().equals(ReadWriteTypeEnum.READ.getCode());
    }

    /**
     * 通过mapper接口的返回值获取DO类名前缀
     * @param returnClassName
     * @return
     */
    private String  getHumpClassDOName(String returnClassName){
        return returnClassName.replace("List","")
                .replace("<","")
                .replace(">","")
                .replace("Set","")
                .replace("DO","");

    }

    /**
     * 根据BO类上的表信息等获取转换bodo转换类
     * @param classDOPreFix
     * @param plantUmlContextBean
     * @return
     */
    private InterfaceBean getBODOConvertInterfaceBean(String classDOPreFix, PlantUmlContextBean plantUmlContextBean){
        String convertInterfacName = "";
        for (Map.Entry<String,ClassBean> entry : plantUmlContextBean.getClassBeanMap().entrySet()){
            if(entry.getValue().getTableBean() != null && entry.getValue().getTableBean().getHumpClassName().equals(classDOPreFix)){
                convertInterfacName = entry.getValue().getBodoConvertInterface();
                break;
            }
        }

        if(StringUtils.isNotEmpty(convertInterfacName)){
            return plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(convertInterfacName);
        }
        return null;
    }

}
