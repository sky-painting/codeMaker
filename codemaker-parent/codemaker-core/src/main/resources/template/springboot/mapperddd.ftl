package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
* @Description:${class.classDesc}接口
* @Author:${class.author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@Mapper
public interface ${class.className}{

<#list methods as method>
	${method.doc}
	${method.returnClass} ${method.methodName};

</#list>
}