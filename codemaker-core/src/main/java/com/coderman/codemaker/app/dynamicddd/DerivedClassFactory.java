package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.service.ReadInvokePlantUMLFileService;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private ClassConvertFactory classConvertFactory;
    /**
     * 处理bo到dto的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2DTO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> dtoList = classConvertFactory.getDTOClassList(domainBoElementBeanList);
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
     * 处理enum到enum的派生
     * @param domainEnumElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveEnum2Enum(List<EnumBean> domainEnumElementBeanList, PlantUmlContextBean plantUmlContextBean){

        List<EnumBean> enumBeanList = classConvertFactory.getApiEnumBeanList(domainEnumElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            enumBeanList.forEach(v->derivedPlantUmlContextBean.addEnumBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            enumBeanList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addEnumBean(v));
        }
    }



    /**
     * 处理dto到facade的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDTO2Facade(List<ClassBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<InterfaceBean> dtoList = classConvertFactory.getFacadeInterfaceList(dtoElementBeanList);
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
        List<InterfaceBean> dtoboConvertList = classConvertFactory.getDTOBOConvertInterfaceList(dtoElementBeanList);
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
        Map<String,Object> convertMap = classConvertFactory.getDOBOConvertInterfaceList(boElementBeanList);
        List<InterfaceBean> doboConvertInterfaceList = (List<InterfaceBean>)convertMap.get("doboConvertList");
        Map<String,String> BoConvertRelationMap = (Map<String,String>)convertMap.get("doboConvertRelationMap");
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            doboConvertInterfaceList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            doboConvertInterfaceList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }

        //将dobo转换接口挂到BO类上
        boElementBeanList.forEach(classBean -> {
            ClassBean boClassBean = plantUmlContextBean.getClassBeanMap().get(classBean.getClassName());
            boClassBean.setBodoConvertInterface(BoConvertRelationMap.get(classBean.getClassName()));
        });


    }


    /**
     * 处理bo到dto的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2VO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> dtoList = classConvertFactory.getVOClassList(domainBoElementBeanList);
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
        List<InterfaceBean> facadeList = classConvertFactory.getFacadeClassList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            facadeList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            facadeList.forEach(v-> {

                if(v.getClassName().contains(",")){
                    String [] classNameArr = v.getClassName().split(",");
                    for (String className : classNameArr){
                        InterfaceBean interfaceBean = new InterfaceBean();

                        interfaceBean.setClassName(className);
                        interfaceBean.setImportClassList(v.getImportClassList());
                        interfaceBean.setPackageName(v.getPackageName());
                        interfaceBean.setMethodBeanList(v.getMethodBeanList());
                        interfaceBean.setPlantUMLPackage(v.getPlantUMLPackage());
                        interfaceBean.setRelationClassStr(v.getRelationClassStr());
                        interfaceBean.setFieldBeanList(v.getFieldBeanList());
                        interfaceBean.setImplInterfaceBean(v.getImplInterfaceBean());
                        interfaceBean.setAuthor(v.getAuthor());
                        interfaceBean.setDerived(v.isDerived());
                        plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(interfaceBean);
                    }
                }else {
                    plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v);
                }
            });
        }
    }


    /**
     * 处理bo到facade的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2Controller(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> controllerBeanList = classConvertFactory.getControllerClassList(domainBoElementBeanList);
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            controllerBeanList.forEach(v->derivedPlantUmlContextBean.addClassBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {

            //将派生类放到派生类上下文里面
            controllerBeanList.forEach(v-> {

                if(v.getClassName().contains(",")){
                    String [] classNameArr = v.getClassName().split(",");
                    for (String className : classNameArr){
                        ClassBean classBean = new ClassBean();
                        classBean.setClassName(className);
                        classBean.setImportClassList(v.getImportClassList());
                        classBean.setPackageName(v.getPackageName());
                        classBean.setMethodBeanList(v.getMethodBeanList());
                        classBean.setPlantUMLPackage(v.getPlantUMLPackage());
                        classBean.setRelationClassStr(v.getRelationClassStr());
                        classBean.setFieldBeanList(v.getFieldBeanList());
                        classBean.setImplInterfaceBean(v.getImplInterfaceBean());
                        classBean.setAuthor(v.getAuthor());
                        classBean.setDerived(v.isDerived());
                        plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(classBean);
                    }
                }else {
                    plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v);
                }
            });
        }
    }


    /**
     * 处理bo-dto到convert的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBoDTO2Convert(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        Map<String,Object> convertMap = classConvertFactory.getConvertInterfaceBeanList(domainBoElementBeanList);

        List<InterfaceBean> convertList = (List<InterfaceBean>)convertMap.get("interfaceList");
        Map<String,String> facadeConvertRelationMap  = (Map<String,String>)convertMap.get("facadeconvertrelation");

        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            convertList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            convertList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }

        //facade接口关联对应的convert接口
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
            if(k.toLowerCase().endsWith("facade")){
                v.setBodtoConvertInterface(facadeConvertRelationMap.get(k));
            }
        });
    }

    /**
     * 处理bo-dto到convert的派生
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBoVO2Convert(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        Map<String,Object> convertMap = classConvertFactory.getConvertBOVOInterfaceBeanList(domainBoElementBeanList);
        List<InterfaceBean> bovoConvertInterfaceList = (List<InterfaceBean>)convertMap.get("voboconvertlist");
        Map<String,String> BoConvertRelationMap = (Map<String,String>)convertMap.get("voboconvertrelation");


        if(plantUmlContextBean.getDerivedPlantUmlContextBean() == null){
            PlantUmlContextBean derivedPlantUmlContextBean = new PlantUmlContextBean();
            //将派生类放到派生类上下文里面
            bovoConvertInterfaceList.forEach(v->derivedPlantUmlContextBean.addInterfaceBean(v));
            plantUmlContextBean.setDerivedPlantUmlContextBean(derivedPlantUmlContextBean);
        }else {
            //将派生类放到派生类上下文里面
            bovoConvertInterfaceList.forEach(v->plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        }

        //将dobo转换接口挂到BO类上,也挂到VO类上便于调用链路绘制
        domainBoElementBeanList.forEach(classBean -> {
            ClassBean boClassBean = plantUmlContextBean.getClassBeanMap().get(classBean.getClassName());
            boClassBean.setBovoConvertInterface(BoConvertRelationMap.get(classBean.getClassName()));
            Optional<FieldBean> optionalFieldBean = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isVoKey()).findFirst();
            if(optionalFieldBean.isPresent()){
                FieldBean fieldBean = optionalFieldBean.get();
                String [] classNameArr = fieldBean.getFieldName()
                        .replace("String","")
                        .replace("string","")
                        .trim()
                        .split(",");
                for (String voClassname : classNameArr){
                    String voClassName = voClassname.endsWith("VO") ? voClassname : voClassname+"VO";
                    ClassBean voClassBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(voClassName);
                    if(voClassBean == null){
                        continue;
                    }
                    voClassBean.setBovoConvertInterface(BoConvertRelationMap.get(classBean.getClassName()));
                }
            }
        });

    }


    /**
     * 处理facade到facadeimpl的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveFacade2FacadeImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> facadeImplList = classConvertFactory.getFacadeImplList(dtoElementBeanList);
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
     * 处理gataway到gatawayimpl的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public List<ClassBean> deriveGataway2GatawayImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> gatawayImplList = classConvertFactory.getGatawayImplList(dtoElementBeanList);

        if(CollectionUtils.isNotEmpty(gatawayImplList)){
            gatawayImplList.forEach(classBean -> {
                plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(),classBean);
            });
        }
        return gatawayImplList;
    }


    /**
     * 处理infrast acl到infrastaclimpl的派生
     * @param infrastAclElementBeanList
     * @param plantUmlContextBean
     */
    public List<ClassBean> deriveInfrastAcl2InfrastImpl(List<InterfaceBean> infrastAclElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> gatawayImplList = classConvertFactory.getInfrastAclImplList(infrastAclElementBeanList);

        if(CollectionUtils.isNotEmpty(gatawayImplList)){
            gatawayImplList.forEach(classBean -> {
                plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(),classBean);
            });
        }
        return gatawayImplList;
    }

    /**
     * 处理Repository到Repositoryimpl的派生
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public List<ClassBean> deriveRepository2RepositoryImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean){
        List<ClassBean> repositoryImplList = classConvertFactory.getRepositoryImplList(dtoElementBeanList);
        if(CollectionUtils.isNotEmpty(repositoryImplList)){
            repositoryImplList.forEach(classBean -> {
                plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(),classBean);
            });
        }
        return repositoryImplList;
    }

    /**
     * 构建方法内容
     * @param methodBeanList
     * @return
     */
    private List<MethodBean> getMethodBeanList(List<MethodBean> methodBeanList){
        for (MethodBean methodBean : methodBeanList){
            methodBean.buildMethodContent();
        }
        return methodBeanList;
    }

}
