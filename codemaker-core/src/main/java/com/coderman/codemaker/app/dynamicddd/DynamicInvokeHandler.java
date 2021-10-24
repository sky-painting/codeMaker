package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.service.ReadInvokePlantUMLFileService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * date: 2021/10/19
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class DynamicInvokeHandler {

    @Autowired
    private ReadInvokePlantUMLFileService readInvokePlantUMLFileService;

    /**
     * 进行动态调用的入口
     * @param plantUmlContextBean
     */
    public void exeDynamicInvoke(PlantUmlContextBean plantUmlContextBean){
        if(CollectionUtils.isNotEmpty(plantUmlContextBean.getDynamicInvokeFileList())){
            plantUmlContextBean.getDynamicInvokeFileList().forEach(plantUmlFile->{
                readInvokePlantUMLFileService.parseInvokeChainList(plantUmlContextBean,plantUmlFile);
            });
        }
    }



}