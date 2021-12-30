package com.coderman.codemaker.classloader;

import com.coderman.codemaker.bean.plantuml.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Description:
 * date: 2021/11/23
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class CompClassFactory {


    /**
     * 批量转换
     * @param classList
     * @param compName
     * @return
     */
    public List<ClassBean> convert2ClassBean(List<Class<?>> classList,String compName){
        if(CollectionUtils.isEmpty(classList)){
            return new ArrayList<>();
        }
        List<ClassBean> list = new ArrayList<>();
        for (Class<?> clazz : classList){
            list.add(buildClassBean(clazz,compName));
        }
        return list;
    }


    /**
     * 批量转换
     * @param classList
     * @return
     */
    public List<InterfaceBean> convert2InterfaceBean(List<Class<?>> classList,String compName){
        if(CollectionUtils.isEmpty(classList)){
            return new ArrayList<>();
        }
        List<InterfaceBean> list = new ArrayList<>();
        for (Class<?> clazz : classList){
            list.add(buildInterfaceBean(clazz,compName));
        }
        return list;
    }

    /**
     * 通过类加载将类的信息转换到代码模型上
     * @param clazz
     * @return
     */
    private ClassBean buildClassBean(Class clazz,String compName){
        ClassBean classBean = new ClassBean();
        classBean.setPackageName(clazz.getCanonicalName());
        classBean.setClassName(clazz.getSimpleName());
        classBean.setCompName(compName);
        classBean.setFieldBeanList(buildFieldBeanList(clazz.getDeclaredFields()));
        classBean.setMethodBeanList(buildMethodBeanList(clazz.getDeclaredMethods()));
        return classBean;
    }

    /**
     * 通过类加载将类的信息转换到代码模型上
     * @param clazz
     * @return
     */
    private InterfaceBean buildInterfaceBean(Class clazz,String compName){
        InterfaceBean interfaceBean = new InterfaceBean();
        interfaceBean.setPackageName(clazz.getPackage().getName());
        interfaceBean.setClassName(clazz.getSimpleName());
        interfaceBean.setCompName(compName);
        interfaceBean.setFieldBeanList(buildFieldBeanList(clazz.getDeclaredFields()));

        interfaceBean.setMethodBeanList(buildMethodBeanList(clazz.getDeclaredMethods()));

        return interfaceBean;
    }



    /**
     * 构建属性模型
     * @param fields
     * @return
     */
    private List<FieldBean> buildFieldBeanList(Field[] fields){
        List<FieldBean> fieldBeanList = new ArrayList<>();
        for (Field field : fields){
            FieldBean fieldBean = new FieldBean();
            fieldBean.setFieldSimpleName(field.getName());
            fieldBean.setFieldType(field.getType().getName());
            fieldBean.setFieldName(field.getType().getCanonicalName() +" "+field.getName());
            fieldBeanList.add(fieldBean);
        }
        return fieldBeanList;
    }


    /**
     * 构建方法模型
     * @param methods
     * @return
     */
    private List<MethodBean> buildMethodBeanList(Method [] methods){
        List<MethodBean> methodBeanList = new ArrayList<>();
        for (Method method : methods){
            MethodBean methodBean = new MethodBean();
            String paramStr = buildSimpleMethodParam(method);
            if(!StringUtils.isEmpty(paramStr)){
                methodBean.setMethodName(method.getName()+"("+paramStr+")");
            }else {
                methodBean.setMethodName(method.getName());
            }
            if(method.getGenericReturnType() != null){
                methodBean.setGenericReturnType(method.getGenericReturnType().getTypeName());
            }
            methodBean.setReturnClass(buildSimpleReturnClass(methodBean.getGenericReturnType()));
            methodBean.setClassName(method.getDeclaringClass().getSimpleName());
            methodBean.setReturnClassPackage(method.getReturnType().getCanonicalName());
            methodBean.buildParamArr();
            if (Modifier.isStatic(method.getModifiers())) {
                methodBean.setStatic(true);
            }
            methodBean.setGenericParamList(getParamList(method));
            methodBeanList.add(methodBean);
        }
        return methodBeanList;
    }



    /**
     * 构建注解模型
     * @param annotationList
     * @return
     */
    public List<AnnotationBean> convert2AnnotationBean(List<String> annotationList){
        List<AnnotationBean> annotationBeanList = new ArrayList<>();
        for (String annotation : annotationList){
            AnnotationBean annotationBean = new AnnotationBean();
            annotationBean.setPackageName(annotation);
            annotationBean.setClassName(annotation.substring(annotation.lastIndexOf(".")+1));
            annotationBeanList.add(annotationBean);
        }

        return annotationBeanList;
    }

    /**
     * 构建注解模型
     * @param annotationList
     * @return
     */
    public List<AnnotationBean> convert2AnnotationBeanClass(List<Class<?>> annotationList){
        List<AnnotationBean> annotationBeanList = new ArrayList<>();
        for (Class<?> annotation : annotationList){
            AnnotationBean annotationBean = new AnnotationBean();
            annotationBean.setPackageName(annotation.getPackage().getName());
            annotationBean.setClassName(annotation.getSimpleName());
            annotationBeanList.add(annotationBean);
        }

        return annotationBeanList;
    }



    /**
     * 根据反射构建简单方法名词
     * @param method
     * @return
     */
    private String buildSimpleMethodParam(Method method){
        // 获取参数，可能是多个，所以是数组
        Type[] parameterTypes = method.getGenericParameterTypes();
        if(parameterTypes == null){
            return null;
        }
        List<String> simpleParamList = new ArrayList<>();
        for (Type paramType : parameterTypes) {
            if(paramType == null || StringUtils.isEmpty(paramType.getTypeName())){
                continue;
            }
            if(paramType.getTypeName().contains(".")){
                String [] paramArr = paramType.getTypeName().split("\\.");
                List<String> paramList = new ArrayList<>();

                String pattern = "^[A-Z].*?";
                for (String param : paramArr){
                    boolean match = Pattern.matches(pattern, param);
                    if(match){
                        paramList.add(param.replace("<","").replace(">",""));
                    }
                }

                String paramStr = buildSimpleParam(paramList);

                simpleParamList.add(paramStr);
            }else {
                simpleParamList.add(paramType.getTypeName()+" "+paramType.getTypeName().substring(0,1).toLowerCase());
            }
        }
        if(simpleParamList.size() == 1){
            return simpleParamList.get(0);
        }
        return StringUtils.join(simpleParamList,", ");
    }


    /**
     * 构建方法的每个参数
     * @param list
     * @return
     */
    private String buildSimpleParam(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        //简单参数类型，没有范型符号或者不是集合类数据
        if(list.size() == 1){
            return list.get(0) + " " + list.get(0).substring(0,1).toLowerCase()+list.get(0).substring(1);
        }
        String first = list.get(0);
        if(list.size() == 2 && first.endsWith("List")){
            return list.get(0)+"<"+list.get(1)+">" + " list";
        }
        if(list.size() == 2 && first.endsWith("Set")){
            return list.get(0) + "<" + list.get(1)+">"+" set";
        }
        if(list.size() == 3 && first.endsWith("Map")){
            return list.get(0) + "<" + list.get(1)+","+list.get(2)+">"+" map";
        }
        return null;
    }


    /**
     * 从范型中构建精简的返回类型
     * @param genericReturnType
     * @return
     */
    private String buildSimpleReturnClass(String genericReturnType){
        if(StringUtils.isEmpty(genericReturnType) || genericReturnType.equals("void")){
            return "void";
        }
        String [] paramArr = genericReturnType.split("\\.");
        List<String> paramList = new ArrayList<>();

        String pattern = "^[A-Z].*?";
        for (String param : paramArr){
            boolean match = Pattern.matches(pattern, param);
            if(match){
                if(param.contains("<")){
                    param = param.split("<")[0]+"<";
                }
                paramList.add(param);
            }
        }
        return StringUtils.join(paramList,"");
    }

    /**
     * 获取组件方法的范型参数列表
     * @param method
     * @return
     */
    private List<String> getParamList(Method method) {
        List<String> paramList = new ArrayList<>();
        Type[] types = method.getGenericParameterTypes();// 获取参数，可能是多个，所以是数组
        if(types == null){
            return new ArrayList<>();
        }
        for (Type type : types) {
            if(type == null || StringUtils.isEmpty(type.getTypeName())){
                continue;
            }
            paramList.add(type.getTypeName());
        }
        return paramList;
    }

}
