# bootutils

#### 介绍

基于springboot的便捷开发工具

#### 软件架构

springboot 3.13 hutool 5.8.21

#### 快速开始

- 通过import方式

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.chenggongdu</groupId>
            <artifactId>bootutils-bom</artifactId>
            <version>1.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

在子模块中就可以引入自己需要的模块了：

```xml
<dependency>
    <groupId>com.chenggongdu</groupId>
    <artifactId>bootutils-gencode</artifactId>
    <scope>test</scope>
</dependency>
```
- 通过dependency直接引入
```xml
<dependency>
    <groupId>com.chenggongdu</groupId>
    <artifactId>bootutils-gencode</artifactId>
    <scope>test</scope>
    <version>1.0.0</version>
</dependency>
```
#### 使用说明

1.  代码生成器: 面向单一原则的代码生成，依赖springboot3 和 openapi3
    

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5. Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
