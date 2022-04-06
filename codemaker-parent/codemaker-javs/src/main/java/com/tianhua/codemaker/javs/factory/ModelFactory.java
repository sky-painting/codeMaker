package com.tianhua.codemaker.javs.factory;

import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.javs.client.bean.DomainModelBean;
import com.tianhua.javs.client.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2022/3/10
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ModelFactory {
    /**
     * 将代码生成平台的plantuml模型适配到javs引擎的模型
     * @param plantUmlContextBean
     * @return
     */
    public DomainModelBean buildJavsDomainModel(PlantUmlContextBean plantUmlContextBean) {
        DomainModelBean domainModelBean = new DomainModelBean();
        domainModelBean.setCurrentCompName(plantUmlContextBean.getAppName());
        plantUmlContextBean.getClassBeanMap().forEach((k,v)-> domainModelBean.addClassBean(buildClassModel(v, plantUmlContextBean.getAppName())));
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)-> domainModelBean.addInterfaceBean(buildInterfaceModel(v, plantUmlContextBean.getAppName())));
        plantUmlContextBean.getEnumBeanMap().forEach((k,v)-> domainModelBean.addEnumBean(buildEnumModel(v, plantUmlContextBean.getAppName())));

        if(plantUmlContextBean.getDerivedPlantUmlContextBean() != null){
            PlantUmlContextBean derivedPlantUmlContextBean = plantUmlContextBean.getDerivedPlantUmlContextBean();
            derivedPlantUmlContextBean.getClassBeanMap().forEach((k,v)-> domainModelBean.addClassBean(buildClassModel(v, plantUmlContextBean.getAppName())));
            derivedPlantUmlContextBean.getInterfaceBeanMap().forEach((k,v)-> domainModelBean.addInterfaceBean(buildInterfaceModel(v, plantUmlContextBean.getAppName())));
            derivedPlantUmlContextBean.getEnumBeanMap().forEach((k,v)-> domainModelBean.addEnumBean(buildEnumModel(v, plantUmlContextBean.getAppName())));

        }
        Map<String, ComponentContextBean> componentContextBeanMap = plantUmlContextBean.getCompContextBeanMap();
        if(componentContextBeanMap == null || componentContextBeanMap.isEmpty()){
            return domainModelBean;
        }

        componentContextBeanMap.forEach((k,componentContextBean)->{
            if(CollectionUtils.isNotEmpty(componentContextBean.getClassBeanList())){
                componentContextBean.getClassBeanList().forEach(classBean -> domainModelBean.addClassBean(buildClassModel(classBean,componentContextBean.getComponentName())));
            }
            if(CollectionUtils.isNotEmpty(componentContextBean.getInterfaceBeanList())){
                componentContextBean.getInterfaceBeanList().forEach(interfaceBean -> domainModelBean.addInterfaceBean(buildInterfaceModel(interfaceBean,componentContextBean.getComponentName())));
            }

            if(CollectionUtils.isNotEmpty(componentContextBean.getEnumBeanList())){
                componentContextBean.getEnumBeanList().forEach(enumBean -> domainModelBean.addEnumBean(buildEnumModel(enumBean,componentContextBean.getComponentName())));
            }
        });


        return domainModelBean;
    }


    /**
     * 转换枚举元数据模型
     * @param enumBean
     * @param compName
     * @return
     */
    private EnumModel buildEnumModel(EnumBean enumBean,String compName){
        EnumModel enumModel = new EnumModel();
        enumModel.setCompName(compName);
        enumModel.setClassDesc(enumBean.getClassDesc());
        enumModel.setPackageName(enumBean.getPackageName());
        enumModel.setFieldBeanList(buildFieldList(enumBean.getFieldBeanList(),enumBean.getClassName()));
        enumModel.setEnumValueList(enumBean.getEnumValueList());
        enumModel.setMethodBeanList(buildMethodList(enumBean.getMethodBeanList()));
        enumModel.setImplInterfaceBean(buildInterfaceModel(enumBean.getImplInterfaceBean(),compName));
        return enumModel;
    }

    /**
     * 转换接口元数据模型
     * @param interfaceBean
     * @param compName
     * @return
     */
    private InterfaceModel buildInterfaceModel(InterfaceBean interfaceBean,String compName){
        if(interfaceBean == null){
            return null;
        }
        InterfaceModel interfaceModel = new InterfaceModel();
        interfaceModel.setCompName(compName);
        interfaceModel.setClassDesc(interfaceBean.getClassDesc());
        interfaceModel.setClassName(interfaceBean.getClassName());
        interfaceModel.setPackageName(interfaceBean.getPackageName());
        interfaceModel.setRelationClassStr(interfaceBean.getRelationClassStr());
        interfaceModel.setMethodBeanList(buildMethodList(interfaceBean.getMethodBeanList()));
        return interfaceModel;
    }




    /**
     * 转换class元数据模型
     * @param classBean
     * @param compName
     * @return
     */
    private ClassModel buildClassModel(ClassBean classBean,String compName){
        ClassModel classModel = new ClassModel();
        classModel.setCompName(compName);
        classModel.setClassName(classBean.getClassName());
        classModel.setPackageName(classBean.getPackageName());
        classModel.setClassDesc(classBean.getClassDesc());
        classModel.setRelationClassStr(classBean.getRelationClassStr());
        classModel.setImplInterfaceBean(buildInterfaceModel(classBean.getImplInterfaceBean(),compName));
        classModel.setFieldBeanList(buildFieldList(classBean.getFieldBeanList(),classBean.getClassName()));
        classModel.setMethodBeanList(buildMethodList(classBean.getMethodBeanList()));

        classModel.setSuperFieldBeanList(buildFieldList(classBean.getSuperFieldBeanList(),classBean.getClassName()));
        classModel.setSuperMethodBeanList(buildMethodList(classBean.getSuperMethodBeanList()));


        classModel.setClassName(classBean.getClassName());
        return classModel;
    }








    /**
     * 转换属性元数据模型
     * @param fieldBeanList
     * @param className
     * @return
     */
    private List<FieldModel> buildFieldList(List<FieldBean> fieldBeanList, String className){
        List<FieldModel> fieldModelList = new ArrayList<>();
        if(CollectionUtils.isEmpty(fieldBeanList)){
            return fieldModelList;
        }
        for (FieldBean fieldBean : fieldBeanList){
            FieldModel fieldModel = new FieldModel();
            fieldModel.setFieldType(fieldBean.getFieldType());
            fieldModel.setFieldName(fieldBean.getFieldName());
            fieldModel.setClassName(className);
            fieldModel.setDesc(fieldBean.getDesc());
            fieldModelList.add(fieldModel);
        }

        return fieldModelList;
    }

    /**
     * 转换方法元数据模型
     * @param methodBeanList
     * @return
     */
    private List<MethodModel> buildMethodList(List<MethodBean> methodBeanList){
        List<MethodModel> methodModelList = new ArrayList<>();
        if(CollectionUtils.isEmpty(methodBeanList)){
            return methodModelList;
        }
        for (MethodBean methodBean : methodBeanList){
            MethodModel methodModel = new MethodModel();
            methodModel.setStatic(methodBean.isStatic());
            methodModel.setMethodName(methodBean.getMethodName());
            methodModel.setReturnClass(methodBean.getReturnClass());
            methodModel.setGenericReturnType(methodBean.getGenericReturnType());
            methodModel.setDesc(methodBean.getDesc());
            methodModel.setReturnClassPackage(methodBean.getReturnClassPackage());
            methodModel.setParamModelList(buildParamModelList(methodBean.getMethodName()));
            methodModelList.add(methodModel);
        }

        return  methodModelList;
    }


    /**
     * 从方法名称中解析到方法参数
     */
    private List<ParamModel> buildParamModelList(String methodName){
        List<ParamModel> paramModelList = new ArrayList<>();
        if(!methodName.contains("(") && !methodName.contains(")")){
            return paramModelList;
        }
        if (!methodName.contains("()")){
            String [] paramArr = methodName.replace(")","").split("\\(")[1].split(",");
            if(paramArr != null && paramArr.length>=1){
                for (String paramStr : paramArr){
                    ParamModel paramModel = new ParamModel();
                    if(paramStr.trim().contains(" ")){
                        paramModel.setParamName(paramStr.split(" ")[1]);
                        paramModel.setParamType(paramStr.split(" ")[0]);
                    }else {
                        paramModel.setParamType(paramStr);
                    }
                    paramModelList.add(paramModel);
                }
            }
        }

        return paramModelList;
    }

}
