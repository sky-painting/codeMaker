package ${class.packageName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;
<#list imports as importClass>
import ${importClass};
</#list>
/**
 * @Description:${class.classDesc}接口实现类
 * @Author：${class.author}
 * @CreateTime：${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Service
public class ${class.className}  ${class.relationClassStr}{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
<#list methods as method>

    @Override
    public ${method.returnClass} ${method.methodName}{
        ${method.returnBody}
    }
</#list>

}