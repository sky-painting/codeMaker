package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.GatawayElementBean;
import com.tianhua.codemaker.bean.dddelement.ServiceElementBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
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
@Component(value = "domainServiceElementHandler")
public class DomainServiceElementHandler implements DomainElementHandler<ServiceElementBean> {
    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public ServiceElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        ServiceElementBean serviceElementBean = new ServiceElementBean();
        List<InterfaceBean> gatawInterfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.SERVICE.getElement())){
                importPackageService.setPackageName(v,"domain.service");

                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                gatawInterfaceBeanList.add(v);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());
            }
        });

        serviceElementBean.setInterfaceBeanList(gatawInterfaceBeanList);
        return serviceElementBean;
    }


}
