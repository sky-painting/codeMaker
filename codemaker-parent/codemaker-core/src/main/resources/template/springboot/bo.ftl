package ${package}.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:${table.tableComment}BO类
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Data
@ToString
public class ${table.humpClassName}BO{

	<#list columns as column>

	/** ${column.columnComment} **/
	private ${column.columnTypeName} ${column.columnFieldName};
	</#list>

}