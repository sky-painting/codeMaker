package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:${class.classDesc}接口
* @Author:${class.author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@Mapper
public interface ${class.className}{
	${class.className} INSTANCE = Mappers.getMapper(${class.className}.class);

    <#list methods as method>
	/**
	 *
	 * @Description:${method.desc}
	 * @return ${method.returnClass}
	 */
	 ${method.returnClass} ${method.methodName};
    </#list>
}