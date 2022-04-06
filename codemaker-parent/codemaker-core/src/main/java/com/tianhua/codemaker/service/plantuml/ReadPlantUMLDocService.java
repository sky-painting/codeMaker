package com.tianhua.codemaker.service.plantuml;

import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.config.AppServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Description: 读取plantUML类图统一处理类
 * date: 2021/10/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class ReadPlantUMLDocService {
    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 读取领域模型plantUML类图文档
     * @param plantUMLFileName
     * @return
     */
    public List<String> readDomainPlantDoc(String plantUMLFileName){
        return readPlantUMLContent(GlobalConstant.DOMAIN_MODLE_FILE_DIR,appServiceConfig.getApplicationName(),plantUMLFileName);
    }

    /**
     * 读取调用时序plantUML类图文档
     * @param plantUMLFileName
     * @return
     */
    public List<String> readInvokeSequencePlantDoc(String plantUMLFileName){
        return readPlantUMLContent(GlobalConstant.INVOKE_SEQUENCE_FILE_DIR,appServiceConfig.getApplicationName(),plantUMLFileName);
    }


    /**
     * 读取plantUMl文件通用方法
     * @param plantUMLFileDir
     * @param applicationName
     * @param plantUMLFileName
     * @return
     */
    private List<String> readPlantUMLContent(String plantUMLFileDir,String applicationName,String plantUMLFileName){
        if(StringUtils.isEmpty(plantUMLFileName)){
            log.error("plantUMLFileName is empty,can't read content----------------!!!!!!!!!!");
            return null;
        }
        try {
            Resource resource = resourceLoader.getResource("classpath:"+plantUMLFileDir+"/"+applicationName+"/"+plantUMLFileName);
            File file = resource.getFile();
            //File file = ResourceUtils.getFile("classpath:"+plantUMLFileDir+"/"+applicationName+"/"+plantUMLFileName);
            return FileUtils.readLines(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
