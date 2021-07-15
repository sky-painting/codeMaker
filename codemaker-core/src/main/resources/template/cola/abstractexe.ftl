package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>


 /**
 * @Description:${class.className}类
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public abstract class ${class.className}{

	<#list fields as field>
	/** ${field.desc} **/
    ${field.visibility} ${field.fieldName};
	</#list>

    <#list methods as method>
    /**
     *
     * @Description:${method.desc}
     * @return ${method.returnClass}
     */
    public abstract ${method.returnClass} ${method.methodName};
    </#list>

}