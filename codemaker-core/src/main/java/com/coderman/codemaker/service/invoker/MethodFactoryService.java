package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Description: 针对动态调用时序中出现而领域文档没有出现的方法进行智能识别和构建
 * date: 2021/10/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class MethodFactoryService {

    /**
     * 动态构建调用时序中的方法
     *
     * @param abstractClassBean
     * @param methodStr
     * @return
     */
    public MethodBean buildDynamicMethod(PlantUmlContextBean plantUmlContextBean, AbstractClassBean abstractClassBean, String methodStr) {
        Optional<MethodBean> methodBeanOptional = abstractClassBean.getMatchMethodBean(methodStr);
        if (methodBeanOptional.isPresent()) {
            methodBeanOptional.get().initInvokeRowContentList();
            return methodBeanOptional.get();
        }
        MethodBean methodBean = new MethodBean();
        methodBean.setDesc("");

        if (!methodStr.contains("(")) {
            methodBean.setMethodName(methodStr + "()");
        }else {
            //如果方法带参数并且参数只有类型没有对应参数变量名，则重新构建方法名称
            String methodName = reBuildMethodName(methodStr,methodBean);
            methodBean.setMethodName(methodName);
            methodBean.buildParamArr();
        }

        methodBean.setReturnClass("void");
        methodBean.setVisibility("public");
        methodBean.initInvokeRowContentList();
        String returnClassTypeName = "void";
        String wrType = ReadWriteTypeEnum.getCodeByMethod(methodStr.trim());


        //对mapper接口方法进行构建--特殊处理
        if (abstractClassBean.getClassName().toLowerCase().endsWith(TemplateFileEnum.MAPPER.getTempFileName())) {
            String returnClassTypeDO = abstractClassBean.getClassName().replace("Mapper", "") + "DO";
            if (wrType.equals(ReadWriteTypeEnum.READ.getCode())) {
                if (methodStr.toLowerCase().contains("one")) {
                    returnClassTypeName = returnClassTypeDO;
                } else {
                    returnClassTypeName = "List<" + returnClassTypeDO + ">";
                }
            }

            if (wrType.equals(ReadWriteTypeEnum.WRITE.getCode())) {
                if (methodStr.toLowerCase().contains("save")) {
                    returnClassTypeName = "long";
                } else {
                    returnClassTypeName = "int";
                }
            }
            methodBean.setReturnClass(returnClassTypeName);
            methodBean.setDesc("查询");
            abstractClassBean.getMethodBeanList().add(methodBean);
            return methodBean;
        }
        //如果方法没带参数则智能推导
        if (!methodStr.contains("(")) {
            FieldBean fieldBean = buildParamArrFromName(plantUmlContextBean, abstractClassBean,methodStr,wrType);
            if(fieldBean != null){
                String [] paramArr = new String[]{fieldBean.getFieldName()};
                String paramStr = StringUtils.join(paramArr,", ");
                methodBean.setMethodName(methodStr + "("+paramStr+")");
                methodBean.buildParamArr();
                methodBean.setDesc("根据"+fieldBean.getDesc()+"查询");
            }
        }

        //构建返回值
        String returnClass = buildReturnClassFromName(plantUmlContextBean,abstractClassBean,methodStr,wrType);
        methodBean.setReturnClass(returnClass);
        methodBean.buildDoc();
        abstractClassBean.getMethodBeanList().add(methodBean);
        return methodBean;
    }


    /**
     * 根据方法名动态推导出方法参数
     * @param plantUmlContextBean
     * @param abstractClassBean
     * @param methodName
     * @param rwType
     * @return
     */
    public FieldBean buildParamArrFromName(PlantUmlContextBean plantUmlContextBean, AbstractClassBean abstractClassBean, String methodName, String rwType) {
        //说明是一个不带参数的方法
        if (methodName.contains("(")) {
            return null;
        }
        //先支持单参数推导---读场景推导
        if (rwType.equals(ReadWriteTypeEnum.READ.getCode())) {
            String fieldName = ReadWriteTypeEnum.getSomeFieldFromMethodName(methodName);
            return  getMatchModelField(plantUmlContextBean,abstractClassBean.getMethodBeanList(),fieldName);
        }
        return null;
    }

    /**
     * 根据当前提供的class找到其他方法返回值和方法参数对应的BO,DTO,VO,DO类
     * 然后从model中找到对应的参数属性
     * @return
     */
    private FieldBean getMatchModelField(PlantUmlContextBean plantUmlContextBean, List<MethodBean> methodBeanList, String fieldName) {

        ClassBean classBeanModel = getMatchClassModel(plantUmlContextBean, methodBeanList);
        if(classBeanModel == null){
            return null;
        }
        Optional<FieldBean> fieldBeanOptional = classBeanModel.getFieldBeanList().stream().filter(fieldBean -> fieldBean.getFieldName().toLowerCase().contains(fieldName.toLowerCase())).findFirst();
        if(fieldBeanOptional.isPresent()){
            return fieldBeanOptional.get();
        }
        return null;

    }


    /**
     * 从方法中找到一个bo或者do类
     * @param plantUmlContextBean
     * @param methodBeanList
     * @return
     */
    private ClassBean getMatchClassModel(PlantUmlContextBean plantUmlContextBean, List<MethodBean> methodBeanList){
        if (CollectionUtils.isEmpty(methodBeanList)) {
            return null;
        }
        for (MethodBean methodBean : methodBeanList) {
            String paramModel = getParamModelFromParam(methodBean.getParamArr());
            if (StringUtils.isEmpty(paramModel)) {
                paramModel = getParamModelFromReturn(methodBean.getReturnClassTypeModel());
            }
            if (StringUtils.isEmpty(paramModel)) {
                continue;
            }
            ClassBean classBeanModel = plantUmlContextBean.getClassBeanMap().get(paramModel);
            if(classBeanModel == null){
                continue;
            }
            return classBeanModel;
        }
        return null;
    }



    /**
     * 从参数中找到classModel模型
     *
     * @param paramArr
     * @return
     */
    private String getParamModelFromParam(String[] paramArr) {
        if (paramArr == null || paramArr.length > 0) {
            return null;
        }
        for (int i = 0; i < paramArr.length; i++) {
            String param = paramArr[i];
            if (param.contains(" ")) {
                param = param.split(" ")[0];
            }
            if (param.contains("<")) {
                param = param
                        .replace("<", "")
                        .replace(">", "")
                        .replace("List", "")
                        .replace("Map", "")
                        .replace("Set", "");

            }
            //主要针对bo,do的缺失方法参数进行推导
            if (param.toLowerCase().endsWith("bo") || param.toLowerCase().endsWith("do")) {
                return param;
            }
        }
        return null;
    }


    /**
     * 从方法返回值中找到classModel模型
     *
     * @param returnClass
     * @return
     */
    private String getParamModelFromReturn(String returnClass) {
        if (returnClass.contains("void")) {
            return null;
        }
        if (returnClass.contains("<")) {
            returnClass = returnClass
                    .replace("<", "")
                    .replace(">", "")
                    .replace("List", "")
                    .replace("Map", "")
                    .replace("Set", "");
        }
        //主要针对bo,do的缺失方法参数进行推导
        if (returnClass.toLowerCase().endsWith("bo") || returnClass.toLowerCase().endsWith("do")) {
            return returnClass;
        }
        return null;
    }


    /**
     * 通过方法名推导方法的返回值
     * @param plantUmlContextBean
     * @param abstractClassBean
     * @param methodName
     * @param rwType
     * @return
     */
    private String buildReturnClassFromName(PlantUmlContextBean plantUmlContextBean, AbstractClassBean abstractClassBean, String methodName, String rwType) {
        //先支持读场景的方法返回值推导
        if (rwType.equals(ReadWriteTypeEnum.READ.getCode())) {
            ClassBean classModelBean = getMatchClassModel(plantUmlContextBean,abstractClassBean.getMethodBeanList());
            if(classModelBean == null){
                return "void";
            }
            if(methodName.toLowerCase().contains("One")){
                return classModelBean.getClassName();
            }
            if(methodName.toLowerCase().contains("Set")){
                return "Set<" + classModelBean.getClassName() + ">";
            }
            if(methodName.toLowerCase().contains("Map")){
                return "Map<String, " + classModelBean.getClassName() + ">";
            }
            return "List<"+classModelBean.getClassName()+">";
        }
        return "void";
    }

    /**
     * 重新构建方法名
     * @param methodStr
     * @param methodBean
     * @return
     */
    private String reBuildMethodName(String methodStr,MethodBean methodBean){
        String [] methodArr = methodStr.split("\\(");
        String paramBody = methodArr[1].replace(")","").trim();
        String simpleMethodName = methodArr[0];

        List<String> paramList = Lists.newArrayList();
        String [] paramArr = paramBody.split(",");
        for (String param : paramArr){
            if(!param.trim().contains(" ")){
                String paramVar = methodBean.getParamVar(param);
                paramList.add(param +" "+ paramVar);
            }else {
                paramList.add(param);
            }
        }
        return simpleMethodName+"("+StringUtils.join(paramList,",")+")";
    }
}
