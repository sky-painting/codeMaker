package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.enums.ClassEnum;
import com.coderman.codemaker.enums.ClassRelationEnum;
import com.coderman.codemaker.enums.VisibilityEnum;
import com.coderman.codemaker.utils.StringCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Description: 读文件服务
 * 读取plantUML类图
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 *
 */
@Service
@Slf4j
public class ReadPlantUMLFileServiceV2 {

    @Autowired
    private AppServiceConfig appServiceConfig;
    /**
     * 读取plantUMl文件
     * @param plantUMLFileName
     * @return
     */
    private List<String> readPlantUMLContent(String plantUMLFileName){
        if(StringUtils.isEmpty(plantUMLFileName)){
            log.error("plantUMLFileName is empty,can't read content----------------!!!!!!!!!!");
            return null;
        }
        try {
            File file = ResourceUtils.getFile("classpath:ddd-plantuml/"+plantUMLFileName);
            return FileUtils.readLines(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解析plantUML文件内容
     * @param plantUMLFileName
     * @return
     */
    public PlantUmlContextBean getPlantUmlContextBean(String plantUMLFileName){
        List<String> contentList = readPlantUMLContent(plantUMLFileName);
        if(CollectionUtils.isEmpty(contentList)){
            return null;
        }
        List<String> elementList = new ArrayList<>();
        PlantUmlContextBean plantUmlContextBean = new PlantUmlContextBean();

        String currentPackage= "";
        //对class,enum,interface进行解析
        for(String str : contentList){
            if(StringUtils.isEmpty(str) || str.contains("@startuml")){
                continue;
            }

            if(str.contains("package")){
                currentPackage = str.trim().replace("package","").replace("{","").trim();
                continue;
            }

            if(str.trim().contains("{")){
                elementList.add(str.trim());
                continue;
            }
            if(str.trim().contains("}")){
                elementList.add(str.trim());
                parseClassElement(elementList,plantUmlContextBean,currentPackage);
                elementList.clear();
                continue;
            }
            elementList.add(str.trim());
        }

        //对类与类的关系进行解析
        Map<String, List<String>> relationListMap = new HashMap<>();
        for(String str : contentList){
            if(StringUtils.isEmpty(str) || str.contains("@startuml")
                    || str.contains("package") || str.trim().contains("{") || str.trim().contains("}")){
                continue;
            }

            Map<String, String> relationMap = ClassRelationEnum.parseRelation(str);
            if(relationMap == null || relationMap.size()<2){
                continue;
            }
            List<String> relationList =  relationListMap.get(relationMap.get("class"));
            if(CollectionUtils.isEmpty(relationList)){
                relationList = new ArrayList<>();
            }
            relationList.add(relationMap.get("relation"));
            relationListMap.put(relationMap.get("class").trim(),relationList);
        }

        plantUmlContextBean.getClassBeanMap().forEach((className,classBean)->{
            List<String> relationList = relationListMap.get(className);
            if(CollectionUtils.isNotEmpty(relationList)){
                classBean.setRelationClassStr(StringUtils.join(relationList,","));
            }
        });

        //统一对解析内容进行校验，提前暴露
        plantUmlContextBean.getClassBeanMap().forEach((className,classBean)->{
            List<FieldBean> fieldBeanList = classBean.getFieldBeanList();
            Set<String> fieldNameSet = new HashSet<>();
            fieldBeanList.forEach(fieldBean -> {
                if(fieldNameSet.contains(fieldBean.getFieldName())){
                    log.error("属性重复,类名{},属性名:{}",className,fieldBean.getFieldName());
                }
                fieldNameSet.add(fieldBean.getFieldName());
            });

            List<MethodBean> methodBeanList = classBean.getMethodBeanList();
            Set<String> methodNameSet = new HashSet<>();
            methodBeanList.forEach(methodBean -> {
                if(fieldNameSet.contains(methodBean.getMethodName())){
                    log.error("方法重复,类名{},属性名:{}",className,methodBean.getMethodName());
                }
                methodNameSet.add(methodBean.getMethodName());
            });

        });

        plantUmlContextBean.getInterfaceBeanMap().forEach((className,interfaceBean)->{
            List<MethodBean> methodBeanList = interfaceBean.getMethodBeanList();
            Set<String> methodNameSet = new HashSet<>();
            methodBeanList.forEach(methodBean -> {
                if(methodNameSet.contains(methodBean.getMethodName())){
                    log.error("方法重复,类名{},属性名:{}",className,methodBean.getMethodName());
                }
                methodNameSet.add(methodBean.getMethodName());
            });
        });

        return plantUmlContextBean;
    }


    /**
     * 解析文件内容整体路由
     * @param elementList
     * @param plantUmlContextBean
     */
    private void parseClassElement(List<String> elementList, PlantUmlContextBean plantUmlContextBean, String currentPackage){
        String classType = getClassType(elementList.get(0));
        if(classType.equals(ClassEnum.CLASS.getClassType())){
            ClassBean classBean = buildClassBean(elementList);
            classBean.buildPlantUMLPackage(currentPackage);
            plantUmlContextBean.addClassBean(classBean);
        }
        else if(classType.equals(ClassEnum.ENUM.getClassType())){
            EnumBean enumBean = builEnumBean(elementList);
            enumBean.buildPlantUMLPackage(currentPackage);
            plantUmlContextBean.addEnumBean(enumBean);
        }
        else if(classType.equals(ClassEnum.INTERFACE.getClassType())){
            InterfaceBean interfaceBean = buildInterfaceBean(elementList);
            interfaceBean.buildPlantUMLPackage(currentPackage);
            plantUmlContextBean.addInterfaceBean(interfaceBean);
        }

    }


    private String getClassType(String ele){
        return ele.split(" ")[0];
    }

    /**
     * 解析class类型数据
     * @param elementList
     * @return
     */
    private ClassBean buildClassBean(List<String> elementList ){

        String[] array = elementList.get(0).trim().replace("{","").trim().split("\"");

        String classMetaInfoArr = array[1];
        List<FieldBean> fieldBeanList = getFieldBeanList(elementList.subList(1,elementList.size()));
        List<MethodBean> methodBeanList = getMethodBeanList(elementList.subList(1,elementList.size()));
        ClassBean classBean = new ClassBean();
        classBean.setFieldBeanList(fieldBeanList);

        classBean.setAuthor(appServiceConfig.getAuthor());
        classBean.setClassName(classMetaInfoArr.split("-")[1].replace("\"",""));
        classBean.setClassDesc(classMetaInfoArr.split("-")[0].replace("\"",""));

        if(StringCheckUtils.isContainChinese(classBean.getClassName())){
            String className = classBean.getClassDesc();
            classBean.setClassDesc(classBean.getClassName());
            classBean.setClassName(className);
        }
        methodBeanList.forEach(methodBean -> methodBean.setClassName(classBean.getClassName()));
        classBean.setMethodBeanList(methodBeanList);

        return classBean;
    }

    /**
     * 解析接口类型数据
     * @param elementList
     * @return
     */
    private InterfaceBean buildInterfaceBean(List<String> elementList ){

        String[] array = elementList.get(0).trim().replace("{","").trim().split("\"");

        String classMetaInfoArr = array[1];
        List<MethodBean> methodBeanList = getMethodBeanList(elementList.subList(1,elementList.size()));
        InterfaceBean interfaceBean = new InterfaceBean();
        interfaceBean.setAuthor(appServiceConfig.getAuthor());

        interfaceBean.setClassName(classMetaInfoArr.split("-")[1].replace("\"",""));
        interfaceBean.setClassDesc(classMetaInfoArr.split("-")[0].replace("\"",""));
        if(StringCheckUtils.isContainChinese(interfaceBean.getClassName())){
            String className = interfaceBean.getClassDesc();
            interfaceBean.setClassDesc(interfaceBean.getClassName());
            interfaceBean.setClassName(className);
        }
        methodBeanList.stream().forEach(methodBean -> methodBean.setClassName(interfaceBean.getClassName()));
        interfaceBean.setMethodBeanList(methodBeanList);

        return interfaceBean;
    }

    /**
     * 解析enum类型数据
     * @param elementList
     * @return
     */
    private EnumBean builEnumBean(List<String> elementList ){

        String[] array = elementList.get(0).trim().replace("{","").trim().split("\"");

        String classMetaInfoArr = array[1];
        List<FieldBean> fieldBeanList = getFieldBeanList(elementList.subList(1,elementList.size()));
        EnumBean enumBean = new EnumBean();
        enumBean.setFieldBeanList(fieldBeanList);
        enumBean.setAuthor(appServiceConfig.getAuthor());
        enumBean.setClassName(classMetaInfoArr.split("-")[1].replace("\"",""));
        enumBean.setClassDesc(classMetaInfoArr.split("-")[0].replace("\"",""));
        if(StringCheckUtils.isContainChinese(enumBean.getClassName())){
            String className = enumBean.getClassDesc();
            enumBean.setClassDesc(enumBean.getClassName());
            enumBean.setClassName(className);
        }
        dealEnumMethodBeanList(elementList,enumBean);
        return enumBean;
    }

    /**
     * 获取类的属性列表
     * @param elementList
     * @return
     */
    private List<FieldBean> getFieldBeanList(List<String> elementList){
        List<FieldBean> fieldBeanList = new ArrayList<>();

        for (String fieldStr : elementList){
            if(fieldStr.contains("(") || fieldStr.contains(")")){
                continue;
            }
            if(!fieldStr.trim().contains(":")){
                continue;
            }
            String[] fieldArr = fieldStr.trim().split(":");
            FieldBean fieldBean = new FieldBean();
            fieldBean.buildDesc(fieldArr[0]);
            fieldBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldArr[0]));
            fieldBean.setFieldName(fieldArr[1]);
            fieldBeanList.add(fieldBean);
        }
        return fieldBeanList;
    }

    /**
     * 获取类的方法列表
     * @param elementList
     * @return
     */
    private List<MethodBean> getMethodBeanList(List<String> elementList){
        List<MethodBean> methodBeanList = new ArrayList<>();
        for (String fieldStr : elementList){
            if(!fieldStr.contains("()") && !fieldStr.contains("(") && !fieldStr.contains(")")){
                continue;
            }
            MethodBean methodBean = new MethodBean();
            if(fieldStr.contains(":")){
                String[] fieldArr = fieldStr.trim().split(":");
                methodBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldArr[0]));
                methodBean.buildDesc(fieldArr[0]);

                String[] arr = fieldArr[1].trim().split(" ");
                if(arr.length == 2){
                    methodBean.setReturnClass(arr[0]);
                    methodBean.setMethodName(arr[1]);
                }else{
                    methodBean.setReturnClass(arr[0]);
                    methodBean.setMethodName(fieldArr[1].trim().replaceFirst(arr[0],"").trim());
                }
            }else {
                String[] fieldArr = fieldStr.trim().split(" ");
                //没有注释--->中文校验
                if(fieldArr.length ==2){
                    methodBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldStr.trim()));
                    methodBean.setReturnClass(fieldStr.trim().split(" ")[0]);
                    methodBean.setMethodName(fieldStr.trim().split(" ")[1]);
                }else{
                    methodBean.buildDesc(fieldArr[0]);
                    methodBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldStr.trim()));
                    methodBean.setReturnClass(fieldArr[1]);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 2;i<fieldArr.length;i++){
                        builder.append(" "+fieldArr[i]);
                    }
                    methodBean.setMethodName(builder.toString());
                }
            }
            if(!methodBean.getReturnClass().contains("void")){
                methodBean.setReturnBody("return null;");
            }
            methodBean.buildParamArr();
            methodBeanList.add(methodBean);
        }
        return methodBeanList;
    }


    /**
     * 获取类的方法列表
     * @param elementList
     * @return
     */
    private void dealEnumMethodBeanList(List<String> elementList, EnumBean enumBean){
        List<MethodBean> methodBeanList = new ArrayList<>();
        List<String> enumValueList  = new ArrayList<>();
        for (String fieldStr : elementList){
            if(!fieldStr.contains("()") && !fieldStr.contains("(") && !fieldStr.contains(")")){
                continue;
            }

            //处理枚举值
            String valueCode = fieldStr.substring(0,fieldStr.indexOf("("));
            if(!StringUtils.isEmpty(valueCode) && !valueCode.contains(" ")){
                enumValueList.add(fieldStr);
                continue;
            }

            MethodBean methodBean =new MethodBean();

            //-创建变更日志: TradeOrderLogBO getTradeOrderLogBO()
            if(fieldStr.contains(":")){
                String[] fieldArr = fieldStr.trim().split(":");
                methodBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldArr[0]));
                methodBean.setDesc(fieldArr[0]);
                methodBean.setReturnClass(fieldArr[1].trim().split(" ")[0]);
                methodBean.setMethodName(fieldArr[1].trim().split(" ")[1]);
            }else {
                String[] fieldArr = fieldStr.trim().split(" ");
                //没有注释--->中文校验
                if(fieldArr.length ==2){
                    methodBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldStr.trim()));
                    methodBean.setReturnClass(fieldStr.trim().split(" ")[0]);
                    methodBean.setMethodName(fieldStr.trim().split(" ")[1]);
                }else{
                    methodBean.setDesc(fieldArr[0]);
                    methodBean.setVisibility(VisibilityEnum.getVisibilityStr(fieldStr.trim()));
                    methodBean.setReturnClass(fieldArr[1]);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 2;i<fieldArr.length;i++){
                        builder.append(" "+fieldArr[i]);
                    }
                    methodBean.setMethodName(builder.toString());
                }
            }
            if(!methodBean.getReturnClass().contains("void")){
                methodBean.setReturnBody("return null;");
            }

            methodBeanList.add(methodBean);
        }
        enumBean.setEnumValueList(enumValueList);
        enumBean.setMethodBeanList(methodBeanList);
    }
}
