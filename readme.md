

#  poseidon-cloud: 分布式平台快速开发框架

![poseidon](https://github.com/muggle0/poseidon-cloud/blob/master/project-document/png/factory.jpg?raw=true) 

 :penguin: 
![AppVeyor](https://img.shields.io/badge/cloud-poseidon-orange.svg) 嘻嘻嘻嘻嘻嘻嘻嘻寻

# 项目说明
集成 spring-cloud 环境，方便分布式项目的开发

## 使用的组件
- sentinel
- zipkin
- zuul
- logstash
- nacos
- rabbitmq
- spring-boot-admin
- spring-security
- oauth2（开发中）
- docker
- txLCN

## 环境搭建

# 快速开始
框架使用远程的注册中心和数据库，pull下来就可以运行体验。配置完整的运行环境还需要下载中间件，由于在github 上下载东西很慢，我把相关中间件上传到了百度云，down下来后按照教程安装

```
链接：https://pan.baidu.com/s/1ufn-pZxOYvq0wdmNuxquzQ 
提取码：65wj 
```

## sentinel 安装



##  logstash 安装



# 框架的使用（集成业务）

# 教程资料

# 二次开发
// 

```
Eureka 中的 metadataMap 是专门用来存放一些自定义的数据，当注册中心或者其他服务需要此服务的某些配置时可以在 metadataMap 里取。实际上，每个 instance 都有各自的 metadataMap，map 中存放着需要用到的属性。例如，上面配置中的 eureka.instance.metadata-map.user.name，当这个服务成功注册到 Eureka 上，Spring Boot Admin 就会拿到这个 instance，进而拿到 metadataMap 里的属性，然后放入请求头，向此服务发送请求，访问此服务的 Actuator 开放的端点。
```