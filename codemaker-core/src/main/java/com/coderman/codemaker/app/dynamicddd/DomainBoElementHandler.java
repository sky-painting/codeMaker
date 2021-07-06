package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.bean.dddelement.DomainBoElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainElementEnum;
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
@Component(value = "domainBoElementHandler")
public class DomainBoElementHandler implements DomainElementHandler<DomainBoElementBean> {


    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public DomainBoElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DomainBoElementBean domainBoElementBean = new DomainBoElementBean();
        List<ClassBean> domainBoElementBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement())){
                importPackageService.setPackageName(v,"domain.bo");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                domainBoElementBeanList.add(v);
            }
        });
        domainBoElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));
        domainBoElementBean.setClassBeanList(domainBoElementBeanList);
        return domainBoElementBean;
    }
}

