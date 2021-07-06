package com.coderman.codemaker.app;

import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import com.coderman.codemaker.bean.plantuml.FieldBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.DefaultPackageConfig;
import com.coderman.codemaker.config.ProjectTemplateDynamicDDDConfig;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ImportPackageService {
    @Autowired
    private DefaultPackageConfig defaultPackageConfig;

    @Autowired
    private ProjectTemplateDynamicDDDConfig projectTemplateDynamicDDDConfig;
    /**
     * 处理需要导入的包
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    public void dealImportClass(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean){
        Set<String> importClassSet = new HashSet<>();

        abstractClassBean.getMethodBeanList().forEach(methodBean -> {
            String returnClassName = methodBean.getReturnClass();

            String defaultPackageName = defaultPackageConfig.getPackage(returnClassName);
            if(!StringUtils.isEmpty(defaultPackageName)){
                importClassSet.add(defaultPackageName);
            }

            plantUmlContextBean.getClassBeanMap().forEach((k,v)->{

                if(!abstractClassBean.getPackageName().equals(v.getPackageName())){
                    //返回参数匹配
                    if(returnClassName.toLowerCase().contains(k.toLowerCase())){
                        importClassSet.add(v.getPackageName()+"."+v.getClassName());
                    }
                    /**
                     * 方法参数匹配
                     */
                    if(methodBean.getMethodName().toLowerCase().contains(k.toLowerCase())){
                        importClassSet.add(v.getPackageName()+"."+v.getClassName());
                    }
                }

            });

            plantUmlContextBean.getEnumBeanMap().forEach((k,v)->{
                if(k.toLowerCase().equals(returnClassName.toLowerCase())){
                    importClassSet.add(v.getPackageName()+"."+v.getClassName());
                }
                if(methodBean.getMethodName().toLowerCase().contains(k.toLowerCase())){
                    importClassSet.add(v.getPackageName()+"."+v.getClassName());
                }
            });
        });


        if (CollectionUtils.isNotEmpty(abstractClassBean.getFieldBeanList())) {
            abstractClassBean.getFieldBeanList().forEach(fieldBean -> {
                String fieldClass = fieldBean.getFieldName().trim().toLowerCase();

                String defaultPackageName = defaultPackageConfig.getPackage(fieldClass);
                if(!StringUtils.isEmpty(defaultPackageName)){
                    importClassSet.add(defaultPackageName);
                }

                plantUmlContextBean.getClassBeanMap().forEach((k,v)->{

                    if(!abstractClassBean.getPackageName().equals(v.getPackageName())){
                        //属性类型匹配
                        if(fieldClass.contains(k.toLowerCase())){
                            importClassSet.add(v.getPackageName()+"."+v.getClassName());
                        }
                    }
                });

                plantUmlContextBean.getEnumBeanMap().forEach((k,v)->{
                    if(fieldClass.contains(k.toLowerCase())){
                        importClassSet.add(v.getPackageName()+"."+v.getClassName());
                    }
                });
            });
        }

        if(importClassSet.isEmpty()){
            abstractClassBean.setImportClassList(Lists.newArrayList());
        }else {
            abstractClassBean.setImportClassList(Lists.newArrayList(importClassSet));
        }

    }


    /**
     * 设置包名
     * @param abstractClassBean
     * @param defaultChildPackage 当plantUML中没有包名则使用配置的全局包名+子包名
     */
    public void setPackageName(AbstractClassBean abstractClassBean, String defaultChildPackage){
        if(org.apache.commons.lang3.StringUtils.isEmpty(abstractClassBean.getPlantUMLPackage())){
            String packageName = projectTemplateDynamicDDDConfig.getGlobalPackage()+"."+defaultChildPackage;
            abstractClassBean.setPackageName(packageName);
            return;
        }else {
            if(!abstractClassBean.getPlantUMLPackage().contains(projectTemplateDynamicDDDConfig.getGlobalPackage())
                    && abstractClassBean.getPlantUMLPackage().split("\\.").length > 2
                    && !abstractClassBean.getPlantUMLPackage().contains(" as ")){
                abstractClassBean.setPackageName(abstractClassBean.getPlantUMLPackage());
                return;
            }
            if(abstractClassBean.getPlantUMLPackage().contains(projectTemplateDynamicDDDConfig.getGlobalPackage())){
                abstractClassBean.setPackageName(abstractClassBean.getPlantUMLPackage());
                return;
            }
            else if(!abstractClassBean.getPlantUMLPackage().contains("-") && abstractClassBean.getPlantUMLPackage().split("\\.").length == 2){
                String packageName = projectTemplateDynamicDDDConfig.getGlobalPackage() +"."+ abstractClassBean.getPlantUMLPackage();
                abstractClassBean.setPackageName(packageName);
                return;
            }
            else if(abstractClassBean.getPlantUMLPackage().contains("-") && !abstractClassBean.getPlantUMLPackage().contains(" as ")){
                String childPackage = abstractClassBean.getPlantUMLPackage().replace("\"","").split("-")[1];
                String packageName = projectTemplateDynamicDDDConfig.getGlobalPackage() +"."+ childPackage;
                abstractClassBean.setPackageName(packageName);
                return;
            }else {
                String[] array = abstractClassBean.getPlantUMLPackage().replace("\"","").trim().split(" ");
                String packageName = "";
                for (String str : array){
                    if(str.contains("-")){
                        packageName = str.split("-")[1];
                    }
                }
                if(packageName.split("\\.").length == 2){
                    packageName = projectTemplateDynamicDDDConfig.getGlobalPackage() +"."+ packageName;
                    abstractClassBean.setPackageName(packageName);
                    return;
                }else {
                    //超过两级包名则默认 为全包名
                    abstractClassBean.setPackageName(packageName);
                }
                return;
            }
            //如果配置的全局包名与plantUML中的文档包名不一致则最终使用文档中的包名
            //abstractClassBean.setPackageName(abstractClassBean.getPlantUMLPackage());

        }
    }




    /**
     *
     * @param fieldBeanList
     * @param packageName
     * @param plantUmlContextBean
     */
    public List<String> dealImportClassFromField(List<FieldBean> fieldBeanList, String packageName, PlantUmlContextBean plantUmlContextBean){
        Set<String> importClassSet = new HashSet<>();

        fieldBeanList.forEach(fieldBean -> {
            String fieldClass = fieldBean.getFieldName().trim().toLowerCase();

            String defaultPackageName = defaultPackageConfig.getPackage(fieldClass);
            if(!StringUtils.isEmpty(defaultPackageName)){
                importClassSet.add(defaultPackageName);
            }

            plantUmlContextBean.getClassBeanMap().forEach((k,v)->{

                if(!packageName.equals(v.getPackageName())){
                    //属性类型匹配
                    if(fieldClass.contains(k.toLowerCase())){
                        importClassSet.add(v.getPackageName()+"."+v.getClassName());
                    }
                }
            });

            plantUmlContextBean.getEnumBeanMap().forEach((k,v)->{
                if(fieldClass.contains(k.toLowerCase())){
                    importClassSet.add(v.getPackageName()+"."+v.getClassName());
                }
            });
        });

        return Lists.newArrayList(importClassSet);
    }



}
