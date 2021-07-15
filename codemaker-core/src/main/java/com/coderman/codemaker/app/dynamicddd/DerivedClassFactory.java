package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import com.coderman.codemaker.enums.DomainElementEnum;
import com.coderman.codemaker.service.TemlateVarService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * 派生类工厂服务
 * date: 2021/7/8
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class DerivedClassFactory {

    @Autowired
    private TemlateVarService temlateVarService;

    @Autowired
    private AppServiceConfig appServiceConfig;
    /**
     * 处理bo到dto的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2DTO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> dtoList = getDTOClassList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            dtoList.forEach(v->derivedPlantUmlContextBean.addClassBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            dtoList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
        }
    }

    /**
     * 处理dto到facade的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDTO2Facade(List<ClassBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> dtoList = getFacadeInterfaceList(dtoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            dtoList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            dtoList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }
    }


    /**
     * 处理dto-boconvert的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDTOBOConvert(List<ClassBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> dtoboConvertList = getDTOBOConvertInterfaceList(dtoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            dtoboConvertList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            dtoboConvertList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }
    }
    /**
     * 处理do-boconvert的派生
     * @param boElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDOBOConvert(List<ClassBean> boElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> dtoboConvertList = getDOBOConvertInterfaceList(boElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            dtoboConvertList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            dtoboConvertList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }
    }


    /**
     * 处理bo到dto的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2VO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> dtoList = getVOClassList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            dtoList.forEach(v->derivedPlantUmlContextBean.addClassBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            dtoList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
        }
    }


    /**
     * 处理bo到facade的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2Facade(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> facadeList = getFacadeClassList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            facadeList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            facadeList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }
    }


    /**
     * 处理bo到facade的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2Controller(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> controllerBeanList = getControllerClassList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            controllerBeanList.forEach(v->derivedPlantUmlContextBean.addClassBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            controllerBeanList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
        }
    }


    /**
     * 处理bo-dto到convert的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBoDTO2Convert(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> convertList = getConvertInterfaceBeanList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            convertList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            convertList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }
    }

    /**
     * 处理bo-dto到convert的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBoVO2Convert(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> convertList = getConvertBOVOInterfaceBeanList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            convertList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            convertList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }
    }






    /**
     * 处理facade到facadeimpl的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveFacade2FacadeImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> facadeImplList = getFacadeImplList(dtoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            facadeImplList.forEach(v->derivedPlantUmlContextBean.addClassBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            facadeImplList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
        }
    }


    /**
     * 处理派生类bo->facade
     * @param domainBOClassList
     * @return
     */
    private List<InterfaceBean> getFacadeClassList(List<ClassBean> domainBOClassList){
        Map<String,List<ClassBean>> facadeBOmap = new HashMap<>();
        domainBOClassList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isFacadeKey()).findFirst();
            if(fieldBeanOptional.isPresent()){
                String facadeClassName = fieldBeanOptional.get().getFieldName().replace("String","")
                        .replace("string","").trim();
                List<ClassBean> classBeanList = facadeBOmap.get(facadeClassName);
                if(CollectionUtils.isEmpty(classBeanList)){
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                facadeBOmap.put(facadeClassName,classBeanList);
            }
        });

        List<InterfaceBean> interfaceBeanList = new ArrayList<>();
        facadeBOmap.forEach((k,v)->{
            InterfaceBean facadeInterfaceBean = new InterfaceBean();
            if(k.toLowerCase().endsWith("facade")){
                facadeInterfaceBean.setClassName(k);
            }else {
                facadeInterfaceBean.setClassName(k+"Facade");
            }
            facadeInterfaceBean.setPlantUMLPackage("api.facade");

            List<MethodBean> facadeMethodList = new ArrayList<>();

            v.stream().forEach(classBean -> {
                List<MethodBean> classMethodList = classBean.getMethodBeanList();
                if(CollectionUtils.isNotEmpty(classMethodList)){
                    //通过特定字符过滤facade方法
                    List<MethodBean> newMethodList = classMethodList.stream().filter(methodBean ->
                            methodBean.getReturnClass().toLowerCase().contains("dto")
                                    || methodBean.getReturnClass().toLowerCase().contains("result")
                                    || methodBean.getMethodName().toLowerCase().contains("facade")
                                    || methodBean.getMethodName().toLowerCase().contains("dto")
                    ).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(newMethodList)){
                        facadeMethodList.addAll(newMethodList);
                    }
                }
            });

            facadeInterfaceBean.setMethodBeanList(facadeMethodList);

            interfaceBeanList.add(facadeInterfaceBean);
        });

        return interfaceBeanList;

    }

    /**
     * 处理派生类bo->controller
     * @param domainBOClassList
     * @return
     */
    private List<ClassBean> getControllerClassList(List<ClassBean> domainBOClassList){
        Map<String,List<ClassBean>> controllermap = new HashMap<>();
        domainBOClassList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isControllerKey()).findFirst();
            if(fieldBeanOptional.isPresent()){
                String controllerClassName = fieldBeanOptional.get().getFieldName().replace("String","")
                        .replace("string","").trim();
                List<ClassBean> classBeanList = controllermap.get(controllerClassName);
                if(CollectionUtils.isEmpty(classBeanList)){
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                controllermap.put(controllerClassName,classBeanList);
            }
        });

        List<ClassBean> controllerBeanList = new ArrayList<>();
        controllermap.forEach((k,v)->{
            ClassBean controllerBean = new ClassBean();
            if(k.toLowerCase().endsWith("controller")){
                controllerBean.setClassName(k);
            }else {
                controllerBean.setClassName(k+"Controller");
            }
            controllerBean.setPlantUMLPackage("adapter.controller");

            List<MethodBean> controllerMethodList = new ArrayList<>();

            v.stream().forEach(classBean -> {
                List<MethodBean> classMethodList = classBean.getMethodBeanList();
                if(CollectionUtils.isNotEmpty(classMethodList)){
                    //通过特定字符过滤facade方法
                    List<MethodBean> newMethodList = classMethodList.stream().filter(methodBean -> methodBean.getDesc().contains("/")
                    ).collect(Collectors.toList());


                    if (CollectionUtils.isNotEmpty(newMethodList)){
                        newMethodList.forEach(methodBean -> {
                            String pathValue = methodBean.getDesc().substring(methodBean.getDesc().indexOf("/"));
                            methodBean.setPathValue(pathValue);
                            methodBean.setDesc(methodBean.getDesc().replace(methodBean.getPathValue(),""));
                        });

                        controllerMethodList.addAll(newMethodList);
                    }
                }
            });

            controllerBean.setMethodBeanList(controllerMethodList);

            controllerBeanList.add(controllerBean);
        });

        return controllerBeanList;

    }



    /**
     * 处理派生类bo->dto
     * @param domainBOClassList
     * @return
     */
    private List<ClassBean> getDTOClassList(List<ClassBean> domainBOClassList){
        List<ClassBean> dtoClassList = new ArrayList<>();

        for (ClassBean classBean : domainBOClassList){
            //dto没有bo那么多的丰富信息，需要去掉
            List<FieldBean> fieldBeanList = classBean.getFieldBeanList().stream().filter(fieldBean -> !fieldBean
                    .getFieldName().trim().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !fieldBean.isTableKey() && !fieldBean.isDtoKey()).collect(Collectors.toList());
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(f -> f.isDtoKey()).findFirst();
            if(fieldBeanOptional.isPresent()){
                String [] classNameArr = fieldBeanOptional.get().getFieldName()
                        .replace("String","")
                        .replace("string","")
                        .trim()
                        .split(",");
                for (String className : classNameArr){
                    ClassBean dtoClassBean = new ClassBean();
                    if(!className.toLowerCase().endsWith("dto")){
                        dtoClassBean.setClassName(className+"DTO");
                    }else {
                        dtoClassBean.setClassName(className);
                    }
                    dtoClassBean.setFieldBeanList(fieldBeanList);
                    dtoClassBean.setClassDesc(classBean.getClassDesc());
                    dtoClassBean.setMethodBeanList(Lists.newArrayList());
                    dtoClassBean.setPlantUMLPackage("api.dto");
                    dtoClassList.add(dtoClassBean);
                }

            }else {
                ClassBean dtoClassBean = new ClassBean();
                int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainElementEnum.BO.getElement());
                if(x >= 0){
                    String className = classBean.getClassName().substring(0,x)+"DTO";
                    dtoClassBean.setClassName(className);
                }
                dtoClassBean.setFieldBeanList(fieldBeanList);
                dtoClassBean.setMethodBeanList(Lists.newArrayList());
                dtoClassBean.setPlantUMLPackage("api.dto");
                dtoClassList.add(dtoClassBean);
            }
        }
        return dtoClassList;
    }


    /**
     * 处理派生类bo->dto
     * @param domainBOClassList
     * @return
     */
    private List<ClassBean> getVOClassList(List<ClassBean> domainBOClassList){
        List<ClassBean> dtoClassList = new ArrayList<>();

        for (ClassBean classBean : domainBOClassList){
            //vo没有bo那么多的丰富信息，需要去掉
            List<FieldBean> fieldBeanList = classBean.getFieldBeanList().stream().filter(fieldBean -> !fieldBean
                    .getFieldName().trim().toLowerCase().endsWith(DomainElementEnum.BO.getElement())
                    && !fieldBean.isTableKey()
                    && !fieldBean.isDtoKey()
                    && !fieldBean.isFacadeKey()
                    && !fieldBean.isVoKey()
            ).collect(Collectors.toList());
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(f -> f.isVoKey()).findFirst();
            if(fieldBeanOptional.isPresent()){
                String [] classNameArr = fieldBeanOptional.get().getFieldName()
                        .replace("String","")
                        .replace("string","")
                        .trim()
                        .split(",");
                for (String className : classNameArr){
                    ClassBean dtoClassBean = new ClassBean();
                    if(!className.toLowerCase().endsWith("vo")){
                        dtoClassBean.setClassName(className+"VO");
                    }else {
                        dtoClassBean.setClassName(className);
                    }
                    dtoClassBean.setFieldBeanList(fieldBeanList);
                    dtoClassBean.setClassDesc(classBean.getClassDesc());
                    dtoClassBean.setMethodBeanList(Lists.newArrayList());
                    dtoClassBean.setPlantUMLPackage("adapter.vo");
                    dtoClassList.add(dtoClassBean);
                }

            }else {
                ClassBean dtoClassBean = new ClassBean();
                int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainElementEnum.BO.getElement());
                if(x >= 0){
                    String className = classBean.getClassName().substring(0,x)+"VO";
                    dtoClassBean.setClassName(className);
                }
                dtoClassBean.setFieldBeanList(fieldBeanList);
                dtoClassBean.setMethodBeanList(Lists.newArrayList());
                dtoClassBean.setPlantUMLPackage("adapter.vo");
                dtoClassList.add(dtoClassBean);
            }
        }
        return dtoClassList;
    }


    /**
     * 处理派生类dto->facade
     * @param dtoClassList
     * @return
     */
    private List<InterfaceBean> getFacadeInterfaceList(List<ClassBean> dtoClassList){
        List<InterfaceBean> facadeInterfaceList = new ArrayList<>();

        for (ClassBean classBean : dtoClassList){
            InterfaceBean interfaceBean = new InterfaceBean();
            int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainDerivedElementEnum.DTO.getElement());
            if(x >= 0){
                String className = classBean.getClassName().substring(0,x)+"Facade";
                interfaceBean.setClassName(className);
                interfaceBean.setPlantUMLPackage("api.facade");
                interfaceBean.setClassDesc(classBean.getClassDesc());
                interfaceBean.setMethodBeanList(Lists.newArrayList());
                facadeInterfaceList.add(interfaceBean);
            }
        }
        return facadeInterfaceList;
    }


    /**
     * 处理派生类dto-bo convert
     * @param dtoClassList
     * @return
     */
    private List<InterfaceBean> getDTOBOConvertInterfaceList(List<ClassBean> dtoClassList){
        List<InterfaceBean> facadeInterfaceList = new ArrayList<>();
        for (ClassBean classBean : dtoClassList){
            InterfaceBean interfaceBean = new InterfaceBean();
            int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainDerivedElementEnum.DTO.getElement());
            if(x >= 0){
                String className = classBean.getClassName().substring(0,x)+"Convert";
                interfaceBean.setClassName(className);
                interfaceBean.setPlantUMLPackage("model.convert");
                interfaceBean.setClassDesc(classBean.getClassDesc());
                interfaceBean.setMethodBeanList(getDTOBOConvertMethodList(classBean));
                List<String> importList = new ArrayList<>();
                importList.add(classBean.getPackageName()+"."+classBean.getClassName());
                interfaceBean.setImportClassList(importList);
                facadeInterfaceList.add(interfaceBean);
            }
        }
        return facadeInterfaceList;
    }


    /**
     * 处理派生类dto-bo convert
     * @param dtoClassList
     * @return
     */
    private List<InterfaceBean> getDOBOConvertInterfaceList(List<ClassBean> dtoClassList){

        List<InterfaceBean> facadeInterfaceList = new ArrayList<>();
        for (ClassBean classBean : dtoClassList){
            InterfaceBean interfaceBean = new InterfaceBean();
            int x = classBean.getClassName().toLowerCase().lastIndexOf(DomainElementEnum.BO.getElement());
            if(x >= 0){
                String className = classBean.getClassName().substring(0,x)+"Converter";
                interfaceBean.setClassName(className);
                interfaceBean.setPlantUMLPackage("data.convert");
                interfaceBean.setClassDesc(classBean.getClassDesc());
                interfaceBean.setMethodBeanList(getDOBOConvertMethodList(classBean));
                interfaceBean.setImportClassList(Lists.newArrayList());
                facadeInterfaceList.add(interfaceBean);
            }
        }
        return facadeInterfaceList;
    }

    /**
     * 处理派生类dto-bo convert
     * @param classBean
     * @return
     */
    private List<MethodBean> getDOBOConvertMethodList(ClassBean classBean){
        Optional<FieldBean> fieldBean = classBean.getFieldBeanList().stream().filter(v->v.isTableKey()).findFirst();
        if(!fieldBean.isPresent()){
            return Lists.newArrayList();
        }

        String tableName = fieldBean.get().getFieldName().replace("String","").replace("string","").trim();
        if(StringUtils.isEmpty(tableName)){
            return Lists.newArrayList();
        }
        String classDOName = temlateVarService.getClassDOName(tableName)+"DO";


        List<MethodBean> methodBeanList = new ArrayList<>();
        MethodBean do2bo = new MethodBean();
        String boClassName = classBean.getClassName();
        String varClassName = classDOName.substring(0,1).toLowerCase().concat(classDOName.substring(1));
        do2bo.setMethodName("do2bo("+classDOName+" "+varClassName+")");
        do2bo.setReturnClass(boClassName);
        methodBeanList.add(do2bo);

        MethodBean dtoList2boList = new MethodBean();
        dtoList2boList.setMethodName("doList2boList(List<"+classDOName+"> "+varClassName+"List)");
        dtoList2boList.setReturnClass("List<"+boClassName+">");
        methodBeanList.add(dtoList2boList);



        MethodBean bo2to = new MethodBean();
        varClassName = boClassName.substring(0,1).toLowerCase().concat(classBean.getClassName().substring(1));
        bo2to.setMethodName("bo2do("+boClassName+" "+varClassName+")");
        bo2to.setReturnClass(classDOName);
        methodBeanList.add(bo2to);

        MethodBean boList2dtoList = new MethodBean();
        boList2dtoList.setMethodName("boList2doList(List<"+boClassName+"> "+varClassName+"List)");
        boList2dtoList.setReturnClass("List<"+classDOName+">");
        methodBeanList.add(boList2dtoList);

        return methodBeanList;
    }



    /**
     * 处理派生类dto-bo convert
     * @param classBean
     * @return
     */
    private List<MethodBean> getDTOBOConvertMethodList(ClassBean classBean){
        List<MethodBean> methodBeanList = new ArrayList<>();
        MethodBean dto2bo = new MethodBean();
        String boClassName = classBean.getClassName().replace("DTO","BO");
        String varClassName = classBean.getClassName().substring(0,1).toLowerCase().concat(classBean.getClassName().substring(1));
        dto2bo.setMethodName("dto2bo("+classBean.getClassName()+" "+varClassName+")");
        dto2bo.setReturnClass(boClassName);
        methodBeanList.add(dto2bo);

        MethodBean dtoList2boList = new MethodBean();
        dtoList2boList.setMethodName("dtoList2boList(List<"+classBean.getClassName()+"> "+varClassName+"List)");
        dtoList2boList.setReturnClass("List<"+boClassName+">");
        methodBeanList.add(dtoList2boList);



        MethodBean bo2to = new MethodBean();
        varClassName = boClassName.substring(0,1).toLowerCase().concat(classBean.getClassName().substring(1));
        bo2to.setMethodName("bo2dto("+boClassName+" "+varClassName+")");
        bo2to.setReturnClass(classBean.getClassName().replace("DTO","BO"));
        methodBeanList.add(bo2to);

        MethodBean boList2dtoList = new MethodBean();
        boList2dtoList.setMethodName("boList2dtoList(List<"+boClassName+"> "+varClassName+"List)");
        boList2dtoList.setReturnClass("List<"+classBean.getClassName()+">");
        methodBeanList.add(boList2dtoList);

        return methodBeanList;
    }





    /**
     * 处理派生类facade->facadeimpl
     * @param facadeInterfaceList
     * @return
     */
    private List<ClassBean> getFacadeImplList(List<InterfaceBean> facadeInterfaceList){
        List<ClassBean> facadeImplList = new ArrayList<>();

        for (InterfaceBean interfaceBean : facadeInterfaceList){
            ClassBean classBean = new ClassBean();
            String className = interfaceBean.getClassName()+"Impl";
            classBean.setClassName(className);
            classBean.setClassDesc(classBean.getClassDesc());
            classBean.setPlantUMLPackage("app.facadeimpl");
            if(CollectionUtils.isNotEmpty(interfaceBean.getMethodBeanList())){
                classBean.setMethodBeanList(interfaceBean.getMethodBeanList());
            }else {
                classBean.setMethodBeanList(Lists.newArrayList());
            }
            classBean.setImportClassList(interfaceBean.getImportClassList());
            classBean.setRelationClassStr(" implements "+interfaceBean.getClassName());
            facadeImplList.add(classBean);
        }
        return facadeImplList;
    }

    /**
     * 对bo类进行解析
     * @param domainBOList
     * @return
     */
    private List<InterfaceBean> getConvertInterfaceBeanList(List<ClassBean> domainBOList){

        Map<String,List<ClassBean>> facadeBOmap = new HashMap<>();
        domainBOList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isFacadeKey()).findFirst();
            if(fieldBeanOptional.isPresent()){
                String facadeClassName = fieldBeanOptional.get().getFieldName().replace("String","")
                        .replace("string","").trim();
                List<ClassBean> classBeanList = facadeBOmap.get(facadeClassName);
                if(CollectionUtils.isEmpty(classBeanList)){
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                facadeBOmap.put(facadeClassName,classBeanList);
            }
        });

        List<InterfaceBean> interfaceConvertBeanList = new ArrayList<>();
        facadeBOmap.forEach((k,v)->{
            InterfaceBean convertInterfaceBean = new InterfaceBean();
            String convertClassName = k.replace("Facade","").replace("facade","")+"Convert";
            convertInterfaceBean.setClassName(convertClassName);
            convertInterfaceBean.setPlantUMLPackage("model.convert");
            convertInterfaceBean.setClassDesc(convertClassName);
            Set<String> importClassSet = new HashSet<>();
            List<MethodBean> classMethodList = new ArrayList<>();


            v.stream().forEach(classBean -> {

                List<MethodBean> methodBeanList = new ArrayList<>();
                Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(f -> f.isDtoKey()).findFirst();
                if(fieldBeanOptional.isPresent()){
                    String dtoClassNameStr = fieldBeanOptional.get().getFieldName()
                            .replace("String","")
                            .replace("string","")
                            .trim();


                    String [] arr = dtoClassNameStr.split(",");
                    for (String dtoClassName : arr){
                        importClassSet.add(appServiceConfig.getPackage()+".api.dto."+dtoClassName);
                        MethodBean dto2bo = new MethodBean();
                        String vardtoName = dtoClassName.substring(0,1).toLowerCase().concat(dtoClassName.substring(1));
                        dto2bo.setMethodName("dto2bo("+dtoClassName+" "+vardtoName+")");
                        dto2bo.setReturnClass(classBean.getClassName());
                        methodBeanList.add(dto2bo);

                        MethodBean dtoList2boList = new MethodBean();
                        dtoList2boList.setMethodName(vardtoName+"s2boList(List<"+dtoClassName+"> "+vardtoName+"List)");
                        dtoList2boList.setReturnClass("List<"+classBean.getClassName()+">");
                        methodBeanList.add(dtoList2boList);

                        MethodBean bo2to = new MethodBean();
                        String varBOClassName = classBean.getClassName().substring(0,1).toLowerCase().concat(classBean.getClassName().substring(1));
                        bo2to.setMethodName("bo2dto("+classBean.getClassName()+" "+varBOClassName+")");
                        bo2to.setReturnClass(dtoClassName);
                        methodBeanList.add(bo2to);

                        MethodBean boList2dtoList = new MethodBean();
                        boList2dtoList.setMethodName(varBOClassName+"s2dtoList(List<"+classBean.getClassName()+"> "+varBOClassName+"List)");
                        boList2dtoList.setReturnClass("List<"+dtoClassName+">");
                        methodBeanList.add(boList2dtoList);
                    }
                    classMethodList.addAll(methodBeanList);
                }
            });

            convertInterfaceBean.setImportClassList(Lists.newArrayList(importClassSet));

            Map<String,MethodBean> map = new HashMap<>();
            classMethodList
                    .stream().filter(methodBean ->
                        !methodBean.getMethodName().toLowerCase().contains("response") &&
                        !methodBean.getMethodName().toLowerCase().contains("responsedto") &&
                        !methodBean.getReturnClass().toLowerCase().contains("request") &&
                        !methodBean.getReturnClass().toLowerCase().contains("requestdto") ||
                        methodBean.getReturnClass().toLowerCase().contains("requestbo")
            ).collect(Collectors.toList()).forEach(methodBean -> map.putIfAbsent(methodBean.getReturnClass()+methodBean.getMethodName(),methodBean));
            convertInterfaceBean.setMethodBeanList(map.values().stream().collect(Collectors.toList()));
            interfaceConvertBeanList.add(convertInterfaceBean);
        });

        return interfaceConvertBeanList;

    }


    /**
     * 对bo类进行解析
     * @param domainBOList
     * @return
     */
    private List<InterfaceBean> getConvertBOVOInterfaceBeanList(List<ClassBean> domainBOList){

        Map<String,List<ClassBean>> contrllerBOmap = new HashMap<>();
        domainBOList.forEach(classBean -> {
            Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isControllerKey()).findFirst();
            if(fieldBeanOptional.isPresent()){
                String controllerClassName = fieldBeanOptional.get().getFieldName().replace("String","")
                        .replace("string","").trim();
                List<ClassBean> classBeanList = contrllerBOmap.get(controllerClassName);
                if(CollectionUtils.isEmpty(classBeanList)){
                    classBeanList = Lists.newArrayList();
                }
                classBeanList.add(classBean);
                contrllerBOmap.put(controllerClassName,classBeanList);
            }
        });

        List<InterfaceBean> interfaceConvertBeanList = new ArrayList<>();
        contrllerBOmap.forEach((k,v)->{
            InterfaceBean convertInterfaceBean = new InterfaceBean();
            String convertClassName = k.replace("Controller","").replace("controller","")+"Convertervobo";
            convertInterfaceBean.setClassName(convertClassName);
            convertInterfaceBean.setPlantUMLPackage("model.convert");
            convertInterfaceBean.setClassDesc(convertClassName);
            Set<String> importClassSet = new HashSet<>();
            List<MethodBean> classMethodList = new ArrayList<>();


            v.stream().forEach(classBean -> {

                List<MethodBean> methodBeanList = new ArrayList<>();
                Optional<FieldBean> fieldBeanOptional = classBean.getFieldBeanList().stream().filter(f -> f.isVoKey()).findFirst();
                if(fieldBeanOptional.isPresent()){
                    String voClassNameStr = fieldBeanOptional.get().getFieldName()
                            .replace("String","")
                            .replace("string","")
                            .trim();


                    String [] arr = voClassNameStr.split(",");
                    for (String voClassName : arr){
                        String varBOClassName = classBean.getClassName().substring(0,1).toLowerCase().concat(classBean.getClassName().substring(1));

                        importClassSet.add(appServiceConfig.getPackage()+".adapter.vo."+voClassName);
                        MethodBean dto2bo = new MethodBean();
                        String varVoName = voClassName.substring(0,1).toLowerCase().concat(voClassName.substring(1));
                        dto2bo.setMethodName("vo2bo("+voClassName+" "+varVoName+")");
                        dto2bo.setReturnClass(classBean.getClassName());
                        methodBeanList.add(dto2bo);

                        MethodBean dtoList2boList = new MethodBean();
                        String tmpVar = varVoName.replace(varBOClassName.replace("BO","").replace("Bo",""),"");
                        varVoName = tmpVar.substring(0,1).toLowerCase().concat(tmpVar.substring(1));
                        if(StringUtils.isEmpty(varVoName)){
                            varVoName = "vo";
                        }

                        dtoList2boList.setMethodName(varVoName+"List2boList(List<"+voClassName+"> "+varVoName+"List)");
                        dtoList2boList.setReturnClass("List<"+classBean.getClassName()+">");
                        methodBeanList.add(dtoList2boList);

                        MethodBean bo2to = new MethodBean();
                        tmpVar = voClassName.replace(classBean.getClassName().replace("BO","").replace("Bo",""),"");

                        bo2to.setMethodName("bo2"+tmpVar+"("+classBean.getClassName()+" "+varBOClassName+")");
                        bo2to.setReturnClass(voClassName);
                        methodBeanList.add(bo2to);

                        MethodBean boList2dtoList = new MethodBean();
                        boList2dtoList.setMethodName(varBOClassName+"List2"+tmpVar+"List(List<"+classBean.getClassName()+"> "+varBOClassName+"List)");
                        boList2dtoList.setReturnClass("List<"+voClassName+">");
                        methodBeanList.add(boList2dtoList);
                    }
                    classMethodList.addAll(methodBeanList);
                }
            });

            convertInterfaceBean.setImportClassList(Lists.newArrayList(importClassSet));

            Map<String,MethodBean> map = new HashMap<>();
            classMethodList
                    .stream().filter(methodBean ->
                    !methodBean.getMethodName().toLowerCase().contains("response") &&
                            !methodBean.getMethodName().toLowerCase().contains("responsevo") &&
                            !methodBean.getReturnClass().toLowerCase().contains("request") &&
                            !methodBean.getReturnClass().toLowerCase().contains("requestvo") ||
                            methodBean.getReturnClass().toLowerCase().contains("requestbo")
            ).collect(Collectors.toList()).forEach(methodBean -> map.putIfAbsent(methodBean.getReturnClass()+methodBean.getMethodName(),methodBean));
            convertInterfaceBean.setMethodBeanList(map.values().stream().collect(Collectors.toList()));
            interfaceConvertBeanList.add(convertInterfaceBean);
        });

        return interfaceConvertBeanList;

    }
}
