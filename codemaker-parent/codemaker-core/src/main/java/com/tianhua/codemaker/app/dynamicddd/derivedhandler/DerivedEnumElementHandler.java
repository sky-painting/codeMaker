package com.tianhua.codemaker.app.dynamicddd.derivedhandler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelementderive.EnumElementBean;
import com.tianhua.codemaker.bean.plantuml.*;
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
 * 处理派生类domain.enum->rpc.client.enum
 */
@Component(value = "derivedEnumElementHandler")
public class DerivedEnumElementHandler implements DomainElementHandler<EnumElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;

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
