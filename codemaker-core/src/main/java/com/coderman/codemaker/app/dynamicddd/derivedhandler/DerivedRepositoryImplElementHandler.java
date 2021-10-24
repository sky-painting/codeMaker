package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.FacadeImplElementBean;
import com.coderman.codemaker.bean.dddelementderive.GatawayImplElementBean;
import com.coderman.codemaker.bean.dddelementderive.RepositoryImplElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
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
 * 处理repositoryimpl
 */
@Component(value = "derivedRepositoryImplElementHandler")
public class DerivedRepositoryImplElementHandler implements DomainElementHandler<RepositoryImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public RepositoryImplElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        RepositoryImplElementBean repositoryImplElementBean = new RepositoryImplElementBean();
        List<InterfaceBean> repositoryElementBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.REPOSITORY.getElement())){
                importPackageService.setPackageName(v,"app.facadeimpl");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                repositoryElementBeanList.add(v);
            }
        });

        List<ClassBean> repositoryImplElementBeanList = derivedClassFactory.deriveRepository2RepositoryImpl(repositoryElementBeanList,plantUmlContextBean);


        repositoryImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        repositoryImplElementBean.setClassBeanList(repositoryImplElementBeanList);

        return repositoryImplElementBean;

    }
}
