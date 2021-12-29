package com.coderman.codemaker.bean.plantuml;

import com.alibaba.fastjson.annotation.JSONField;
import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.apidoc.ApiDocBean;
import com.coderman.codemaker.bean.apidoc.ApiParamBean;
import com.coderman.codemaker.bean.invoke.InvokeRowBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.enums.VisibilityEnum;
import com.coderman.codemaker.utils.StringHandleUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

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

    public MethodBean(String methodName,String returnClass, String desc){
        this.methodName = methodName;
        this.returnClass = returnClass;
        this.desc = desc;
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
     * 对于mapper和controller需要增加参数注解的进行动态构建参数注解
     */
    private String [] paramAnnotationArr;

    /**
     * 所属类名
     */
    private String className;

    /**
     * 方法上的注解
     */
    private String annotation;

    /**
     * 方法的范型返回值
     */
    private String genericReturnType;

    /**
     * 方法的范型参数
     */
    private List<String> genericParamList;

    /**
     * 是否是bo模型定义的扩展接口
     */
    private boolean modelExtendMethod;

    public boolean isModelExtendMethod() {
        return modelExtendMethod;
    }

    public void setModelExtendMethod(boolean modelExtendMethod) {
        this.modelExtendMethod = modelExtendMethod;
    }

    public List<String> getGenericParamList() {
        return genericParamList;
    }

    public void setGenericParamList(List<String> genericParamList) {
        this.genericParamList = genericParamList;
    }

    public String getGenericReturnType() {
        return genericReturnType;
    }

    public void setGenericReturnType(String genericReturnType) {
        this.genericReturnType = genericReturnType;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String[] getParamAnnotationArr() {
        return paramAnnotationArr;
    }

    public void setParamAnnotationArr(String[] paramAnnotationArr) {
        this.paramAnnotationArr = paramAnnotationArr;
    }

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
                if(param.contains(" ")){
                    builder.append("\t * @param "+param.trim().split(" ")[1]+"\n");
                }else {
                    builder.append("\t * @param "+ StringHandleUtils.getParamVar(param)+"\n");
                }
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
        if(this.getParamArr() != null){
            return;
        }
        if(!this.getMethodName().contains("(") && !this.getMethodName().contains(")")){
            return;
        }
        if (!this.getMethodName().contains("()")){
            String [] paramArr = this.getMethodName().replace(")","").split("\\(")[1].split(",");

            if(paramArr != null && paramArr.length>=1){
                this.setParamArr(paramArr);
            }
        }
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
        newBean.setMethodName(refreshMethodName());

        return newBean;
    }

    /**
     * 刷新方法声明，注入方法参数注解，也可能是方法注解，后续可扩展
     * @return
     */
    public String refreshMethodName(){
        if(this.getParamAnnotationArr() == null || this.getParamAnnotationArr().length == 0){
            return this.getMethodName();
        }
        String preMethod = this.getMethodName().split("\\(")[0];
        StringBuilder builder = new StringBuilder(preMethod);
        List<String> paramList = new ArrayList<>();
        for (int i = 0;i < this.getParamArr().length;i++){
            String annotation = this.getParamAnnotationArr()[i];
            String param = this.getParamArr()[i];
            paramList.add(annotation + " " + param);
        }
        builder.append("(");
        builder.append(StringUtils.join(paramList,", "));
        builder.append(")");

        return builder.toString();
    }

    /**
     * 根据返回类型刷新方法返回体
     */
    public void refreshReturnBodyByReturnType(){
        if(this.getReturnClass().contains("void")){
            return;
        }
        if(StringHandleUtils.isStrictBasicType(this.getReturnClass())){
            if(this.getReturnClass().contains("boolean")){
                this.setReturnBody("return false;");
            }
            else if(this.getReturnClass().contains("long")){
                this.setReturnBody("return 0L;");
            }
            else if(this.getReturnClass().contains("double")){
                this.setReturnBody("return 0.0;");
            }
            else if(this.getReturnClass().contains("float")){
                this.setReturnBody("return 0.0f;");
            } else {
                this.setReturnBody("return 0;");
            }
        }

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

        if(CollectionUtils.isEmpty( this.getInvokeRowBeanList())){
            this.getInvokeMethodList().add(currentRowContent);
            this.getInvokeRowBeanList().add(currentRowBean);
        }else {
            //判断变量名是否重复
            Set<String> returnSet = new HashSet<>();
            for (InvokeRowBean invokeRowBean :  this.getInvokeRowBeanList()){
                String returnValue = invokeRowBean.getReturnClassName() + invokeRowBean.getReturnClassValue();
                returnSet.add(returnValue);
            }
            String currentReturnValue = currentRowBean.getReturnClassName() + currentRowBean.getReturnClassValue();
            if(returnSet.contains(currentReturnValue)){
                currentRowBean.setReturnClassValue(currentRowBean.getReturnClassValue()+this.getInvokeRowBeanList().size());
                this.getInvokeRowBeanList().add(currentRowBean);
                this.getInvokeMethodList().add(currentRowBean.buildInvokeContent());
            }

        }

    }

    /**
     * 初始化
     */
    public void initInvokeRowContentList(){
        if(CollectionUtils.isEmpty(invokeRowBeanList)){
            this.setInvokeRowBeanList(new ArrayList<>());
        }
        if(CollectionUtils.isEmpty(invokeMethodList)){
            this.setInvokeMethodList(new LinkedList<>());
        }
    }


    /**
     * 解析方法返回参数类型，是否包含vo,dto,bo
     * @return
     */
    @JSONField(serialize = false)
    public String getReturnClassTypeModel(){
        if(this.getReturnClass().contains("void")){
            return "";
        }

        if(this.getReturnClass().contains("<")){
            String [] returnClassArr = this.getReturnClass().split("<");
            String matchClassType = "";
            for (String classStr : returnClassArr){
                String classType = classStr.trim().replace(">","");
                if(TemplateFileEnum.isClassModel(classType)){
                    matchClassType = classType;
                }
            }
            return  matchClassType;
        }
        return this.getReturnClass();
    }

    /**
     * 解析方法返回参数类型，是否包含vo,dto,bo
     * @return
     */
    @JSONField(serialize = false)
    public String getReturnClassTypeNoWrapper(){
        if(this.getReturnClass().contains("void")){
            return "";
        }

        if(!this.wrapperResultDto() && !this.wrapperResultDataDto()){
            return this.getReturnClass();
        }

        if(this.getReturnClass().contains("<")){
            int index = this.getReturnClass().indexOf("<");
            return this.getReturnClass().substring(index+1,this.getReturnClass().length() - 1);
        }
        return this.getReturnClass();
    }



    /**
     * 校验外部参数是否与方法参数匹配,这里只需要匹配一个即可
     * @param param
     * @return
     */
    public boolean paramMatchOne(String param){
        if(this.getParamArr() == null || this.getParamArr().length == 0){
            return false;
        }
        String tempParam = param;
        if(tempParam.trim().contains(" ")){
            tempParam = tempParam.split(" ")[0];
        }
        for (String paramInfo : this.getParamArr()){
            String paramType = paramInfo;
            if(paramInfo.trim().contains(" ")){
                paramType = paramInfo.split(" ")[0];
            }
            if(paramType.toLowerCase().endsWith(tempParam.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断方法是不是有包装类对象
     * @return
     */
    public boolean wrapperResultDto(){

        if (this.getReturnClass().contains("Result")){
            return true;
        }
        return false;
    }

    public boolean wrapperResultDataDto(){
        if(this.getReturnClass().contains("ResultDataDto")){
            return true;
        }
        return false;
    }


    /**
     * 动态构建方法返回值包装
     * @param returnBody
     */
    public void wrapperResultBody(String returnBody){
        if(this.getReturnClass().equals("void")){
            return;
        }
        if(this.getReturnClass().contains(returnBody)){
            return;
        }

        this.setReturnClass(returnBody+"<"+this.getReturnClass()+">");
    }



    /**
     * 构建api文档
     * @return
     */
    public ApiDocBean buildApiDocBean(Map<String,ClassBean> classVOBeanMap){
        ApiDocBean apiDocBean = new ApiDocBean();
        apiDocBean.setInterfaceName(this.getSimplMethodName());
        apiDocBean.setParamBeanList(convertToApiParam(classVOBeanMap));
        apiDocBean.setReturnClass(this.getReturnClass());
        apiDocBean.setInterfaceDesc(this.getDesc());
        apiDocBean.setHttpPath(this.getPathValue());
        return apiDocBean;
    }

    private List<ApiParamBean> convertToApiParam(Map<String,ClassBean> classVOBeanMap){
        if(this.getParamArr() == null || this.getParamArr().length == 0){
            return null;
        }
        List<ApiParamBean> apiParamBeanList = new ArrayList<>();
        for (String param : this.getParamArr()){
            ApiParamBean apiParamBean = new ApiParamBean();
            if(!param.trim().contains(" ")){
                continue;
            }
            String [] arr = param.split(" ");
            apiParamBean.setFieldName(arr[1]);
            apiParamBean.setFieldType(arr[0]);
            apiParamBeanList.add(apiParamBean);
            if(arr[0].toLowerCase().endsWith(TemplateFileEnum.VO.getTempFileName())
                    || arr[0].toLowerCase().endsWith(TemplateFileEnum.DTO.getTempFileName())){
                ClassBean classBean = classVOBeanMap.get(arr[0]);
                if(classBean == null){

                    continue;
                }
                apiParamBeanList.get(apiParamBeanList.size() - 1).setFieldDesc("对象类型,参数如下:");
                for (FieldBean fieldBean : classBean.getFieldBeanList()){
                    if(fieldBean.getFieldName().contains("*")){
                        continue;
                    }
                    ApiParamBean paramBean = new ApiParamBean();
                    String [] arr2 = fieldBean.getFieldName().split(" ");
                    paramBean.setFieldName(arr2[1]);
                    paramBean.setFieldType(arr2[0]);
                    paramBean.setFieldDesc(fieldBean.getDesc());
                    apiParamBeanList.add(paramBean);
                }

            }

        }
        return apiParamBeanList;
    }


    /**
     * 判断方法是否需要导出acl param
     * @return
     */
    @JSONField(serialize = false)
    public boolean isExportAclKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.EXPORT_ACL_KEY)
                || this.methodName.toLowerCase().contains(GlobalConstant.EXPORT_ACL_KEY);
    }

}
