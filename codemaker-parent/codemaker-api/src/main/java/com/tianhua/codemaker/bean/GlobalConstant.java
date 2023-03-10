package com.tianhua.codemaker.bean;

/**
 * Description:
 * date: 2021/7/12
 *
 * @author shenshuai
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
     * plantuml bo中的特殊属性-标示BO对象是哪个上下文的，帮助按上下文分包
     */
    public static final String CONTEXT_KEY = "contextkey";



    /**
     * plantuml bo中的特殊属性-标示BO对象的派生查询dto
     */
    public static final String QUERY_DTO_KEY = "querydtokey";



    /**
     * plantuml bo中的特殊属性-标示BO对象的派生查询vo
     */
    public static final String QUERY_VO_KEY = "queryvokey";


    /**
     * plantuml bo中的特殊属性-标示BO对象需要导出参数到下游哪个服务的哪个接口做防腐适配
     */
    public static final String EXPORT_ACL_KEY = "exportaclkey";

    /**
     * plantuml bo中的特殊属性-标示 api参数是否需要校验
     */
    public static final String  VALIDATE_KEY = "validatekey";




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


    /**
     * api doc文件名称
     */
    public static final String API_DOC = "api-doc";
    /**
     * plantuml doc文件名称
     */
    public static final String PLANT_UML_DOC = "uml-doc";

    public static final String PACKAGE_$ = "${package}";

    /**
     * 存放工具类 独立类的文件目录名
     */
    public static final String SINGLE_CLASS_COMMON = "singleClass";

    /**
     * 存放配置文件模板的文件目录名
     */
    public static final String CONFIG = "config";
    /**
     * 组件配置文件key--version
     */
    public static final String COMP_VERSION="version";

    /**
     * 组件配置文件key--groupId
     */
    public static final String COMP_GROUPID="groupId";

    /**
     * 组件配置文件key--artifactId
     */
    public static final String COMP_ARTIFACTID="artifactId";


    /**
     * 组件包扫描的包名称
     */
    public static final String COMP_PACKAGE = "packageName";

    /**
     * 组件代表的服务别名
     */
    public static final String COMP_SERVICE_NAME = "serviceName";


    /**
     * 组件官网
     */
    public static final String COMP_SITE = "compSite";

    /**
     * 组件简述
     */
    public static final String COMP_DESC = "compDesc";

    /**
     * pom类型
     */
    public static final String COMP_GAV_TYPE = "type";
    /**
     * pom类型
     */
    public static final String COMP_GAV_SCOPE = "scope";

    /**
     * 组件类型
     */
    public static final String COMP_TYPE = "compType";

    /**
     * 独立类-类型
     */
    public static final String COMP_SINGLE_CLASS_TYPE = "classType";

    /**
     * 需要扫描的类列表前缀
     */
    public static final String COMP_CLASS_PRE = "class";


    /**
     * 需要扫描的接口列表前缀
     */
    public static final String COMP_INTERFACE_PRE = "interface";

    /**
     * 需要扫描的枚举列表前缀
     */
    public static final String COMP_ENUM_PRE = "enum";

    /**
     * 需要扫描的方法列表前缀
     */
    public static final String COMP_CLASS_METHOD_PRE = "method";
    /**
     * 需要扫描的配置文件内容
     */
    public static final String COMP_CONFIG_NAME = "configName";

    /**
     * 需要扫描的排除GA
     */
    public static final String COMP_EXCLUSION_GA_LIST = "exclusionGAList";
    /**
     * 需要扫描的方法列表前缀
     */
    public static final String COMP_FIELD_PRE = "field";


    /**
     * 需要扫描的注解列表前缀
     */
    public static final String COMP_ANNOTATION_PRE = "annotation";



    /**
     * 注解需要修饰的地方
     */
    public static final String COMP_MODIFY_ON = "modifyOn";


    /**
     * 注解tag别名
     */
    public static final String COMP_ANNOTATION_ALIAS = "classAlias";


    /**
     * 扫描组件的文件前缀
     */
    public static final String COMP_COMPONENT_PATH_PRE = "/component/";

    /**
     * 独立类组件扫描路径
     */
    public static final String COMP_COMPONENT_SINGLE_CLASS_PATH = COMP_COMPONENT_PATH_PRE + "singleClass";

    /**
     * 注解标签扫描路径
     */
    public static final String COMP_COMPONENT_ANNOTATION_TAG_PATH = COMP_COMPONENT_PATH_PRE + "annotationClass";



    /**
     * 配置的包依赖
     */
    public static final String CLASS_IMPORT_FILE = "/class-import.properties";




    public static final String REQUEST_DTO = "requestdto";
    public static final String RESPONSE_DTO = "responsedto";


    public static final String REQUEST_VO = "requestvo";
    public static final String RESPONSE_VO = "responsevo";



    public static final String CUSTOM_CLASS = "customclass";




    /**--------模块名---------**/

    public static final String API = "-api";

    public static final String APP = "-app";

    public static final String CLIENT = "-client";

    public static final String DOMAIN = "-domain";

    public static final String START = "-start";

    public static final String ADAPTER = "-adapter";

    public static final String COMMON = "-common";

    public static final String CORE = "-core";

    public static final String INFRAST = "-infrast";

    public static final String FEIGN_API = "-feignapi";

    public static final String FEIGN_PROVIDER = "-feignprovider";

    public static final String PROVIDER = "-provider";

    public static final String SPRING_BOOT = "-springboot";

    public static String PARENT = "parent";
}
