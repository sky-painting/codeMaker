package com.tianhua.codemaker.custom.elementhandler;

import com.tianhua.codemaker.annotations.ElementTag;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.custom.bean.CustomElementBean;
import com.tianhua.codemaker.service.ImportPackageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:自定义代码元素cmp元素处理器
 * date: 2021/7/8
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 *
 */
@Component(value = "cmpElementHandler")
@ElementTag(elementName = "cmp")
public class CmpElementHandler implements DomainElementHandler<CustomElementBean> {

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public CustomElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        FtlBean ftlBean = plantUmlContextBean.getFtlBean();
        List<ClassBean> classBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(ftlBean.getCodeTempFileName())) {
                String moduleTag = ftlBean.getModuleName();
                if(StringUtils.isEmpty(v.getPlantUMLPackage())){
                    moduleTag = moduleTag+".cmp";
                }else if(v.getPlantUMLPackage().contains(moduleTag)){
                    moduleTag = "";
                }

                importPackageService.setPackageNameWithModule(v,moduleTag);

                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());

                classBeanList.add(v);
            }
        });
        CustomElementBean validatorElementBean = new CustomElementBean();
        validatorElementBean.setClassBeanList(classBeanList);
        return validatorElementBean;
    }
}
