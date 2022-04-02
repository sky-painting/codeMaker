package com.tianhua.codemaker.service.adapter;


import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2021/10/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PackgeConstants {
    private static Map<String,String> defaultPackageMap = new HashMap<>();
    /**
     * 初始化默认可能需要导入的包
     */
    static {
        defaultPackageMap.put("List","java.util.List");
        defaultPackageMap.put("Map","java.util.Map");
        defaultPackageMap.put("HashMap","java.util.HashMap");
        defaultPackageMap.put("BigDecimal","java.math.BigDecimal");
        defaultPackageMap.put("Date","java.util.Date");
        defaultPackageMap.put("ArrayList","java.util.ArrayList");
        defaultPackageMap.put("Set","java.util.Set");
        defaultPackageMap.put("HashSet","java.util.HashSet");
    }

    public static Map<String,String> getDefaultPackageMap(){
        return defaultPackageMap;
    }

}
