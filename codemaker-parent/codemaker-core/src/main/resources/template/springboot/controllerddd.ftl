package ${class.packageName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

<#list imports as importClass>
import ${importClass};
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:${table.tableComment}控制层
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@RestController
<#list class.annotationTagList as annotation>
${annotation}
</#list>
public class ${class.className} ${class.relationClassStr}{
	
	protected Logger logger = LoggerFactory.getLogger(${class.className}.class);

<#list fields as field>
	@Autowired
	${field.visibility} ${field.fieldName};
</#list>

<#list methods as method>

${method.doc}
	<#list method.annotationTagList as annotation>
	${annotation}
	</#list>
	@RequestMapping(value = "${method.pathValue}")
	public ${method.returnClass} ${method.methodName}{
${method.methodContent}
		${method.returnBody}
	}
</#list>
}
