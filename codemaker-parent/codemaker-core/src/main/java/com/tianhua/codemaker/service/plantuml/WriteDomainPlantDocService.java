package com.tianhua.codemaker.service.plantuml;

import com.tianhua.codemaker.config.AppServiceConfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Description: 将plantuml 文档同步写到项目文件中
 * date: 2021/6/28
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 *
 */
@Service
@Slf4j
public class WriteDomainPlantDocService {

    @Autowired
    private AppServiceConfig appServiceConfig;
    @Autowired
    private ReadPlantUMLDocService readPlantUMLDocService;

    /**
     * 将领域模型文档写入到项目工程中
     */
    public void writePlantUMLDomainDoc(){
        String outPath = appServiceConfig.getUMLDocOutPath();
        List<String> contentList = readPlantUMLDocService.readDomainPlantDoc(appServiceConfig.getPlantUMLFileName());
        if(CollectionUtils.isEmpty(contentList)){
            return;
        }
        String filePath = outPath + "/" + appServiceConfig.getPlantUMLFileName();
        try {
            FileUtils.writeLines(new File(filePath),contentList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将调用时序文档写入到项目工程中
     */
    public void writePlantUMLSequenceDoc(List<String> invokeSequenceFileList){
        String outPath = appServiceConfig.getUMLDocOutPath();

        for (String fileName : invokeSequenceFileList){
            List<String> contentList = readPlantUMLDocService.readInvokeSequencePlantDoc(fileName);
            if(CollectionUtils.isEmpty(contentList)){
                return;
            }
            String filePath = outPath + "/" + fileName;
            try {
                FileUtils.writeLines(new File(filePath),contentList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
