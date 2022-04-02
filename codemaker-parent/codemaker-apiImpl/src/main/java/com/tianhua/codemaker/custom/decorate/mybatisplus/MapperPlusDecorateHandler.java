package com.tianhua.codemaker.custom.decorate.mybatisplus;

import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.element.ElementDecorateHandler;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:适配mybatis-plus
 * 将mapper接口增加父类BaseMapper
 * date: 2022/3/1
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "mapperplusDecorateHandler")
public class MapperPlusDecorateHandler implements ElementDecorateHandler {
    private static final String BASE_MAPPER = "BaseMapper";

    @Override
    public void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean) {
        AbstractClassBean baseMapperBean = classCompMap.get(BASE_MAPPER);
        if (baseMapperBean != null) {
            decorateMapper(baseMapperBean, plantUmlContextBean);
        }
    }

    /**
     * 装饰mapper
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    private void decorateMapper(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean) {
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if (k.toLowerCase().endsWith(TemplateFileEnum.MAPPER.getTempFileName())) {
                ClassBean doClass = plantUmlContextBean.getClassBeanMap().get(k.replace("Mapper","") + "DO");
                if(doClass != null){
                    v.setRelationClassStr(" extends BaseMapper<" + doClass.getClassName() + ">");
                    v.addImportClass(abstractClassBean.getPackageName()+".BaseMapper");
                    //清空mapper方法
                    v.setMethodBeanList(Lists.newArrayList());


                    List<MethodBean> superMethodList = new ArrayList<>();
                    superMethodList.add(getInsertMethod());
                    superMethodList.add(getSelectByIdMethod());
                    superMethodList.add(getUpdateByIdMethod());
                    v.setSuperMethodBeanList(superMethodList);
                }
            }
        });
    }


    private MethodBean getInsertMethod(){
        MethodBean methodBean = new MethodBean();
        methodBean.setMethodName("insert(T entity)");
        methodBean.setReturnClass("Integer");
        methodBean.setDesc("插入一条记录");
        methodBean.setVisibility("public");
        methodBean.buildParamArr();
        List<ParamBean> paramBeanList = new ArrayList<>();
        paramBeanList.add(ParamBean.getInstance("T","entity",true));
        methodBean.setParamBeanList(paramBeanList);
        return methodBean;
    }

    private MethodBean getUpdateByIdMethod(){
        MethodBean methodBean = new MethodBean();
        methodBean.setMethodName("updateById(T entity)");
        methodBean.setReturnClass("Integer");
        methodBean.setDesc("修改一条记录");
        methodBean.setVisibility("public");
        methodBean.buildParamArr();
        List<ParamBean> paramBeanList = new ArrayList<>();
        paramBeanList.add(ParamBean.getInstance("T","entity",true));
        methodBean.setParamBeanList(paramBeanList);
        return methodBean;
    }
    private MethodBean getSelectByIdMethod(){
        MethodBean methodBean = new MethodBean();
        methodBean.setMethodName("T selectById(Serializable id)");
        methodBean.setReturnClass("T");
        methodBean.setGenericReturnType("T");
        methodBean.setDesc("查询一条记录");
        methodBean.setVisibility("public");
        methodBean.buildParamArr();
        List<ParamBean> paramBeanList = new ArrayList<>();
        paramBeanList.add(ParamBean.getInstance("Serializable","id",true));
        methodBean.setParamBeanList(paramBeanList);
        return methodBean;
    }


}
