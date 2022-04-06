package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.bean.dddelementderive.FacadeImplElementBean;
import com.tianhua.codemaker.bean.dddelementderive.ServiceImplElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
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
 * 处理serviceimpl
 */
@Component(value = "derivedServiceImplElementHandler")
public class DerivedServiceImplElementHandler implements DomainElementHandler<ServiceImplElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Override
    public ServiceImplElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        ServiceImplElementBean serviceImplElementBean = new ServiceImplElementBean();
        List<InterfaceBean> serviceList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.SERVICE.getElement())){
                importPackageService.setPackageName(v,"infrast.serviceimpl");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                if(CollectionUtils.isNotEmpty(v.getMethodBeanList())){
                    v.getMethodBeanList().stream().forEach(methodBean -> methodBean.refreshReturnBodyByReturnType());
                }
                serviceList.add(v);
            }
        });

        serviceList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));


        List<ClassBean> serviceImplElementBeanList = derivedClassFactory.deriveService2ServiceImpl(serviceList,plantUmlContextBean);

        serviceImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        serviceImplElementBean.setClassBeanList(serviceImplElementBeanList);

        return serviceImplElementBean;

    }
}
