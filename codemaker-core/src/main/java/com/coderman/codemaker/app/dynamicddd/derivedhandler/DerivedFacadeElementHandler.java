package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.DomainBoElementBean;
import com.coderman.codemaker.bean.dddelementderive.DtoElementBean;
import com.coderman.codemaker.bean.dddelementderive.FacadeElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import com.coderman.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 处理派生类bo->dto
 */
@Component(value = "derivedFacadeElementHandler")
public class DerivedFacadeElementHandler implements DomainElementHandler<FacadeElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public FacadeElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        FacadeElementBean facadeElementBean = new FacadeElementBean();
        List<InterfaceBean> facadeElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.FACADE.getElement())){
                importPackageService.setPackageName(v,"api.facade");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                v.setDerived(true);
                //过滤扩展方法
                List<MethodBean> methodBeanList = v.getMethodBeanList().stream().filter(methodBean ->
                        !methodBean.getReturnClass().toLowerCase().contains("vo")
                                &&  !methodBean.getMethodName().toLowerCase().contains("vo")
                ).collect(Collectors.toList());
                v.setMethodBeanList(methodBeanList);

                facadeElementBeanList.add(v);
            }
        });


        facadeElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));
        //处理facade到facadeimpl的派生
        derivedClassFactory.deriveFacade2FacadeImpl(facadeElementBeanList,plantUmlContextBean);
        facadeElementBean.setInterfaceBeanList(facadeElementBeanList);

        return facadeElementBean;

    }
}
