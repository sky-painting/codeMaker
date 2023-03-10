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


        //?????????????????????????????????bo
        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            //?????????pagebo
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
                    //??????bo????????????ID
                    FieldBean idFieldBean = new FieldBean("Long id","??????ID","id");
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
        //bo ?????? do-bo convert
        derivedClassFactory.deriveDOBOConvert(boWithTableKeyList, plantUmlContextBean);
        //?????????????????????
        if(CollectionUtils.isEmpty(boWithDtoKeyList)){
            //bo ?????? dto
            derivedClassFactory.deriveBo2DTO(domainBoElementBeanList, plantUmlContextBean);
        }else {
            //??????plantuml.bo???????????????????????????
            derivedClassFactory.deriveBo2DTO(boWithDtoKeyList, plantUmlContextBean);
        }

        //????????????dto
        if(CollectionUtils.isNotEmpty(boWithQueryDTOKeyList)){
            derivedClassFactory.deriveBo2Query(boWithQueryDTOKeyList, plantUmlContextBean, TemplateFileEnum.DTO.getTempFileName());
        }

        //????????????vo
        if(CollectionUtils.isNotEmpty(boWithQueryVOKeyList)){
            derivedClassFactory.deriveBo2Query(boWithQueryVOKeyList, plantUmlContextBean, TemplateFileEnum.VO.getTempFileName());
        }


        //??????bo-vo?????????
        if(CollectionUtils.isNotEmpty(boWithVoKeyList)){
            //??????plantuml.bo???????????????????????????
            derivedClassFactory.deriveBo2VO(boWithVoKeyList, plantUmlContextBean);
            //??????vo ??????convert,
            derivedClassFactory.deriveBoVO2Convert(boWithVoKeyList,plantUmlContextBean);
        }

        //??????facade
        if(CollectionUtils.isNotEmpty(boWithFacadeKeyList)){
            derivedClassFactory.deriveBo2Facade(boWithFacadeKeyList,plantUmlContextBean);
            //??????facade ??????convert,
            derivedClassFactory.deriveBoDTO2Convert(boWithFacadeKeyList,plantUmlContextBean);
        }

        //??????controller
        if(CollectionUtils.isNotEmpty(boWithControllerKeyList)){
            derivedClassFactory.deriveBo2Controller(boWithControllerKeyList,plantUmlContextBean);
        }

        //?????????????????????????????????????????????
        if(CollectionUtils.isNotEmpty(boWithExportACLKeyList)){
            derivedClassFactory.deriveBo2AclDTO(boWithExportACLKeyList, plantUmlContextBean);
        }


        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if (v.getClassName().toLowerCase().endsWith(DomainElementEnum.BO.getElement()) && !v.getClassName().equals("PageBO")) {
                //??????????????????
                List<FieldBean> beanList = v.getFieldBeanList().stream().filter(f -> f.isSimpleField()).collect(Collectors.toList());
                v.setFieldBeanList(beanList);

                //??????????????????
                List<MethodBean> methodBeanList = v.getMethodBeanList().stream().filter(methodBean ->
                        !methodBean.isModelExtendMethod()
                ).collect(Collectors.toList());
                //???bo???????????????????????????refresh??????
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
     * ??????table??????????????????table?????????DO????????????TableBean
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

