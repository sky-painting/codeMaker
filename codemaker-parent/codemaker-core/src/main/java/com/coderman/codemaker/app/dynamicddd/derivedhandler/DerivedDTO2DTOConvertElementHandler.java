package com.coderman.codemaker.app.dynamicddd.derivedhandler;

import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelementderive.Dto2DtoConvertElementBean;
import com.coderman.codemaker.bean.dddelementderive.VoBoConvertElementBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import com.coderman.codemaker.service.ImportPackageService;
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
@Component(value = "derivedDTO2DTOConvertElementHandler")
public class DerivedDTO2DTOConvertElementHandler implements DomainElementHandler<Dto2DtoConvertElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public Dto2DtoConvertElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            return null;
        }
        Dto2DtoConvertElementBean dto2DtoConvertElementBean = new Dto2DtoConvertElementBean();
        List<InterfaceBean> convertElementBeanList = new ArrayList<>();
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.DTO2DTO_CONVERT.getElement())){
                importPackageService.setPackageName(v,"infrast.acl.convert");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1)).replace("DTO","");
                v.setClassName(className);
                convertElementBeanList.add(v);
            }
        });


        convertElementBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        dto2DtoConvertElementBean.setInterfaceBeanList(convertElementBeanList);

        return dto2DtoConvertElementBean;
    }
}
