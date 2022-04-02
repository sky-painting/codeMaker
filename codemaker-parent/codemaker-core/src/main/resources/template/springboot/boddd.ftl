package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:${class.classDesc}类
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Data
@ToString
public class ${class.className} ${class.relationClassStr}{

	<#list fields as field>

    /**
     * ${field.desc}
     */
    ${field.visibility} ${field.fieldName};
	</#list>

    <#list methods as method>
    /**
     *
     * @Description:${method.desc}
     * @return ${method.returnClass}
     */
    ${method.visibility} ${method.returnClass} ${method.methodName}{
${method.methodContent}
        ${method.returnBody}
    }
    </#list>

}