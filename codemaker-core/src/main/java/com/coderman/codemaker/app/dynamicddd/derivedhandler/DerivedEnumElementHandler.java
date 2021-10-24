package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.DtoElementBean;
import com.coderman.codemaker.bean.dddelementderive.EnumElementBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import org.apache.commons.collections4.CollectionUtils;
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
 * 处理派生类domain.enum->rpc.client.enum
 */
@Component(value = "derivedEnumElementHandler")
public class DerivedEnumElementHandler implements DomainElementHandler<EnumElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public EnumElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        EnumElementBean enumElementBean = new EnumElementBean();
        List<EnumBean> enumElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getEnumBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.ENUM.getElement())){
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                List<FieldBean> beanList = v.getFieldBeanList().stream().filter(f -> !f.isCopyToRpcClientKey())
                        .collect(Collectors.toList());
                v.setFieldBeanList(beanList);
                enumElementBeanList.add(v);
            }
        });
        enumElementBean.setEnumBeanList(enumElementBeanList);

        return enumElementBean;
    }
}
