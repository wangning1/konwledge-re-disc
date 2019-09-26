## spring cloud学习 
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
    
    