## lynncommuity

##部署
-git
-jdk
-maven
-mysql
## 快速运行
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地  
3. 运行命令创建数据库脚本
```sh
mvn flyway:migrate
```
4. 运行打包命令
```sh
mvn package
```
5. 运行项目  
```sh
java -jar target/community-0.0.1-SNAPSHOT.jar
```
## 资料
    [Spring文档](https://spring.io/guides/)
    [SpringWeb](https://spring.io/guides/gs/serving-web-content/)
    [GitHub OAuth document](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)
    [BootStrap](https://v3.bootcss.com/components/#navbar)
    [Es社区](https://elasticsearch.cn/)
    [Lombok](https://projectlombok.org/setup/maven)
    [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
    [Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-config-interceptors)
    [MarkDown插件](https://pandao.github.io/editor.md/)
    [ucloud](https://docs.ucloud.cn/storage_cdn/ufile/tools/sdk)
    [spring boot schedule](https://spring.io/guides/gs/scheduling-tasks/)
## 工具
    [Git](https://git-scm.com/)
    [flyway](https://flywaydb.org/getstarted/firststeps/maven)
    [Lombok](https://projectlombok.org/setup/maven)
    [Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)
## 脚本
```sql

        create table USER
        (
          ID INT auto_increment  primary key,
          ACCOUNT_ID   VARCHAR(100),
          NAME         VARCHAR(50),
          TOKEN        CHAR(36),
          GMT_CREATE   BIGINT,
          GMT_MODIFIED BIGINT
        );
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```