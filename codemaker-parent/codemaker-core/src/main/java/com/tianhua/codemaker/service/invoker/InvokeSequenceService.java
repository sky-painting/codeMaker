package com.tianhua.codemaker.service.invoker;

import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.service.plantuml.ReadInvokeSequencePlantDocService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * date: 2021/10/19
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class InvokeSequenceService {

    @Autowired
    private ReadInvokeSequencePlantDocService readInvokePlantUMLFileService;

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
