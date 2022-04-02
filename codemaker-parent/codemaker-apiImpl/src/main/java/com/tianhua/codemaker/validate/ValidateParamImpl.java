package com.tianhua.codemaker.validate;

import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.bean.segment.SegmentBean;
import com.tianhua.codemaker.enums.CodeSegmentEnum;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.utils.StringHandleUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:通过参数对象内部的参数校验方法校验
 *
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "validateParamService")
public class ValidateParamImpl implements IValidateService {

    @Override
    public void dealValidate(PlantUmlContextBean plantUmlContextBean) {
        Map<String, SegmentBean> validateSegmentMap = new HashMap<>();
        plantUmlContextBean.getClassBeanMap().forEach((s, classBean) -> {
            if (classBean.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !classBean.getClassName().equals("PageBO")) {
                ExtendFieldBean extendFieldBean = classBean.getExtendFieldBean();
                Map<String,SegmentBean> dtoValidateSegmentMap = getSegmentMap(extendFieldBean.getDtoKeyList(),extendFieldBean.getValidateKeyList(),classBean);
                Map<String,SegmentBean> voValidateSegmentMap = getSegmentMap(extendFieldBean.getVoKeyList(), extendFieldBean.getValidateKeyList(),classBean);
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
    private void relateValidateSegment(PlantUmlContextBean plantUmlContextBean, Map<String,SegmentBean> validateSegmentMap){
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,classBean)->{
            //对controller的入参数进行参数校验方法植入
            if(classBean.getClassName().toLowerCase().endsWith(DomainDerivedElementEnum.CONTROLLER.getElement()) ) {
                classBean.getMethodBeanList().forEach(methodBean -> {
                    String [] paramArr = methodBean.getParamArr();
                    if(paramArr != null){
                        for (String param : paramArr){
                            String paramType = "";
                            String paramVar = "";
                            if(param.contains(" ")){
                                paramType = param.split(" ")[0];
                                paramVar = param.split(" ")[1];
                            }else {
                                paramType = param;
                                paramVar = param.substring(0,1).toLowerCase()+param.substring(1);
                            }

                            SegmentBean segmentBean = validateSegmentMap.get(paramType);
                            if(segmentBean != null){
                                MethodBean checkParamMethodBean = buildCheckParamMethodBean(segmentBean.getCode());
                                ClassBean voBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(paramType);
                                if(CollectionUtils.isEmpty(voBean.getMethodBeanList())){
                                    voBean.setMethodBeanList(new ArrayList<>());
                                }
                                voBean.getMethodBeanList().add(checkParamMethodBean);
                                voBean.addImportClassBatch(segmentBean.getPackageNameList());
                                classBean.addImportClassBatch(segmentBean.getPackageNameList());
                                methodBean.addInvokeRowContentSimple(buildMethodContent(paramVar));
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
                            String paramVar = "";
                            if(param.contains(" ")){
                                paramType = param.split(" ")[0];
                                paramVar = param.split(" ")[1];
                            }else {
                                paramType = param;
                                paramVar = param.substring(0,1).toLowerCase()+param.substring(1);
                            }

                            SegmentBean segmentBean = validateSegmentMap.get(paramType);
                            if(segmentBean != null){
                                MethodBean checkParamMethodBean = buildCheckParamMethodBean(segmentBean.getCode());
                                ClassBean dtoBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(paramType);
                                if(CollectionUtils.isEmpty(dtoBean.getMethodBeanList())){
                                    dtoBean.setMethodBeanList(new ArrayList<>());
                                }
                                dtoBean.getMethodBeanList().add(checkParamMethodBean);
                                dtoBean.addImportClassBatch(segmentBean.getPackageNameList());
                                classBean.addImportClassBatch(segmentBean.getPackageNameList());
                                methodBean.addInvokeRowContentSimple(buildMethodContent(paramVar));
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


    private Map<String,SegmentBean> getSegmentMap(String [] modelKeyList, String [] validateKeyList, ClassBean boClassBean){
        Map<String,SegmentBean> validateMap = new HashMap<>();
        if(modelKeyList != null && validateKeyList != null){
            for (String modelKey : modelKeyList){
                validateMap.put(modelKey, getValidateSegment(validateKeyList,boClassBean));
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
        Map<String,FieldBean> fieldBeanMap = boClassBean.getFieldBeanList().stream().collect(Collectors.toMap(FieldBean::getSimpleName,o->o));

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
