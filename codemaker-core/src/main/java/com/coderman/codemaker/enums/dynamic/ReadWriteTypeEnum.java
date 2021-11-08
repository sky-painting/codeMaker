package com.coderman.codemaker.enums.dynamic;

import org.apache.commons.lang3.StringUtils;
import org.mockito.internal.util.collections.Sets;

import java.util.Locale;
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
    WRITE("write","写场景"),
    INSERT("insert","插入"),
    UPDATE("update","更新"),
    DELETE("delete","删除"),
    ;
    private String code;
    private String desc;

    //支持业务领域动词注册功能
    private static Set<String> readSet = Sets.newSet("search","get","find","select","load","read","query","export");

    private static Set<String> writeSet = Sets.newSet("write","regist","update","create","save","insert","delete","init","import");


    private static Set<String> insertSet = Sets.newSet("batchinsert","insertbatch","savebatch","save","batchsave");

    private static Set<String> updateSet = Sets.newSet("batchupdate","updatebatch","update");

    private static Set<String> deleteSet = Sets.newSet("deletebatch","batchdelete","update");

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
            if(methodName.toLowerCase().startsWith(v)){
                code.set(ReadWriteTypeEnum.READ.getCode());
            }
        });
        if(StringUtils.isNotEmpty(code.get())){
            return code.get();
        }
        writeSet.stream().forEach(v->{
            if(methodName.toLowerCase().startsWith(v)){
                code.set(ReadWriteTypeEnum.WRITE.getCode());
            }
        });
        return code.get();
    }


    /**
     * 判断是否是保存
     * @param methodName
     * @return
     */
    public static boolean isInsert(String methodName){
        AtomicReference<Boolean> insertTag = new AtomicReference<>(false);
        insertSet.stream().forEach(v->{
            if(methodName.toLowerCase().startsWith(v)){
                insertTag.set(true);
            }
        });
        return insertTag.get();
    }

    /**
     * 判断是否是更新
     * @param methodName
     * @return
     */
    public static boolean isUpdate(String methodName){
        AtomicReference<Boolean> insertTag = new AtomicReference<>(false);
        updateSet.stream().forEach(v->{
            if(methodName.toLowerCase().startsWith(v)){
                insertTag.set(true);
            }
        });
        return insertTag.get();
    }

    /**
     * 判断是否是删除
     * @param methodName
     * @return
     */
    public static boolean isDelete(String methodName){
        AtomicReference<Boolean> insertTag = new AtomicReference<>(false);
        deleteSet.stream().forEach(v->{
            if(methodName.toLowerCase().startsWith(v)){
                insertTag.set(true);
            }
        });
        return insertTag.get();
    }

    /**
     * 根据方法名推导出可能的属性
     * 先对 read场景推导
     * @return
     */
    public static String getSomeFieldFromMethodName(String methodName){
        final String[] tempMethodName = {methodName};
        readSet.forEach(read->{
            tempMethodName[0] = tempMethodName[0].replace(read,"");
        });
        String fieldName = tempMethodName[0].replace("By","");
        if(!StringUtils.isEmpty(fieldName)){
            return fieldName;
        }
        return null;
    }
}
