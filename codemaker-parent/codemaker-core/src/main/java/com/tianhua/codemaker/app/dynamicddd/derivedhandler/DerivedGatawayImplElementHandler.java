package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.GatawayImplElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 处理gatawayimpl
 */
@Component(value = "derivedGatawayImplElementHandler")
public class DerivedGatawayImplElementHandler implements DomainElementHandler<GatawayImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageServiceImpl importPackageService;

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

                if(CollectionUtils.isNotEmpty(v.getMethodBeanList())){
                    v.getMethodBeanList().stream().forEach(methodBean -> methodBean.refreshReturnBodyByReturnType());
                }
                interfaceBeanList.add(v);
            }
        });


        List<ClassBean> gatawayImplElementBeanList = derivedClassFactory.deriveGataway2GatawayImpl(interfaceBeanList,plantUmlContextBean);

        gatawayImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        gatawayImplElementBean.setClassBeanList(gatawayImplElementBeanList);


        return gatawayImplElementBean;

    }
}
