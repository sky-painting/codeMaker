package com.tianhua.codemaker.validate;

import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.bean.segment.SegmentBean;
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
import java.util.stream.Collectors;

/**
 * Description:通过aop+自定义注解实现
 *
 * 参考：https://www.cnblogs.com/zhangruifeng/p/13460060.html
 *
 * https://www.jianshu.com/p/816c90b6ddc5
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Component(value = "validateAopService")
public class ValidateAopImpl implements IValidateService {
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
                                voBean.addImportClass("com.coderman.param.valid.ParamAnnotation");
                                if(CollectionUtils.isNotEmpty(voBean.getFieldBeanList())){
                                    voBean.getFieldBeanList().forEach(fieldBean -> {
                                        if(valdateKeyList.contains(fieldBean.getSimpleName())){
                                            fieldBean.addAnnotationTag("@ParamAnnotation(required = true)");
                                        }
                                    });
                                }
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

                                ClassBean voBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(paramType);
                                voBean.addImportClass("com.coderman.param.valid.ParamAnnotation");
                                if(CollectionUtils.isNotEmpty(voBean.getFieldBeanList())){
                                    voBean.getFieldBeanList().forEach(fieldBean -> {
                                        if(valdateKeyList.contains(fieldBean.getSimpleName())){
                                            fieldBean.addAnnotationTag("@ParamAnnotation(required = true)");
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

    private String buildMethodContent(String paramVar){
        StringBuilder builder = new StringBuilder("ResultDataDto resultDataDto = "+paramVar+".checkParam()\n");
        builder.append("\t\tif(!resultDataDto.isSuccess()){return resultDataDto;}");
        return builder.toString();
    }


    private MethodBean buildCheckParamMethodBean(String methodContent){
        MethodBean methodBean = new MethodBean();
        methodBean.setMethodName("checkParam()");
        methodBean.setReturnClass("ResultDataDto");
        methodBean.setDesc("参数校验");
        methodBean.setVisibility("public ");
        methodBean.setMethodContent(methodContent);
        methodBean.setReturnBody(methodContent);
        return methodBean;
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

    /**
     * 构建验证的代码段内容
     * @param validateKeyList
     * @return
     */
    private SegmentBean getValidateSegment(String [] validateKeyList, ClassBean boClassBean){
        StringBuilder builder = new StringBuilder();
        Map<String, FieldBean> fieldBeanMap = boClassBean.getFieldBeanList().stream().collect(Collectors.toMap(FieldBean::getSimpleName, o->o));

        int i = 0;
        for (String varName : validateKeyList){
            FieldBean fieldBean = fieldBeanMap.get(varName);
            if(fieldBean == null){
                builder.append("\t\t//if(this."+varName+" == null ){ return ResultDataDto.fail(null);}\n");
                continue;
            }else {
                if(StringHandleUtils.isStrictBasicType(fieldBean.getFieldType())){
                    builder.append("\t\t//if(this."+varName+" == 0 ){ return ResultDataDto.fail(null);}\n");
                    continue;
                }
                if(fieldBean.getFieldType().toLowerCase().equals("string")){
                    builder.append("\t\tif(this."+varName+" == null || this."+varName+" == \"\"" +"){ return ResultDataDto.fail(null);}\n");
                }else {
                    builder.append("\t\tif(this."+varName+" == null){ return ResultDataDto.fail(null);}\n");
                }
            }
        }

        builder.append("\t\treturn ResultDataDto.success();");

        SegmentBean segmentBean = SegmentBean.getInstance(CodeSegmentEnum.VALIDATE.getTag(),builder.toString());
        segmentBean.importPackageName("com.coderman.utils.response.ResultDataDto");

        return segmentBean;
    }

}
