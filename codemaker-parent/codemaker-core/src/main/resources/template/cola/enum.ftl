package ${class.packageName};

import lombok.Getter;

 /**
 * @Description:${class.classDesc}类
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Getter
public enum ${class.className}{

    /**
     *
     */
    <#list enums as enumValue>
    ${enumValue},
    </#list>
    ;

	<#list fields as field>
	/** ${field.desc} **/
    ${field.visibility} ${field.fieldName};
	</#list>


    ${class.className}(${class.constructParamStr}){
        <#list bodys as body>
        ${body};
        </#list>
    }



    <#list methods as method>
    /**
     *
     * @Description:${method.desc}
     * @return ${method.returnClass}
     */
     public static ${method.returnClass} ${method.methodName}{
        ${method.returnBody}
     }
    </#list>

}