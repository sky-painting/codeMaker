package com.tianhua.codemaker.bean.plantuml;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.bean.segment.SegmentBean;
import com.tianhua.codemaker.enums.CompTypeEnum;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.enums.TemplateFileEnum;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Description:
 * 抽象plantuml 类
 * date: 2021/6/28
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public abstract class AbstractClassBean {

    /**
     * 类或者接口的范型信息
     */
    private PatternBean patternBean;
    /**
     * 类名
     */
    protected String className;

    /**
     * 所在包
     */
    protected String packageName;
    /**
     * 所属模块
     */
    protected String belongModel;

    /**
     * 类描述
     */
    protected String classDesc;

    /**
     * 方法
     */
    private List<MethodBean> methodBeanList;


    /**
     * 父类方法列表
     */
    private List<MethodBean> superMethodBeanList;

    /**
     * 父类属性列表
     *
     */
    private List<FieldBean> superFieldBeanList;

    /**
     * 需要引入的class包名
     */
    private List<String> importClassList;
    /**
     * 属性列表
     *
     */
    private List<FieldBean> fieldBeanList;



    /**
     * 实现接口
     */
    private InterfaceBean implInterfaceBean;

    /**
     * 继承类列表
     */
    private String relationClassStr;
    /**
     * 项目作者
     */
    private String author;

    /**
     * 所在plantUML的包名
     */
    private String plantUMLPackage;

    /**
     * 是否是派生类
     */
    private boolean isDerived;

    /**
     * 组件名称
     * 根据该标示判断是否是plantUML类图中的还是组件扫描引入的
     */
    private String compName;


    //类所属组件类型，默认为spring,即plantUML类图中发起调用的类都是spring类型
    //这里当然还有其他类型，如xxbo.xxxMethod()，这里需要进行特殊识别
    private String compType = CompTypeEnum.SPRING.getCompTag();


    private String bodtoConvertInterface;

    private String bodoConvertInterface;

    private String bovoConvertInterface;

    /**
     * 在动态调用中产生的引用包
     */
    private List<String> dynamicImportPackageList = new ArrayList<>();


    private List<String> thisClassImportPackageList = new ArrayList<>();

    /**
     * 子类需要导入的包
     */
    private List<String> childClassImportPackageList = new ArrayList<>();



    /**
     * 派生类链路
     * 如bo派生dto
     * dto的派生链路里就有boclass
     * 用来记录派生过程中的关联关系
     */
    private List<String> derivedChainClassList;

    /**
     * bo上的扩展属性对象
     */
    private ExtendFieldBean extendFieldBean = new ExtendFieldBean();

    /**
     * 对应的tableBean  mapper对象使用
     */
    private TableBean tableBean;

    /**
     * 对应的columnBeanList  mapper对象使用
     */
    private List<ColumnBean> columnBeanList;

    /**
     * 类上的注释
     */
    private String annotation;

    /**
     * 所属上下文
     */
    private String context = "";

    /**
     * plantUML的扩展注解标示
     */
    private List<String> annotationTagList;

    /**
     * 代码片段列表
     */
    private List<SegmentBean> codeSegmentBeanList = new ArrayList<>();


    public PatternBean getPatternBean() {
        return patternBean;
    }

    public void setPatternBean(PatternBean patternBean) {
        this.patternBean = patternBean;
    }

    public List<MethodBean> getSuperMethodBeanList() {
        return superMethodBeanList;
    }

    public void setSuperMethodBeanList(List<MethodBean> superMethodBeanList) {
        this.superMethodBeanList = superMethodBeanList;
    }

    public List<FieldBean> getSuperFieldBeanList() {
        return superFieldBeanList;
    }

    public void setSuperFieldBeanList(List<FieldBean> superFieldBeanList) {
        this.superFieldBeanList = superFieldBeanList;
    }

    public List<SegmentBean> getCodeSegmentBeanList() {
        return codeSegmentBeanList;
    }

    public List<String> getAnnotationTagList() {
        return annotationTagList;
    }

    public void setAnnotationTagList(List<String> annotationTagList) {
        this.annotationTagList = annotationTagList;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public TableBean getTableBean() {
        return tableBean;
    }

    public void setTableBean(TableBean tableBean) {
        this.tableBean = tableBean;
    }

    public List<ColumnBean> getColumnBeanList() {
        return columnBeanList;
    }

    public void setColumnBeanList(List<ColumnBean> columnBeanList) {
        this.columnBeanList = columnBeanList;
    }

    public ExtendFieldBean getExtendFieldBean() {
        return extendFieldBean;
    }

    public void setExtendFieldBean(ExtendFieldBean extendFieldBean) {
        this.extendFieldBean = extendFieldBean;
    }

    public List<String> getDerivedChainClassList() {
        return derivedChainClassList;
    }

    public void setDerivedChainClassList(List<String> derivedChainClassList) {
        this.derivedChainClassList = derivedChainClassList;
    }

    public List<String> getDynamicImportPackageList() {
        return dynamicImportPackageList;
    }


    public String getBodtoConvertInterface() {
        return bodtoConvertInterface;
    }

    public void setBodtoConvertInterface(String bodtoConvertInterface) {
        this.bodtoConvertInterface = bodtoConvertInterface;
    }

    public String getBodoConvertInterface() {
        return bodoConvertInterface;
    }

    public void setBodoConvertInterface(String bodoConvertInterface) {
        this.bodoConvertInterface = bodoConvertInterface;
    }

    public String getBovoConvertInterface() {
        return bovoConvertInterface;
    }

    public void setBovoConvertInterface(String bovoConvertInterface) {
        this.bovoConvertInterface = bovoConvertInterface;
    }

    public boolean isDerived() {
        return isDerived;
    }

    public void setDerived(boolean derived) {
        isDerived = derived;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getPlantUMLPackage() {

        return plantUMLPackage;
    }

    public void setPlantUMLPackage(String plantUMLPackage) {
        this.plantUMLPackage = plantUMLPackage;
    }

    public String getRelationClassStr() {
        return relationClassStr;
    }

    public void setRelationClassStr(String relationClassStr) {
        this.relationClassStr = relationClassStr;
    }

    public InterfaceBean getImplInterfaceBean() {
        return implInterfaceBean;
    }

    public void setImplInterfaceBean(InterfaceBean implInterfaceBean) {
        this.implInterfaceBean = implInterfaceBean;
    }


    public List<MethodBean> getMethodBeanList() {
        return methodBeanList;
    }

    public void setMethodBeanList(List<MethodBean> methodBeanList) {
        this.methodBeanList = methodBeanList;
    }

    public List<FieldBean> getFieldBeanList() {
        return fieldBeanList;
    }

    public void setFieldBeanList(List<FieldBean> fieldBeanList) {
        this.fieldBeanList = fieldBeanList;
    }

    public List<String> getImportClassList() {
        return importClassList;
    }

    public void setImportClassList(List<String> importClassList) {
        this.importClassList = importClassList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBelongModel() {
        return belongModel;
    }

    public void setBelongModel(String belongModel) {
        this.belongModel = belongModel;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }


    public List<String> getThisClassImportPackageList() {
        return thisClassImportPackageList;
    }

    public void setThisClassImportPackageList(List<String> thisClassImportPackageList) {
        this.thisClassImportPackageList = thisClassImportPackageList;
    }

    public List<String> getChildClassImportPackageList() {
        return childClassImportPackageList;
    }

    public void setChildClassImportPackageList(List<String> childClassImportPackageList) {
        this.childClassImportPackageList = childClassImportPackageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractClassBean that = (AbstractClassBean) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, packageName);
    }


    public void buildPlantUMLPackage(String plantUMLPackage){
        if(plantUMLPackage.contains("\"")){
            String content = plantUMLPackage.split("\"")[1];
            if(content.contains("-")){
                String [] arr = content.split("-");
                if(arr[1].contains(".")){
                    this.setPlantUMLPackage(arr[1].trim());
                    return;
                }
                if(arr[0].contains(".")){
                    this.setPlantUMLPackage(arr[0].trim());
                    return;
                }

            }
        }

        this.setPlantUMLPackage(plantUMLPackage.trim().trim());
    }

    /**
     * 合并引用包，
     * 接口的引用包与实现的引用包合并
     * @param importClassList
     */
    public void mergeImportClass(List<String> importClassList){
        if(CollectionUtils.isEmpty(importClassList)){
            return;
        }
        if(CollectionUtils.isEmpty(this.getImportClassList())){
            this.setImportClassList(importClassList);
        }else {
            Set<String> newHashSet = Sets.newHashSet(importClassList);
            for (String importClassName : this.getImportClassList()){
                newHashSet.remove(importClassName);
            }
            this.getImportClassList().addAll(Lists.newArrayList(newHashSet));
        }
    }
    /**
     * 构建简化版的field,去除扩展字段属性内容，对dto,vo屏蔽bo属性之间的关联关系，但是bo本身保留
     * @return
     */
    public List<FieldBean> buildSimpleFieldList(){

        List<FieldBean> fieldBeanList = new ArrayList<>();
        if(CollectionUtils.isEmpty(this.getFieldBeanList())){
            return fieldBeanList;
        }
        for (FieldBean oldBean : this.getFieldBeanList()){
            fieldBeanList.add(oldBean.copySelf());
        }

        //dto没有bo那么多的丰富信息，需要去掉
        List<FieldBean> newFieldBeanList = fieldBeanList.stream().filter(fieldBean ->
                !fieldBean.getFieldName().trim().toLowerCase().endsWith(DomainElementEnum.BO.getElement())
                        && fieldBean.isSimpleField()
                        && !fieldBean.getFieldName().trim().toLowerCase().contains(DomainElementEnum.BO.getElement()+">"))
                .collect(Collectors.toList());

        List<FieldBean> fieldBeanFilterList = fieldBeanList.stream().filter(fieldBean ->
                fieldBean.getFieldName().trim().toLowerCase().endsWith(DomainElementEnum.BO.getElement())
                        && fieldBean.isSimpleField()
                        || fieldBean.getFieldName().trim().toLowerCase().contains(DomainElementEnum.BO.getElement()+">"))
                .collect(Collectors.toList());

        fieldBeanFilterList.forEach(fieldBean -> {
            fieldBean.setVisibility("/** "+fieldBean.getVisibility());
            fieldBean.setFieldName(fieldBean.getFieldName()+" **/");
        });

        newFieldBeanList.addAll(fieldBeanFilterList);
        return newFieldBeanList;
    }


    /**
     * 在动态调用过程中匹配出调用者的调用方法和提供者的提供方法
     * 兼容写法
     * xxx.methodName invoke yyy.methodName
     * xxx.method(2) invoke yyy.method(3)
     * xxx.method(XxxBO, XxxEvent) invoke yyy.method(YyyDTO, YyyCmd)
     * xxx.method(XxxBO xxxBO, XxxEvent xxxEvent) invoke yyy.method(YyyDTO yyyDTO, YyyCmd yyyCmd)
     * @param currentMethod
     * @return
     */
    public Optional<MethodBean> getMatchMethodBean(String currentMethod){

        //如果没有方法参数则直接匹配
        if(!currentMethod.contains("(")){
            return  this.getMethodBeanList().stream().filter(methodBean1 -> methodBean1.getMethodName().toLowerCase().startsWith(currentMethod.toLowerCase()+"(")).findFirst();
        }

        String methodName = currentMethod.split("\\(")[0];
        String currentMethodTag = currentMethod+"(";
        String paramCountStr = currentMethod.replace(methodName,"").trim();
        //判断是否是xxx.method(2)这种写法
        int count = 0;
        if(paramCountStr.length() == 3){
            String paramCount = paramCountStr.replace("(","").replace(")","");
            count = Integer.parseInt(paramCount);
        }

        //方法名一样，参数数量不同
        if(count > 0){
            int finalCount = count;
            return  this.getMethodBeanList().stream()
                    .filter(methodBean1 -> methodBean1.getMethodName().toLowerCase().startsWith(currentMethodTag)
                        && methodBean1.getParamArr().length == finalCount
                    )
                    .findFirst();
        }


        //通过参数类型进行匹配
        String [] currentParamArr = currentMethod.split("\\(")[1].split(",");

        AtomicReference<MethodBean> matchResultBean = new AtomicReference<>();
        this.getMethodBeanList().stream().forEach(
                methodBean -> {
                    if(methodBean.getMethodName().toLowerCase().startsWith(currentMethodTag)){
                        String [] paramArr = methodBean.getParamArr();
                        for (int i = 0;i < paramArr.length;i ++){
                            for (int j = 0;j < currentParamArr.length;j ++){
                                String currentParamType = currentParamArr[j].trim().split(" ")[0];
                                String paramType = paramArr[i].trim().split(" ")[0];
                                if(currentParamType.equals(paramType.toLowerCase())){
                                    matchResultBean.set(methodBean);
                                }
                            }
                        }
                    }
                }
        );
        if(matchResultBean.get() == null){
            return  Optional.empty();
        }

        return Optional.of(matchResultBean.get());
    }


    /**
     * 如果匹配不到，则将plantUML调用时序图中的方法注册到当前类中，做动态扩展
     * @param currentMethod
     * @return
     */
    public MethodBean getOrAddMethodBean(String currentMethod){
        Optional<MethodBean> methodBeanOptional = getMatchMethodBean(currentMethod);
        if(methodBeanOptional.isPresent()){
            return methodBeanOptional.get();
        }
        if(!currentMethod.contains("(")){
            currentMethod = currentMethod +"()";
        }

        MethodBean methodBean = new MethodBean();
        methodBean.setDesc("");
        methodBean.setMethodName(currentMethod);
        methodBean.buildParamArr();
        methodBean.setReturnClass("void");
        methodBean.setVisibility("public");
        this.getMethodBeanList().add(methodBean);
        return methodBean;
    }


    /**
     * 根据调用时序的调用内容判断调用方和被调用方是不是当前类
     * @param contentArr
     * @return
     */
    public boolean checkMatchClass(String [] contentArr){
        for (String content : contentArr){
            if(content.trim().toLowerCase().endsWith(this.getClassName().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * 将classBean转换为WrteContentBean为后续的写文件做准备
     * @param content
     * @param templateFileEnum
     * @return
     */
    public WriteContentBean buildWriteContentBean(String content, TemplateFileEnum templateFileEnum){
        return WriteContentBean.builder().content(content)
                .templateName(templateFileEnum.getTempFileName())
                .humpClassName(this.getClassName())
                .classPackageName(this.getPackageName())
                .build();
    }


    /**
     * 将classBean转换为WrteContentBean为后续的写文件做准备
     * @param content
     * @param templateFileCode
     * @return
     */
    public WriteContentBean buildWriteContentBeanV2(String content, String templateFileCode, boolean custom){
        return WriteContentBean.builder().content(content)
                .templateName(templateFileCode)
                .customCodeElement(custom)
                .humpClassName(this.getClassName())
                .classPackageName(this.getPackageName())
                .build();
    }


    /**
     * 由当前对象派生到新对象
     * @param className
     * @param plantUMLPackage
     * @param fieldBeanList
     * @return
     */
    public ClassBean derivedNewClassBean(String className,String plantUMLPackage,List<FieldBean> fieldBeanList){
        ClassBean modelClassBean = new ClassBean();
        modelClassBean.setClassName(className);
        modelClassBean.setFieldBeanList(fieldBeanList);
        modelClassBean.setClassDesc(this.getClassDesc());
        modelClassBean.setContext(this.getContext());
        modelClassBean.setMethodBeanList(Lists.newArrayList());
        modelClassBean.setPlantUMLPackage(plantUMLPackage);
        modelClassBean.setDerivedChainClassList(Lists.newArrayList(this.getClassName()));
        return modelClassBean;
    }

    /**
     * 给方法增加导入类
     * @param classPackageName
     */
    public void addImportClass(String classPackageName){
        if(CollectionUtils.isEmpty(this.getImportClassList())){
            this.setImportClassList(Lists.newArrayList(classPackageName));
            return;
        }
        if(!this.getImportClassList().contains(classPackageName)){
            this.getImportClassList().add(classPackageName);
        }
    }

    /**
     * 给方法增加导入类
     * @param importClassList
     */
    public void addImportClassBatch(List<String> importClassList){
        if(CollectionUtils.isEmpty(importClassList)){
            return;
        }
        importClassList.stream().forEach(this::addImportClass);
    }

    public void addDynamicImportClass(String packageName){
        if(this.getDynamicImportPackageList().contains(packageName)){
           return;
        }
        this.getDynamicImportPackageList().add(packageName);
    }


    public void addThisImportClass(String packageName){
        if(this.getThisClassImportPackageList().contains(packageName)){
            return;
        }
        this.getThisClassImportPackageList().add(packageName);
    }


    public void addChildImportClass(String packageName){
        if(this.getChildClassImportPackageList().contains(packageName)){
            return;
        }
        this.getChildClassImportPackageList().add(packageName);
    }



    /**
     * 为class增加fieldBean,根据名称判断是否存在，已存在则忽略
     * @param fieldBean
     */
    public void addField(FieldBean fieldBean){
        if (CollectionUtils.isEmpty(this.getFieldBeanList())){
            this.setFieldBeanList(new ArrayList<>());
        }
        Optional<FieldBean> fieldBeanOptional = this.getFieldBeanList().stream().filter(fieldBean1 -> fieldBean1.getSimpleName().equals(fieldBean.getSimpleName())).findFirst();
        if(fieldBeanOptional.isPresent()){
            return;
        }
        this.getFieldBeanList().add(fieldBean);
    }

    /**
     * 增加代码段
     * @param segmentBean
     */
    public void addCodeSegment(SegmentBean segmentBean){
        this.codeSegmentBeanList.add(segmentBean);
    }

}
