package com.coderman.codemaker.app.dynamicddd.handler;

import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.service.ImportPackageService;
import com.coderman.codemaker.app.dynamicddd.DerivedClassFactory;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.DomainBoElementBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.enums.DomainElementEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "domainBoElementHandler")
public class DomainBoElementHandler implements DomainElementHandler<DomainBoElementBean> {

    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageService importPackageService;

    @Override
    public DomainBoElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DomainBoElementBean domainBoElementBean = new DomainBoElementBean();
        List<ClassBean> domainBoElementBeanList = new ArrayList<>();

        List<ClassBean> boWithTableKeyList = new ArrayList<>();
        List<ClassBean> boWithDtoKeyList = new ArrayList<>();
        List<ClassBean> boWithFacadeKeyList = new ArrayList<>();
        List<ClassBean> boWithVoKeyList = new ArrayList<>();
        List<ClassBean> boWithControllerKeyList = new ArrayList<>();

        //过滤带有数据库表映射的bo
        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            //过滤掉pagebo
            if (v.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !v.getClassName().equals("PageBO")) {
                importPackageService.setPackageName(v, "domain.bo");
                Optional<FieldBean> optionalFieldBean = v.getFieldBeanList().stream().filter(f -> f.isTableKey()).findFirst();
                if (optionalFieldBean.isPresent()) {
                    boWithTableKeyList.add(v);
                    //处理bo关联表的ID
                    FieldBean idFieldBean = new FieldBean("Long id","主键ID","id");
                    List<FieldBean> fieldBeanList = Lists.newArrayList(idFieldBean);
                    fieldBeanList.addAll(v.getFieldBeanList());
                    v.setFieldBeanList(fieldBeanList);
                    setTableBean(v,plantUmlContextBean,optionalFieldBean.get().getFieldName());
                    v.getExtendFieldBean().buildTableKey(optionalFieldBean.get().getFieldName());
                }
                Optional<FieldBean> optionalFieldBeanDtoKey = v.getFieldBeanList().stream().filter(f -> f.isDtoKey()).findFirst();
                if (optionalFieldBeanDtoKey.isPresent()) {
                    boWithDtoKeyList.add(v);
                    v.getExtendFieldBean().buildDtoKeyArr(optionalFieldBeanDtoKey.get().getFieldName());
                }
                Optional<FieldBean> optionalFieldBeanFacadeKey = v.getFieldBeanList().stream().filter(f -> f.isFacadeKey()).findFirst();
                if (optionalFieldBeanFacadeKey.isPresent()) {
                    boWithFacadeKeyList.add(v);
                    v.getExtendFieldBean().buildFacadeKeyArr(optionalFieldBeanFacadeKey.get().getFieldName());
                }

                Optional<FieldBean> optionalFieldBeanVOKey = v.getFieldBeanList().stream().filter(f -> f.isVoKey()).findFirst();
                if (optionalFieldBeanVOKey.isPresent()) {
                    boWithVoKeyList.add(v);
                    v.getExtendFieldBean().buildVoKeyArr(optionalFieldBeanVOKey.get().getFieldName());

                }
                Optional<FieldBean> optionalFieldBeanControllerKey = v.getFieldBeanList().stream().filter(f -> f.isControllerKey()).findFirst();
                if (optionalFieldBeanControllerKey.isPresent()) {
                    boWithControllerKeyList.add(v);
                    v.getExtendFieldBean().buildControllerKeyArr(optionalFieldBeanControllerKey.get().getFieldName());

                }

                Optional<FieldBean> optionalFieldBeanInvokeFileKey= v.getFieldBeanList().stream().filter(f -> f.isInvokeFileKey()).findFirst();
                if (optionalFieldBeanInvokeFileKey.isPresent()) {
                    v.getExtendFieldBean().buildInvokeFileKeyArr(optionalFieldBeanInvokeFileKey.get().getFieldName());

                    String invokeFileName = optionalFieldBeanInvokeFileKey.get().getFieldName().replace("String","").replace("string","").trim();
                    plantUmlContextBean.addDynamicInvokeFile(invokeFileName);
                }
            }
        });
        //bo 派生 do-bo convert
        derivedClassFactory.deriveDOBOConvert(boWithTableKeyList, plantUmlContextBean);
        //默认的派生策略
        if(CollectionUtils.isEmpty(boWithDtoKeyList)){
            //bo 派生 dto
            derivedClassFactory.deriveBo2DTO(domainBoElementBeanList, plantUmlContextBean);
        }else {
            //基于plantuml.bo的扩展信息进行派生
            derivedClassFactory.deriveBo2DTO(boWithDtoKeyList, plantUmlContextBean);
        }

        //处理bo-vo的派生
        if(CollectionUtils.isNotEmpty(boWithVoKeyList)){
            //基于plantuml.bo的扩展信息进行派生
            derivedClassFactory.deriveBo2VO(boWithVoKeyList, plantUmlContextBean);
            //通过vo 派生convert,
            derivedClassFactory.deriveBoVO2Convert(boWithVoKeyList,plantUmlContextBean);
        }

        //处理facade
        if(CollectionUtils.isNotEmpty(boWithFacadeKeyList)){
            derivedClassFactory.deriveBo2Facade(boWithFacadeKeyList,plantUmlContextBean);
            //通过facade 处理convert,
            derivedClassFactory.deriveBoDTO2Convert(boWithFacadeKeyList,plantUmlContextBean);
        }

        //处理controller
        if(CollectionUtils.isNotEmpty(boWithControllerKeyList)){
            derivedClassFactory.deriveBo2Controller(boWithControllerKeyList,plantUmlContextBean);
        }

        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if (v.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !v.getClassName().equals("PageBO")) {
                //过滤扩展属性
                List<FieldBean> beanList = v.getFieldBeanList().stream().filter(f -> !f.isTableKey()
                        && !f.isDtoKey()
                        && !f.isFacadeKey()
                        && !f.isVoKey()
                        && !f.isControllerKey()
                        && !f.isInvokeFileKey()
                ).collect(Collectors.toList());
                v.setFieldBeanList(beanList);


                //过滤扩展方法
                List<MethodBean> methodBeanList = v.getMethodBeanList().stream().filter(methodBean ->
                        !methodBean.getReturnClass().toLowerCase().contains("dto")
                        &&  !methodBean.getMethodName().toLowerCase().contains("dto")
                        &&  !methodBean.getReturnClass().toLowerCase().contains("vo")
                        &&  !methodBean.getMethodName().toLowerCase().contains("vo")
                ).collect(Collectors.toList());
                v.setMethodBeanList(methodBeanList);

                String className = v.getClassName().substring(0, 1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                domainBoElementBeanList.add(v);

            }
        });

        domainBoElementBeanList.stream().forEach(v -> importPackageService.dealImportClass(v, plantUmlContextBean));
        domainBoElementBean.setClassBeanList(domainBoElementBeanList);

        return domainBoElementBean;
    }


    /**
     * 根据table名称标示找到table对应的DO进而找到TableBean
     * @param boClassBean
     * @param plantUmlContextBean
     * @param tableFieldName
     */
    private void setTableBean(ClassBean boClassBean,PlantUmlContextBean plantUmlContextBean,String tableFieldName){
        String tableName = tableFieldName.toLowerCase().trim().replace("string","").replace(" ","");
        for (Map.Entry<String, ClassBean> entry : plantUmlContextBean.getClassBeanMap().entrySet()){
            TableBean tableBean = entry.getValue().getTableBean();
            if(tableBean != null && tableBean.getTableName().equals(tableName)){
                boClassBean.setTableBean(tableBean);
            }
        }

    }

}

