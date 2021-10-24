package com.coderman.codemaker.bean.plantuml;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * class 类信息
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ClassBean extends AbstractClassBean {

    public Map<String,Object> buildVarMap(){
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("class",this);
        varMap.put("fields",this.getFieldBeanList());
        varMap.put("methods",this.getMethodBeanList());
        varMap.put("imports",this.getImportClassList());
        return varMap;
    }
}
