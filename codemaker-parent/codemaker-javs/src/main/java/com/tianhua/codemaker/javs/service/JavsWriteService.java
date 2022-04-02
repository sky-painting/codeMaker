package com.tianhua.codemaker.javs.service;

import com.alibaba.fastjson.JSON;
import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.FieldBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.javs.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2022/3/11
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class JavsWriteService {
    private Logger logger = LoggerFactory.getLogger(JavsWriteService.class);
    /**
     * 写javs文件
     * @param javsScriptProjectPath
     * @param abstractClassBean
     */
    public void writeJavs(String javsScriptProjectPath, AbstractClassBean abstractClassBean){
        String filePath = getFilePath(javsScriptProjectPath, abstractClassBean);
        //对没有方法的Java类进行过滤
        if(CollectionUtils.isEmpty(abstractClassBean.getMethodBeanList())){
            return;
        }
        if(StringUtils.isEmpty(filePath)){
            logger.warn("java class "+abstractClassBean.getClassName()+" has no className.no javs file");
            return;
        }
        try {
            FileUtils.writeLines(new File(filePath), generateJavsFileContent(abstractClassBean));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取javs文件路径
     * @param javsScriptProjectPath
     * @param abstractClassBean
     * @return
     */
    public String getFilePath(String javsScriptProjectPath, AbstractClassBean abstractClassBean) {
        String packageName = abstractClassBean.getPackageName();
        if(StringUtils.isEmpty(packageName)){
            System.out.println("abstractClassBean = "+ JSON.toJSONString(abstractClassBean));
            return null;
        }
        String packagePath = packageName.replace(".", "/") ;
        packagePath = Constants.JAVS + "/" + packagePath + "/";
        String fileName = abstractClassBean.getClassName() + "."+Constants.JAVS;
        return javsScriptProjectPath + "/" + packagePath + fileName;
    }


    /**
     * 生成javs文件内容
     * @param abstractClassBean
     * @return
     */
    private List<String> generateJavsFileContent(AbstractClassBean abstractClassBean){
        List<String> list = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(abstractClassBean.getFieldBeanList())){
            for (FieldBean fieldBean : abstractClassBean.getFieldBeanList()){
                list.add(fieldBean.getFieldName());
            }
        }
        list.add("\n");
        if(CollectionUtils.isNotEmpty(abstractClassBean.getMethodBeanList())){
            for (MethodBean methodBean : abstractClassBean.getMethodBeanList()){
                list.add("func "+getNewMethod(methodBean)+"{");
                list.add("}");
                list.add("\n");
            }
        }
        return list;
    }

    /**
     * 简化函数声明
     * @param methodBean
     * @return
     */
    private String getNewMethod(MethodBean methodBean){
        if(methodBean.getParamArr() == null || methodBean.getParamArr().length ==0){
            return methodBean.getSimplMethodName()+"()";
        }

        List<String> paramNameList = new ArrayList<>();
        for (String param : methodBean.getParamArr()){
            if(param.contains(" ")){
                String paramName = param.split(" ")[1];
                paramNameList.add(paramName);
            }else {
                paramNameList.add(param);
            }
        }
        return  methodBean.getSimplMethodName()+"("+StringUtils.join(paramNameList,",")+")";

    }
}
