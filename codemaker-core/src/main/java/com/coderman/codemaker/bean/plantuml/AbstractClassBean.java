package com.coderman.codemaker.bean.plantuml;

import com.coderman.codemaker.enums.DomainElementEnum;
import com.coderman.codemaker.utils.StringHelperUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 * 抽象plantuml 类
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public abstract class AbstractClassBean {
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


    private String bodtoConvertInterface;

    private String bodoConvertInterface;

    private String bovoConvertInterface;

    /**
     * 在动态调用中产生的引用包
     */
    private List<String> dynamicImportPackageList;


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

    public void setDynamicImportPackageList(List<String> dynamicImportPackageList) {
        this.dynamicImportPackageList = dynamicImportPackageList;
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
     * 如果是bo类的情况下可以通过bo类找到对应的DO类名
     * @return
     */
    public String getDOClassFromTableKey(){
        String tableKey = this.getExtendFieldBean().getTableKey();
        if(StringUtils.isEmpty(tableKey)){
            return null;
        }
        return StringHelperUtils.getClassDOName(tableKey);
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
                        && !fieldBean.isTableKey()
                        && !fieldBean.isDtoKey()
                        && !fieldBean.isInvokeFileKey()
                        && !fieldBean.getFieldName().trim().toLowerCase().contains(DomainElementEnum.BO.getElement()+">"))
                .collect(Collectors.toList());

        List<FieldBean> fieldBeanFilterList = fieldBeanList.stream().filter(fieldBean ->
                fieldBean.getFieldName().trim().toLowerCase().endsWith(DomainElementEnum.BO.getElement())
                        && !fieldBean.isTableKey()
                        && !fieldBean.isDtoKey()
                        && !fieldBean.isInvokeFileKey()
                        || fieldBean.getFieldName().trim().toLowerCase().contains(DomainElementEnum.BO.getElement()+">"))
                .collect(Collectors.toList());

        fieldBeanFilterList.forEach(fieldBean -> {
            fieldBean.setVisibility("/** "+fieldBean.getVisibility());
            fieldBean.setFieldName(fieldBean.getFieldName()+" **/");
        });

        newFieldBeanList.addAll(fieldBeanFilterList);
        return newFieldBeanList;
    }
}
