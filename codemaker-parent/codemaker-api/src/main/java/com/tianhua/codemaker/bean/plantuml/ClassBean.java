package com.tianhua.codemaker.bean.plantuml;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * class 类信息
 * date: 2021/6/28
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ClassBean extends AbstractClassBean {

    public Map<String,Object> buildVarMap(){
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("class",this);
        varMap.put("table",this.getTableBean());
        varMap.put("columns",this.getColumnBeanList());
        varMap.put("fields",this.getFieldBeanList());
        if(CollectionUtils.isNotEmpty(this.getMethodBeanList())){
            this.getMethodBeanList().stream().forEach(methodBean -> methodBean.getSimplMethodName());
        }

        varMap.put("methods",this.getMethodBeanList());
        varMap.put("imports",this.getImportClassList());

        return varMap;
    }
}
