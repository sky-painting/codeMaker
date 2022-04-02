package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.FacadeImplElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
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
 * 处理派生类bo->dto
 */
@Component(value = "derivedFacadeImplElementHandler")
public class DerivedFacadeImplElementHandler implements DomainElementHandler<FacadeImplElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public FacadeImplElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        FacadeImplElementBean facadeImplElementBean = new FacadeImplElementBean();
        List<ClassBean> facadeImplElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.FACADE_IMPL.getElement())){
                importPackageService.setPackageName(v,"app.facadeimpl");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                if(CollectionUtils.isNotEmpty(v.getMethodBeanList())){
                    v.getMethodBeanList().stream().forEach(methodBean -> methodBean.refreshReturnBodyByReturnType());
                }
                facadeImplElementBeanList.add(v);
            }
        });

        facadeImplElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        facadeImplElementBean.setClassBeanList(facadeImplElementBeanList);

        return facadeImplElementBean;

    }
}
