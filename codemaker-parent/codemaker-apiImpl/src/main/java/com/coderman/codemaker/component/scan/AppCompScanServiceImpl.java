package com.coderman.codemaker.component.scan;

import com.coderman.codemaker.api.ICompScanService;
import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.component.ComponentConfigBean;
import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.classloader.CompClassLoadService;
import com.coderman.codemaker.component.CompPropReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:应用层面依赖的业务组件进行扫描
 *
 * 扫描可以按一个组件一个组件的扫描实现也可以按应用维度一次性扫描多个组件
 *
 * 这里扫描apiresult组件进行接口方法返回值和方法page参数的包装
 * date: 2021/11/25
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "appCompScanService")
public class AppCompScanServiceImpl implements ICompScanService {
    @Autowired
    private CompPropReadService compPropReadService;

    @Autowired
    private CompClassLoadService compClassLoadService;

    @Override
    public Map<String, ComponentContextBean> scanComponent(List<String> componentList) {
        Map<String,ComponentContextBean> componentContextBeanMap = new HashMap<>();
        for (String compName : componentList){
            String path = GlobalConstant.COMP_COMPONENT_PATH_PRE+compName+"/"+compName+".properties";
            ComponentConfigBean componentConfigBean = compPropReadService.buildFromProp(path);
            if(componentConfigBean == null){
                continue;
            }
            ComponentContextBean  componentContextBean = compClassLoadService.loadComponent(componentConfigBean);
            componentContextBean.setComponentName(compName);
            componentContextBean.setCompType(componentConfigBean.getCompType());
            componentContextBeanMap.put(compName,componentContextBean);
        }
        return componentContextBeanMap;
    }
}
