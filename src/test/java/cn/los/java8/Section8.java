package cn.los.java8;

import java.util.stream.IntStream;

//Java 知道您的类型
public class Section8 {

    public static void main(String[] args) {

        /* 显式类型和冗余 */
        // rangeClosed 方法生成一个从 1 到 5 的 int 值流。lambda 表达式的唯一职责就是接收一个名为 number 的
        // int 参数，使用 PrintStream 的 println 方法输出该值的双倍值。从语法上讲，该 lambda
        // 表达式没有错，但类型细节有些冗余。
        IntStream.rangeClosed(1, 5)
                .forEach((int number) -> System.out.println(number * 2));

        // 在 Java 8 中，我们可以丢弃 lambda 表达式中的类型
        IntStream.rangeClosed(1, 5)
                .forEach(number -> System.out.println(number * 2));

    }

}
