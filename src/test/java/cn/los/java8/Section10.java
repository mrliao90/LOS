package cn.los.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//使用闭包捕获状态
public class Section10 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        numbers.stream().filter(e -> e % 2 == 0).map(e -> e * 2).collect(Collectors.toList());

        // lambda 表达式中的词法范围
        int factor = 3;

        // map 方法现在接受一个闭包，而不是一个 lambda 表达式。我们知道，这个闭包接受一个参数 e，但它也捕获并携带 factor
        // 变量的状态。
        numbers.stream().filter(e -> e % 2 == 0).map(e -> e * factor)
                .collect(Collectors.toList());

    }

}
