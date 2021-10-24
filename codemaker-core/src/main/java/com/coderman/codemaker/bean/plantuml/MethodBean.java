package com.coderman.codemaker.bean.plantuml;

import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.enums.VisibilityEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class MethodBean {

    public MethodBean(){}
    public MethodBean(String methodName,String returnClass){
        this.methodName = methodName;
        this.returnClass = returnClass;
    }

    /**
     * 方法名称，
     * 包括参数，括号
     */
    private String methodName;
    /**
     * 访问权限
     */
    private String visibility;

    /**
     * 方法描述
     */
    private String desc;

    /**
     * 是否是静态属性
     */
    private boolean isStatic;

    /**
     * 方法返回值
     */
    private String returnClass;

    /**
     * 方法返回对象所在包
     */
    private String returnClassPackage;


    /**
     * 方法内容
     */
    private String  methodContent;

    /**
     * 方法返回体
     */
    private String returnBody;

    /**
     * controller方法的请求路径
     */
    private String pathValue;


    /**
     * 方法调用内容，根据调用流程图-plantUML解析而来
     */
    private LinkedList<String> invokeMethodList;

    /**
     * 方法调用内容，根据调用流程图-plantUML解析而来
     * 与invokeMethodList保持一致
     * 帮助去除重复和进行上下文调用分析辅助动态绘制
     */
    private List<InvokeRowBean> invokeRowBeanList;


    /**
     * 方法文档
     */
    private String doc;

    /**
     * 解析出来的方法参数列表
     */
    private String [] paramArr;

    /**
     * 所属类名
     */
    private String className;

    public List<InvokeRowBean> getInvokeRowBeanList() {
        return invokeRowBeanList;
    }

    public void setInvokeRowBeanList(List<InvokeRowBean> invokeRowBeanList) {
        this.invokeRowBeanList = invokeRowBeanList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String[] getParamArr() {
        return paramArr;
    }

    public void setParamArr(String[] paramArr) {
        this.paramArr = paramArr;
    }

    public String getMethodContent() {
        return methodContent;
    }

    public void setMethodContent(String methodContent) {
        this.methodContent = methodContent;
    }

    public LinkedList<String> getInvokeMethodList() {
        return invokeMethodList;
    }

    public void setInvokeMethodList(LinkedList<String> invokeMethodList) {
        this.invokeMethodList = invokeMethodList;
    }

    public String getPathValue() {
        return pathValue;
    }

    public void setPathValue(String pathValue) {
        this.pathValue = pathValue;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(String returnClass) {
        this.returnClass = returnClass;
    }

    public String getReturnClassPackage() {
        return returnClassPackage;
    }

    public void setReturnClassPackage(String returnClassPackage) {
        this.returnClassPackage = returnClassPackage;
    }


    public String getReturnBody() {
        return returnBody;
    }

    public void setReturnBody(String returnBody) {
        this.returnBody = returnBody;
    }


    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    /**
     * 动态构建方法内容
     */
    public void buildMethodContent(){
        if(CollectionUtils.isEmpty(invokeMethodList)){
            return;
        }

        StringBuilder contentBuilder = new StringBuilder();
        for (String str : invokeMethodList){
            contentBuilder.append("        "+str+";\n");
        }
        if(StringUtils.isEmpty(this.getMethodContent())){
            this.setMethodContent( contentBuilder.toString());
        }else {
            this.setMethodContent(this.getMethodContent() + contentBuilder.toString());
        }
    }

    /**
     * 优化方法接口注释
     * @param desc
     */
    public void buildDesc(String desc){
        if(desc.startsWith(VisibilityEnum.PUBLIC.getTag())
          || desc.startsWith(VisibilityEnum.PRIVATE.getTag())
          || desc.startsWith(VisibilityEnum.PROTECT.getTag())){
            String newDesc = desc.substring(1,desc.length()-1);
            this.setDesc(newDesc);
        }else {
            this.setDesc(desc);
        }
    }



    /**
     * 构建方法接口注释
     */
    public void buildDoc(){
        StringBuilder builder = new StringBuilder("\t/**\n" +
                "\t *\n");
        builder.append("\t * @Description "+this.getDesc()+"\n");

        if (!this.getMethodName().contains("()")){
            String [] paramArr = this.getMethodName().replace(")","").split("\\(")[1].split(",");
            for (String param : paramArr){
                builder.append("\t * @param "+param.trim().split(" ")[1]+"\n");
            }
        }
        builder.append("\t * @return "+this.getReturnClass()+"\n");
        builder.append("\t */");
        this.setDoc(builder.toString());
    }


    /**
     * 从方法名称中解析到方法参数
     */
    public void buildParamArr(){
        if (!this.getMethodName().contains("()")){
            String [] paramArr = this.getMethodName().replace(")","").split("\\(")[1].split(",");

            if(paramArr != null && paramArr.length>=1){
                this.setParamArr(paramArr);
            }
        }
    }

    /**
     * 构建返回类型
     * @return
     */
    public String buildReturnClassType(){
        if(this.getReturnClass().toLowerCase().equals("void")){
            return null;
        }
        return this.getReturnClass()
                .replace("ResultDataDto","")
                .replace("ResultDto","")
                .replace("<","")
                .replace(">","")
                .replace("Map","")
                .replace("Set","")
                .replace(",","")
                .replace("List","").trim();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodBean that = (MethodBean) o;
        return Objects.equals(methodName, that.methodName) &&
                Objects.equals(returnClass, that.returnClass) &&
                Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName, returnClass, className);
    }

    /**
     * 防止由于引用问题导致的MethodBean对象修改影响其他类下的method方法内容
     * @param methodContent
     * @return
     */
    public MethodBean copySelf(String methodContent){
        MethodBean newBean = new MethodBean();
        newBean.setClassName(this.getClassName());
        newBean.setDoc(this.getDoc());
        newBean.setMethodContent(methodContent);
        newBean.setDesc(this.getDesc());
        newBean.setReturnClass(this.getReturnClass());
        newBean.setReturnBody(this.getReturnBody());
        newBean.setVisibility(this.getVisibility());
        newBean.setParamArr(this.getParamArr());
        newBean.setStatic(this.isStatic());
        newBean.setPathValue(this.getPathValue());
        newBean.setMethodName(this.getMethodName());
        return newBean;
    }


    /**
     * 将方法中的参数信息去掉，仅仅获取方法名
     * @return
     */
    public String getSimplMethodName(){
        if(this.getMethodName().contains("()")){
            return this.getMethodName().replace("()","");
        }
        else {
            return this.getMethodName().split("\\(")[0];
        }
    }

    /**
     * 构建方法的调用内容
     * @param currentRowContent
     * @param currentRowBean
     */
    public void addInvokeRowContent(String currentRowContent,InvokeRowBean currentRowBean){

        for (InvokeRowBean oldRowBean : this.getInvokeRowBeanList()){
            if(oldRowBean.equals(currentRowBean)){
                return;
            }
        }
        this.getInvokeMethodList().add(currentRowContent);
        this.getInvokeRowBeanList().add(currentRowBean);
    }

    /**
     * 初始化
     */
    public void initInvokeRowContentList(){
        this.setInvokeMethodList(new LinkedList<>());
        this.setInvokeRowBeanList(new ArrayList<>());
    }

}
