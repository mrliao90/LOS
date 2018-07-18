package cn.los.java8;

import java.util.Arrays;
import java.util.List;

//传递表达式（pass-through lambdas）的替代方案
public class Section5 {

    // 在函数式编程中，常常传递 lambda 表达式作为匿名函数，使用 lambda 作为更高阶函数的实参
    public static void main(String[] args) {

        // 清单 1. 一个传递 lambda 表达式
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> strS = Arrays.asList("a", "b", "c");

        numbers.stream()
                .filter(e -> e % 2 == 0)
                .forEach(e -> System.out.print(e));

        // 使用方法引用会减少理解代码的工作。尽管最初的好处可能看起来较小，但随着我们编写和阅读更多代码，好处会倍增。
        numbers.stream()
                .filter(e -> e % 2 == 0)
                .forEach(System.out::print);
        System.out.println("");
        // 求和
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        String Str = strS.stream().reduce("", String::concat);

        System.out.println(Str);
    }

}
