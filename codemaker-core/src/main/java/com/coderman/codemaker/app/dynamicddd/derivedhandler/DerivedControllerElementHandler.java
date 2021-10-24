package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.ControllerElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
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
 * 处理派生类bo->dto
 */
@Component(value = "derivedControllerElementHandler")
public class DerivedControllerElementHandler implements DomainElementHandler<ControllerElementBean> {


    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public ControllerElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        ControllerElementBean controllerElementBean = new ControllerElementBean();
        List<ClassBean> classBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.CONTROLLER.getElement())){
                importPackageService.setPackageName(v,"adapter.controller");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                v.setDerived(true);

                List<MethodBean> methodBeanFilterList = v.getMethodBeanList().stream().filter(methodBean ->
                        methodBean.getMethodName().toLowerCase().contains(v.getClassName().toLowerCase())
                ).collect(Collectors.toList());
                //如果有多个facade则覆盖默认的方法列表
                if(CollectionUtils.isNotEmpty(methodBeanFilterList)){
                    methodBeanFilterList.stream().forEach(methodBean -> methodBean.setMethodName(methodBean.getMethodName().split("\\.")[1]));
                    v.setMethodBeanList(methodBeanFilterList);
                }
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());
                classBeanList.add(v);
            }
        });


        classBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));
        controllerElementBean.setClassBeanList(classBeanList);
        return controllerElementBean;
    }
}
