package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.VoElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.FieldBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/7/8
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 处理派生类bo->dto
 */
@Component(value = "derivedVOElementHandler")
public class DerivedVOElementHandler implements DomainElementHandler<VoElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public VoElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        VoElementBean voElementBean = new VoElementBean();
        List<ClassBean> dtoElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.VO.getElement())){
                importPackageService.setPackageName(v,"adapter.vo");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                List<FieldBean> beanList = v.getFieldBeanList().stream().filter(f -> !f.isTableKey()
                        && !f.isDtoKey()
                        && !f.isFacadeKey()
                        && !f.isVoKey()
                        && !f.isInvokeFileKey()
                        && !f.isControllerKey()
                ).collect(Collectors.toList());
                v.setFieldBeanList(beanList);
                dtoElementBeanList.add(v);
            }
        });

        dtoElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        voElementBean.setClassBeanList(dtoElementBeanList);
        return voElementBean;
    }
}
