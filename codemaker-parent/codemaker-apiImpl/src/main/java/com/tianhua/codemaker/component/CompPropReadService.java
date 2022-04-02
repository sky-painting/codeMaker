package com.tianhua.codemaker.component;

import com.google.common.collect.Lists;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import com.tianhua.codemaker.enums.ConfigFileEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Description:
 * date: 2021/11/23
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class CompPropReadService {

    /**
     * 从配置文件中构建组件bean
     * @return
     */
    public ComponentConfigBean buildFromProp(String componentPath){
        try {
            Properties properties = readPropertiesFile(componentPath);
            if(properties == null){
                //System.out.println("加载组件配置失败,componentPath = "+componentPath);
                System.out.println("加载组件配置失败,componentPath = "+componentPath);
                return null;
            }
            ComponentConfigBean componentBean = new ComponentConfigBean();

            Object packageValue = properties.getProperty(GlobalConstant.COMP_PACKAGE);
            if(packageValue == null){
                componentBean.setPackageName("");
            }else {
                componentBean.setPackageName(packageValue.toString());
            }

            for (Map.Entry<Object, Object> entry : properties.entrySet()){
                if(entry.getKey().equals(GlobalConstant.COMP_GROUPID)){
                    componentBean.setGroupId(entry.getValue().toString());
                }
                if(entry.getKey().equals(GlobalConstant.COMP_VERSION)){
                    componentBean.setVersion(entry.getValue().toString());
                }
                if(entry.getKey().equals(GlobalConstant.COMP_ARTIFACTID)){
                    componentBean.setArtifactId(entry.getValue().toString());
                }
                if(entry.getKey().equals(GlobalConstant.COMP_SERVICE_NAME)){
                    componentBean.setServiceName(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_TYPE)){
                    componentBean.setCompType(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_GAV_TYPE)){
                    componentBean.setType(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_GAV_SCOPE)){
                    componentBean.setScope(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_SITE)){
                    componentBean.setCompSite(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_DESC)){
                    componentBean.setCompDesc(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_SINGLE_CLASS_TYPE)){
                    componentBean.setClassType(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_SINGLE_CLASS_TYPE)){
                    componentBean.setClassType(entry.getValue().toString());
                }
                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_CLASS_PRE)){
                    componentBean.addClass(entry.getValue().toString());
                }
                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_INTERFACE_PRE)){
                    componentBean.addInterface(entry.getValue().toString());
                }
                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_ANNOTATION_PRE)){
                    componentBean.addAnnotation(entry.getValue().toString());
                }
                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_ENUM_PRE)){
                    componentBean.addEnum(entry.getValue().toString());
                }

                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_CONFIG_NAME)){
                    componentBean.setConfigName(entry.getValue().toString());
                }

                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_EXCLUSION_GA_LIST)){
                    componentBean.setExclusionGAList(entry.getValue().toString());
                }

                if(entry.getKey().toString().contains(".")){
                    String [] classKeyArr = entry.getKey().toString().split("\\.");
                    if(classKeyArr[1].startsWith(GlobalConstant.COMP_CLASS_METHOD_PRE)){
                        componentBean.addMethod(classKeyArr[0], entry.getValue().toString());
                    }
                    if(classKeyArr[1].startsWith(GlobalConstant.COMP_FIELD_PRE)){
                        componentBean.addField(classKeyArr[0], entry.getValue().toString());
                    }
                }
            }

            //如果组件需要有配置内容配置到目标工程的配置文件中
            if(StringUtils.isNotEmpty(componentBean.getConfigName())){
                int index = componentPath.lastIndexOf("/")+1;
                String configPath = componentPath.substring(0,index)+"config.properties";
                List<String> configList = getConfigList(configPath);
                if(CollectionUtils.isEmpty(configList)){
                    //todo yml配置支持
                    configPath = componentPath.substring(0,index)+"config.yml";
                    configList = getConfigList(configPath);
                    componentBean.setConfigList(configList);
                }else {
                    componentBean.setConfigList(configList);
                }
            }
            return componentBean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取组件的自身配置
     * @param filePath
     * @return
     */
    private List<String> getConfigList(String filePath){
        List<String> list = new ArrayList<>();
        try {

            if(filePath.endsWith(ConfigFileEnum.PROPERTIES.getFileExtName())){
                Properties configProperties = readPropertiesFile(filePath);
                if(configProperties == null){
                    System.out.println("not find component filePath = "+filePath);
                    return list;
                }
                for (Map.Entry<Object, Object> entry : configProperties.entrySet()){
                    list.add(entry.getKey().toString()+"="+entry.getValue().toString());
                }
            }else {
                return readCommonConfigFile(filePath);
            }

        } catch (IOException e) {
            return list;
        }
        return list;

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
            ClassPathResource classPathResource = new ClassPathResource(filePath);

            inputStream = classPathResource.getInputStream();
            Properties props = new Properties();
            props.load(new InputStreamReader(inputStream, "UTF-8"));
            return props;
        } catch (Exception e) {
            //log("读取组件配置失败,filePath={}",filePath);
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }



    /**
     * 读取组件配置文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public  List<String> readCommonConfigFile(String filePath) throws  IOException {
        InputStream inputStream = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            return FileUtils.readLines(classPathResource.getFile(),"UTF-8");
        } catch (Exception e) {
            //log("读取组件配置失败,filePath={}",filePath);
            return Lists.newArrayList();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
