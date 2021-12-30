package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.RepositoryElementBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
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
@Component(value = "repositoryElementHandler")
public class RepositoryElementHandler implements DomainElementHandler<RepositoryElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public RepositoryElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        RepositoryElementBean repositoryElementBean = new RepositoryElementBean();
        List<InterfaceBean> repositoryInterfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.REPOSITORY.getElement())){
                importPackageService.setPackageName(v,"domain.repository");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                repositoryInterfaceBeanList.add(v);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());
            }
        });
        repositoryElementBean.setInterfaceBeanList(repositoryInterfaceBeanList);
        return repositoryElementBean;
    }

}
