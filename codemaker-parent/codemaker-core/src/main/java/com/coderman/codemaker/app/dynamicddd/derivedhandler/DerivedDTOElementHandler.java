package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.DtoElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.FieldBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
@Component(value = "derivedDTOElementHandler")
public class DerivedDTOElementHandler implements DomainElementHandler<DtoElementBean> {
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public DtoElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        DtoElementBean dtoElementBean = new DtoElementBean();
        List<ClassBean> dtoElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
            if(StringUtils.isEmpty(v.getClassName())){
                System.out.println(JSON.toJSONString(v));
            }
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.DTO.getElement())){
                importPackageService.setPackageName(v,"api.dto");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                List<FieldBean> beanList = v.getFieldBeanList().stream().filter(f -> !f.isTableKey()
                        && !f.isDtoKey()
                        && !f.isFacadeKey()
                        && !f.isVoKey()
                        && !f.isControllerKey()
                ).collect(Collectors.toList());
                v.setFieldBeanList(beanList);
                dtoElementBeanList.add(v);
            }
        });


        dtoElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        List<InterfaceBean> facadeList = plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().values().stream().filter(
                interfaceBean -> interfaceBean.getClassName().toLowerCase().contains("facade")
        ).collect(Collectors.toList());
        //如果没有派生过facade则使用默认的方式派生facade
        if(CollectionUtils.isEmpty(facadeList)){
            //dto 派生 facade
            derivedClassFactory.deriveDTO2Facade(dtoElementBeanList,plantUmlContextBean);
        }

        List<InterfaceBean> convertList = plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().values().stream().filter(
                interfaceBean -> interfaceBean.getClassName().toLowerCase().contains("convert")
        ).collect(Collectors.toList());

        //如果没有派生过convert则使用默认的方式派生convert
        if(CollectionUtils.isEmpty(convertList)){
            //dto 派生 dto-bo-convert
            derivedClassFactory.deriveDTOBOConvert(dtoElementBeanList,plantUmlContextBean);
        }
        dtoElementBean.setClassBeanList(dtoElementBeanList);
        return dtoElementBean;
    }
}
