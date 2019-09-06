## spring cloud学习 
 - 服务注册与发现中心 Eureka server 
    + 对应 module [eureka-server] 
    + port 7761 ...
 - 服务提供者
    + 对应 module [service-producer] 
    + port 8761 ...
 - 服务消费者 Ribbon + rest
    + 断路器 Hystrix 熔断处理
    + 对应 module [service-ribbon-consumer] 
    + port 9761 ...
 - 服务消费者 Feign 
    + 断路器 Hystrix 熔断处理
    + 对应 module [feign-consumer] 
    + port 9765 ...
 - 路由网关 Zuul
    + 对应 module [zuul-server] 
    + port 6661
    