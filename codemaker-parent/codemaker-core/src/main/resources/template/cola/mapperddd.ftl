package ${class.packageName};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

<#list imports as importClass>
import ${importClass};
</#list>


/**
* @Description:${class.classDesc}接口
* @Author:${class.author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@Mapper
public interface ${class.className} ${class.relationClassStr}{

<#list methods as method>
${method.doc}
	${method.returnClass} ${method.methodName};

</#list>
}