package cn.los.java8;

import java.util.stream.IntStream;

//https://www.ibm.com/developerworks/cn/java/j-java8idioms3/index.html
//传统 for 循环的函数式替代方案
public class Section3 {

    // 将了解如何使用 IntStream 方法 range、iterate 和 limit 来迭代范围和跳过范围中的值。您还将了解新的
    // takeWhile 和 dropWhile 方法（即将在 Java 9 中引入）。
    public static void main(String[] args) {

        // 清单 1. 完成一个简单任务的复杂代码
        System.out.print("Get set...");
        for (int i = 1; i < 4; i++) {
            System.out.print(i + "...");
        }

        // 清单 2. 完成一个简单任务的简单代码
        // 不同于 for，range 不会强迫我们初始化某个可变变量。
        // 迭代会自动执行，所以我们不需要像循环索引一样定义增量。
        System.out.print("Get set...");
        IntStream.range(1, 4)
                .forEach(i -> System.out.print(i + "..."));

        System.out.println("");

        /* 封闭范围 */

        for (int i = 0; i <= 5; i++) {
            System.out.print(i + "...");
        }
        // 包含边界值 5 在内的值。
        IntStream.rangeClosed(0, 5).forEach(i -> System.out.print(i + "..."));

        /* 跳过值 */
        int total = 0;
        for (int i = 1; i <= 100; i = i + 3) {
            total += i;
        }

        // 我们对 1 到 100 之间的值感兴趣，而且想从 1 开始跳过两个值。稍加运算，即可确定给定范围中有 34
        // 个符合要求的值。所以我们将该数字传递给 limit 方法。
        // 此代码很有效，但过程太复杂
        IntStream.iterate(1, e -> e + 3)
                .limit(34)
                .sum();
        // Java 9 中即将引入的 takeWhile 是一个新方法，它使得执行有限制的迭代变得更容易。使用
        // takeWhile，可以直接表明只要满足想要的条件，迭代就应该继续执行。以下是使用 takeWhile 实现清单 9 中的迭代的代码。
        // IntStream.iterate(1, e -> e + 3)
        // .takeWhile(i -> i <= 100) //available in Java 9
        // .sum();

        /* 逆向迭代 */
        for (int i = 7; i > 0; i--) {

        }
        IntStream.iterate(7, e -> e - 1)
                .limit(7);
    }

}
