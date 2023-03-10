package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.RepositoryImplElementBean;
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
 * 处理repositoryimpl
 */
@Component(value = "derivedRepositoryImplElementHandler")
public class DerivedRepositoryImplElementHandler implements DomainElementHandler<RepositoryImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageServiceImpl importPackageService;

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
                if(CollectionUtils.isNotEmpty(v.getMethodBeanList())){
                    v.getMethodBeanList().stream().forEach(methodBean -> methodBean.refreshReturnBodyByReturnType());
                }
                repositoryElementBeanList.add(v);
            }
        });

        List<ClassBean> repositoryImplElementBeanList = derivedClassFactory.deriveRepository2RepositoryImpl(repositoryElementBeanList,plantUmlContextBean);


        repositoryImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        repositoryImplElementBean.setClassBeanList(repositoryImplElementBeanList);

        return repositoryImplElementBean;

    }
}
