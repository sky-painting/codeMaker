#基于动态DDD模式(默认)

## 说明
基于PlantUML类图文档构建，类图文档中包含了DDD相关的实体,值对象,工厂,聚合,仓库,领域服务等相关的代码元素
所以这里基于动态DDD的模式是不会生成API接口和基础设施层相关的代码的(如mapper),这里对于动态DDD模式的定位是
模块级别的，主要用于构建并尽快将领域驱动设计模型落地到代码上并评估其领域业务模型，领域行为接口的合理性。

## 使用步骤
这里的使用步骤参考
[纯数据库模式](USEDBYONLYDB.md)
的前几部分，唯一要注意的是程序启动依然会链接一个数据库，但是这个数据库将仅仅是保持服务启动正常。

### 1.配置application.properties文件
具体配置参考纯数据库模式

### 2.配置projecttemplate-dynamicddd.properties文件
```properties
#目标工程输出目录，这里填写对应的codemaker-dynamicddd工程的绝对路径
dynamicddd.code.outpath=/Users/shenshuai/myspace/code-maker/codemaker-dynamicddd
#领域plantUML 类图
dynamicddd.domain.plantuml=SchoolManager.puml

```

### 3.codeMaker-core启动
1. codemaker-core项目已迁移到codemaker-parent父级工程下
2. 使用Main方法启动即可
    1. 启动时会通过类加载机制扫描配置的组件
    2. 如有报错信息请按上述步骤检查相关配置
### 4.准备生成代码
#### 访问接口
直接访问:http://127.0.0.1:8099/makeddd,构建DDD相关的代码元素


