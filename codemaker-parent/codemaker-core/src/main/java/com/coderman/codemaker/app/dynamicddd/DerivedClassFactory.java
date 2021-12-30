package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.enums.TemplateFileEnum;
import org.apache.commons.collections4.CollectionUtils;
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
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2DTO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> dtoList = classConvertFactory.getDTOClassList(domainBoElementBeanList);
        //将派生类放到派生类上下文里面
        dtoList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
    }


    /**
     * 处理enum到enum的派生
     *
     * @param domainEnumElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveEnum2Enum(List<EnumBean> domainEnumElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<EnumBean> enumBeanList = classConvertFactory.getApiEnumBeanList(domainEnumElementBeanList);
        //将派生类放到派生类上下文里面
        enumBeanList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addEnumBean(v));
    }


    /**
     * 处理dto到facade的派生
     *
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDTO2Facade(List<ClassBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<InterfaceBean> dtoList = classConvertFactory.getFacadeInterfaceList(dtoElementBeanList);
        //将派生类放到派生类上下文里面
        dtoList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
    }


    /**
     * 处理dto-boconvert的派生
     *
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDTOBOConvert(List<ClassBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<InterfaceBean> dtoboConvertList = classConvertFactory.getDTOBOConvertInterfaceList(dtoElementBeanList);
        //将派生类放到派生类上下文里面
        dtoboConvertList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
    }

    /**
     * 处理do-boconvert的派生
     *
     * @param boElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveDOBOConvert(List<ClassBean> boElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        Map<String, Object> convertMap = classConvertFactory.getDOBOConvertInterfaceList(boElementBeanList);
        List<InterfaceBean> doboConvertInterfaceList = (List<InterfaceBean>) convertMap.get("doboConvertList");
        Map<String, String> BoConvertRelationMap = (Map<String, String>) convertMap.get("doboConvertRelationMap");

        //将派生类放到派生类上下文里面
        doboConvertInterfaceList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));

        //将dobo转换接口挂到BO类上
        boElementBeanList.forEach(classBean -> {
            ClassBean boClassBean = plantUmlContextBean.getClassBeanMap().get(classBean.getClassName());
            boClassBean.setBodoConvertInterface(BoConvertRelationMap.get(classBean.getClassName()));
        });
    }


    /**
     * 处理bo到dto的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2VO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> dtoList = classConvertFactory.getVOClassList(domainBoElementBeanList);
        //将派生类放到派生类上下文里面
        dtoList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
    }


    /**
     * 处理bo到facade的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2Facade(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<InterfaceBean> facadeList = classConvertFactory.getFacadeClassList(domainBoElementBeanList);
        //将派生类放到派生类上下文里面
        facadeList.forEach(v -> {
            if (v.getClassName().contains(",")) {
                String[] classNameArr = v.getClassName().split(",");
                for (String className : classNameArr) {
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
                    interfaceBean.setContext(v.getContext());
                    interfaceBean.setDerived(v.isDerived());
                    plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(interfaceBean);
                }
            } else {
                plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v);
            }
        });
    }


    /**
     * 处理controller到feign的派生
     *
     * @param controllerElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveController2Feign(List<ClassBean> controllerElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<InterfaceBean> feignInterfaceList = classConvertFactory.getFeignClassList(controllerElementBeanList);
        //将派生类放到派生类上下文里面
        feignInterfaceList.forEach(v -> {
            if (v.getClassName().contains(",")) {
                String[] classNameArr = v.getClassName().split(",");
                for (String className : classNameArr) {
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
            } else {
                plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v);
            }
        });
    }



    /**
     * 处理bo到facade的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2Controller(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> controllerBeanList = classConvertFactory.getControllerClassList(domainBoElementBeanList);
        //将派生类放到派生类上下文里面
        controllerBeanList.forEach(v -> {
            if (v.getClassName().contains(",")) {
                String[] classNameArr = v.getClassName().split(",");
                for (String className : classNameArr) {
                    ClassBean classBean = new ClassBean();
                    classBean.setClassName(className);
                    classBean.setImportClassList(v.getImportClassList());
                    classBean.setPackageName(v.getPackageName());
                    classBean.setMethodBeanList(v.getMethodBeanList());
                    classBean.setPlantUMLPackage(v.getPlantUMLPackage());
                    classBean.setRelationClassStr(v.getRelationClassStr());
                    classBean.setFieldBeanList(v.getFieldBeanList());
                    classBean.setContext(v.getContext());
                    classBean.setImplInterfaceBean(v.getImplInterfaceBean());
                    classBean.setAuthor(v.getAuthor());
                    classBean.setDerived(v.isDerived());
                    plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(classBean);
                }
            } else {
                plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v);
            }
        });

    }


    /**
     * 处理bo-dto到convert的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBoDTO2Convert(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        Map<String, Object> convertMap = classConvertFactory.getConvertInterfaceBeanList(domainBoElementBeanList);

        List<InterfaceBean> convertList = (List<InterfaceBean>) convertMap.get("interfaceList");
        Map<String, String> facadeConvertRelationMap = (Map<String, String>) convertMap.get("facadeconvertrelation");
        //将派生类放到派生类上下文里面
        convertList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));
        //facade接口关联对应的convert接口
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith("facade")) {
                v.setBodtoConvertInterface(facadeConvertRelationMap.get(k));
            }
        });
    }

    /**
     * 处理bo-vo到convert的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBoVO2Convert(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        Map<String, Object> convertMap = classConvertFactory.getConvertBOVOInterfaceBeanList(domainBoElementBeanList);
        List<InterfaceBean> bovoConvertInterfaceList = (List<InterfaceBean>) convertMap.get("voboconvertlist");
        Map<String, String> BoConvertRelationMap = (Map<String, String>) convertMap.get("voboconvertrelation");
        //将派生类放到派生类上下文里面
        bovoConvertInterfaceList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addInterfaceBean(v));

        //将dobo转换接口挂到BO类上,也挂到VO类上便于调用链路绘制
        domainBoElementBeanList.forEach(classBean -> {
            ClassBean boClassBean = plantUmlContextBean.getClassBeanMap().get(classBean.getClassName());
            boClassBean.setBovoConvertInterface(BoConvertRelationMap.get(classBean.getClassName()));
            Optional<FieldBean> optionalFieldBean = classBean.getFieldBeanList().stream().filter(fieldBean -> fieldBean.isVoKey()).findFirst();
            if (optionalFieldBean.isPresent()) {
                FieldBean fieldBean = optionalFieldBean.get();
                String[] classNameArr = fieldBean.getFieldName()
                        .replace("String", "")
                        .replace("string", "")
                        .trim()
                        .split(",");
                for (String voClassname : classNameArr) {
                    String voClassName = voClassname.endsWith("VO") ? voClassname : voClassname + "VO";
                    ClassBean voClassBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(voClassName);
                    if (voClassBean == null) {
                        continue;
                    }
                    voClassBean.setBovoConvertInterface(BoConvertRelationMap.get(classBean.getClassName()));
                }
            }
        });
    }

    /**
     * 处理facade到facadeimpl的派生
     *
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveFacade2FacadeImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> facadeImplList = classConvertFactory.getFacadeImplList(dtoElementBeanList);
        //将派生类放到派生类上下文里面
        facadeImplList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
    }


    /**
     * 处理gataway到gatawayimpl的派生
     *
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public List<ClassBean> deriveGataway2GatawayImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> gatawayImplList = classConvertFactory.getGatawayImplList(dtoElementBeanList);
        if (CollectionUtils.isNotEmpty(gatawayImplList)) {
            gatawayImplList.forEach(classBean -> plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(), classBean));
        }
        return gatawayImplList;
    }


    /**
     * 处理infrast acl到infrastaclimpl的派生
     *
     * @param infrastAclElementBeanList
     * @param plantUmlContextBean
     */
    public List<ClassBean> deriveInfrastAcl2InfrastImpl(List<InterfaceBean> infrastAclElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> gatawayImplList = classConvertFactory.getInfrastAclImplList(infrastAclElementBeanList);

        if (CollectionUtils.isNotEmpty(gatawayImplList)) {
            gatawayImplList.forEach(classBean -> plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(), classBean));
        }
        return gatawayImplList;
    }

    /**
     * 处理Repository到Repositoryimpl的派生
     *
     * @param dtoElementBeanList
     * @param plantUmlContextBean
     */
    public List<ClassBean> deriveRepository2RepositoryImpl(List<InterfaceBean> dtoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> repositoryImplList = classConvertFactory.getRepositoryImplList(dtoElementBeanList);
        if (CollectionUtils.isNotEmpty(repositoryImplList)) {
            repositoryImplList.forEach(classBean -> plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(), classBean));
        }
        return repositoryImplList;
    }


    /**
     * 处理bo到querydto的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2Query(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean, String element) {
        List<ClassBean> queryClassBeanList;
        if (element.equals(TemplateFileEnum.DTO.getTempFileName())) {
            queryClassBeanList = classConvertFactory.getQueryDTOClassList(domainBoElementBeanList);
        } else {
            queryClassBeanList = classConvertFactory.getQueryVOClassList(domainBoElementBeanList);
        }
        //将派生类放到派生类上下文里面
        queryClassBeanList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
    }


    /**
     * 处理bo到acl param的派生
     *
     * @param domainBoElementBeanList
     * @param plantUmlContextBean
     */
    public void deriveBo2AclDTO(List<ClassBean> domainBoElementBeanList, PlantUmlContextBean plantUmlContextBean) {
        List<ClassBean> queryClassBeanList = classConvertFactory.getExportAclDTOClassList(domainBoElementBeanList);
        //将派生类放到派生类上下文里面
        queryClassBeanList.forEach(v -> plantUmlContextBean.getDerivedPlantUmlContextBean().addClassBean(v));
    }

}
