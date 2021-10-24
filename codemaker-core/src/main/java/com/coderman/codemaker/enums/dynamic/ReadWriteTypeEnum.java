package com.coderman.codemaker.enums.dynamic;

import org.apache.commons.lang3.StringUtils;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description:读写类型枚举
 * date: 2021/10/16
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum  ReadWriteTypeEnum {
    READ("read","读场景"),
    WRITE("write","写场景")
    ;
    private String code;
    private String desc;
    private static Set<String> readSet = Sets.newSet("search","get","find","select","load","read");

    private static Set<String> writeSet = Sets.newSet("write","regist","update","create","save","insert","delete","init");

    ReadWriteTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    /**
     * 根据方法名判断方法的读写属性
     * @param methodName
     * @return
     */
    public static String getCodeByMethod(String methodName){
        AtomicReference<String> code = new AtomicReference<>("");
        readSet.stream().forEach(v->{
            if(methodName.startsWith(v)){
                code.set(ReadWriteTypeEnum.READ.getCode());
            }
        });
        if(StringUtils.isNotEmpty(code.get())){
            return code.get();
        }
        writeSet.stream().forEach(v->{
            if(methodName.startsWith(v)){
                code.set(ReadWriteTypeEnum.WRITE.getCode());
            }
        });
        return code.get();
    }

}
