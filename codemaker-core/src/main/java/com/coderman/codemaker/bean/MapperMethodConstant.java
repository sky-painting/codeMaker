package com.coderman.codemaker.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: mapperxml默认方法名
 * date: 2021/10/27
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class MapperMethodConstant {
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String GET_BYID = "getById";
    public static final String GET_ALL = "getAll";
    public static final String DELETE_BYID = "deleteById";
    public static Set<String> defaultMethodSet(){
        Set<String> set = new HashSet<>();
        set.add(INSERT);
        set.add(UPDATE);
        set.add(DELETE_BYID);
        set.add(GET_ALL);
        set.add(GET_BYID);

        return set;
    }
}
