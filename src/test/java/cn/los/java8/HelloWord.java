package cn.los.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HelloWord {

    public static void main(String[] args) {
        String[] strArr = { "A", "B", "C", "D", "E", "F", "A" };

        // 使用匿名内部类 排序
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2.compareTo(s1));
            }
        });

        List<String> strList = Arrays.asList(strArr);

        long count = strList.stream()
                .filter(str -> (str.equals("A") || str.equals("B"))).count();
        System.out.println(count);

        strList.stream().forEach(str -> System.out.println(str));

        // 以前的循环方式
        for (String str : strList) {
            System.out.print(str);
        }
        // 使用 lambda 表达式以及函数操作 （functional operation）
        System.out.println("");
        System.out.print("functional operation--");

        strList.forEach(str -> System.out.print(str));

        System.out.println("");
        System.out.print("double colon operator--");

        // java 8 中使用 双冒号操作符 （double colon operator）

        strList.forEach(System.out::print);

        System.out.println();

        Predicate<Integer> i = x -> x > 5;

        filter(strList, str -> str == "A");

    }

    public static void filter(List<String> names, Predicate<String> condition) {

        // 更好的办法
        // names.stream().filter((name) ->
        // (condition.test(name))).forEach((name) -> {
        // System.out.println(name + " ");
        // });

        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

}
