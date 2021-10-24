package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.DoBoConvertElementBean;
import com.coderman.codemaker.bean.dddelementderive.DtoBoConvertElementBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
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
 * 处理派生类do-bo convert
 */
@Component(value = "derivedDOBOConvertElementHandler")
public class DerivedDOBOConvertElementHandler implements DomainElementHandler<DoBoConvertElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public DoBoConvertElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        DoBoConvertElementBean doBoConvertElementBean = new DoBoConvertElementBean();
        List<InterfaceBean> convertElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.DOBO_CONVERT.getElement())){
                v.setPlantUMLPackage(null);
                importPackageService.setPackageName(v,"infrast.dataconvert");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className.substring(0,className.lastIndexOf("er")));
                convertElementBeanList.add(v);
            }
        });


        convertElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        doBoConvertElementBean.setInterfaceBeanList(convertElementBeanList);

        return doBoConvertElementBean;
    }
}
