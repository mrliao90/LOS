# LOS
Learning open source

1.springboot 2.x 创建web项目  打包 jar

2. 数据持久层框架  引入 :Spring Data JPA

执行命令，通过 active 加载测试环境的配置。
java -jar  ***.jar  --spring.profiles.active=test


核心函数接口

简化或二元拓展

基本类型

Function ,T ->R

共25个接口

 

IntFunction,int->R

LongFunction

DoubleFunction

IntToDoubleFunction, int->double

IntToLongFunction

LongToDoubleFunction,

LongToIntFunction,

DoubleToIntFunction

DoubleToLongFunction

ToIntFunction, T->int

ToDoubleFunction,

ToLongFunction

BiFunction ,(T,U) ->R

ToIntBiFunction,

ToLongBiFunction,

ToDoubleBiFunction

UnaryOperator, T->T

IntUnaryOperator,

LongUnaryOperator,

DoubleUnaryOperator

BinaryOperator (T,T) ->T 

IntBinaryOperator,

LongBinaryOperator,

DoubleBinaryOperator

Predicate  T->boolean 

共5个接口

 

IntPredicate,

LongPredicate,

DoublePredicate

BiPredicate  (L,R)->boolean

 

Consumer, T->void

共8个接口

 

IntConsumer,

LongConsumer,

DoubleConsumer

BiConsumer  (T,U)->void

ObjIntConsumer,

ObjLongConsumer,

ObjDoubleConsumer

Supplier ()->T

共5个接口

 

BooleanSupplier,

IntSupplier,

LongSupplier,

DoubleSupplier