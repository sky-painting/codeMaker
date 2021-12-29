package com.coderman.codemaker.config;

import com.coderman.codemaker.service.adapter.PackgeConstants;
import com.coderman.codemaker.service.packageimport.PackageImportService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PackageImportService packageImportService;


    /**
     * 根据不同公司不同开发部门的业务规范制定不同的引用包
     *
     */
    public synchronized void addAdapterPackages(){
        if(defaultPackageMap.isEmpty()){
            defaultPackageMap.putAll(PackgeConstants.getDefaultPackageMap());
            defaultPackageMap.putAll(packageImportService.getSystemImportPackageConfig());
        }
    }



    /**
     * 探测需要导入的包
     * @param className
     * @return
     */
    public String getPackage(String className){
        for (Map.Entry<String,String> entry : defaultPackageMap.entrySet()){
            if(className.toLowerCase().startsWith(entry.getKey().toLowerCase()) || className.toLowerCase().endsWith(entry.getKey().toLowerCase())){
                return entry.getValue();
            }
        }
        return "";
    }
}
