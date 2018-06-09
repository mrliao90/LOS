package cn.los.java8;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

//定制一个字符收集器
public class StringCombinerDemo implements Collector<String, StringCombinerDemo, String> {

    // 创建容器的工厂
    @Override
    public Supplier<StringCombinerDemo> supplier() {

        return null;
    }

    // 将当前元素叠加的收集器
    @Override
    public BiConsumer<StringCombinerDemo, String> accumulator() {
        return null;
    }

    // 合并两个容器
    @Override
    public BinaryOperator<StringCombinerDemo> combiner() {
        return null;
    }

    // 方法返回收集器操作的最终结果
    @Override
    public Function<StringCombinerDemo, String> finisher() {
        // TODO Auto-generated method stub
        return null;
    }

    // 定义特征
    @Override
    public Set<Characteristics> characteristics() {
        // TODO Auto-generated method stub
        return null;
    }

}
