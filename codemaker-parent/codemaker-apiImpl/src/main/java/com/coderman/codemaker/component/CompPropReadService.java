package com.coderman.codemaker.component;

import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.component.ComponentConfigBean;
import lombok.extern.slf4j.Slf4j;
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
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
//@Slf4j
public class CompPropReadService {
    /**
     * 从配置文件中构建组件bean
     * @return
     */
    public ComponentConfigBean buildFromProp(String componentPath){
        try {
            Properties properties = readPropertiesFile(componentPath);
            if(properties == null){
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
                if(entry.getKey().toString().equals(GlobalConstant.COMP_SITE)){
                    componentBean.setCompSite(entry.getValue().toString());
                }
                if(entry.getKey().toString().equals(GlobalConstant.COMP_DESC)){
                    componentBean.setCompDesc(entry.getValue().toString());
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

                if(entry.getKey().toString().startsWith(GlobalConstant.COMP_CLASS_METHOD_PRE)){
                    componentBean.addMethod(entry.getValue().toString());
                }
            }
            return componentBean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
}
