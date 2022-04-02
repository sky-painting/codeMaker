package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.FactoryElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
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
@Component(value = "domainFactoryElementHandler")
public class DomainFactoryElementHandler implements DomainElementHandler<FactoryElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public FactoryElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        FactoryElementBean factoryElementBean = new FactoryElementBean();
        List<ClassBean> domainBoElementBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.FACTORY.getElement())){
                importPackageService.setPackageName(v,"domain.factory");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                domainBoElementBeanList.add(v);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());
            }
        });
        factoryElementBean.setClassBeanList(domainBoElementBeanList);
        return factoryElementBean;
    }

}

