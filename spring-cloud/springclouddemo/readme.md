## spring cloud学习
#### Spring各组件对应工程及访问
 - 服务注册与发现中心 Eureka server 
    + 对应 module [eureka-server] 
    + 集群高可用的方式启动
      + application-peer-1.yml 端口:域名 eurekaServerPeer1:7761
      + application-peer-2.yml 端口:域名 eurekaServerPeer2:7762
      + application-peer-3.yml 端口:域名 eurekaServerPeer3:7763
    + 依次将profile设置为peer-1、peer-2、peer-3,在本地启动通过访问 http://eurekaServerPeer1:7761 查看Eureka界面
    + 解决节点出现在unavailable-replicas下 
      + 1.将registerWithEureka: true
      + 2.将fetchRegistry: true 
 - 服务提供者
    + 对应 module [service-producer] 
    + port 8765 ...
    + 本地启动通过访问 http://localhost:8765/sayHi 可以访问接口   
 - 服务消费者 Ribbon + rest
    + 断路器 Hystrix 熔断处理
    + 对应 module [service-ribbon-consumer] 
    + port 9761 ...
    + 本地启动通过访问 http://localhost:9761/sayHi 可以访问service-producer的接口
    + 增加断路器监控 
        + 增加注解 @EnableHystrixDashboard和@EnableCircuitBreaker
        + 访问路径 http://localhost:9761/actuator/hystrix.stream
        + 访问页面 http://localhost:9761/hystrix
        + actuator 需要暴露所有端点
 - 服务消费者 Feign 
    + 断路器 Hystrix 熔断处理
    + 对应 module [feign-consumer] 
    + port 9765 ...
    + 本地启动通过访问 http://localhost:9765/feignSayHi 可以service-producer的访问   
 - 路由网关 Zuul
    + 对应 module [zuul-server] 
    + port 6661
    + 本地启动通过访问 http://localhost:6661/api-rabbion 将请求转发给service-ribbon-consumer
    + 本地启动通过访问 http://localhost:6661/api-feign 将请求转发给feign-consumer
 - 配置中心
    + 配置中心server端
        + 对应module [config-server]
        + 启动项目，访问http://localhost:10000/config/dev 或 http://localhost:10000/config-dev.yml
        + 增加刷新机制
            + 增加actuator 依赖
            + 通过post 访问http://localhost:10000/actuator/refresh
        + 调整为高可用
            + 调整配置文件，增加application-server1.yml和application-server2.yml
            + pom中增加eureka配置支持，服务依赖eureka集群
    + 配置中心client端
        + 对应module [config-client]
        + 需要创建bootstrap.yml
        + 调整为调用集群
            + bootstrap.yml文件去掉spring.cloud.config.uri配置，增加服务发现配置
            + pom文件中增加eureka发现机制
 - 分布式链路跟踪sleuth和zipkin
    + 服务提供端和服务消费端需要的配置
        + 引入spring-cloud-starter-sleuth和spring-cloud-starter-zipkin依赖
        + 配置  spring.zipkin.base-url: http://localhost:9411/  # 指定了 Zipkin 服务器的地址
    + zipkin 服务端的启动 
        + 通过docker方式：docker run -d -p 9411:9411 openzipkin/zipkin
        + 通过jar包方式： java -jar zipkin.jarz
    + zipkin 界面访问
        + 访问http://localhost:9411/zipkin/ 
        
#### Spring各组件启动顺序 
   + 1. 启动eureka 集群 peer-1、peer-2、peer-3
     2. 启动服务提供者
     3. 启动服务消费者 
     4. 如果要测试Zuul网关，需要启动Zuul
     5. 如何要手机链路日志，需要先启动zipkin服务
     6. 配置中心需要启动config server集群，config-server1、config-server2 
    