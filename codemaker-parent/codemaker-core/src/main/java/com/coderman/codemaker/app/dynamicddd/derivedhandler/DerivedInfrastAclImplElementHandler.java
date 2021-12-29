package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.InfrastAclImplElementBean;
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
 * 处理infrastaclImpl
 */
@Component(value = "derivedInfrastAclImplElementHandler")
public class DerivedInfrastAclImplElementHandler implements DomainElementHandler<InfrastAclImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public InfrastAclImplElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {

        InfrastAclImplElementBean infrastAclImplElementBean = new InfrastAclImplElementBean();

        List<InterfaceBean> interfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.ACL.getElement())
                || v.getClassName().toLowerCase().endsWith(DomainElementEnum.ADAPTER.getElement())){
                interfaceBeanList.add(v);
            }
        });


        List<ClassBean> infrastAclImplElementBeanList = derivedClassFactory.deriveInfrastAcl2InfrastImpl(interfaceBeanList,plantUmlContextBean);

        infrastAclImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        infrastAclImplElementBean.setClassBeanList(infrastAclImplElementBeanList);


        return infrastAclImplElementBean;

    }
}
