<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- https://github.com/spring-projects/spring-boot/blob/v1.5.13.RELEASE/spring-boot/src/main/resources/org/springframework/boot/logging/logback/defaults.xml -->
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <property resource="application.properties"></property>
    <property name="APP_NAME" value="toms"/>
    <property name="LOG_PATH" value="${user.home}/${APP_NAME}/logs"/>
    <property name="LOG_FILE" value="${LOG_PATH}/toms-root.log"/>

    <appender name="APPLICATION"
    class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_FILE}/toms-root.log</file>
        <encoder>
            <pattern><![CDATA[%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%level] [traceId:%X{EAGLEEYE_TRACE_ID}] [%class:%line] - %m %n ]]> </pattern>
            <charset>UTF-8</charset>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}/logs_saved/toms-root.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxHistory>5</maxHistory>
            <maxFileSize>1GB</maxFileSize>
            <totalSizeCap>20GB</totalSizeCap>
        </rollingPolicy>
    </appender>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>

    <!--业务日志-->
    <appender name="TOMS-BIZ-APPENDER"
        class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${LOG_PATH}/toms-biz.log</File>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <FileNamePattern>${LOG_PATH}/logs_saved/toms-biz.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <maxHistory>5</maxHistory>
            <maxFileSize>2GB</maxFileSize>
            <totalSizeCap>20GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern><![CDATA[%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%level] [traceId:%X{EAGLEEYE_TRACE_ID}] [%class:%line] - %m %n ]]> </pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!--hsf日志-->
    <appender name="TOMS-HSF-APPENDER"
        class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${LOG_PATH}/toms-hsf.log</File>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <FileNamePattern>${LOG_PATH}/logs_saved/toms-hsf.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <maxHistory>5</maxHistory>
            <maxFileSize>2GB</maxFileSize>
            <totalSizeCap>20GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern><![CDATA[%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%level] [traceId:%X{EAGLEEYE_TRACE_ID}] [%class:%line] - %m %n ]]> </pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- 通用错误日志 -->
    <appender name="TOMS-ERROR-APPENDER"
        class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${LOG_PATH}/toms-error.log</File>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <FileNamePattern>${LOG_PATH}/logs_saved/toms-error.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <maxHistory>5</maxHistory>
            <maxFileSize>2GB</maxFileSize>
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern><![CDATA[%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%level] [traceId:%X{EAGLEEYE_TRACE_ID}] [%class:%line] - %m %n ]]> </pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- 异常日志 -->
    <appender name="TOMS-EXCEPTION-APPENDER"
        class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${LOG_PATH}/toms-exception.log</File>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${LOG_PATH}/logs_saved/toms-exception.%d{yyyy-MM-dd}.log</FileNamePattern>
            <maxHistory>5</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern><![CDATA[%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%level] [traceId:%X{EAGLEEYE_TRACE_ID}] [%class:%line] - %m %n ]]> </pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <logger name="HSF" level="${logback.info.level}" additivity="false">
        <appender-ref ref="TOMS-HSF-APPENDER"/>
    </logger>

    <logger name="BIZ" level="${logback.info.level}" additivity="false">
        <appender-ref ref="TOMS-BIZ-APPENDER"/>
        <appender-ref ref="TOMS-ERROR-APPENDER"/>
    </logger>

    <logger name="EXCEPTION" level="${logback.info.level}" additivity="false">
        <appender-ref ref="TOMS-EXCEPTION-APPENDER"/>
        <appender-ref ref="TOMS-ERROR-APPENDER"/>
    </logger>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>