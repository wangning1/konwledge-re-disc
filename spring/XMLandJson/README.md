## xml、json格式的数据与javabean相互转换
### 1.JAXB的使用
#### 1)JavaBean 转 xml
      > 步骤：
      > 1.引入相关jar包
      > 2.声明javabean对象，使用JAXB注解声明java属性与xml中对应的关系
      > 3.使用JAXB编组（marshall）javabean对象成XML
#### 2)xml 转 javaBean
      > 步骤：
      > 步骤同上
      > 3.使用JAXB解组（unmarshall）xml成javabean对象

### 3)注意：
      > 当使用@XmlElemet修饰属性的时候，需要使用@XmlAccessorType
      > 当使用@XmlElemet修饰属性的get或者set方法时，可以不使用@XmlAccessorType，当且仅当只能修饰get或者set方法中的一个
      > 当使用List泛型的时候，需要使用注解 //@XmlSeeAlso({Customer.class})
### 2.Jackson的使用(有多种方式：stream、TreeNode、dataBind，这里使用dataBind方式<包括注解和非注解的方式>)
#### 1)javabean 转 json
      > 引入jar包
      > 使用ObjectMapper将对象转换为json
#### 2)json对象转javabean
      > 注意泛型的转换:
      > 通过@JsonTypeInfo 和 @JsonSubTypes的使用进行记录子类信息，@JsonSubTypes可以省略，但@JsonTypeInfo必须保留
  ## 3)json注解的使用
      >@JsonIgnoreProperties 指定忽略的属性
      >@JsonIgnore 同上
      >@JsonFormat 对Date类型进行格式化
      >@JsonSerialize 用在属性或getXX上，进行序列化操作，可自定义格式
      >@JsonDeserialize 用在反序列化上
      >@JsonAnySetter/JsonAnyGetter 额外的属性进行存储通过map
      >@JsonProperty 指定json和属性对应的名称，可以不同
### 3.SpringMvc 结果响应json数据格式或xml数据格式内容
     > 使用注解@ResponseBody
     > 在RequestMapping中通过produces = {"application/json", "application/xml"}中两者之一指定返回格式