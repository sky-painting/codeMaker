package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.FeignElementBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
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
@Component(value = "derivedFeignElementHandler")
public class DerivedFeignElementHandler implements DomainElementHandler<FeignElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public FeignElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        FeignElementBean feignElementBean = new FeignElementBean();
        List<InterfaceBean> feignElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.FEIGN.getElement())){
                importPackageService.setPackageName(v,"api.feign");
                v.setDerived(true);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                feignElementBeanList.add(v);
            }
        });

        feignElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));
        //处理feign 到feignimpl这里需要看情况是否是单独实现还是由controller实现
        //derivedClassFactory.deriveFacade2FacadeImpl(feignElementBeanList,plantUmlContextBean);
        feignElementBean.setInterfaceBeanList(feignElementBeanList);

        return feignElementBean;

    }
}
