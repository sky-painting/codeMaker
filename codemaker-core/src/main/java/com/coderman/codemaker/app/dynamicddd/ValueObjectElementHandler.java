package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.bean.dddelement.ValueObjectElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.FieldBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.DomainElementEnum;
import com.coderman.codemaker.enums.VisibilityEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "valueObjectElementHandler")
public class ValueObjectElementHandler implements DomainElementHandler<ValueObjectElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

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
            importPackageService.setPackageName(v,"domain.enums");


            dealConstruct(v);

            enumBeanList.add(v);
        });
        valueObjectElementBean.setEnumBeanList(enumBeanList);
        valueObjectElementBean.setClassBeanList(domainBoElementBeanList);
        return valueObjectElementBean;
    }

    /**
     * 处理枚举类中的构造方法
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
     * 类过滤
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
