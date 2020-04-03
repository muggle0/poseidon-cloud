
#  poseidon-cloud: 分布式平台快速开发框架

![poseidon](https://github.com/muggle0/poseidon-cloud/blob/master/project-document/png/factory.jpg?raw=true) 

 :penguin: 
 
![AppVeyor](https://img.shields.io/badge/cloud-poseidon-orange.svg)

![AppVeyor](https://img.shields.io/badge/jdk8-support-orange.svg)

# 项目说明

集成 `spring-cloud` 环境，方便分布式项目的快速开发。使用该框架，使用者无需操心业务之外的问题，极大的节省了开发者的开发时间。

## 使用的组件
- [sentinel](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel "点我") (阿里开源服务限流框架)
- [zipkin](https://github.com/openzipkin/zipkin/wiki "点我")(开源链路追踪框架)
- [zuul](https://github.com/Netflix/zuul/wiki "点我")(开源微服务网关)
- [logstash](https://www.elastic.co/cn/logstash "点我")(elk中的数据收集管道)
- [nacos](https://nacos.io/zh-cn/docs/what-is-nacos.html "点我")(注册中心)
- [rabbitmq]( https://muggle.javaboy.org/2019/08/30/rabbitmq/ "点我")
- [spring-boot-admin](https://codecentric.github.io/spring-boot-admin/current/ "应用监控")
- [spring-security](https://muggle.javaboy.org/2019/04/20/springSecurity2/ "权限控制框架")
- [oauth2](https://muggle.javaboy.org/2019/04/12/security-oauth2%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0/ "点我")(权限控制框架，开发中)
- docker
- [txLCN](http://www.txlcn.org/zh-cn/docs/preface.html "点我")(分布式事务框架)

# 快速开始
框架使用远程的注册中心和数据库，pull下来就可以运行体验。配置完整的运行环境还需要下载中间件，由于在github 上下载东西很慢，我把相关中间件上传到了百度云，down下来后按照教程安装

```
链接：https://pan.baidu.com/s/1ufn-pZxOYvq0wdmNuxquzQ 
提取码：65wj 
```
## 环境准备
请确保你的电脑上安装了 maven 、mysql、mongoDB.
 
## sentinel-dashboard 安装
框架集成了`sentinel`来做限流和熔断，`sentinel-dashboard`是sentinel控制台，你可以在这个控制台里面看到各个资源的访问情况，配置熔断规则等。
框架的配置文件制定控制台的`ip:prot` :
```properties
spring.cloud.sentinel.transport.dashboard=127.0.0.1:8000
```
`sentinel-dashboard` 是一个springboot应用，我们用如下方式启动
```properties
 java -jar sentinel-dashboard-1.6.0.jar --server.port=8000
```
启动之后打开浏览器，访问 `http://localhost:8000` 进入登录页，用户名密码均为 `sentinel`。
至此，sentinel安装成功，sentinel使用的相关教程我会在我的 [博客](https://muggle.javaboy.org/) 或者 [公众号](https://muggle.javaboy.org/2019/03/20/home/) 上发布

##  logstash 安装
`logstash` 作为日志的输出管道，将日志处理和框架解耦开来；它能将应用日志输出到 `mq`, `db` ,`file` 等地方

该框架配置的是将日志输出到mongoDB,因此我们需要先对logstash 安装MongoDB插件：

首先要给logstash配置国内ruby镜像源：打开安装目录下的 `Gemfile` ,把 `source` 改为：
```properties
source "https://gems.ruby-china.com/"
```
然后在bin目录下运行

```aidl
 ./logstash-plugin install logstash-input-mongodb
```

安装好之后，在MongoDB新建 `database` 为 `poseidon_cloud` , `collection` 为 `app_log`

在bin目录下新建文件 `logstash.conf`

```config
# Sample Logstash configuration for creating a simple
# Beats -> Logstash -> Elasticsearch pipeline.

input {
  tcp {
	host => "127.0.0.1"
    port => 8003
	codec => json_lines
  }
}


output {
  #elasticsearch {
   # hosts => ["http://localhost:9200"]
    #index => "%{[@metadata][beat]}-%{[@metadata][version]}-%{+YYYY.MM.dd}"
    #user => "elastic"
    #password => "changeme"
  #}
	
   stdout {
      # codec => json_lines
	   codec => rubydebug
  }
  
  mongodb {
	# 过滤掉 @timestamp 会报错
    uri => "mongodb://127.0.0.1:27017"
    database => "poseidon_cloud"
    collection => "app_log"
  }
}

filter {
    mutate {
		remove_field => ["@version", "host","port"]
    
    }
}
```

然后执行命令：
```aidl
 ./logstash.bat -f logstash.conf
```
到这里`logstash`就配置启动完成了

## 运行项目

环境搭建完毕，我们开始运行项目。
先启动 `tx-manager` 模块，访问 `localhost:7970` 密码是 `codingapi` 输入密码进入管理界面，说明项目启动成功

再启动 `poseidon-cloud-admin` 访问 `localhost:8001` 可以看到一个监控界面，说明项目启动成功

最后启动 `user-center` 和 `document-center`

项目启动完成后找到文件 `.\project-document\http\user.http` 发送请求进行测试。

访问 `http://49.233.148.110:8949/nacos/#/login` 用户名和密码均为 `nacos` 可以看到注册中心和配置中心的信息。
**注意：为了不影响项目的使用，请不要去改里面的配置**

# 框架的使用（集成业务）

现在将展示如何将我们的业务代码集成到框架中；

第一步：创建一个 `spring boot` 项目，并修改maven配置文件。 框架的maven依赖你可以选择install 到本地，也可以在其中添加一个模块项目。

这里以安装到本地为例
执行命令

```xml
mvn install org.springframework.boot:spring-boot-maven-plugin:2.2.5.RELEASE:build-info
```
创建maven项目，改`parent`:

```xml
 <parent>
        <groupId>com.muggle</groupId>
        <artifactId>poseidon-cloud-parent</artifactId>
        <version>0.0.1.Alpha</version>
    </parent>
```

## 部署到docker

框架组件均支持部署到docker

# 教程资料

本项目教程发布在 `http://muggle-book.gitee.io/` 

# 二次开发

 

```
Eureka 中的 metadataMap 是专门用来存放一些自定义的数据，当注册中心或者其他服务需要此服务的某些配置时可以在 metadataMap 里取。实际上，每个 instance 都有各自的 metadataMap，map 中存放着需要用到的属性。例如，上面配置中的 eureka.instance.metadata-map.user.name，当这个服务成功注册到 Eureka 上，Spring Boot Admin 就会拿到这个 instance，进而拿到 metadataMap 里的属性，然后放入请求头，向此服务发送请求，访问此服务的 Actuator 开放的端点。
```

// todo 