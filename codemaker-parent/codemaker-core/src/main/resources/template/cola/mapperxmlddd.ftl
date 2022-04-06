<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${class.packageName}.${class.className}">


    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${doPackageName}">
        <#list columns as column>
        <result column="${column.columnName}" property="${column.columnFieldName}" />
        </#list>
    </resultMap>

    <!--查询总数-->
    <resultMap type="java.lang.Integer" id="count">
        <result column="total"/>
    </resultMap>


    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        ${table.columnNameList}
    </sql>

<#list fields as field>
${field.fieldName}

</#list>


</mapper>
