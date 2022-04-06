## 模块-${moduleDesc}


### API列表
<#list apiList as api>

#### ${api.interfaceDesc}
1. 访问路径:${api.httpPath}
2. 访问方式:
3. 接口参数:

|  属性名   |  类型  |  描述  |  是否必填  |
|  ----    |  ----  | ----  | ----  |
    <#list api.paramBeanList as param>
| ${param.fieldName}  | ${param.fieldType} | ${param.fieldDesc} | ${param.nullable} |
    </#list>



4. 返回值:${api.returnClass}

</#list>

### 公共说明

