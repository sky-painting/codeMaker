package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.DomainEventElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "eventElementHandler")
public class EventElementHandler implements DomainElementHandler<DomainEventElementBean> {

    @Autowired
    private ImportPackageService importPackageService;


    @Override
    public DomainEventElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DomainEventElementBean domainEventElementBean = new DomainEventElementBean();
        List<ClassBean> domainmsgBodyBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.EVENT.getElement())){
                importPackageService.setPackageName(v,"domain.event");

                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                domainmsgBodyBeanList.add(v);
            }
        });
        domainEventElementBean.setClassBeanList(domainmsgBodyBeanList);
        return domainEventElementBean;
    }
}
