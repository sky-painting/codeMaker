package com.tianhua.codemaker.validate;

import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.bean.plantuml.ExtendFieldBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.bean.segment.SegmentBean;
import com.tianhua.codemaker.enums.CodeSegmentEnum;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:基于paramCheckUtil工具类实现校验逻辑
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "validateUtilService")
public class ValidateUtilImpl implements IValidateService {

    @Override
    public void dealValidate(PlantUmlContextBean plantUmlContextBean) {
        Map<String,SegmentBean> validateSegmentMap = new HashMap<>();
        plantUmlContextBean.getClassBeanMap().forEach((s, classBean) -> {
            if (classBean.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !classBean.getClassName().equals("PageBO")) {
                ExtendFieldBean extendFieldBean = classBean.getExtendFieldBean();
                Map<String,SegmentBean> dtoValidateSegmentMap = getSegmentMap(extendFieldBean.getDtoKeyList(),extendFieldBean.getValidateKeyList());
                Map<String,SegmentBean> voValidateSegmentMap = getSegmentMap(extendFieldBean.getVoKeyList(), extendFieldBean.getValidateKeyList());
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
                                paramVar = param.substring(0,1).toLowerCase() + param.substring(1);
                            }

                            SegmentBean segmentBean = validateSegmentMap.get(paramType);
                            if(segmentBean != null){
                                methodBean.addInvokeRowContentSimple(segmentBean.getCode().replace("${paramObject}",paramVar));
                                classBean.addImportClassBatch(segmentBean.getPackageNameList());
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
                                paramVar = param.substring(0,1).toLowerCase() + param.substring(1);
                            }

                            SegmentBean segmentBean = validateSegmentMap.get(paramType);
                            if(segmentBean != null){
                                methodBean.addInvokeRowContentSimple(segmentBean.getCode().replace("${paramObject}",paramVar));
                                classBean.addImportClassBatch(segmentBean.getPackageNameList());
                            }

                        }
                    }
                });
            }
        });

    }

    private Map<String,SegmentBean> getSegmentMap(String [] modelKeyList, String [] validateKeyList){
        Map<String,SegmentBean> validateMap = new HashMap<>();
        if(modelKeyList != null && validateKeyList != null){
            for (String modelKey : modelKeyList){
                //todo replace paramObject to constant
                String validateCodeSegment = "ParamCheckUtils.check(${paramObject}, " + getValidateStr(validateKeyList) +")";
                SegmentBean segmentBean = SegmentBean.getInstance(CodeSegmentEnum.VALIDATE.getTag(),validateCodeSegment);
                segmentBean.importPackageName("com.coderman.check.ParamCheckUtils");
                validateMap.put(modelKey,segmentBean);
            }
        }
        return validateMap;
    }

    private String getValidateStr(String [] validateKeyList){
        if(validateKeyList.length == 1){
            return "\"" + validateKeyList[0] + "\"";
        }
        String valideStr = StringUtils.join(validateKeyList,"\", \"");
        return "\"" + valideStr + "\"";
    }
}
