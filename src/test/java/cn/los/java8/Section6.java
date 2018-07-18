package cn.los.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

//为什么完美的 lambda 表达式只有一行
public class Section6 {

    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int result = 0;
        for (int e : values) {
            if (e > 3 && e % 2 == 0) {
                result = e * 2;
                break;
            }
        }

        // 此代码不仅优雅，而且它的工作量并不比命令式代码多。得益于 Stream 的惰性计算能力，这里没有浪费计算资源。
        int result2 = values.stream()
                .filter(e -> e > 3)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .findFirst() // 挑选第一个结果
                .orElse(0); // 如果没有任何值存在，则返回 0。

        System.out.println(result);
        System.out.println(result2);

        // 充满危险的长 lambda 表达式
        // 难以读懂
        // 用途不明
        // 代码质量差
        // 难以测试
        // 代码覆盖范围小
        System.out.println(
                values.stream()
                        .mapToInt(e -> {
                            int sum = 0;
                            for (int i = 1; i <= e; i++) {
                                if (e % i == 0) {
                                    sum += i;
                                }
                            }
                            return sum;
                        })
                        .sum());

        // 给定一组值，将该列表转换为每个数的因数之和
        System.out.println(
                values.stream()
                        .mapToInt(Section6::sumOfFactors)
                        .sum());

    }

    private static int sumOfFactors(Integer number) {
        return IntStream.rangeClosed(1, number)
                .filter(i -> number % i == 0)
                .sum();
    }

}
