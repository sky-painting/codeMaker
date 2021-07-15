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
public class ${class.className} {
	
	protected Logger logger = LoggerFactory.getLogger(${class.className}.class);

<#list methods as method>
	/**
	 *
	 * @Description:${method.desc}
	 * @return ${method.returnClass}
	 */
	@RequestMapping(value = "${method.pathValue}")
	public ${method.returnClass} ${method.methodName}{
		${method.returnBody}
	}
</#list>
}
