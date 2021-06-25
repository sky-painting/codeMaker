<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package}.mapper.${table.humpClassName}Mapper">


    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package}.entity.${table.humpClassName}Entity">
        <#list columns as column>
        <result column="${column.columnName}" property="${column.columnFieldName}" />
        </#list>
    </resultMap>


    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        ${table.columnNameList}
    </sql>

    <insert id="insert" parameterType="${package}.entity.${table.humpClassName}Entity">
        insert into ${table.tableName}(
        ${table.insertColumnNames}
        )
        values(
        ${table.insertColumnNameList}
        )
    </insert>

    <update id="update" parameterType="${package}.entity.${table.humpClassName}Entity">
        update ${table.tableName}
        set
        ${table.updateColumnNameList}
        where  id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>
    </update>

    <select id="getAll"  resultMap="BaseResultMap">
        select <include refid="Base_Column_List" />  from ${table.tableName}
    </select>

    <select id="getById" parameterType="${package}.entity.${table.humpClassName}Entity"
            resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${table.tableName}  where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>
    </select>

    <delete id="deleteById" >
        delete from ${table.tableName} where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>
    </delete>

</mapper>
