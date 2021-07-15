package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.DtoBoConvertElementBean;
import com.coderman.codemaker.bean.dddelementderive.DtoElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
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
 * 处理派生类bo->dto
 */
@Component(value = "derivedDTOBOConvertElementHandler")
public class DerivedDTOBOConvertElementHandler implements DomainElementHandler<DtoBoConvertElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public DtoBoConvertElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        DtoBoConvertElementBean dtoElementBean = new DtoBoConvertElementBean();
        List<InterfaceBean> convertElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.DTOBO_CONVERT.getElement())){
                importPackageService.setPackageName(v,"model.convert");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                convertElementBeanList.add(v);
            }
        });


        convertElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        dtoElementBean.setInterfaceBeanList(convertElementBeanList);

        return dtoElementBean;
    }
}
