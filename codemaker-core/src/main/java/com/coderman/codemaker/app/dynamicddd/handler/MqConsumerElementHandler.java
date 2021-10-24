package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.AppListenerElementBean;
import com.coderman.codemaker.bean.dddelement.MqConsumerElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainElementEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "mqConsumerElementHandler")
public class MqConsumerElementHandler implements DomainElementHandler<MqConsumerElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public MqConsumerElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        MqConsumerElementBean mqConsumerElementBean = new MqConsumerElementBean();
        List<ClassBean> appListenerElementBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.MQ_CONSUMER.getElement())){
                String moduleTag = "infrast";
                if(StringUtils.isEmpty(v.getPlantUMLPackage())){
                    moduleTag = moduleTag+".mq.consumer";
                }
                importPackageService.setPackageNameWithModule(v,moduleTag);
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                appListenerElementBeanList.add(v);
            }
        });
        mqConsumerElementBean.setClassBeanList(appListenerElementBeanList);
        return mqConsumerElementBean;
    }

}

