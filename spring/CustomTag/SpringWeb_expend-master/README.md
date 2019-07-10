## 扩展自定义标签

---

#### 步骤
- 1、创建一个需要扩展的组件
- 2、定义一个XSD文件描述组件内容
- 3、创建一个文件，实现BeanDefinitionParser接口，用于解析XSD文件的定义和组件的定义
- 4、创建一个Handler文件，扩展自nameSpaceHandlerSupport,目的是注册到Spring容器
- 5、编写spring.handlers文件 和spring.schema文件

#### 注意
- 扩展的组件需要有ID属性，否则报错，报错如下：
> org.springframework.beans.factory.parsing.BeanDefinitionParsingException: Configuration problem: Configuration problem: Id is required for element


  