package ${package}.dto;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;


/**
* @Description:${table.tableComment}DTO类
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@Data
@ToString
public class ${table.humpClassName}DTO  implements Serializable {

<#list columns as column>
	/** ${column.columnComment} **/
	private ${column.columnTypeName} ${column.columnFieldName};
</#list>

}