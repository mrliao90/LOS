package cn.los.function;

@FunctionalInterface // 表明它是一个函数接口。因为该注释包含在 java.lang 包中，所以没有必要导入
public interface Transformer<T> {
    // 接受一个参数化为 T 类型的对象，并返回一个相同类型的转换后对象。转换的语义将由该接口的实现来决定
    T transform(T input);
}
