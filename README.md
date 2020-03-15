## Jackson
2.9.6
ObjectMapper类：JsonParser读，JsonGenerator写

1. 序列化：Object转Json
- 日期：默认为时间戳，设置时区，设置日期显示格式
- 枚举：默认输出为name()方法，如何输出toString()，如何输出下标
- 空属性：NON_NULL, NON_EMPTY, NON_DEFAULT, ALWAYS
- @JsonIgnore

经验是在实践中产生的。

2. 反序列化：Json转Object

