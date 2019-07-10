## 传统JDBC方式和MyBatis方式操作数据库
####  传统JDBC方式步骤
   > 1）加载（注册）数据库驱动（到JVM）。

   > 2）建立（获取）数据库连接。

   > 3）创建（获取）数据库操作对象。

   > 4）定义操作的SQL语句。

   > 5）执行数据库操作。

   > 6）获取并操作结果集

   > 7）回收数据库资源（关闭结果集-->关闭数据库操作对象-->关闭连接）

#### MyBatis方式步骤
  > 1） 首先，我们配置了MyBatis最主要的配置文件MyBatis.xml,里面包含了JDBC连接参数；

  > 2） 配置了映射器Mapper XML配置文件文件，里面包含了SQL语句的映射。

  > 3） 使用mybatis-config.xml内的信息创建了SqlSessionFactory对象。每个数据库环境应该就一个SqlSessionFactory对象实例，
        所以我们使用了单例模式只创建一个SqlSessionFactory实例。

  > 4） 创建了一个映射器Mapper接口-StudentMapper，其定义的方法签名和在StudentMapper.xml中定义的完全一样
       （即映射器Mapper接口中的方法名跟StudentMapper.xml中的id的值相同）。注意StudentMapper.xml中namespace
        的值被设置成com.mybatis3.mappers.StudentMapper，是StudentMapper接口的完全限定名。这使我们可以使用接
        口来调用映射的SQL语句。

  > 5） 在StudentService.java中，我们在每一个方法中创建了一个新的SqlSession，并在方法功能完成后关闭SqlSession。
        每一个线程应该有它自己的SqlSession实例。SqlSession对象实例不是线程安全的，并且不被共享。所以SqlSession的
        作用域最好就是其所在方法的作用域。从Web应用程序角度上看，SqlSession应该存在于request级别作用域上。

#### MyBatis自定义类型处理器步骤：
  > 1） 继承BaseTypeHandler<T>类实现自定义类型处理器

  > 2） 在MyBatis.xml中进行注册

#### 使用ResultSetHandler自定义结果集处理
  >     实现ResultHandler的handleResult方法

#### 基于注解的SQL映射和基于XML的SQL映射
  > 1） 当两者共存的时候，MyBatis.xml中只需要配置xml的SQL映射，同时配置会出现注册重复的异常

  > 2） 当只有基于注解的SQL映射时，MyBatis.xml文件可配置形式：
        <mapper class="com.winner.mybatis.mapper.StudentMapper"/>




