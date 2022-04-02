package com.tianhua.codemaker.service.expertest;

import com.tianhua.codemaker.bean.ClassContentBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Description:
 * date: 2021/12/18
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class Test3 {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        String paramStr = "java.util.List<com.tianhua.codemaker.bean.plantuml.ClassBean>";
        String [] paramArr = paramStr.split("\\.");
        List<String> paramList = new ArrayList<>();

        String pattern = "^[A-Z].*?";

        for (String param : paramArr){
            boolean match = Pattern.matches(pattern, param);
            if(match){
                paramList.add(param);
            }
        }

        if(paramList.size() == 1){
            System.out.println(paramList.get(0));
        }


    }
}
