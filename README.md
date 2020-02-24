## xiaowu commuity

## 资料
    [Spring文档](https://spring.io/guides/)
    [SpringWeb](https://spring.io/guides/gs/serving-web-content/)
    [GitHub OAuth document](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)
    [BootStrap](https://v3.bootcss.com/components/#navbar)
    [Es社区](https://elasticsearch.cn/)
    [Lombok](https://projectlombok.org/setup/maven)
    [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
    [Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-config-interceptors)
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