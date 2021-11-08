package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.GatawayImplElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 处理gatawayimpl
 */
@Component(value = "derivedGatawayImplElementHandler")
public class DerivedGatawayImplElementHandler implements DomainElementHandler<GatawayImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public GatawayImplElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        GatawayImplElementBean gatawayImplElementBean = new GatawayImplElementBean();

        List<InterfaceBean> interfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.GATAWAY.getElement())){
                importPackageService.setPackageName(v,"app.facadeimpl");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                interfaceBeanList.add(v);
            }
        });


        List<ClassBean> gatawayImplElementBeanList = derivedClassFactory.deriveGataway2GatawayImpl(interfaceBeanList,plantUmlContextBean);

        gatawayImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        gatawayImplElementBean.setClassBeanList(gatawayImplElementBeanList);


        return gatawayImplElementBean;

    }
}
