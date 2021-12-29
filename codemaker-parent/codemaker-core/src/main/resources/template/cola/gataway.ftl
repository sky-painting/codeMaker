package ${class.packageName};

<#list imports as importClass>
import ${importClass};
</#list>


/**
 * @Description:${class.classDesc}接口
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${class.className}{
    <#list methods as method>

${method.doc}
     ${method.returnClass} ${method.methodName};
    </#list>
}