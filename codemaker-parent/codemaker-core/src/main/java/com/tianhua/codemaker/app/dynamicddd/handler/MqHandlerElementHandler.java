package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.MqHandlerElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "mqHandlerElementHandler")
public class MqHandlerElementHandler implements DomainElementHandler<MqHandlerElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public MqHandlerElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        MqHandlerElementBean mqHandlerElementBean = new MqHandlerElementBean();
        List<ClassBean> appListenerElementBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.MQ_HANDLER.getElement())){
                String moduleTag = "infrast";
                if(StringUtils.isEmpty(v.getPlantUMLPackage())){
                    moduleTag = moduleTag+".mq.handler";
                }
                importPackageService.setPackageNameWithModule(v,moduleTag);
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                appListenerElementBeanList.add(v);
            }
        });
        mqHandlerElementBean.setClassBeanList(appListenerElementBeanList);
        return mqHandlerElementBean;
    }

}

