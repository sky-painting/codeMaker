package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.ValueObjectElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.EnumBean;
import com.tianhua.codemaker.bean.plantuml.FieldBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.enums.VisibilityEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "valueObjectElementHandler")
public class ValueObjectElementHandler implements DomainElementHandler<ValueObjectElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;
    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Override
    public ValueObjectElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        ValueObjectElementBean valueObjectElementBean = new ValueObjectElementBean();
        List<ClassBean> domainBoElementBeanList = new ArrayList<>();
        List<EnumBean> enumBeanList = new ArrayList<>();

        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if (classFilter(v.getClassName())) {
                importPackageService.setPackageName(v,"domain.valuobject");
                domainBoElementBeanList.add(v);
            }
        });


        List<EnumBean> enumWithToRpcClientKeyList = new ArrayList<>();

        plantUmlContextBean.getEnumBeanMap().forEach((k, v) -> {
            List<String> enumValueList = new ArrayList<>();
            v.getEnumValueList().forEach(value -> {
                if (value.endsWith(",")) {
                    enumValueList.add(value.substring(0,value.length() - 1));
                } else if (value.endsWith(";")) {
                    enumValueList.add(value.replace(";", ""));
                } else {
                    enumValueList.add(value);
                }
            });

            v.setEnumValueList(enumValueList);
            //??????????????????
            if(v.getPlantUMLPackage().toLowerCase().endsWith("enum")){
                v.setPlantUMLPackage(v.getPlantUMLPackage()+"s");
            }
            importPackageService.setPackageName(v,"domain.enums");


            Optional<FieldBean> optionalFieldBean = v.getFieldBeanList().stream().filter(f -> f.isCopyToRpcClientKey()).findFirst();
            if (optionalFieldBean.isPresent()) {
                enumWithToRpcClientKeyList.add(v);
            }

            List<FieldBean> fieldBeanList = v.getFieldBeanList().stream()
                    .filter(fieldBean -> !fieldBean.isCopyToRpcClientKey())
                    .collect(Collectors.toList());
            v.setFieldBeanList(fieldBeanList);
            dealConstruct(v);

            enumBeanList.add(v);
        });

        //??????????????????-??????????????????rpc client???
        if(CollectionUtils.isNotEmpty(enumWithToRpcClientKeyList)){
            //??????plantuml.enum???????????????????????????
            derivedClassFactory.deriveEnum2Enum(enumWithToRpcClientKeyList, plantUmlContextBean);
        }
        valueObjectElementBean.setEnumBeanList(enumBeanList);
        valueObjectElementBean.setClassBeanList(domainBoElementBeanList);
        return valueObjectElementBean;
    }

    /**
     * ?????????????????????????????????
     * @param enumBean
     */
    private void dealConstruct(EnumBean enumBean){
        List<FieldBean> fieldBeanList = enumBean.getFieldBeanList();
        List<String> paramList = new ArrayList<>();
        List<String> constructMethodBodyList = new ArrayList<>();
        for (FieldBean fieldBean : fieldBeanList){
            String fieldName = fieldBean.getFieldName().replace(fieldBean.getVisibility(), "");
            fieldName.replace(VisibilityEnum.PRIVATE.getVisibility(),"")
                    .replace(VisibilityEnum.PROTECT.getVisibility(),"")
                    .replace(VisibilityEnum.PUBLIC.getVisibility(),"")
                    .replace(VisibilityEnum.PACKAGE_PRIVATE.getVisibility(),"");
            paramList.add(fieldName);
            String[] fieldValueArr = fieldName.trim().split(" ");
            String realFieldName = fieldValueArr[fieldValueArr.length - 1];
            constructMethodBodyList.add("this."+realFieldName+" = "+realFieldName);
        }
        enumBean.setConstructParamStr(StringUtils.join(paramList,","));
        enumBean.setConstructMethodBodyList(constructMethodBodyList);
    }


    /**
     * ?????????
     * @param className
     * @return
     */
    private boolean classFilter(String className){
        String[] cmdArr = DomainElementEnum.VALUEOBJECT.getElement().split(",");
        for (String cmd : cmdArr){
            if (className.toLowerCase().endsWith(cmd)) {
                return true;
            }
        }
        return false;
    }
}
