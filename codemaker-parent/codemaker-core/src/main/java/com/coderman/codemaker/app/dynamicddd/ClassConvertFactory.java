package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import com.coderman.codemaker.enums.DomainElementEnum;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.enums.VisibilityEnum;
import com.coderman.codemaker.utils.StringHelperUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:类转换构建工厂
 * date: 2021/10/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ClassConvertFactory {

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private ClassBeanFactory classBeanFactory;

    @Autowired
    private MethodBeanFactory methodBeanFactory;

    /**
     * 处理派生类bo->dto
     *
     * @param domainBOClassList
     * @return
     */
    public List<ClassBean> getDTOClassList(List<ClassBean> domainBOClassList) {
        List<ClassBean> dtoClassList = new ArrayList<>();
        for (ClassBean classBean : domainBOClassList) {
            List<FieldBean> fieldBeanList = classBean.buildSimpleFieldList();
            String[] classNameArr = classBean.getExtendFieldBean().getDtoKeyList();
            for (String className : classNameArr) {
                dtoClassList.add(classBeanFactory.buildDtoClassBean(className, classBean, fieldBeanList));
            }
        }
        return dtoClassList;
    }

    /**
     * 处理派生类bo->dto
     *
     * @param domainBOClassList
     * @return
     */
    public List<ClassBean> getVOClassList(List<ClassBean> domainBOClassList) {
        List<ClassBean> voClassList = new ArrayList<>();

        for (ClassBean classBean : domainBOClassList) {
            List<FieldBean> fieldBeanList = classBean.buildSimpleFieldList();
            String[] classNameArr = classBean.getExtendFieldBean().getVoKeyList();
            if (classNameArr == null || classNameArr.length == 0) {
                continue;
            }
            for (String className : classNameArr) {
                voClassList.add(classBeanFactory.buildVOClassBean(className, classBean, fieldBeanList));
            }
        }

        return voClassList;
    }


    /**
     * 处理派生类dto->facade
     *
     * @param dtoClassList
     * @return
     */
    public List<InterfaceBean> getFacadeInterfaceList(List<ClassBean> dtoClassList) {
        List<InterfaceBean> facadeInterfaceList = new ArrayList<>();

        for (ClassBean classBean : dtoClassList) {
            InterfaceBean interfaceBean = classBeanFactory.buildInterfaceBean(classBean);
            if (interfaceBean == null) {
                continue;
            }
            facadeInterfaceList.add(interfaceBean);

        }
        return facadeInterfaceList;
    }

    /**
     * 处理派生类dto-bo convert
     *
     * @param dtoClassList
     * @return
     */
    public List<InterfaceBean> getDTOBOConvertInterfaceList(List<ClassBean> dtoClassList) {
        List<InterfaceBean> facadeInterfaceList = new ArrayList<>();
        for (ClassBean classBean : dtoClassList) {
            InterfaceBean interfaceBean = new InterfaceBean();
            int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainDerivedElementEnum.DTO.getElement());
            if (x >= 0) {
                String className = classBean.getClassName().substring(0, x) + "Convert";
                interfaceBean.setClassName(className);
                interfaceBean.setPlantUMLPackage("model.convert");
                interfaceBean.setClassDesc(classBean.getClassDesc());
                interfaceBean.setMethodBeanList(getDTOBOConvertMethodList(classBean));
                List<String> importList = new ArrayList<>();
                importList.add(classBean.getPackageName() + "." + classBean.getClassName());
                interfaceBean.setImportClassList(importList);
                facadeInterfaceList.add(interfaceBean);
            }
        }
        return facadeInterfaceList;
    }


    /**
     * 处理派生类dto-bo convert
     *
     * @param boElementBeanList
     * @return
     */
    public Map<String, Object> getDOBOConvertInterfaceList(List<ClassBean> boElementBeanList) {

        Map<String, Object> convertMap = new HashMap<>();
        Map<String, String> boConvertRelationMap = new HashMap<>();

        List<InterfaceBean> doboConvertInterfaceList = new ArrayList<>();
        for (ClassBean classBean : boElementBeanList) {
            InterfaceBean interfaceBean = new InterfaceBean();
            int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainElementEnum.BO.getElement());
            if (x >= 0) {
                String className = classBean.getClassName().substring(0, x) + "Converter";
                interfaceBean.setClassName(className);
                interfaceBean.setPlantUMLPackage("data.convert");
                interfaceBean.setClassDesc(classBean.getClassDesc());
                interfaceBean.setMethodBeanList(getDOBOConvertMethodList(classBean));
                interfaceBean.setImportClassList(Lists.newArrayList());
                doboConvertInterfaceList.add(interfaceBean);
                boConvertRelationMap.put(classBean.getClassName(), className);
            }
        }
        convertMap.put("doboConvertList", doboConvertInterfaceList);
        convertMap.put("doboConvertRelationMap", boConvertRelationMap);
        return convertMap;
    }

    /**
     * 处理派生类dto-bo convert
     *
     * @param classBean
     * @return
     */
    public List<MethodBean> getDOBOConvertMethodList(ClassBean classBean) {
        Optional<FieldBean> fieldBean = classBean.getFieldBeanList().stream().filter(v -> v.isTableKey()).findFirst();
        if (!fieldBean.isPresent()) {
            return Lists.newArrayList();
        }

        String tableName = fieldBean.get().getFieldName().replace("String", "").replace("string", "").trim();
        if (StringUtils.isEmpty(tableName)) {
            return Lists.newArrayList();
        }
        String classDOName = StringHelperUtils.getClassDOName(tableName) + "DO";


        List<MethodBean> methodBeanList = new ArrayList<>();
        MethodBean do2bo = new MethodBean();
        String boClassName = classBean.getClassName();
        String varClassName = classDOName.substring(0, 1).toLowerCase().concat(classDOName.substring(1));
        do2bo.setMethodName("do2bo(" + classDOName + " " + varClassName + ")");
        do2bo.setReturnClass(boClassName);
        methodBeanList.add(do2bo);

        MethodBean dtoList2boList = new MethodBean();
        dtoList2boList.setMethodName("doList2boList(List<" + classDOName + "> " + varClassName + "List)");
        dtoList2boList.setReturnClass("List<" + boClassName + ">");
        methodBeanList.add(dtoList2boList);


        MethodBean bo2to = new MethodBean();
        varClassName = boClassName.substring(0, 1).toLowerCase().concat(classBean.getClassName().substring(1));
        bo2to.setMethodName("bo2do(" + boClassName + " " + varClassName + ")");
        bo2to.setReturnClass(classDOName);
        methodBeanList.add(bo2to);

        MethodBean boList2dtoList = new MethodBean();
        boList2dtoList.setMethodName("boList2doList(List<" + boClassName + "> " + varClassName + "List)");
        boList2dtoList.setReturnClass("List<" + classDOName + ">");
        methodBeanList.add(boList2dtoList);

        methodBeanList.forEach(methodBean -> methodBean.buildParamArr());
        return methodBeanList;
    }


    /**
     * 处理派生类dto-bo convert
     *
     * @param classBean
     * @return
     */
    public List<MethodBean> getDTOBOConvertMethodList(ClassBean classBean) {
        List<MethodBean> methodBeanList = new ArrayList<>();
        MethodBean dto2bo = new MethodBean();
        String boClassName = classBean.getClassName().replace("DTO", "BO");
        String varClassName = classBean.getClassName().substring(0, 1).toLowerCase().concat(classBean.getClassName().substring(1));
        dto2bo.setMethodName("dto2bo(" + classBean.getClassName() + " " + varClassName + ")");
        dto2bo.setReturnClass(boClassName);
        methodBeanList.add(dto2bo);

        MethodBean dtoList2boList = new MethodBean();
        dtoList2boList.setMethodName("dtoList2boList(List<" + classBean.getClassName() + "> " + varClassName + "List)");
        dtoList2boList.setReturnClass("List<" + boClassName + ">");
        methodBeanList.add(dtoList2boList);


        MethodBean bo2to = new MethodBean();
        varClassName = boClassName.substring(0, 1).toLowerCase().concat(classBean.getClassName().substring(1));
        bo2to.setMethodName("bo2dto(" + boClassName + " " + varClassName + ")");
        bo2to.setReturnClass(classBean.getClassName().replace("DTO", "BO"));
        methodBeanList.add(bo2to);

        MethodBean boList2dtoList = new MethodBean();
        boList2dtoList.setMethodName("boList2dtoList(List<" + boClassName + "> " + varClassName + "List)");
        boList2dtoList.setReturnClass("List<" + classBean.getClassName() + ">");
        methodBeanList.add(boList2dtoList);

        return methodBeanList;
    }


    /**
     * 处理派生类facade->facadeimpl
     *
     * @param facadeInterfaceList
     * @return
     */
    public List<ClassBean> getFacadeImplList(List<InterfaceBean> facadeInterfaceList) {
        List<ClassBean> facadeImplList = new ArrayList<>();

        for (InterfaceBean interfaceBean : facadeInterfaceList) {
            ClassBean classBean = new ClassBean();
            String className = interfaceBean.getClassName() + "Impl";
            classBean.setClassName(className);
            classBean.setClassDesc(classBean.getClassDesc());
            classBean.setPlantUMLPackage("app.facadeimpl");
            List<MethodBean> methodBeanList = new ArrayList<>();
            interfaceBean.getMethodBeanList().forEach(methodBean -> methodBeanList.add(methodBean.copySelf("")));
            List<FieldBean> fieldBeanList = new ArrayList<>(interfaceBean.getFieldBeanList());
            classBean.setContext(interfaceBean.getContext());
            classBean.setMethodBeanList(methodBeanList);
            classBean.setFieldBeanList(fieldBeanList);
            classBean.setImportClassList(interfaceBean.getImportClassList());
            classBean.setRelationClassStr(" implements " + interfaceBean.getClassName());
            facadeImplList.add(classBean);
        }
        return facadeImplList;
    }

    /**
     * 处理派生类bo->facade
     *
     * @param domainBOClassList
     * @return
     */
    public List<InterfaceBean> getFacadeClassList(List<ClassBean> domainBOClassList) {
        Map<String, List<ClassBean>> facadeBOmap = new HashMap<>();
        domainBOClassList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isFacadeKey()).findFirst();
            if (fieldBeanOptional.isPresent()) {
                String facadeClassName = fieldBeanOptional.get().getFieldName().replace("String", "")
                        .replace("string", "").trim();
                List<ClassBean> classBeanList = facadeBOmap.get(facadeClassName);
                if (CollectionUtils.isEmpty(classBeanList)) {
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                facadeBOmap.put(facadeClassName, classBeanList);
            }
        });

        List<InterfaceBean> interfaceBeanList = new ArrayList<>();
        facadeBOmap.forEach((k, v) -> {
            InterfaceBean facadeInterfaceBean = new InterfaceBean();
            if (k.toLowerCase().endsWith("facade")) {
                facadeInterfaceBean.setClassName(k);
            } else {
                facadeInterfaceBean.setClassName(k + "Facade");
            }
            facadeInterfaceBean.setPlantUMLPackage("api.facade");

            List<MethodBean> facadeMethodList = new ArrayList<>();

            v.stream().forEach(classBean -> {
                List<MethodBean> classMethodList = classBean.getMethodBeanList();
                if (CollectionUtils.isNotEmpty(classMethodList)) {
                    //通过特定字符过滤facade方法
                    List<MethodBean> newMethodList = classMethodList.stream().filter(methodBean ->
                            (methodBean.getReturnClass().toLowerCase().contains("dto")
                                    || methodBean.getReturnClass().toLowerCase().contains("result")
                                    || methodBean.getMethodName().toLowerCase().contains("facade")
                                    || methodBean.getMethodName().toLowerCase().contains("dto"))
                                    && !methodBean.isExportAclKey()
                    ).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(newMethodList)) {
                        facadeMethodList.addAll(newMethodList);
                    }
                }
                facadeInterfaceBean.setContext(classBean.getContext());
            });
            facadeMethodList.forEach(methodBean -> methodBean.buildParamArr());
            facadeInterfaceBean.setMethodBeanList(facadeMethodList);
            facadeInterfaceBean.setFieldBeanList(new ArrayList<>());
            interfaceBeanList.add(facadeInterfaceBean);
        });

        return interfaceBeanList;

    }

    /**
     * 处理派生类controller->feign
     *
     * @param controllerClassList
     * @return
     */
    public List<InterfaceBean> getFeignClassList(List<ClassBean> controllerClassList) {
        List<InterfaceBean> interfaceBeanList = new ArrayList<>();
        controllerClassList.forEach(classBean -> {
            InterfaceBean feignInterfaceBean = new InterfaceBean();
            feignInterfaceBean.setContext(classBean.getContext());
            feignInterfaceBean.setClassName(classBean.getClassName().replace("Controller","") + "Feign");
            feignInterfaceBean.setPlantUMLPackage("api.feign");
            List<MethodBean> feignMethodList = new ArrayList<>();

            classBean.getMethodBeanList().stream().forEach(methodBean -> {
                feignMethodList.add(methodBean.copySelf(""));
            });
            feignMethodList.forEach(methodBean -> methodBean.buildParamArr());
            feignInterfaceBean.setMethodBeanList(feignMethodList);
            feignInterfaceBean.setFieldBeanList(new ArrayList<>());
            interfaceBeanList.add(feignInterfaceBean);
        });
        return interfaceBeanList;
    }


    /**
     * 处理派生类bo->controller
     *
     * @param domainBOClassList
     * @return
     */
    public List<ClassBean> getControllerClassList(List<ClassBean> domainBOClassList) {
        Map<String, List<ClassBean>> controllermap = new HashMap<>();
        domainBOClassList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isControllerKey()).findFirst();
            if (fieldBeanOptional.isPresent()) {
                String controllerClassName = fieldBeanOptional.get().getFieldName().replace("String", "")
                        .replace("string", "").trim();
                List<ClassBean> classBeanList = controllermap.get(controllerClassName);
                if (CollectionUtils.isEmpty(classBeanList)) {
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                controllermap.put(controllerClassName, classBeanList);
            }
        });

        List<ClassBean> controllerBeanList = new ArrayList<>();
        controllermap.forEach((k, v) -> {
            ClassBean controllerBean = new ClassBean();
            if (k.toLowerCase().endsWith("controller")) {
                controllerBean.setClassName(k);
            } else {
                controllerBean.setClassName(k + "Controller");
            }
            controllerBean.setPlantUMLPackage("adapter.controller");
            List<MethodBean> controllerMethodList = new ArrayList<>();

            v.stream().forEach(classBean -> {
                controllerBean.setContext(classBean.getContext());
                List<MethodBean> classMethodList = classBean.getMethodBeanList();
                if (CollectionUtils.isNotEmpty(classMethodList)) {
                    //通过特定字符过滤facade方法
                    List<MethodBean> newMethodList = classMethodList.stream().filter(methodBean -> methodBean.getDesc().contains("/")
                    ).collect(Collectors.toList());


                    if (CollectionUtils.isNotEmpty(newMethodList)) {
                        newMethodList.forEach(methodBean -> {
                            String pathValue = methodBean.getDesc().substring(methodBean.getDesc().indexOf("/"));
                            methodBean.setPathValue(pathValue);
                            methodBean.setDesc(methodBean.getDesc().replace(methodBean.getPathValue(), ""));
                        });

                        controllerMethodList.addAll(newMethodList);
                    }
                }
            });
            controllerMethodList.forEach(methodBean -> methodBean.buildParamArr());
            controllerBean.setMethodBeanList(controllerMethodList);

            controllerBeanList.add(controllerBean);
        });

        return controllerBeanList;

    }


    /**
     * 对bo类进行解析
     *
     * @param domainBOList
     * @return
     */
    public Map<String, Object> getConvertInterfaceBeanList(List<ClassBean> domainBOList) {

        Map<String, List<ClassBean>> facadeBOmap = new HashMap<>();

        Map<String, Object> convertMap = new HashMap<>();

        domainBOList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isFacadeKey()).findFirst();
            if (fieldBeanOptional.isPresent()) {
                String facadeClassName = fieldBeanOptional.get().getFieldName().replace("String", "")
                        .replace("string", "").trim();
                if (facadeClassName.contains(",")) {
                    facadeClassName = classBean.getClassName().replace("BO", "").replace("Bo", "").replace("bo", "").trim();
                }
                List<ClassBean> classBeanList = facadeBOmap.get(facadeClassName);
                if (CollectionUtils.isEmpty(classBeanList)) {
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                facadeBOmap.put(facadeClassName, classBeanList);
            }
        });

        List<InterfaceBean> interfaceConvertBeanList = new ArrayList<>();

        Map<String, String> facadeConvertRelationMap = new HashMap<>();

        facadeBOmap.forEach((k, v) -> {
            InterfaceBean convertInterfaceBean = new InterfaceBean();
            String convertClassName = k.replace("Facade", "").replace("facade", "") + "Convert";
            convertInterfaceBean.setClassName(convertClassName);
            convertInterfaceBean.setPlantUMLPackage("model.convert");
            convertInterfaceBean.setClassDesc(convertClassName);
            facadeConvertRelationMap.put(k, convertClassName);
            Set<String> importClassSet = new HashSet<>();
            List<MethodBean> classMethodList = new ArrayList<>();

            v.stream().forEach(classBean -> {

                List<MethodBean> methodBeanList = new ArrayList<>();
                Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(f -> f.isDtoKey()).findFirst();
                if (fieldBeanOptional.isPresent()) {
                    String dtoClassNameStr = fieldBeanOptional.get().getFieldName()
                            .replace("String", "")
                            .replace("string", "")
                            .trim();


                    String[] arr = dtoClassNameStr.split(",");
                    for (String dtoClassName : arr) {
                        importClassSet.add(appServiceConfig.getPackage() + ".api.dto." + dtoClassName);
                        MethodBean dto2bo = new MethodBean();
                        String vardtoName = dtoClassName.substring(0, 1).toLowerCase().concat(dtoClassName.substring(1));
                        dto2bo.setMethodName("dto2bo(" + dtoClassName + " " + vardtoName + ")");
                        dto2bo.setReturnClass(classBean.getClassName());
                        methodBeanList.add(dto2bo);

                        MethodBean dtoList2boList = new MethodBean();
                        dtoList2boList.setMethodName(vardtoName + "s2boList(List<" + dtoClassName + "> " + vardtoName + "List)");
                        dtoList2boList.setReturnClass("List<" + classBean.getClassName() + ">");
                        methodBeanList.add(dtoList2boList);

                        MethodBean bo2to = new MethodBean();
                        String varBOClassName = classBean.getClassName().substring(0, 1).toLowerCase().concat(classBean.getClassName().substring(1));
                        bo2to.setMethodName("bo2dto(" + classBean.getClassName() + " " + varBOClassName + ")");
                        bo2to.setReturnClass(dtoClassName);
                        methodBeanList.add(bo2to);

                        MethodBean boList2dtoList = new MethodBean();
                        boList2dtoList.setMethodName(varBOClassName + "s2dtoList(List<" + classBean.getClassName() + "> " + varBOClassName + "List)");
                        boList2dtoList.setReturnClass("List<" + dtoClassName + ">");
                        methodBeanList.add(boList2dtoList);
                    }
                    classMethodList.addAll(methodBeanList);
                }
            });

            convertInterfaceBean.setImportClassList(Lists.newArrayList(importClassSet));

            Map<String, MethodBean> map = new HashMap<>();
            classMethodList
                    .stream().filter(methodBean ->
                            !methodBean.getMethodName().toLowerCase().contains("response") &&
                                    !methodBean.getMethodName().toLowerCase().contains("responsedto") &&
                                    !methodBean.getReturnClass().toLowerCase().contains("request") &&
                                    !methodBean.getReturnClass().toLowerCase().contains("requestdto") ||
                                    methodBean.getReturnClass().toLowerCase().contains("requestbo")
                    ).collect(Collectors.toList()).forEach(methodBean -> map.putIfAbsent(methodBean.getReturnClass() + methodBean.getMethodName(), methodBean));
            convertInterfaceBean.setMethodBeanList(map.values().stream().collect(Collectors.toList()));
            convertInterfaceBean.getMethodBeanList().forEach(methodBean -> methodBean.buildParamArr());
            interfaceConvertBeanList.add(convertInterfaceBean);
        });


        convertMap.put("interfaceList", interfaceConvertBeanList);
        convertMap.put("facadeconvertrelation", facadeConvertRelationMap);

        return convertMap;

    }


    /**
     * 对bo类进行解析
     *
     * @param domainBOList
     * @return
     */
    public Map<String, Object> getConvertBOVOInterfaceBeanList(List<ClassBean> domainBOList) {
        Map<String, Object> convertMap = new HashMap<>();
        Map<String, String> boConvertRelationMap = new HashMap<>();

        Map<String, List<ClassBean>> contrllerBOmap = new HashMap<>();
        domainBOList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isControllerKey()).findFirst();
            if (fieldBeanOptional.isPresent()) {
                String controllerClassName = fieldBeanOptional.get().getFieldName().replace("String", "")
                        .replace("string", "").trim();
                if (controllerClassName.contains(",")) {
                    controllerClassName = classBean.getClassName().replace("BO", "").replace("Bo", "").replace("bo", "").trim();
                }
                List<ClassBean> classBeanList = contrllerBOmap.get(controllerClassName);
                if (CollectionUtils.isEmpty(classBeanList)) {
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                contrllerBOmap.put(controllerClassName, classBeanList);
            }
        });

        List<InterfaceBean> interfaceConvertBeanList = new ArrayList<>();
        contrllerBOmap.forEach((k, v) -> {
            InterfaceBean convertInterfaceBean = new InterfaceBean();
            String convertClassName = k.replace("Controller", "").replace("controller", "") + "Convertervobo";
            convertInterfaceBean.setClassName(convertClassName);
            convertInterfaceBean.setPlantUMLPackage("model.convert");
            convertInterfaceBean.setClassDesc(convertClassName);
            Set<String> importClassSet = new HashSet<>();
            List<MethodBean> classMethodList = new ArrayList<>();


            v.stream().forEach(classBean -> {

                List<MethodBean> methodBeanList = new ArrayList<>();
                Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(f -> f.isVoKey()).findFirst();
                if (fieldBeanOptional.isPresent()) {
                    String voClassNameStr = fieldBeanOptional.get().getFieldName()
                            .replace("String", "")
                            .replace("string", "")
                            .trim();

                    String[] arr = voClassNameStr.split(",");
                    int i = 0;
                    for (String voClassName : arr) {
                        String varBOClassName = classBean.getClassName().substring(0, 1).toLowerCase().concat(classBean.getClassName().substring(1));

                        importClassSet.add(appServiceConfig.getPackage() + ".adapter.vo." + voClassName);
                        MethodBean dto2bo = new MethodBean();
                        String varVoName = voClassName.substring(0, 1).toLowerCase().concat(voClassName.substring(1));
                        dto2bo.setMethodName("vo2bo(" + voClassName + " " + varVoName + ")");
                        dto2bo.setReturnClass(classBean.getClassName());
                        methodBeanList.add(dto2bo);

                        MethodBean dtoList2boList = new MethodBean();
                        String tmpVar = varVoName.replace(varBOClassName.replace("BO", "").replace("Bo", ""), "");
                        varVoName = tmpVar.substring(0, 1).toLowerCase().concat(tmpVar.substring(1));
                        if (StringUtils.isEmpty(varVoName)) {
                            varVoName = "vo";
                        }
                        String volist2bolist = methodBeanFactory.getvolist2boListMethod(i);
                        dtoList2boList.setMethodName(volist2bolist + "(List<" + voClassName + "> " + varVoName + "List)");
                        dtoList2boList.setReturnClass("List<" + classBean.getClassName() + ">");
                        methodBeanList.add(dtoList2boList);

                        MethodBean bo2to = new MethodBean();
                        tmpVar = voClassName.replace(classBean.getClassName().replace("BO", "").replace("Bo", ""), "");

                        bo2to.setMethodName("bo2" + tmpVar + "(" + classBean.getClassName() + " " + varBOClassName + ")");
                        bo2to.setReturnClass(voClassName);
                        methodBeanList.add(bo2to);

                        MethodBean boList2dtoList = new MethodBean();
                        String bolist2volist = methodBeanFactory.getbolist2voListMethod(i);

                        boList2dtoList.setMethodName(bolist2volist + "(List<" + classBean.getClassName() + "> " + varBOClassName + "List)");
                        boList2dtoList.setReturnClass("List<" + voClassName + ">");
                        methodBeanList.add(boList2dtoList);
                        i++;
                    }
                    classMethodList.addAll(methodBeanList);
                }
                boConvertRelationMap.put(classBean.getClassName(), convertInterfaceBean.getClassName());
            });

            convertInterfaceBean.setImportClassList(Lists.newArrayList(importClassSet));

            Map<String, MethodBean> map = new HashMap<>();
            classMethodList
                    .stream().filter(methodBean ->
                            !methodBean.getMethodName().toLowerCase().contains("response") &&
                                    !methodBean.getMethodName().toLowerCase().contains("responsevo") &&
                                    !methodBean.getReturnClass().toLowerCase().contains("request") &&
                                    !methodBean.getReturnClass().toLowerCase().contains("requestvo") ||
                                    methodBean.getReturnClass().toLowerCase().contains("requestbo")
                    ).collect(Collectors.toList()).forEach(methodBean -> map.putIfAbsent(methodBean.getReturnClass() + methodBean.getMethodName(), methodBean));
            convertInterfaceBean.setMethodBeanList(map.values().stream().collect(Collectors.toList()));
            convertInterfaceBean.getMethodBeanList().forEach(methodBean -> methodBean.buildParamArr());
            interfaceConvertBeanList.add(convertInterfaceBean);
        });
        convertMap.put("voboconvertlist", interfaceConvertBeanList);
        convertMap.put("voboconvertrelation", boConvertRelationMap);
        return convertMap;
    }

    /**
     * 构建方法内容
     *
     * @param methodBeanList
     * @return
     */
    private List<MethodBean> getMethodBeanList(List<MethodBean> methodBeanList) {
        for (MethodBean methodBean : methodBeanList) {
            methodBean.buildMethodContent();
        }
        return methodBeanList;
    }

    /**
     * 处理派生类gataway->gatawayimpl
     *
     * @param gatawayInterfaceList
     * @return
     */
    public List<ClassBean> getGatawayImplList(List<InterfaceBean> gatawayInterfaceList) {
        List<ClassBean> gatawayImplList = new ArrayList<>();

        for (InterfaceBean interfaceBean : gatawayInterfaceList) {
            ClassBean classBean = new ClassBean();
            String className = interfaceBean.getClassName() + "Impl";
            classBean.setClassName(className);
            classBean.setClassDesc(classBean.getClassDesc());
            classBean.setPlantUMLPackage("infrast.gatawayimpl");
            classBean.setPackageName(appServiceConfig.getPackage() + "." + classBean.getPlantUMLPackage());
            if (CollectionUtils.isNotEmpty(interfaceBean.getMethodBeanList())) {
                classBean.setMethodBeanList(getMethodBeanList(interfaceBean.getMethodBeanList()));
                classBean.setFieldBeanList(interfaceBean.getFieldBeanList());
            } else {
                classBean.setMethodBeanList(Lists.newArrayList());
            }
            classBean.setImportClassList(interfaceBean.getImportClassList());
            classBean.setRelationClassStr(" implements " + interfaceBean.getClassName());
            gatawayImplList.add(classBean);
        }
        return gatawayImplList;
    }

    /**
     * 处理派生类infrastacl->infrastaclimpl
     *
     * @param InfrastAclInterfaceList
     * @return
     */
    public List<ClassBean> getInfrastAclImplList(List<InterfaceBean> InfrastAclInterfaceList) {
        List<ClassBean> infrastAclImplList = new ArrayList<>();

        for (InterfaceBean interfaceBean : InfrastAclInterfaceList) {
            ClassBean classBean = new ClassBean();
            String className = interfaceBean.getClassName() + "Impl";
            classBean.setClassName(className);
            classBean.setClassDesc(classBean.getClassDesc());
            classBean.setPlantUMLPackage("infrast.acl.impl");
            classBean.setPackageName(appServiceConfig.getPackage() + "." + classBean.getPlantUMLPackage());
            if (CollectionUtils.isNotEmpty(interfaceBean.getMethodBeanList())) {
                classBean.setMethodBeanList(getMethodBeanList(interfaceBean.getMethodBeanList()));
                classBean.setFieldBeanList(interfaceBean.getFieldBeanList());
            } else {
                classBean.setMethodBeanList(Lists.newArrayList());
            }
            classBean.setImportClassList(interfaceBean.getImportClassList());
            classBean.setRelationClassStr(" implements " + interfaceBean.getClassName());
            infrastAclImplList.add(classBean);
        }
        return infrastAclImplList;
    }

    /**
     * 处理派生类repository->repositoryimpl
     *
     * @param repositoryInterfaceList
     * @return
     */
    public List<ClassBean> getRepositoryImplList(List<InterfaceBean> repositoryInterfaceList) {
        List<ClassBean> repositoryImplList = new ArrayList<>();

        for (InterfaceBean interfaceBean : repositoryInterfaceList) {
            ClassBean classBean = new ClassBean();
            String className = interfaceBean.getClassName() + "Impl";
            classBean.setClassName(className);
            classBean.setClassDesc(classBean.getClassDesc());
            classBean.setPlantUMLPackage("infrast.repositoryimpl");
            if (CollectionUtils.isNotEmpty(interfaceBean.getMethodBeanList())) {
                classBean.setMethodBeanList(getMethodBeanList(interfaceBean.getMethodBeanList()));
                classBean.setFieldBeanList(interfaceBean.getFieldBeanList());
            } else {
                classBean.setMethodBeanList(Lists.newArrayList());
            }
            classBean.setPackageName(appServiceConfig.getPackage() + "." + classBean.getPlantUMLPackage());
            classBean.setImportClassList(interfaceBean.getImportClassList());
            classBean.setRelationClassStr(" implements " + interfaceBean.getClassName());
            repositoryImplList.add(classBean);
        }
        return repositoryImplList;
    }

    /**
     * 构建api的枚举数据
     *
     * @param enumBeanList
     * @return
     */
    public List<EnumBean> getApiEnumBeanList(List<EnumBean> enumBeanList) {
        List<EnumBean> apiEnumBeanList = new ArrayList<>();
        enumBeanList.forEach(enumBean -> apiEnumBeanList.add(enumBean.copySelf()));
        apiEnumBeanList.forEach(enumBean -> enumBean.setPackageName(appServiceConfig.getPackage() + ".api.enums"));
        return apiEnumBeanList;
    }


    /**
     * 处理派生类bo->dto
     *
     * @param domainBOClassList
     * @return
     */
    public List<ClassBean> getQueryDTOClassList(List<ClassBean> domainBOClassList) {
        List<ClassBean> dtoClassList = new ArrayList<>();
        for (ClassBean classBean : domainBOClassList) {
            Optional<FieldBean> optionalFieldBeanQueryDTOKey = classBean.getFieldBeanList().stream().filter(f -> f.isQueryDtoKey()).findFirst();
            if (!optionalFieldBeanQueryDTOKey.isPresent()) {
                continue;
            }
            ClassBean queryDtoBean = optionalFieldBeanQueryDTOKey.get().buildQueryClass();
            queryDtoBean.setAuthor(classBean.getAuthor());
            queryDtoBean.setContext(classBean.getContext());
            queryDtoBean.setClassDesc("查询" + classBean.getClassDesc() + "请求DTO");
            dtoClassList.add(queryDtoBean);
        }
        return dtoClassList;
    }


    /**
     * 处理派生类bo->dto
     *
     * @param domainBOClassList
     * @return
     */
    public List<ClassBean> getQueryVOClassList(List<ClassBean> domainBOClassList) {
        List<ClassBean> dtoClassList = new ArrayList<>();
        for (ClassBean classBean : domainBOClassList) {
            Optional<FieldBean> optionalFieldBeanQueryVOKey = classBean.getFieldBeanList().stream().filter(f -> f.isQueryVoKey()).findFirst();
            if (!optionalFieldBeanQueryVOKey.isPresent()) {
                continue;
            }
            ClassBean queryDtoBean = optionalFieldBeanQueryVOKey.get().buildQueryClass();
            queryDtoBean.setAuthor(classBean.getAuthor());
            queryDtoBean.setClassDesc("查询" + classBean.getClassDesc() + "请求VO");
            queryDtoBean.setContext(classBean.getContext());
            dtoClassList.add(queryDtoBean);
        }
        return dtoClassList;
    }


    /**
     * 处理派生类bo->导出到acl适配防腐下游参数
     *
     * @param domainBOClassList
     * @return
     */
    public List<ClassBean> getExportAclDTOClassList(List<ClassBean> domainBOClassList) {
        Map<String,ClassBean> classBeanMap = new HashMap<>();

        for (ClassBean classBean : domainBOClassList) {
            Optional<MethodBean> optionalMethodBeanQueryDTOKey = classBean.getMethodBeanList().stream().filter(f -> f.isExportAclKey()).findFirst();
            if (!optionalMethodBeanQueryDTOKey.isPresent()) {
                continue;
            }
            //String DepartmentQueryDTO(roleList->list)
            if(!optionalMethodBeanQueryDTOKey.get().getMethodName().contains("(")){
                continue;
            }

            String [] classArr = optionalMethodBeanQueryDTOKey.get().getMethodName().trim().split("\\(");

            //对className打标防止被dto,vo handler扫描到当成bo->dto/vo的派生类处理，
            //打标的类会被aclHandler专门扫描处理
            String className = classArr[0]+TemplateFileEnum.ACL.getTempFileName();
            ClassBean aclDtoclassBean = classBeanMap.get(className);
            if(aclDtoclassBean == null){
                aclDtoclassBean = new ClassBean();
                aclDtoclassBean.setClassName(className);
                aclDtoclassBean.setFieldBeanList(new ArrayList<>());
            }

            String fieldMappingStr = classArr[1].replace(")","");
            String [] fieldMappingArr = fieldMappingStr.split(",");
            for(String fieldMapping : fieldMappingArr){
                String [] fieldArr;
                if(fieldMapping.contains("->")){
                    fieldArr = fieldMapping.split("->");
                }else {
                    fieldArr = fieldMapping.split("-");
                }
                Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean ->  fieldBean.getSimpleName().equals(fieldArr[0])).findFirst();
                if(fieldBeanOptional.isPresent()){
                    FieldBean boFieldBean = fieldBeanOptional.get();
                    String fieldType = boFieldBean.getFieldType();
                    FieldBean fieldBean = new FieldBean();
                    fieldBean.setFieldName(fieldType + " " + fieldArr[1]);
                    fieldBean.setDesc(boFieldBean.getDesc());
                    fieldBean.setVisibility(VisibilityEnum.PRIVATE.getVisibility());
                    fieldBean.buildFieldDetail();
                    aclDtoclassBean.addField(fieldBean);
                }

            }
            classBeanMap.put(className,aclDtoclassBean);
        }

        return Lists.newArrayList(classBeanMap.values());
    }

}
