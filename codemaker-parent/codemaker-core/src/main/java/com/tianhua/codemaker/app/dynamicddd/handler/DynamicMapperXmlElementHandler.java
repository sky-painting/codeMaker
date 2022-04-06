package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.DynamicMapperXmlElementBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
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
@Component(value = "dynamicMapperXmlElementHandler")
public class DynamicMapperXmlElementHandler implements DomainElementHandler<DynamicMapperXmlElementBean> {
    @Autowired
    private ImportPackageServiceImpl importPackageService;

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
