package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.FacadeElementBean;
import com.coderman.codemaker.bean.dddelementderive.FacadeImplElementBean;
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
@Component(value = "derivedFacadeImplElementHandler")
public class DerivedFacadeImplElementHandler implements DomainElementHandler<FacadeImplElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public FacadeImplElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        FacadeImplElementBean FacadeImplElementBean = new FacadeImplElementBean();
        List<ClassBean> facadeImplElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.FACADE_IMPL.getElement())){
                importPackageService.setPackageName(v,"app.facadeimpl");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                facadeImplElementBeanList.add(v);
            }
        });

        facadeImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        FacadeImplElementBean.setClassBeanList(facadeImplElementBeanList);

        return FacadeImplElementBean;

    }
}
