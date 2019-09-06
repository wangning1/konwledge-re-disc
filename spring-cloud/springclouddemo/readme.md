## spring cloud学习 
 - 服务注册与发现中心 Eureka server 
    + 对应 module [eureka-server] 
    + port 7761 ...
    + 本地启动通过访问 http://localhost:7761 查看Eureka界面
 - 服务提供者
    + 对应 module [service-producer] 
    + port 8761 ...
    + 本地启动通过访问 http://localhost:7761/sayHi 可以访问接口
 - 服务消费者 Ribbon + rest
    + 断路器 Hystrix 熔断处理
    + 对应 module [service-ribbon-consumer] 
    + port 9761 ...
    + 本地启动通过访问 http://localhost:9761/sayHi 可以访问service-producer的接口
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
    
    