package com.coderman.codemaker.bean;

/**
 * Description:
 * date: 2021/7/12
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class GlobalConstant {
    /**
     * plantuml bo中的特殊属性-标示bo-table的对应关系
     */
    public static final String TABLE_KEY = "tablekey";

    /**
     * plantuml bo中的特殊属性-标示dto别名
     */
    public static final String DTO_KEY_LIST = "dtokeylist";

    /**
     * plantuml bo中的特殊属性-标示facade接口名称
     */
    public static final String FACADE_KEY = "facadekey";

    /**
     * plantuml bo中的特殊属性-标示vo类名
     */
    public static final String VO_KEY_LIST = "vokeylist";

    /**
     * plantuml bo中的特殊属性-标示controller类名
     */
    public static final String CONTROLLER_KEY = "controllerkey";


    /**
     * plantuml enum中的特殊属性-标示enum要复制到rpc client模块中
     */
    public static final String COPY2RPC_CLIENT_KEY = "torpcclient";


    /**
     * plantuml invoke动态调用流程图的链路标示
     */
    public static final String INVOKE_TAG = "invoke";



    /**
     * plantuml bo中的特殊属性-标示BO对应的业务方法调用流程
     */
    public static final String INVOKE_FILE_KEY = "invokefilekey";


    /**
     * 调用时序文档存储文件夹名称
     */
    public static final String INVOKE_SEQUENCE_FILE_DIR = "invoke-plantuml";


    /**
     * 领域类图模型文档存储文件夹名称
     */
    public static final String DOMAIN_MODLE_FILE_DIR = "ddd-plantuml";


    /**
     * 以!开头的则忽略解析
     */
    public static final String PLANT_DOC_IGNORE = "!";

}
