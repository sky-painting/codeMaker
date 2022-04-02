package com.tianhua.codemaker.config;

import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.config.ConfigFileBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.GAVBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:解析公共配置
 * date: 2022/2/8
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ConfigParseService {
    /**
     * 获取项目配置文件配置
     * @return
     */
    public List<ConfigFileBean> getConfigFileBean(String configFile){
        List<ConfigFileBean> list = new ArrayList<>();
        if(StringUtils.isEmpty(configFile)){
            return list;
        }
        String [] configFileArr = configFile.split(",");
        for (String configStr : configFileArr){
            String [] moduleConfigArr = configStr.split(":");
            String moduleName = moduleConfigArr[1];
            String [] configArr = moduleConfigArr[0].split("_");
            ConfigFileBean configFileBean = ConfigFileBean.getInstance(configArr[0],configArr[1], moduleName);
            configFileBean.setTemplateName(moduleConfigArr[0]);
            list.add(configFileBean);
        }
        return list;
    }

    /**
     * 获取统一的pom配置
     * 默认是父级pom.xml的实例
     * @return
     */
    public Map<String, PomBean> getPomBeanV2(String initPom, String applicationName, String appAliasName,String groupId, String artifactId, String version){
        Map<String, PomBean> pomBeanMap = getPomBeanMap(applicationName, appAliasName, groupId, artifactId, version);
        if(StringUtils.isNotEmpty(initPom)){
            String [] arr = initPom.split(",");
            for (String pom : arr){
                String [] pomArr = pom.split(":");
                PomBean modulePom = pomBeanMap.get(pomArr[1]);
                if(modulePom == null){
                    modulePom = new PomBean();
                    modulePom.setModuleName(pomArr[1]);
                    modulePom.setModuleCode(pomArr[1]);
                    modulePom.setModulePrefix(appAliasName);
                    modulePom.setGroupId(groupId);
                    List<GAVBean> gavBeanList = new ArrayList<>();
                    gavBeanList.add(new GAVBean(pomArr[0]));
                    modulePom.setDependencyList(gavBeanList);
                }else {
                    modulePom.getDependencyList().add(new GAVBean(pomArr[0]));
                }
                pomBeanMap.put(pomArr[1], modulePom);
            }
        }
        return pomBeanMap;
    }
    /**
     * 获取pom配置
     * @return
     */
    private Map<String,PomBean> getPomBeanMap(String applicationName, String appAliasName,String groupId, String artifactId, String version){
        Map<String,PomBean> pomBeanMap = new HashMap<>();
        PomBean parentPom = new PomBean();
        parentPom.setUrl(null);
        parentPom.buildGAV(groupId,artifactId,version);
        parentPom.setTemplateCode(TemplateFileEnum.PARENT_POM.getTempFileName());
        parentPom.setAppName(applicationName);
        parentPom.buildModule(appAliasName, GlobalConstant.PARENT);
        parentPom.setDependencyList(new ArrayList<>());


        PomBean clientPom = new PomBean();
        clientPom.buildGAV(groupId,artifactId,version);
        clientPom.setDependencyList(new ArrayList<>());
        clientPom.setTemplateCode(TemplateFileEnum.CLIENT_POM.getTempFileName());
        clientPom.buildModule(appAliasName, GlobalConstant.CLIENT);


        PomBean apiPom = new PomBean();
        apiPom.buildGAV(groupId,artifactId,version);
        apiPom.setDependencyList(new ArrayList<>());
        apiPom.setTemplateCode(TemplateFileEnum.API_POM.getTempFileName());
        apiPom.buildModule(appAliasName, GlobalConstant.API);

        PomBean corePom = new PomBean();
        corePom.buildGAV(groupId,artifactId,version);
        corePom.setDependencyList(new ArrayList<>());
        corePom.setTemplateCode(TemplateFileEnum.CORE_POM.getTempFileName());
        corePom.buildModule(appAliasName, GlobalConstant.CORE);

        PomBean commonPom = new PomBean();
        commonPom.buildGAV(groupId,artifactId,version);
        commonPom.setDependencyList(new ArrayList<>());
        commonPom.setTemplateCode(TemplateFileEnum.COMMON_POM.getTempFileName());
        commonPom.buildModule(appAliasName, GlobalConstant.COMMON);


        PomBean appPom = new PomBean();
        appPom.buildGAV(groupId,artifactId,version);
        appPom.setDependencyList(new ArrayList<>());
        appPom.setTemplateCode(TemplateFileEnum.APP_POM.getTempFileName());
        appPom.buildModule(appAliasName, GlobalConstant.APP);

        PomBean domainPom = new PomBean();
        domainPom.buildGAV(groupId,artifactId,version);
        domainPom.setDependencyList(new ArrayList<>());
        domainPom.setTemplateCode(TemplateFileEnum.DOMAIN_POM.getTempFileName());
        domainPom.buildModule(appAliasName, GlobalConstant.DOMAIN);


        PomBean infrastPom = new PomBean();
        infrastPom.buildGAV(groupId,artifactId,version);
        infrastPom.setDependencyList(new ArrayList<>());
        infrastPom.setTemplateCode(TemplateFileEnum.INFRAST_POM.getTempFileName());
        infrastPom.buildModule(appAliasName, GlobalConstant.INFRAST);

        PomBean startPom = new PomBean();
        startPom.buildGAV(groupId,artifactId,version);
        startPom.setDependencyList(new ArrayList<>());
        startPom.setTemplateCode(TemplateFileEnum.START_POM.getTempFileName());
        startPom.buildModule(appAliasName, GlobalConstant.START);

        PomBean adapterPom = new PomBean();
        adapterPom.buildGAV(groupId,artifactId,version);
        adapterPom.setDependencyList(new ArrayList<>());
        adapterPom.setTemplateCode(TemplateFileEnum.ADAPTER_POM.getTempFileName());
        adapterPom.buildModule(appAliasName, GlobalConstant.ADAPTER);


        PomBean providerPom = new PomBean();
        providerPom.buildGAV(groupId,artifactId,version);
        providerPom.setDependencyList(new ArrayList<>());
        providerPom.setTemplateCode(TemplateFileEnum.PROVIDER_POM.getTempFileName());
        providerPom.buildModule(appAliasName, GlobalConstant.PROVIDER);


        PomBean feignApiPom = new PomBean();
        feignApiPom.buildGAV(groupId,artifactId,version);
        feignApiPom.setDependencyList(new ArrayList<>());
        feignApiPom.setTemplateCode(TemplateFileEnum.FEIGN_API_POM.getTempFileName());
        feignApiPom.buildModule(appAliasName, GlobalConstant.FEIGN_API);




        PomBean springBootPom = new PomBean();
        springBootPom.buildGAV(groupId,artifactId,version);
        springBootPom.setDependencyList(new ArrayList<>());
        springBootPom.setTemplateCode(TemplateFileEnum.PARENT_POM.getTempFileName());
        springBootPom.buildModule(appAliasName, GlobalConstant.SPRING_BOOT);

        pomBeanMap.put(GlobalConstant.PARENT,parentPom);
        pomBeanMap.put(GlobalConstant.ADAPTER.replace("-",""),adapterPom);
        pomBeanMap.put(GlobalConstant.START.replace("-",""),startPom);
        pomBeanMap.put(GlobalConstant.CLIENT.replace("-",""),clientPom);
        pomBeanMap.put(GlobalConstant.APP.replace("-",""),appPom);
        pomBeanMap.put(GlobalConstant.DOMAIN.replace("-",""),domainPom);
        pomBeanMap.put(GlobalConstant.INFRAST.replace("-",""),infrastPom);
        pomBeanMap.put(GlobalConstant.FEIGN_API.replace("-",""),feignApiPom);
        pomBeanMap.put(GlobalConstant.API.replace("-",""),apiPom);
        pomBeanMap.put(GlobalConstant.CORE.replace("-",""),corePom);
        pomBeanMap.put(GlobalConstant.COMMON.replace("-",""),commonPom);
        pomBeanMap.put(GlobalConstant.PROVIDER.replace("-",""),providerPom);
        pomBeanMap.put(GlobalConstant.SPRING_BOOT.replace("-",""),springBootPom);

        return pomBeanMap;
    }


    /**
     * 返回配置的参数验证服务bean
     * @return
     */
    public List<String> getValidateBeanList(String validateBeans){
        if(StringUtils.isEmpty(validateBeans)){
            return Lists.newArrayList();
        }
        if(!validateBeans.contains(",")){
            return Lists.newArrayList(validateBeans);
        }else {
            return Lists.newArrayList(validateBeans.split(","));
        }
    }

    /**
     * 获取自定义配置的代码元素
     * @return
     */
    public List<FtlBean> getFtlBeanList(String customFtl,String initPom, String applicationName, String appAliasName,String groupId, String artifactId, String version){
        List<FtlBean> ftlBeans = new ArrayList<>();
        Map<String, PomBean> pomBeanMap = getPomBeanV2(initPom, applicationName, appAliasName, groupId, artifactId, version);
        for (Map.Entry<String,PomBean> pomBeanEntry : pomBeanMap.entrySet()){
            PomBean pomBean = pomBeanEntry.getValue();
            String ftlStr = pomBean.getTemplateCode()+":pom:"+pomBean.getModuleCode();
            ftlBeans.add(FtlBean.getInstance(ftlStr));
        }

        if(StringUtils.isEmpty(customFtl)){
            return ftlBeans;
        }
        if(customFtl.contains(",")){
            String [] ftlStrArr = customFtl.split(",");
            for (String flt : ftlStrArr){
                ftlBeans.add(FtlBean.getInstance(flt));
            }
        }else {
            ftlBeans.add(FtlBean.getInstance(customFtl));
        }


        return ftlBeans;
    }

}
