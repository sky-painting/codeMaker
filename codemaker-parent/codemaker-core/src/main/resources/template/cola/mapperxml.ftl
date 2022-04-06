<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package}.infrast.dao.mapper.${table.humpClassName}Mapper">


    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package}.infrast.dao.dataobject.${table.humpClassName}DO">
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

    <insert id="insert" keyColumn="id" keyProperty="id" parameterType="${package}.infrast.dao.dataobject.${table.humpClassName}DO" useGeneratedKeys="true">
        insert into ${table.tableName}(
        ${table.insertColumnNames}
        )
        values(
        ${table.insertColumnNameList}
        )
    </insert>

    <update id="update" parameterType="${package}.infrast.dao.dataobject.${table.humpClassName}DO">
        update ${table.tableName}
        set
        ${table.updateColumnNameList}
        where  id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>
    </update>

    <select id="getAll" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" />  from ${table.tableName}
    </select>

    <select id="getById" parameterType="${package}.infrast.dao.dataobject.${table.humpClassName}DO"
            resultMap="BaseResultMap">
        select <include refid="Base_Column_List" /> from ${table.tableName}  where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>
    </select>

    <delete id="deleteById" >
        delete from ${table.tableName} where id = <#noparse>#{</#noparse>id<#noparse>}</#noparse>
    </delete>

    <select id="getPageList" resultMap="BaseResultMap">
        select <include refid="Base_Column_List" />  from ${table.tableName}
    </select>

    <select id="getCount" resultMap="count">
        select count(1)  from ${table.tableName}
    </select>

</mapper>
