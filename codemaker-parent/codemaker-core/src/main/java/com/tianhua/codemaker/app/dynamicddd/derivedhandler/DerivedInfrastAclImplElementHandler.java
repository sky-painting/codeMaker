package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.InfrastAclImplElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
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
 * 处理infrastaclImpl
 */
@Component(value = "derivedInfrastAclImplElementHandler")
public class DerivedInfrastAclImplElementHandler implements DomainElementHandler<InfrastAclImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageServiceImpl importPackageService;

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
