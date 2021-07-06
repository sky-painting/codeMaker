package com.coderman.codemaker.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
public class DefaultPackageConfig {
    private static Map<String,String> defaultPackageMap = new HashMap<>();

    /**
     * 初始化默认可能需要导入的包
     */
    static {
        defaultPackageMap.put("List","java.util.List");
        defaultPackageMap.put("List","java.util.List");
        defaultPackageMap.put("Map","java.util.Map");
        defaultPackageMap.put("HashMap","java.util.HashMap");
        defaultPackageMap.put("BigDecimal","java.math.BigDecimal");
        defaultPackageMap.put("Date","java.util.Date");
        defaultPackageMap.put("ArrayList","java.util.ArrayList");
        defaultPackageMap.put("Set","java.util.Set");
        defaultPackageMap.put("HashSet","java.util.HashSet");
    }

    /**
     * 探测需要导入的包
     * @param className
     * @return
     */
    public String getPackage(String className){
        for (Map.Entry<String,String> entry : defaultPackageMap.entrySet()){
            if(className.toLowerCase().startsWith(entry.getKey().toLowerCase())){
                return entry.getValue();
            }
        }
        return "";
    }
}
