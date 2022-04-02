package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.MapperMethodConstant;
import com.tianhua.codemaker.bean.dddelement.DynamicMapperElementBean;
import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "dynamicMapperElementHandler")
public class DynamicMapperElementHandler implements DomainElementHandler<DynamicMapperElementBean> {
    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public DynamicMapperElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        DynamicMapperElementBean dynamicMapperElementBean = new DynamicMapperElementBean();
        List<InterfaceBean> mapperInterfaceBeanList = new ArrayList<>();
        List<ClassBean> classBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k, v) -> {
            if (v.getClassName().toLowerCase().endsWith(DomainElementEnum.DYNAMIC_MAPPER.getElement())) {
                importPackageService.dealImportClass(v, plantUmlContextBean);
                v.getMethodBeanList().forEach(methodBean -> {
                    methodBean.buildDoc();
                    buildParamAnnotation(methodBean);
                    methodBean.setMethodName(methodBean.refreshMethodName());
                });
                mapperInterfaceBeanList.add(v);
                //这里把mapperxml当作class将动态增加的方法当作field，构建xml sql循环渲染
                ClassBean mapperXmlBean = new ClassBean();
                mapperXmlBean.setClassName(v.getClassName());
                mapperXmlBean.setTableBean(v.getTableBean());
                mapperXmlBean.setColumnBeanList(v.getColumnBeanList());
                mapperXmlBean.setPackageName(v.getPackageName());
                mapperXmlBean.setImportClassList(v.getImportClassList());
                if(appServiceConfig.containsMybatisPlus()){
                    mapperXmlBean.setFieldBeanList(Lists.newArrayList());
                }else {
                    mapperXmlBean.setFieldBeanList(convertToFieldBeanList(v.getMethodBeanList(), mapperXmlBean));
                }

                classBeanList.add(mapperXmlBean);
            }
        });
        dynamicMapperElementBean.setClassBeanList(classBeanList);
        dynamicMapperElementBean.setInterfaceBeanList(mapperInterfaceBeanList);
        return dynamicMapperElementBean;
    }

    /**
     * 获取plantuml动态调用时序图中的方法，不是内置的mapper方法
     *
     * @param methodBeanList
     * @return
     */
    private List<MethodBean> getInvokeSequenceMethod(List<MethodBean> methodBeanList) {
        Set<String> defaultMethodSet = MapperMethodConstant.defaultMethodSet();
        return methodBeanList.stream().filter(methodBean -> !defaultMethodSet.contains(methodBean.getSimplMethodName())).collect(Collectors.toList());
    }


    /**
     * 这里将mapper方法映射成class的fieldBean,构建sql xml
     *
     * @param methodBeanList
     * @param mapperXmlBean
     * @return
     */
    private List<FieldBean> convertToFieldBeanList(List<MethodBean> methodBeanList, ClassBean mapperXmlBean) {
        List<MethodBean> newMethodBeanList = getInvokeSequenceMethod(methodBeanList);
        List<FieldBean> fieldBeanList = new ArrayList<>();
        String tableName = mapperXmlBean.getTableBean().getTableName();
        Optional<String> doTypePackage = mapperXmlBean.getImportClassList().stream().filter(str -> str.endsWith("DO")).findFirst();
        String parameterTypeStr = tableName;
        if (doTypePackage.isPresent()) {
            parameterTypeStr = doTypePackage.get();
        }



        for (MethodBean methodBean : newMethodBeanList) {
            //判断method是curd哪种
            FieldBean fieldBean = new FieldBean();
            String rwType = ReadWriteTypeEnum.getCodeByMethod(methodBean.getMethodName().trim());
            //读场景-->查询
            if (ReadWriteTypeEnum.READ.getCode().equals(rwType)) {
                fieldBean.setFieldName(buildSelectSqlXml(methodBean.getSimplMethodName(), tableName));
                fieldBeanList.add(fieldBean);
                continue;
            }
            if (ReadWriteTypeEnum.isInsert(methodBean.getMethodName())) {
                fieldBean.setFieldName(buildInsertSqlXml(methodBean.getSimplMethodName(), tableName));
                fieldBeanList.add(fieldBean);
                continue;
            }
            if (ReadWriteTypeEnum.isUpdate(methodBean.getMethodName())) {
                fieldBean.setFieldName(buildUpdateSqlXml(methodBean.getSimplMethodName(), tableName, parameterTypeStr, mapperXmlBean.getColumnBeanList()));
                fieldBeanList.add(fieldBean);
                continue;
            }
            if (ReadWriteTypeEnum.isDelete(methodBean.getMethodName())) {
                fieldBean.setFieldName(buildDeleteSqlXml(methodBean.getSimplMethodName(), tableName));
                fieldBeanList.add(fieldBean);
                continue;
            }
        }
        List<FieldBean> fieldBeanList1 = convertDefaultSqlXmlFieldBeanList(mapperXmlBean);
        fieldBeanList.addAll(fieldBeanList1);
        return fieldBeanList;
    }

    /**
     * 构建查询sqlxml
     *
     * @param methodName
     * @return
     */
    private String buildSelectSqlXml(String methodName, String tableName) {
        String sqlTemplate = "    <select id=\"methodName\" resultMap=\"BaseResultMap\">\n" +
                "        select <include refid=\"Base_Column_List\" />  from tableName\n" +
                "    </select>";
        return sqlTemplate.replace("methodName", methodName).replace("tableName", tableName);
    }

    /**
     * 构建更新sqlxml
     *
     * @param methodName
     * @return
     */
    private String buildUpdateSqlXml(String methodName, String tableName, String parameterTypeStr, List<ColumnBean> columnBeanList) {
        StringBuilder sqlTemplate = new StringBuilder();
        sqlTemplate.append("    <update id=\"methodName\" parameterType=\"parameterTypeStr\">\n" +
                "        update tableName\n" +
                "        <set>\n");
        boolean containsDateUpdate = false;
        int c = 0;
        for (ColumnBean columnBean : columnBeanList){
            c++;
            if(columnBean.getColumnName().equals("id")){
                continue;
            }
            if(columnBean.getColumnName().equals("date_update")){
                containsDateUpdate = true;
                continue;
            }

            String sqlIf = "            <if test=\"columnField != null\">columnName = #{columnField},</if>\n";
            if(!containsDateUpdate && c == (columnBeanList.size()-1)){
                sqlIf = sqlIf.replace(",","");
            }
            sqlIf = sqlIf.replace("columnField",columnBean.getColumnFieldName()).replace("columnName",columnBean.getColumnName());
            sqlTemplate.append(sqlIf);
        }

        if(containsDateUpdate){
            sqlTemplate.append("            date_update = now()\n");
        }

        sqlTemplate.append( "        </set>\n");
        sqlTemplate.append("        where id = #{id}\n" +
                "    </update>");
        
        return sqlTemplate.toString().replace("methodName", methodName).replace("tableName", tableName).replace("parameterTypeStr", parameterTypeStr);
    }

    /**
     * 构建删除sqlxml
     *
     * @param methodName
     * @return
     */
    private String buildDeleteSqlXml(String methodName, String tableName) {
        String sqlTemplate = "    <delete id=\"methodName\" >\n" +
                "        delete from table.tableName where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>\n" +
                "    </delete>";
        return sqlTemplate.replace("methodName", methodName).replace("tableName", tableName);
    }


    /**
     * 构建插入sqlxml
     *
     * @param methodName
     * @return
     */
    private String buildInsertSqlXml(String methodName, String tableName) {
        String sqlTemplate = "    <insert id=\"methodName\" parameterType=\"${package}.dao.dataobject.${table.humpClassName}DO\">\n" +
                "        insert into tableName(\n" +
                "        ${table.insertColumnNames}\n" +
                "        )\n" +
                "        values(\n" +
                "        ${table.insertColumnNameList}\n" +
                "        )\n" +
                "    </insert>";
        return sqlTemplate.replace("methodName", methodName).replace("tableName", tableName);
    }

    /**
     * 动态构建方法参数注解
     * 这里是处理controller的注解
     *
     * @param methodBean
     */
    private void buildParamAnnotation(MethodBean methodBean) {
        if (methodBean.getParamArr() == null || methodBean.getParamArr().length == 0) {
            return;
        }

        for (int i = 0; i < methodBean.getParamArr().length; i++) {
            String value = methodBean.getParamArr()[i];
            if (value.contains(" ")) {
                value = value.split(" ")[1];
            } else {
                value = value.substring(0, 1).toLowerCase() + value.substring(1);
            }
            methodBean.addParamAnnotation("@Param(value = \"" + value + "\")",i);
        }
        return;
    }


    /**
     * 构建默认的xml sql
     * @param mapperXmlBean
     * @return
     */
    private List<FieldBean> convertDefaultSqlXmlFieldBeanList(ClassBean mapperXmlBean) {
        List<FieldBean> fieldBeanList = new ArrayList<>();
        String tableName = mapperXmlBean.getTableBean().getTableName();
        FieldBean insertFieldBean = new FieldBean();
        Optional<String> doTypePackage = mapperXmlBean.getImportClassList().stream().filter(str -> str.endsWith("DO")).findFirst();
        String parameterTypeStr = tableName;
        if (doTypePackage.isPresent()) {
            parameterTypeStr = doTypePackage.get();
        }
        mapperXmlBean.getTableBean().getInsertColumnNames();
        insertFieldBean.setFieldName(buildDefaultInsertSqlXml("insert",  mapperXmlBean.getTableBean(), parameterTypeStr));
        fieldBeanList.add(insertFieldBean);


        FieldBean deleteFieldBean = new FieldBean();
        deleteFieldBean.setFieldName(buildDefaultDeleteSqlXml("deleteById",tableName));
        fieldBeanList.add(deleteFieldBean);



        FieldBean updateFieldBean = new FieldBean();
        updateFieldBean.setFieldName(buildUpdateSqlXml("update",tableName,parameterTypeStr, mapperXmlBean.getColumnBeanList()));
        fieldBeanList.add(updateFieldBean);



        FieldBean getByIdFieldBean = new FieldBean();
        getByIdFieldBean.setFieldName(buildDefaultGetByIdSqlXml("getById",tableName));
        fieldBeanList.add(getByIdFieldBean);


        FieldBean getAllFieldBean = new FieldBean();
        getAllFieldBean.setFieldName(buildSelectSqlXml("getAll",tableName));
        fieldBeanList.add(getAllFieldBean);


        return fieldBeanList;
    }


    private String buildDefaultInsertSqlXml(String methodName, TableBean tableBean, String parameterTypeStr) {
        String sqlTemplate = "    <insert id=\"methodName\" parameterType=\"parameterTypeStr\">\n" +
                "        insert into tableName(\n" +
                "        ${table.insertColumnNames}\n" +
                "        )\n" +
                "        values(\n" +
                "        ${table.insertColumnNameList}\n" +
                "        )\n" +
                "    </insert>";
        return sqlTemplate.replace("methodName", methodName)
                .replace("tableName", tableBean.getTableName())
                .replace("${table.insertColumnNames}", tableBean.getInsertColumnNames())
                .replace("${table.insertColumnNameList}", tableBean.getInsertColumnNameList())
                .replace("parameterTypeStr",parameterTypeStr);

    }

    /**
     * 构建删除sqlxml
     *
     * @param methodName
     * @return
     */
    private String buildDefaultDeleteSqlXml(String methodName, String tableName) {
        String sqlTemplate = "    <delete id=\"methodName\" >\n" +
                "        delete from table.tableName where id = #{id}\n" +
                "    </delete>";
        return sqlTemplate.replace("methodName", methodName).replace("tableName", tableName);
    }


    /**
     * 构建查询sqlxml
     *
     * @param methodName
     * @return
     */
    private String buildDefaultGetByIdSqlXml(String methodName, String tableName) {
        String sqlTemplate = "    <select id=\"methodName\" resultMap=\"BaseResultMap\">\n" +
                "        select <include refid=\"Base_Column_List\" />  from tableName where id = #{id}\n" +
                "    </select>";
        return sqlTemplate.replace("methodName", methodName).replace("tableName", tableName);
    }

}
