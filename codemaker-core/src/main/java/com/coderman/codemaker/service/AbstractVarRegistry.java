package com.coderman.codemaker.service;

import com.coderman.codemaker.config.ProjectTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * description: AbstractVarRegistry <br>
 * date: 2020/7/7 9:56 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
public abstract   class AbstractVarRegistry {

    @Autowired
    private ProjectTemplateConfig projectTemplateConfig;


    /**
     * 合并全局配置
     * @return
     */
    public Map<String,Object> getTemplateVar(){
        System.out.println("=============="+projectTemplateConfig.toString());
        Map<String,Object> varMap = getRegistVarMap();
        varMap.putIfAbsent("package",projectTemplateConfig.getPackageName());
        varMap.putIfAbsent("author",projectTemplateConfig.getAuthor());
        return varMap;
    }

    public  abstract Map<String,Object> getRegistVarMap();

}
