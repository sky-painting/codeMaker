package com.tianhua.codemaker.app.dynamicddd;

import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;


/**
 * Description:动态方法工厂
 * date: 2021/11/1
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class MethodBeanFactory {
    private List<String> do2boList = Lists.newArrayList("do2bo", "DO2DO", "do2Bo", "Do2Bo");
    private List<String> vos2bosList = Lists.newArrayList("VOs2BOs", "voList2boList", "volist2boList", "voList2BoList","vos2bos","volist2bolist","vos2BOs");

    private List<String> bos2vosList = Lists.newArrayList("BOs2VOs", "boList2voList", "bolist2voList", "boList2VoList","bos2vos","boList2VOList","bos2VOs");



    private SecureRandom random = new SecureRandom();
    public MethodBean buildDO2BO(ClassBean classBean,String classDOName){
        MethodBean do2bo = new MethodBean();
        String boClassName = classBean.getClassName();
        String varClassName = classDOName.substring(0, 1).toLowerCase().concat(classDOName.substring(1));
        String randomMethodName = do2boList.get(Math.abs(random.nextInt(do2boList.size())));

        do2bo.setMethodName(randomMethodName + "(" + classDOName + " " + varClassName + ")");
        do2bo.setReturnClass(boClassName);
        return do2bo;
    }

    public MethodBean buildBO2DO(ClassBean classBean,String boClassName,String classDOName){
        MethodBean bo2do = new MethodBean();
        String varClassName = boClassName.substring(0, 1).toLowerCase().concat(classBean.getClassName().substring(1));
        bo2do.setMethodName("bo2do(" + boClassName + " " + varClassName + ")");
        bo2do.setReturnClass(classDOName);
        return bo2do;
    }


    public MethodBean buildDOList2BOList(String classDOName,String varClassName,String boClassName){
        MethodBean doList2boList = new MethodBean();
        doList2boList.setMethodName("doList2boList(List<" + classDOName + "> " + varClassName + "List)");
        doList2boList.setReturnClass("List<" + boClassName + ">");
        return doList2boList;
    }

    public MethodBean buildBOList2DOList(String classDOName,String varClassName,String boClassName){
        MethodBean boList2doList = new MethodBean();
        boList2doList.setMethodName("boList2doList(List<" + boClassName + "> " + varClassName + "List)");
        boList2doList.setReturnClass("List<" + classDOName + ">");
        return boList2doList;
    }


    public String getvolist2boListMethod(int index){
        if(index >= vos2bosList.size()){
            return vos2bosList.get(vos2bosList.size()-1)+"V"+(index - vos2bosList.size());
        }
        return vos2bosList.get(index);
    }

    public String getbolist2voListMethod(int index){
        if(index >= bos2vosList.size()){
            return bos2vosList.get(bos2vosList.size()-1)+"V"+(index - bos2vosList.size());
        }
        return bos2vosList.get(index);
    }
}
