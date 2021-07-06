package ${class.packageName};

import java.util.Date;
import java.math.BigDecimal;
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