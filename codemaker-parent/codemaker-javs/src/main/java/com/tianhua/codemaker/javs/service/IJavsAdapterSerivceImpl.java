package com.tianhua.codemaker.javs.service;

import com.tianhua.codemaker.api.IJavsAdapterSerivce;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.javs.translate.JavsTranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
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
@Component(value = "javsAdapterService")
public class IJavsAdapterSerivceImpl  implements IJavsAdapterSerivce {

    private Logger logger = LoggerFactory.getLogger(IJavsAdapterSerivceImpl.class);

    @Autowired
    private JavsTranslateService javsTranslateService;

    @Autowired
    private JavsWriteService javsWriteService;

    @Autowired
    private JavsReadService javsReadService;

    @Override
    public void translateJavsScript(PlantUmlContextBean plantUmlContextBean, String javsScriptProjectPath) {
        Map<String,List<String>> javsScriptFileContentMap = javsReadService.readJavsScriptFileContent(plantUmlContextBean, javsScriptProjectPath);
        if(javsScriptFileContentMap == null){
            logger.warn("javs项目文件为空,不再进行javs扫描解析.......");
            return;
        }
        javsTranslateService.translateJavsScripte(plantUmlContextBean, javsScriptFileContentMap);
    }

    @Override
    public void generateJavsScriptProject(PlantUmlContextBean plantUmlContextBean, String javsScriptProjectPath) {
        File file = new File(javsScriptProjectPath);
        if(file.exists()){
            return;
        }
        plantUmlContextBean.getClassBeanMap().forEach((k, classBean) -> javsWriteService.writeJavs(javsScriptProjectPath, classBean));
        if (plantUmlContextBean.getDerivedPlantUmlContextBean() != null) {
            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, classBean) -> javsWriteService.writeJavs(javsScriptProjectPath, classBean));
        }
    }


}
