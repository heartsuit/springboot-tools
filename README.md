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

## Lambda
1. 如何调用接口方法：
- 匿名内部类；
- Lambda表达式（以匿名的形式调用接口，不需要实现接口）；
Notes: Lambda表达式，属于函数式编程，仅定义接口，未定义实现类，因此需要允许在接口中定义普通方法，后续便无需再写实现类；

2. Lambda表达式结构
()->{};
():接口中的抽象方法的参数列表；
->：Lambda表达式分隔符；
{}：方法体实现；

3. 函数式接口
@FunctionalInterface检查接口是否为函数式接口
Note: 
- 如果接口中仅有一个方法，则默认为函数式接口；
- 可以定义Object类中的方法为抽象方法；
- 函数式接口中可以存在多个default普通方法；

Example: List foreach, sort; Thread Runnable 

4. Lambda的简化
- 参数类型可省略
- 参数只有一个时可以省去包围参数的()
- 方法体只有一行时可以省去{}与return

5. 不建议滥用Lambda表达式
- 可读性相对较差
- 难以调试