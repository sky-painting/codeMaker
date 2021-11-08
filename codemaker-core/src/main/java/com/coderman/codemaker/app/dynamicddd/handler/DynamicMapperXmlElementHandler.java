package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.DynamicMapperXmlElementBean;
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
@Component(value = "dynamicMapperXmlElementHandler")
public class DynamicMapperXmlElementHandler implements DomainElementHandler<DynamicMapperXmlElementBean> {
    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public DynamicMapperXmlElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DynamicMapperXmlElementBean dynamicMapperElementBean = new DynamicMapperXmlElementBean();
        List<InterfaceBean> mapperInterfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.DYNAMIC_MAPPER.getElement())){
                importPackageService.dealImportClass(v,plantUmlContextBean);
                mapperInterfaceBeanList.add(v);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());
            }
        });
        dynamicMapperElementBean.setInterfaceBeanList(mapperInterfaceBeanList);
        return dynamicMapperElementBean;
    }


}
