#### APACHE DUBBO demo with nacos
###### 依赖
- Nacos
    - version=1.1.0
    - [Git地址](https://github.com/alibaba/nacos)
- Apache Dubbo 
    - version=2.6.7
    - [Git地址](https://github.com/apache/dubbo)

###### 整合步骤
- 引入dubbo依赖
```xml
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>dubbo</artifactId>
  <version>2.6.7</version>
</dependency>
```

###### Increasing Maven dependency
```xml
<dependencies>
    ...
        
    <!-- Dubbo Nacos registry dependency -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>dubbo-registry-nacos</artifactId>
        <version>2.6.7</version>
    </dependency>   
    
    <!-- Alibaba Spring Context extension -->
    <dependency>
        <groupId>com.alibaba.spring</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>1.0.2</version>
    </dependency>

    ...
</dependencies>
```
#### 新建项目：dubbo-demo(父项目)
- pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>dubbo</groupId>
        <artifactId>dubbo-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>dubbo-provider</artifactId>

    <dependencies>
        <!-- 添加junit4依赖 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <!-- 指定范围，在测试时才会加载 -->
            <scope>test</scope>
        </dependency>

        <!-- 添加spring核心依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-oxm</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>

        <!-- 添加fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>

        <!-- 添加日志相关jar包 -->
        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
        </dependency>
        <!-- log end -->

        <!-- 映入JSON -->
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
        </dependency>
        <dependency>
            <groupId>io.netty</groupId>
            <artifactId>netty-all</artifactId>
        </dependency>
        <!-- dubbo -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.spring</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>

        <dependency>
            <groupId>dubbo</groupId>
            <artifactId>dubbo-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```
#### 新建项目：dubbo-api(接口与相关实体类)
- pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>dubbo</groupId>
        <artifactId>dubbo-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>dubbo-api</artifactId>
    <packaging>jar</packaging>
    <name>dubbo-api</name>
    <build>
        <finalName>dubbo-api</finalName>
    </build>
</project>
```
- 新建实体类：User
```java
package com.dubbo.model;

import java.io.Serializable;
public class User implements Serializable {
    private String userId;
    private String userName;
    //隐藏getter/setter
}
```
- 新建接口:DemoService
```java
package com.dubbo.service;
public interface DemoService {
    String sayHello(String userName);
}
```
- 新建接口:UserService
```java
package com.dubbo.service;
import com.dubbo.model.User;
public interface UserService {
    User getName(String userId);
    String getUserName(String userId);
}
```

#### 新建项目：dubbo-provider(服务提供方)

- 新建DemoServiceImpl, 实现接口DemoService
```java
package com.dubbo.service.impl;

import com.dubbo.service.DemoService;
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String userName) {
        return "Hello " + userName;
    }
}
```

- 新建UserServiceImpl, 实现接口UserService
```java
package com.dubbo.service.impl;

import com.dubbo.model.User;
import com.dubbo.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static Map<String, User> userMap = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            String userId = String.valueOf(i);
            userMap.put(userId, new User(userId, "userName" + i));
        }
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public String getUserName(String userId) {
        User user = userMap.get(userId);
        return user == null ? null : user.getUserName();
    }
}
```
- 在目录src/main/resources下新建applicationContext.xml, 配置服务提供方的application和注册中心, 以及服务提供信息
```xml
<!-- application -->
<dubbo:application name="dubbo-provider" owner="admin" organization="dubbo"/>

<!-- Nacos registry address -->
<dubbo:registry address="nacos://127.0.0.1:8848" />
```

- 完整的配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo
       http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <!-- 定义了提供方应用信息，用于计算依赖关系。在dubbo-admin
    或 dubbo-monitor 会显示这个名字，方便识别 -->
    <dubbo:application name="dubbo-provider" owner="admin" organization="dubbo"/>
    <!-- 使用nacos注册中心暴露服务，注意要先开启 nacos -->
    <dubbo:registry address="nacos://127.0.0.1:8848"/>
    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="20880"/>

    <!--配置服务-->
    <bean id="demoService" class="com.dubbo.service.impl.DemoServiceImpl"/>
    <dubbo:service interface="com.dubbo.service.DemoService" ref="demoService" protocol="dubbo"/>

    <bean id="userService" class="com.dubbo.service.impl.UserServiceImpl"/>
    <dubbo:service interface="com.dubbo.service.UserService" ref="userService" protocol="dubbo"/>
</beans>
```
- 新建服务启动类:Application.java
```java
package com.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info(">>>>> dubbo-provider starting <<<<<");
        try {
            String configLocation = "classpath:applicationContext.xml";
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
            logger.info(">>>>> dubbo-provider started <<<<<");
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            logger.error(">>>>> dubbo-provider start failed!! <<<<<", e);
        }
    }
}
```
- 在src/main/resources目标下新建日志文件log4j2.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <!-- 变量配置 -->
    <Properties>
        <Property name="log_path">./logs/</Property>
    </Properties>

    <!-- appender配置 -->
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="[LOGID:%X{logId}] [%d][%t][%p][%c:%L] %m%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```
#### 新建服务消费项目：dubbo-consumer
- pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>dubbo</groupId>
        <artifactId>dubbo-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>dubbo-consumer</artifactId>

    <dependencies>
        <!-- 添加junit4依赖 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <!-- 指定范围，在测试时才会加载 -->
            <scope>test</scope>
        </dependency>

        <!-- 添加spring核心依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-oxm</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>

        <!-- 添加fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>

        <!-- 添加日志相关jar包 -->
        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
        </dependency>
        <!-- log end -->

        <!-- 映入JSON -->
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
        </dependency>
        <dependency>
            <groupId>io.netty</groupId>
            <artifactId>netty-all</artifactId>
        </dependency>
        <!-- dubbo -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.spring</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency>
            <groupId>dubbo</groupId>
            <artifactId>dubbo-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```
- 在src/main/resources目录下新建配置文件：applicaitonContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo
       http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <!-- 定义了提供方应用信息，用于计算依赖关系。-->
    <dubbo:application name="dubbo-consumer" owner="admin" organization="dubbo"/>
    <!--<dubbo:registry address="zookeeper://127.0.0.1:2181"/>-->
    <dubbo:registry address="nacos://127.0.0.1:8848"/>
    <!-- 使用dubbo 协议调用定义好的 api 接口 -->
    <dubbo:reference id="demoService" interface="com.dubbo.service.DemoService"/>
    <dubbo:reference id="userService" interface="com.dubbo.service.UserService"
                     protocol="dubbo" timeout="1000"/>
</beans>
```
- 新建基础测试类
```java
package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public abstract class BaseTest {
}
```
- 新建测试类:DemoServiceTest.java
```java
package com.dubbo.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.service.DemoService;
import test.BaseTest;

import javax.annotation.Resource;

public class DemoServiceTest extends BaseTest {
    @Resource
    DemoService demoService;

    @Test
    public void sayHello() {
        String userName = "Jay";
        String result = demoService.sayHello(userName);
        System.out.println(result);
    }
}
```
- 新建测试:UserServiceTest.java
```java
package com.dubbo.service;

import com.dubbo.model.User;
import com.dubbo.service.DemoService;
import com.dubbo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import test.BaseTest;

import javax.annotation.Resource;

public class UserServiceTest extends BaseTest {
    @Resource
    UserService userService;
    @Resource
    DemoService demoService;

    @Test
    public void getUserName() throws Exception {
        String userId = "1";
        User user = userService.getUser(userId);
        Assert.assertNotNull(user);
        System.out.println(demoService.sayHello(user.getUserName()));
        String userName = userService.getUserName(user.getUserId());
        Assert.assertEquals(userName, user.getUserName());
    }
}
```

#### 测试
- 启动项目dubbo-provider的服务，运行Application
- 运行项目dubbo-consumer的单元测试：DemoServiceTest.java