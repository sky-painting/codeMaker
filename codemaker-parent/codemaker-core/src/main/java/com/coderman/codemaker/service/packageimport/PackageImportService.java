package com.coderman.codemaker.service.packageimport;

import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.component.CompPropReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description:
 * date: 2021/11/24
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class PackageImportService {

    @Autowired
    private CompPropReadService compPropReadService;

    /**
     * 读取class-dependency.properties配置的包依赖
     * @return
     */
    public Map<String,String> getSystemImportPackageConfig(){
        Map<String,String> importPackage = new HashMap<>();

        try {
            Properties properties = compPropReadService.readPropertiesFile(GlobalConstant.CLASS_IMPORT_FILE);
            if(properties == null){
                return importPackage;
            }
            if(properties.isEmpty()){
                return importPackage;
            }
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                importPackage.put(entry.getKey().toString(),entry.getValue().toString());
            }
        } catch (IOException e) {
            log.error("读取自定义类导入包失败",e);
        }
        return importPackage;
    }

}
