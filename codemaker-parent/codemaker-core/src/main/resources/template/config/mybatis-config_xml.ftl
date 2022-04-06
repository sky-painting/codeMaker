<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="callSettersOnNulls" value="true"/>

        <setting name="cacheEnabled" value="true"/>

        <setting name="lazyLoadingEnabled" value="true"/>

        <setting name="aggressiveLazyLoading" value="true"/>

        <setting name="multipleResultSetsEnabled" value="true"/>

        <setting name="useColumnLabel" value="true"/>

        <setting name="useGeneratedKeys" value="false"/>

        <setting name="autoMappingBehavior" value="PARTIAL"/>

        <setting name="defaultExecutorType" value="SIMPLE"/>

        <setting name="mapUnderscoreToCamelCase" value="true"/>

        <setting name="localCacheScope" value="SESSION"/>

        <setting name="jdbcTypeForNull" value="NULL"/>

    </settings>

    <typeAliases>
        <typeAlias alias="Integer" type="java.lang.Integer" />
        <typeAlias alias="Long" type="java.lang.Long" />
        <typeAlias alias="HashMap" type="java.util.HashMap" />
        <typeAlias alias="LinkedHashMap" type="java.util.LinkedHashMap" />
        <typeAlias alias="ArrayList" type="java.util.ArrayList" />
        <typeAlias alias="LinkedList" type="java.util.LinkedList" />
    </typeAliases>
    <mappers>
    </mappers>
</configuration>