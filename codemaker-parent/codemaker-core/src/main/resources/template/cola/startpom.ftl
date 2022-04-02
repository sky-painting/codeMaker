<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>${groupId}</groupId>
        <artifactId>${artifactId}</artifactId>
        <version>${version}</version>
    </parent>

    <groupId>${groupId}</groupId>
    <artifactId>${modulePrefix}-start</artifactId>
    <version>1.0-SNAPSHOT</version>

    <name>${modulePrefix}-start</name>
    <!-- FIXME change it to the project's website -->
    <url>http://www.example.com</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>${groupId}</groupId>
            <artifactId>${modulePrefix}-app</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>${groupId}</groupId>
            <artifactId>${modulePrefix}-infrast</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>${groupId}</groupId>
            <artifactId>${modulePrefix}-adapter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

    <#list dependencyList as dependency>
        <dependency>
            <groupId>${dependency.groupId}</groupId>
            <artifactId>${dependency.artifactId}</artifactId>
            <#if (dependency.version)??>
            <version>${dependency.version}</version>
            </#if>
            <#if (dependency.scope)??>
            <version>${dependency.scope}</version>
            </#if>
            <#if (dependency.type)??>
            <version>${dependency.type}</version>
            </#if>
        </dependency>

        </#list>
    </dependencies>

    <build>
    </build>
</project>
