package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>
import org.springframework.stereotype.Component;

 /**
 * @Description:${class.className}类
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Component("${class.className}")
public class ${class.className} ${class.relationClassStr}{

	<#list fields as field>
	/** ${field.desc} **/
    @Autowired
    ${field.visibility} ${field.fieldName};
	</#list>

    <#list methods as method>

${method.doc}
    ${method.visibility} ${method.returnClass} ${method.methodName}{
${method.methodContent}
        ${method.returnBody}
    }
    </#list>

}