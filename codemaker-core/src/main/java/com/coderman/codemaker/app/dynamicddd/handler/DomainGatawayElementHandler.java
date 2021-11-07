package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.GatawayElementBean;
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
@Component(value = "domainGatawayElementHandler")
public class DomainGatawayElementHandler implements DomainElementHandler<GatawayElementBean> {
    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public GatawayElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        GatawayElementBean gatawayElementBean = new GatawayElementBean();
        List<InterfaceBean> gatawInterfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.GATAWAY.getElement())){
                importPackageService.setPackageName(v,"domain.gataway");

                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                gatawInterfaceBeanList.add(v);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());

            }
        });
        gatawayElementBean.setInterfaceBeanList(gatawInterfaceBeanList);
        return gatawayElementBean;
    }


}
