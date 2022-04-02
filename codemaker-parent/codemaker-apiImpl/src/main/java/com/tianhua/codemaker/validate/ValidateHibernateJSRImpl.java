package com.tianhua.codemaker.validate;

import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.enums.CodeSegmentEnum;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.utils.StringHandleUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:通过aop+自定义注解实现
 *
 * 参考：https://blog.csdn.net/kermit_liu/article/details/90043295
 *
 * https://www.jianshu.com/p/816c90b6ddc5
 *
 *
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Component(value = "validateJSRHibernateService")
public class ValidateHibernateJSRImpl implements IValidateService {
    @Override
    public void dealValidate(PlantUmlContextBean plantUmlContextBean) {
        Map<String,  String[]> validateSegmentMap = new HashMap<>();
        plantUmlContextBean.getClassBeanMap().forEach((s, classBean) -> {
            if (classBean.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !classBean.getClassName().equals("PageBO")) {
                ExtendFieldBean extendFieldBean = classBean.getExtendFieldBean();
                Map<String, String[]> dtoValidateSegmentMap = getSegmentMap(extendFieldBean.getDtoKeyList(),extendFieldBean.getValidateKeyList());
                Map<String, String[]> voValidateSegmentMap = getSegmentMap(extendFieldBean.getVoKeyList(), extendFieldBean.getValidateKeyList());
                validateSegmentMap.putAll(dtoValidateSegmentMap);
                validateSegmentMap.putAll(voValidateSegmentMap);
            }
        });


        if(validateSegmentMap.isEmpty()){
            return;
        }
        relateValidateSegment(plantUmlContextBean, validateSegmentMap);
    }


    /**
     * 将构建出来的segment关联到对应的类上
     * 基于工具类校验的话统一放到class vo/dto上
     * @param plantUmlContextBean
     * @param validateSegmentMap
     */
    private void relateValidateSegment(PlantUmlContextBean plantUmlContextBean, Map<String, String[]> validateSegmentMap){
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,classBean)->{
            //对controller的入参数进行参数校验方法植入
            if(classBean.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.CONTROLLER.getElement()) ) {
                classBean.getMethodBeanList().forEach(methodBean -> {
                    String [] paramArr = methodBean.getParamArr();
                    if(paramArr != null){
                        for (String param : paramArr){
                            String paramType = "";
                            if(param.contains(" ")){
                                paramType = param.split(" ")[0];
                            }else {
                                paramType = param;
                            }
                            String [] validateKeyArr = validateSegmentMap.get(paramType);
                            if(validateKeyArr != null){
                                List<String> valdateKeyList = Lists.newArrayList(validateKeyArr);
                                ClassBean voBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(paramType);
                                voBean.addImportClass("javax.validation.constraints.*");
                                classBean.addImportClass("javax.validation.Valid");
                                if(CollectionUtils.isNotEmpty(voBean.getFieldBeanList())){
                                    voBean.getFieldBeanList().forEach(fieldBean -> {
                                        if(valdateKeyList.contains(fieldBean.getSimpleName())){
                                            if(fieldBean.getFieldType().toLowerCase().equals("string")){
                                                fieldBean.addAnnotationTag("@NotBlank(message = \" " + fieldBean.getSimpleName() +" 不能为空\")");
                                            }else if(fieldBean.getFieldType().toLowerCase().equals("long") || fieldBean.getFieldType().toLowerCase().equals("integer")){
                                                fieldBean.addAnnotationTag("@NotNull(message = \" " + fieldBean.getSimpleName() +" 不能为空\")");
                                            }else {
                                                fieldBean.addAnnotationTag("@NotBlank(message = \" " + fieldBean.getSimpleName() +" 不能为空\")");
                                            }
                                        }
                                    });
                                }
                                methodBean.addParamAnnotation("@Valid",0);
                            }
                        }
                    }
                });
            }
        });


        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,classBean)->{
            //对facade的入参数进行参数校验方法植入
            if(classBean.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.FACADE.getElement()) ) {
                classBean.getMethodBeanList().forEach(methodBean -> {
                    String [] paramArr = methodBean.getParamArr();
                    if(paramArr != null){
                        for (String param : paramArr){
                            String paramType = "";
                            if(param.contains(" ")){
                                paramType = param.split(" ")[0];
                            }else {
                                paramType = param;
                            }
                            String [] validateKeyArr = validateSegmentMap.get(paramType);
                            if(validateKeyArr != null){
                                List<String> valdateKeyList = Lists.newArrayList(validateKeyArr);

                                ClassBean dtoBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(paramType);
                                dtoBean.addImportClass("javax.validation.constraints.*");
                                if(CollectionUtils.isNotEmpty(dtoBean.getFieldBeanList())){
                                    dtoBean.getFieldBeanList().forEach(fieldBean -> {
                                        if(valdateKeyList.contains(fieldBean.getSimpleName())){
                                            if(fieldBean.getFieldType().toLowerCase().equals("string")){
                                                fieldBean.addAnnotationTag("@NotBlank(message = \" " + fieldBean.getSimpleName() +" 不能为空\")");
                                            }else if(fieldBean.getFieldType().toLowerCase().equals("long") || fieldBean.getFieldType().toLowerCase().equals("integer")){
                                                fieldBean.addAnnotationTag("@NotNull(message = \" " + fieldBean.getSimpleName() +" 不能为空\")");
                                            }else {
                                                fieldBean.addAnnotationTag("@NotBlank(message = \" " + fieldBean.getSimpleName() +" 不能为空\")");
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
            }
        });
    }


    private Map<String,String[]> getSegmentMap(String [] modelKeyList, String [] validateKeyList){
        Map<String,String[]> validateMap = new HashMap<>();
        if(modelKeyList != null && validateKeyList != null){
            for (String modelKey : modelKeyList){
                validateMap.put(modelKey, validateKeyList);
            }
        }
        return validateMap;
    }

}
