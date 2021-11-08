package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.VoBoConvertElementBean;
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
@Component(value = "derivedVOBOConvertElementHandler")
public class DerivedVOBOConvertElementHandler implements DomainElementHandler<VoBoConvertElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public VoBoConvertElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        VoBoConvertElementBean voBoConvertElementBean = new VoBoConvertElementBean();
        List<InterfaceBean> convertElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.VOBO_CONVERT.getElement())){
                importPackageService.setPackageName(v,"model.convert");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1)).replace("vobo","");
                v.setClassName(className);
                convertElementBeanList.add(v);
            }
        });


        convertElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        voBoConvertElementBean.setInterfaceBeanList(convertElementBeanList);

        return voBoConvertElementBean;
    }
}
