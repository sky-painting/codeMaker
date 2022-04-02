package ${package}.entity;

import java.util.Date;
import java.math.BigDecimal;

 /**
 * @Description:${table.tableComment}Entity类
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public class ${table.humpClassName}Entity{

	<#list columns as column>
	/** ${column.columnComment} **/
	private ${column.columnTypeName} ${column.columnFieldName};
	</#list>
	
	<#list columns as column>
	/**
	 * 描述：${column.columnComment}
	 */
	public ${column.columnTypeName} get${column.columnUperName}() {
		return ${column.columnFieldName};
	}
	/**
	 * 描述：${column.columnComment}
	 */
	public void set${column.columnUperName}(${column.columnTypeName} ${column.columnFieldName}) {
		this.${column.columnFieldName} = ${column.columnFieldName};
	}
	</#list>
}