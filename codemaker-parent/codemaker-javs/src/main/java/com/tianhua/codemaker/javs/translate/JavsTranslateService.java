package com.tianhua.codemaker.javs.translate;

import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.javs.factory.ModelFactory;
import com.tianhua.javs.client.bean.DomainModelBean;
import com.tianhua.javs.client.bean.context.TranslateResultContext;
import com.tianhua.javs.client.model.FieldModel;
import com.tianhua.javs.client.model.MethodModel;
import com.tianhua.javs.client.service.JavsScriptEngine;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2022/3/10
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class JavsTranslateService {


    @Autowired
    private JavsScriptEngine javsScriptEngine;

    @Autowired
    private ModelFactory modelFactory;

    /**
     * codemaker与javs引擎项目整合的入口
     * @param plantUmlContextBean
     */
    public void translateJavsScripte(PlantUmlContextBean plantUmlContextBean, Map<String, List<String>> javsScriptFileContentMap){
        DomainModelBean domainModelBean = modelFactory.buildJavsDomainModel(plantUmlContextBean);

        List<TranslateResultContext> translateResultContextList = javsScriptEngine.exeParse(domainModelBean, javsScriptFileContentMap);
        Map<String,TranslateResultContext> translateResultContextMap = translateResultContextList.stream().collect(Collectors.toMap(TranslateResultContext::getClassName,o->o));

        plantUmlContextBean.getClassBeanMap().forEach((k,classBean)->{
            TranslateResultContext translateResultContext = translateResultContextMap.get(classBean.getClassName());
            refreshBean(classBean,translateResultContext);
        });
        PlantUmlContextBean derivedPlantUmlContextBean = plantUmlContextBean.getDerivedPlantUmlContextBean();
        if(derivedPlantUmlContextBean != null){
            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,classBean)->{
                TranslateResultContext translateResultContext = translateResultContextMap.get(classBean.getClassName());
                refreshBean(classBean,translateResultContext);
            });
        }
    }

    /**
     * 将经过翻译的javs代码刷回模型上下文中
     * @param classBean
     * @param translateResultContext
     */
    private void refreshBean(AbstractClassBean classBean,TranslateResultContext translateResultContext){
        if(translateResultContext == null){
            return;
        }
        classBean.addImportClassBatch(translateResultContext.getPackageList());
        Map<String, FieldModel> fieldModelMap = translateResultContext.getFieldModelMap();
        if(!fieldModelMap.isEmpty()){
            fieldModelMap.forEach((k,fieldModel)->{
                FieldBean fieldBean = new FieldBean();
                fieldBean.setFieldName(fieldModel.getFieldName());
                fieldBean.setFieldSimpleName(fieldModel.getFieldSimpleName());
                fieldBean.setVisibility(fieldModel.getVisibility());
                classBean.addField(fieldBean);
            });
        }

        if(CollectionUtils.isEmpty(classBean.getMethodBeanList())){
            return;
        }
        classBean.getMethodBeanList().forEach(methodBean -> refreshMethodContent(methodBean, translateResultContext.getMethodModelList()));
    }

    /**
     * 刷新方法内容
     * @param methodBean
     * @param methodModelList
     */
    private void refreshMethodContent(MethodBean methodBean , List<MethodModel> methodModelList){
        if(CollectionUtils.isEmpty(methodModelList)){
            return;
        }
        for (MethodModel methodModel : methodModelList){
            if(methodModel.getMethodName().equals(methodBean.getMethodName()) && methodModel.getReturnClass().equals(methodBean.getReturnClass())){
                if(StringUtils.isNotEmpty(methodModel.getMethodContent())){
                    methodBean.setMethodContent(methodModel.getMethodContent());
                }
                break;
            }
        }
    }

}
