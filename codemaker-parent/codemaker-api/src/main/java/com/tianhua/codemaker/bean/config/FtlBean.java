package com.tianhua.codemaker.bean.config;

/**
 * Description:
 * date: 2022/1/17
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class FtlBean {
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * class类型
     */
    private String classType;
    /**
     * 代码元素模板名称
     */
    private String codeTempFileName;

    /**
     * 自定义模板配置字符串
     */
    private String ftlStr;


    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getCodeTempFileName() {
        return codeTempFileName;
    }

    public void setCodeTempFileName(String codeTempFileName) {
        this.codeTempFileName = codeTempFileName;
    }

    public String getFtlStr() {
        return ftlStr;
    }

    public void setFtlStr(String ftlStr) {
        this.ftlStr = ftlStr;
    }

    public static FtlBean getInstance(String ftlStr){
        String [] arr = ftlStr.split(":");
        FtlBean ftlBean = new FtlBean();
        ftlBean.setFtlStr(ftlStr);
        ftlBean.setClassType(arr[1]);
        ftlBean.setCodeTempFileName(arr[0]);
        ftlBean.setModuleName(arr[2]);
        return ftlBean;
    }
}
