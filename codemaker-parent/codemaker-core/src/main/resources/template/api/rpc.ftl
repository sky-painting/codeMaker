## 模块-${moduleDesc}

### 接口签名

${moduleApi}


### API列表
<#list apiList as api>

#### ${api.interfaceDesc}
1. 接口签名:${api.interfaceName}
2. 接口参数:

|  属性名   |  类型  |  描述  |  是否必填  |
|  ----    |  ----  | ----  | ----  |
<#list api.paramBeanList as param>
| ${param.fieldName}  | ${param.fieldType} | ${param.fieldDesc} | ${param.nullable} |
</#list>
3. 返回值:${api.returnClass}
</#list>

### 公共说明

