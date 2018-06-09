package cn.los.java8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionDemo {

    public static void main(String[] args) {

        List<Integer> ls = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.toList());

        // 数据分块 >4 groupLingBy 分组

        Map<Boolean, List<Integer>> map = ls.stream().collect(Collectors.partitioningBy(x -> x > 4));

        List<Integer> ls2 = map.get(false);
        List<Integer> ls3 = map.get(true);

        ls2.forEach(System.out::print);
        System.out.println("");
        System.out.println("-----");
        ls3.forEach(System.out::print);

        // 字符串
        String str1 = ls.stream().map(x -> x.toString()).collect(Collectors.joining());
        String str2 = ls.stream().map(x -> x.toString()).collect(Collectors.joining(","));
        String str3 = ls.stream().map(x -> x.toString()).collect(Collectors.joining(",", "[", "]"));
        System.out.println("");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

    }

}
