
#  poseidon-cloud: 分布式平台快速开发框架

![poseidon](https://github.com/muggle0/poseidon-cloud/blob/master/project-document/png/factory.jpg?raw=true) 

 :penguin: 
 
![AppVeyor](https://img.shields.io/badge/cloud-poseidon-orange.svg)

![AppVeyor](https://img.shields.io/badge/jdk8-support-orange.svg)

# 项目说明
集成 spring-cloud 环境，方便分布式项目的开发

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
# 框架的使用（集成业务）

# 教程资料

# 二次开发
// 

```
Eureka 中的 metadataMap 是专门用来存放一些自定义的数据，当注册中心或者其他服务需要此服务的某些配置时可以在 metadataMap 里取。实际上，每个 instance 都有各自的 metadataMap，map 中存放着需要用到的属性。例如，上面配置中的 eureka.instance.metadata-map.user.name，当这个服务成功注册到 Eureka 上，Spring Boot Admin 就会拿到这个 instance，进而拿到 metadataMap 里的属性，然后放入请求头，向此服务发送请求，访问此服务的 Actuator 开放的端点。
```