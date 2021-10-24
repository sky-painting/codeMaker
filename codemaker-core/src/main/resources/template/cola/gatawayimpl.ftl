package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description:${class.classDesc}接口实现类
 * @Author：${class.author}
 * @CreateTime：${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Service
public class ${class.className} ${class.relationClassStr}{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());


    <#list fields as field>
    @Autowired
    ${field.visibility} ${field.fieldName};

    </#list>
	<#list methods as method>

    @Override
	public ${method.returnClass} ${method.methodName}{
${method.methodContent}
        ${method.returnBody}
    }
	</#list>

}