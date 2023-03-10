package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.annotations.ElementTag;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.app.dynamicddd.DerivedClassFactory;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.DomainBoElementBean;
import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.enums.DomainElementEnum;
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
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "domainBoElementHandler")
@ElementTag(elementName = "bo")
public class DomainBoElementHandler implements DomainElementHandler<DomainBoElementBean> {

    @Autowired
    private DerivedClassFactory derivedClassFactory;

    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public DomainBoElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DomainBoElementBean domainBoElementBean = new DomainBoElementBean();
        List<ClassBean> domainBoElementBeanList = new ArrayList<>();

        List<ClassBean> boWithTableKeyList = new ArrayList<>();
        List<ClassBean> boWithDtoKeyList = new ArrayList<>();
        List<ClassBean> boWithFacadeKeyList = new ArrayList<>();
        List<ClassBean> boWithVoKeyList = new ArrayList<>();
        List<ClassBean> boWithControllerKeyList = new ArrayList<>();
        List<ClassBean> boWithQueryDTOKeyList = new ArrayList<>();
        List<ClassBean> boWithQueryVOKeyList = new ArrayList<>();
        List<ClassBean> boWithExportACLKeyList = new ArrayList<>();
        List<ClassBean> boWithValidateKeyList = new ArrayList<>();


        //过滤带有数据库表映射的bo
        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            //过滤掉pagebo
            if (v.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !v.getClassName().equals("PageBO")) {

                Optional<FieldBean> optionalFieldBeanContextKey = v.getFieldBeanList().stream().filter(f -> f.isContextKey()).findFirst();
                if (optionalFieldBeanContextKey.isPresent()) {
                    v.setContext(optionalFieldBeanContextKey.get().getSimpleName());
                }

                Optional<FieldBean> optionalFieldBeanQueryDTOKey = v.getFieldBeanList().stream().filter(f -> f.isQueryDtoKey()).findFirst();
                if (optionalFieldBeanQueryDTOKey.isPresent()) {
                    boWithQueryDTOKeyList.add(v);
                }

                Optional<FieldBean> optionalFieldBeanQueryVOKey = v.getFieldBeanList().stream().filter(f -> f.isQueryVoKey()).findFirst();
                if (optionalFieldBeanQueryVOKey.isPresent()) {
                    boWithQueryVOKeyList.add(v);
                }


                importPackageService.setPackageName(v, "domain.bo");

                Optional<FieldBean> optionalFieldBeanTableKey = v.getFieldBeanList().stream().filter(f -> f.isTableKey()).findFirst();
                if (optionalFieldBeanTableKey.isPresent()) {
                    boWithTableKeyList.add(v);
                    //处理bo关联表的ID
                    FieldBean idFieldBean = new FieldBean("Long id","主键ID","id");
                    List<FieldBean> fieldBeanList = Lists.newArrayList(idFieldBean);
                    fieldBeanList.addAll(v.getFieldBeanList());
                    v.setFieldBeanList(fieldBeanList);
                    setTableBean(v,plantUmlContextBean,optionalFieldBeanTableKey.get().getFieldName());
                    v.getExtendFieldBean().buildTableKey(optionalFieldBeanTableKey.get().getFieldName());
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

                Optional<MethodBean> optionalExportAclKey = v.getMethodBeanList().stream().filter(methodBean -> methodBean.isExportAclKey()).findFirst();
                if (optionalExportAclKey.isPresent()) {
                    boWithExportACLKeyList.add(v);
                }

                Optional<FieldBean> optionalFieldBeanValidateKey = v.getFieldBeanList().stream().filter(f -> f.isValidateKey()).findFirst();
                if (optionalFieldBeanValidateKey.isPresent()) {
                    boWithValidateKeyList.add(v);
                    v.getExtendFieldBean().buildValidteKeyArr(optionalFieldBeanValidateKey.get().getFieldName());
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

        //构建查询dto
        if(CollectionUtils.isNotEmpty(boWithQueryDTOKeyList)){
            derivedClassFactory.deriveBo2Query(boWithQueryDTOKeyList, plantUmlContextBean, TemplateFileEnum.DTO.getTempFileName());
        }

        //构建查询vo
        if(CollectionUtils.isNotEmpty(boWithQueryVOKeyList)){
            derivedClassFactory.deriveBo2Query(boWithQueryVOKeyList, plantUmlContextBean, TemplateFileEnum.VO.getTempFileName());
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

        //构建需要导出的下游依赖接口参数
        if(CollectionUtils.isNotEmpty(boWithExportACLKeyList)){
            derivedClassFactory.deriveBo2AclDTO(boWithExportACLKeyList, plantUmlContextBean);
        }


        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if (v.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !v.getClassName().equals("PageBO")) {
                //过滤扩展属性
                List<FieldBean> beanList = v.getFieldBeanList().stream().filter(f -> f.isSimpleField()).collect(Collectors.toList());
                v.setFieldBeanList(beanList);

                //过滤扩展方法
                List<MethodBean> methodBeanList = v.getMethodBeanList().stream().filter(methodBean ->
                        !methodBean.isModelExtendMethod()
                ).collect(Collectors.toList());
                //对bo本身的方法返回值做refresh操作
                methodBeanList.stream().forEach(methodBean -> methodBean.refreshReturnBodyByReturnType());
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

