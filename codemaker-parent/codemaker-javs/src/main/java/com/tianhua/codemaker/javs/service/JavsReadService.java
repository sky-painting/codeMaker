package com.tianhua.codemaker.javs.service;

import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2022/3/11
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class JavsReadService {

    @Autowired
    private JavsWriteService javsWriteService;

    /**
     * 读取javsscript项目的脚本内容
     * @param javsScriptProjectPath
     * @return
     */
    public Map<String, List<String>> readJavsScriptFileContent(PlantUmlContextBean plantUmlContextBean, String javsScriptProjectPath){
        Map<String, List<String>> javsFileContentMap = new HashMap<>();
        File file = new File(javsScriptProjectPath);
        if(!file.exists()){
            return null;
        }
        plantUmlContextBean.getClassBeanMap().forEach((k, classBean) -> {
            String filePath = javsWriteService.getFilePath(javsScriptProjectPath, classBean);


            if(!StringUtils.isEmpty(filePath) ){
                File javsFile = new File(filePath);
                if(javsFile.exists()){
                    try {
                        List<String> fileList = FileUtils.readLines(javsFile,"UTF-8");
                        javsFileContentMap.put(classBean.getClassName(),fileList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        if (plantUmlContextBean.getDerivedPlantUmlContextBean() != null) {
            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, classBean) -> {
                String filePath = javsWriteService.getFilePath(javsScriptProjectPath, classBean);
                if(!StringUtils.isEmpty(filePath)){
                    File javsFile = new File(filePath);
                    if(javsFile.exists()){
                        try {
                            List<String> fileList = FileUtils.readLines(new File(filePath),"UTF-8");
                            javsFileContentMap.put(classBean.getClassName(),fileList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });
        }
        return javsFileContentMap;
    }

}
