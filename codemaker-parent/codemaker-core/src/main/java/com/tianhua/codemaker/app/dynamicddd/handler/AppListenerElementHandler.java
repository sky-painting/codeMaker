package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.AppListenerElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "appListenerElementHandler")
public class AppListenerElementHandler implements DomainElementHandler<AppListenerElementBean> {

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public AppListenerElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        AppListenerElementBean appListenerElementBean = new AppListenerElementBean();
        List<ClassBean> appListenerElementBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.APP_LISTENER.getElement())){
                importPackageService.setPackageName(v,"app.listener");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                appListenerElementBeanList.add(v);
            }
        });
        appListenerElementBean.setClassBeanList(appListenerElementBeanList);
        return appListenerElementBean;
    }

}

