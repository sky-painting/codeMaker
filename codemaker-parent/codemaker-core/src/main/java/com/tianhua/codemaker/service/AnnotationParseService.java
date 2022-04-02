package com.tianhua.codemaker.service;

import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.config.AnnotationTagBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Description:类和方法上的注解标签解析
 * date: 2022/1/24
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class AnnotationParseService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AppServiceConfig appServiceConfig;
    /**
     * 统一处理注解标签内容
     * @param plantUmlContextBean
     */
    public void dealPlantUmlContextAnnotation(PlantUmlContextBean plantUmlContextBean){
        Map<String, AnnotationTagBean> annotationTagBeanMap = buildAnnotationTagBeanMap();
        plantUmlContextBean.getClassBeanMap().forEach((k,classBean)->{
            //处理类上的注解信息
            if(CollectionUtils.isNotEmpty(classBean.getAnnotationTagList())){
                List<String> annotationContentList = new ArrayList<>();

                for (String annotationTag : classBean.getAnnotationTagList()){
                    AnnotationTagBean annotationTagBean = annotationTagBeanMap.get(annotationTag);
                    if(annotationTagBean != null){
                        String newPackage = annotationTagBean.getPackageName().replace("${package}",appServiceConfig.getPackage());
                        classBean.addImportClass(newPackage + "."+annotationTagBean.getClassName());
                        classBean.addDynamicImportClass(newPackage + "."+annotationTagBean.getClassName());
                        annotationContentList.add(annotationTagBean.getAnnotation());
                    }
                }
                classBean.setAnnotationTagList(annotationContentList);
            }

            //处理方法上的注解信息
            if(CollectionUtils.isNotEmpty(classBean.getMethodBeanList())){
                for (MethodBean methodBean : classBean.getMethodBeanList()){
                    List<String> annotationContentList = new ArrayList<>();
                    if(CollectionUtils.isNotEmpty(methodBean.getAnnotationTagList())){
                        for (String annotationTag : methodBean.getAnnotationTagList()){
                            AnnotationTagBean annotationTagBean = annotationTagBeanMap.get(annotationTag);
                            if(annotationTagBean != null){
                                String newPackage = annotationTagBean.getPackageName().replace("${package}",appServiceConfig.getPackage());
                                classBean.addImportClass(newPackage+"."+annotationTagBean.getClassName());
                                classBean.addDynamicImportClass(newPackage + "."+annotationTagBean.getClassName());

                                annotationContentList.add(annotationTagBean.getAnnotation());
                            }
                        }
                        methodBean.setAnnotationTagList(annotationContentList);
                    }
                }
            }
        });

        plantUmlContextBean.getInterfaceBeanMap().forEach((k,interfaceBean)->{
            //处理类上的注解信息
            if(CollectionUtils.isNotEmpty(interfaceBean.getAnnotationTagList())){
                List<String> annotationContentList = new ArrayList<>();
                for (String annotationTag : interfaceBean.getAnnotationTagList()){
                    AnnotationTagBean annotationTagBean = annotationTagBeanMap.get(annotationTag);
                    if(annotationTagBean != null){
                        String newPackage = annotationTagBean.getPackageName().replace("${package}",appServiceConfig.getPackage());
                        if(annotationTagBean.modifyOnThis()){
                            interfaceBean.addImportClass(newPackage+"."+annotationTagBean.getClassName());
                        }else {
                            interfaceBean.addChildImportClass(newPackage+"."+annotationTagBean.getClassName());
                        }
                        annotationContentList.add(annotationTagBean.getAnnotation());
                    }
                }
                interfaceBean.setAnnotationTagList(annotationContentList);
            }

            //处理方法上的注解信息
            if(CollectionUtils.isNotEmpty(interfaceBean.getMethodBeanList())){
                for (MethodBean methodBean : interfaceBean.getMethodBeanList()){
                    List<String> annotationContentList = new ArrayList<>();
                    if(CollectionUtils.isNotEmpty(methodBean.getAnnotationTagList())){
                        for (String annotationTag : methodBean.getAnnotationTagList()){
                            AnnotationTagBean annotationTagBean = annotationTagBeanMap.get(annotationTag);
                            if(annotationTagBean != null){
                                String newPackage = annotationTagBean.getPackageName().replace("${package}",appServiceConfig.getPackage());
                                if(annotationTagBean.modifyOnThis()){
                                    interfaceBean.addImportClass(newPackage+"."+annotationTagBean.getClassName());
                                }else {
                                    interfaceBean.addChildImportClass(newPackage+"."+annotationTagBean.getClassName());
                                }
                                annotationContentList.add(annotationTagBean.getAnnotation());
                            }
                        }
                        methodBean.setAnnotationTagList(annotationContentList);
                    }
                }
            }
        });

    }

    /**
     * 构建注解标示对象配置
     * @return
     */
    public Map<String, AnnotationTagBean> buildAnnotationTagBeanMap(){
        Map<String, AnnotationTagBean> annotationTagBeanMap = new HashMap<>();
        try {
            String path = GlobalConstant.COMP_COMPONENT_ANNOTATION_TAG_PATH;
            Resource resource = resourceLoader.getResource("classpath:"+path);
            File file = resource.getFile();
            for (File annoTagFile : file.listFiles()){
                Properties properties = readPropertiesFile(annoTagFile.getAbsolutePath());
                AnnotationTagBean annotationTagBean = new AnnotationTagBean();
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    if (entry.getKey().equals(GlobalConstant.COMP_PACKAGE)) {
                        annotationTagBean.setPackageName(entry.getValue().toString());
                    }
                    if (entry.getKey().equals(GlobalConstant.COMP_ANNOTATION_ALIAS)) {
                        annotationTagBean.setClassAlias(entry.getValue().toString());
                    }
                    if (entry.getKey().equals(GlobalConstant.COMP_ANNOTATION_PRE)) {
                        annotationTagBean.setAnnotation(entry.getValue().toString());
                    }
                    if (entry.getKey().equals(GlobalConstant.COMP_MODIFY_ON)) {
                        annotationTagBean.setModifyOn(entry.getValue().toString());
                    }
                }
                annotationTagBean.setClassName(annoTagFile.getName().replace(".properties",""));
                annotationTagBeanMap.put(annotationTagBean.getClassAlias(),annotationTagBean);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return annotationTagBeanMap;
    }


    /**
     * 读取组件配置文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public  Properties readPropertiesFile(String filePath) throws  IOException {
        InputStream inputStream = null;
        try {

            Properties props = new Properties();
            // 使用InPutStream流读取properties文件
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            props.load(bufferedReader);
            return props;
        } catch (Exception e) {
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
