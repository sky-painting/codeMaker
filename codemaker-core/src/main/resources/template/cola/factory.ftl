package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>

import org.springframework.stereotype.Service;

 /**
 * @Description:${class.className}类
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Service
public class ${class.className}{

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
    ${method.visibility} ${method.returnClass} ${method.methodName}{
        ${method.returnBody}
    }
    </#list>

}