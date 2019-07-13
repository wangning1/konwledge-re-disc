# logback review
### 日志使用
  - 日志样式
  - 日志过滤
  - 日志写入策略
  - 日志分包展示
  - 自定义Appender
 
 ### 配置文件的加载顺序
   1. 如果配置了 指定了 logback.configurationFile属性,将使用这个属性的地址，如 java -Dlogback.configurationFile=/path/to/config.xml     
   2. 先找logback-test.xml  
   3. 再找logback.groovy  
   4. 然后再找项目中logback.xml    
   5. 如果还没找到，如果是 jdk6+,那么会调用ServiceLoader 查找 com.qos.logback.classic.spi.Configurator接口的第一个实现类
   5. 如果不存在，最后logback则会使用基本的配置ch.qos.logback.classic.BasicConfigurator，将日志输出定向到控制台  
    
 
 ### 执行流程
  1. 获得过滤链的策略，从logger节点中进行判断，找不到logger，默认找root节点  
    依据过滤器链返回的结果做出不同的响应,共有三个响应结果：  
    ① FilterReply.DENY, 直接退出，不执行后续流程  
    ② FilterReply.NEUTRA，继续向下执行  
    ③ FilterReply.ACCEPT，不进行步骤二,即类型输出类型检查
  2. 执行基本的选择规则  
     主要是比较下level，如果级别低直接退出后续执行
  3. 创建LoggingEvent对象       
     这个对象包裹一些基本信息，包括日志界别，信息本身，可能的异常信息，执行时间，执行线程，其实一些随日志
  请求一起发出的数据和MDC。其中MDC是用来装一些额外的上下文信息的。     
  4. 调用appenders        
     此时logback会调用appender的doAppender，如果appender里有一些filer的话，此时也会调用
  5. 格式化输出结果  
     通常情况下都是由layout层将event格式化成String型。当然也有意外比如说SocketAppender就是将event格式化成流。
  6. 输出LoggingEvent     
     将格式化好的结果，输出到appender中记录的地址   