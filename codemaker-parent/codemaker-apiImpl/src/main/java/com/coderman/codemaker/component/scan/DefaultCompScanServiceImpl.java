package com.coderman.codemaker.component.scan;

import com.coderman.codemaker.api.ICompScanService;
import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.component.ComponentConfigBean;
import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.classloader.CompClassLoadService;
import com.coderman.codemaker.component.CompPropReadService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:组件扫描注册服务类
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "defaultCompScanService")
public class DefaultCompScanServiceImpl implements ICompScanService {

    @Autowired
    private CompPropReadService compPropReadService;

    @Autowired
    private CompClassLoadService compClassLoadService;

    @Override
    public Map<String,ComponentContextBean> scanComponent(List<String> componentList){
        Map<String,ComponentContextBean> componentContextBeanMap = new HashMap<>();
        if(CollectionUtils.isEmpty(componentList)){
            return componentContextBeanMap;
        }

        for (String compName : componentList){
            String path = GlobalConstant.COMP_COMPONENT_PATH_PRE+compName+"/"+compName+".properties";
            ComponentConfigBean componentConfigBean = compPropReadService.buildFromProp(path);
            if(componentConfigBean == null){
                continue;
            }
            ComponentContextBean  componentContextBean = compClassLoadService.loadComponent(componentConfigBean);
            componentContextBean.setComponentName(compName);
            componentContextBeanMap.put(compName,componentContextBean);
        }
        return componentContextBeanMap;
    }



}
