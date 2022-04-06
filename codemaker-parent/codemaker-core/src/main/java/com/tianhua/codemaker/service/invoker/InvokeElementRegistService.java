package com.tianhua.codemaker.service.invoker;

import com.tianhua.codemaker.api.ICompRegistService;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.config.DefaultPackageConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:预先读取plantUML调用时序图文档，以及补充其他内置的工具类
 * 如BaseEvent,AppEventPublisher类等
 * date: 2021/10/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class InvokeElementRegistService {

    @Autowired
    private DefaultPackageConfig defaultPackageConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private ICompRegistService iCompRegistService;

    /**
     * 注册独立类和工具
     * @param plantUmlContextBean
     */
    public void registDefaultClass(PlantUmlContextBean plantUmlContextBean){
        ComponentContextBean componentContextBean = iCompRegistService.registSingleClass();

        if(CollectionUtils.isNotEmpty(componentContextBean.getClassBeanList())){
            componentContextBean.getClassBeanList().forEach(classBean -> {
                String packageName = classBean.getPackageName().replace("${package}",appServiceConfig.getPackage())+"."+classBean.getClassName();
                defaultPackageConfig.addPackage(classBean.getClassName(),packageName);
            });
        }

        if(CollectionUtils.isNotEmpty(componentContextBean.getInterfaceBeanList())){
            componentContextBean.getInterfaceBeanList().forEach(interfaceBean -> {
                String packageName = interfaceBean.getPackageName().replace("${package}",appServiceConfig.getPackage())+"."+interfaceBean.getClassName();
                defaultPackageConfig.addPackage(interfaceBean.getClassName(),packageName);
            });
        }
        plantUmlContextBean.addCompContextBean("singleClass",componentContextBean);
    }

}
