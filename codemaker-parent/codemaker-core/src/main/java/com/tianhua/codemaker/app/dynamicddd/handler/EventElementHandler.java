package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.DomainEventElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "eventElementHandler")
public class EventElementHandler implements DomainElementHandler<DomainEventElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;


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
