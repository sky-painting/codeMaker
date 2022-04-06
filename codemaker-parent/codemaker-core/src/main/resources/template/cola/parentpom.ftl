<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <packaging>pom</packaging>
    <version>${version}</version>
    <modules>
        <module>${modulePrefix}-adapter</module>
        <module>${modulePrefix}-domain</module>
        <module>${modulePrefix}-infrast</module>
        <module>${modulePrefix}-app</module>
        <module>${modulePrefix}-client</module>
        <module>${modulePrefix}-start</module>
        <module>${modulePrefix}-feignapi</module>
    </modules>

    <name>${artifactId}</name>
    <!-- FIXME change it to the project's website -->
    <url>${url}</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <java.version>1.8</java.version>
        <spring-boot.version>2.1.9.RELEASE</spring-boot.version>
        <dubbo.version>2.7.3</dubbo.version>
        <mapstruct.version>1.2.0.Final</mapstruct.version>
        <rocketmq.version>4.8.0</rocketmq.version>
        <spring-cloud.version>Hoxton.SR3</spring-cloud.version>
        <spring-alibaba-cloud.version>2.2.0.RELEASE</spring-alibaba-cloud.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${r'${spring-alibaba-cloud.version}'}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${r'${spring-cloud.version}'}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>


            <!-- Spring Boot -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${r'${spring-boot.version}'}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- Apache Dubbo  -->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-dependencies-bom</artifactId>
                <version>${r'${dubbo.version}'}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- Dubbo Spring Boot Starter -->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-spring-boot-starter</artifactId>
                <version>${r'${dubbo.version}'}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${r'${dubbo.version}'}</version>
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework</groupId>
                        <artifactId>spring</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>javax.servlet</groupId>
                        <artifactId>servlet-api</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>log4j</groupId>
                        <artifactId>log4j</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>

            <dependency>
                <groupId>com.alibaba.spring</groupId>
                <artifactId>spring-context-support</artifactId>
                <version>1.0.5</version>
            </dependency>

            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct</artifactId>
                <version>${r'${mapstruct.version}'}</version>
            </dependency>
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>${r'${mapstruct.version}'}</version>
            </dependency>
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-jdk8</artifactId>
                <version>${r'${mapstruct.version}'}</version>
            </dependency>


            <dependency>
                <groupId>commons-collections</groupId>
                <artifactId>commons-collections</artifactId>
                <version>3.2.1</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.72</version>
            </dependency>


            <dependency>
                <groupId>commons-lang</groupId>
                <artifactId>commons-lang</artifactId>
                <version>2.4</version>
            </dependency>
            <!-- Dubbo Registry Nacos -->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-registry-nacos</artifactId>
                <version>2.7.3</version>
            </dependency>


            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>1.7.28</version>
            </dependency>
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-ext</artifactId>
                <version>1.7.28</version>
            </dependency>

            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-log4j12</artifactId>
                <version>1.7.28</version>
            </dependency>
            <!-- 添加 log4j 依赖 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-log4j2</artifactId>
                <version>${r'${spring-boot.version}'}</version>
            </dependency>

            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.9.4</version>
            </dependency>

            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjrt</artifactId>
                <version>1.8.13</version>
            </dependency>

            <dependency>
                <groupId>org.assertj</groupId>
                <artifactId>assertj-core</artifactId>
                <version>3.9.1</version>
            </dependency>

            <!--注意： 这里的版本,要和部署在服务器上的版本号一致-->
            <dependency>
                <groupId>org.apache.rocketmq</groupId>
                <artifactId>rocketmq-client</artifactId>
                <version>${r'${rocketmq.version}'}</version>
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
    </dependencyManagement>



    <repositories>
        <repository>
            <id>aliyun-releases</id>
            <name>阿里云仓库</name>
            <url>https://maven.aliyun.com/repository/public</url>
        </repository>
        <repository>
            <id>apache.snapshots.https</id>
            <name>Apache Development Snapshot Repository</name>
            <url>https://repository.apache.org/content/repositories/snapshots</url>
            <releases>
                <enabled>false</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>