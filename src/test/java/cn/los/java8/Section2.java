package cn.los.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.los.entity.Car;

//https://www.ibm.com/developerworks/cn/java/j-java8idioms2/index.html
//函数组合与集合管道模式
public class Section2 {

    // 在 Java 中，for 和 while
    // 都是语句。语句执行一个操作，但不会生成任何结果。
    // 就本质而言，任何执行有用的操作的语句都会导致数据变化。这是语句表达其效果的唯一方式。而表达式则相反：它们可以得出结果而不会导致变化。
    // 表达式的工作更像一条链：当某个人完成一项任务时，他将结果转交给链中的下一个人。
    public static void main(String[] args) {

        List<Car> carList = Arrays.asList(
                new Car("Jeep", "Wrangler", 2011),
                new Car("Jeep", "Comanche", 1990),
                new Car("Dodge", "Avenger", 2010),
                new Car("Buick", "Cascada", 2016),
                new Car("Ford", "Focus", 2012),
                new Car("Chevrolet", "Geo Metro", 1992));

        List<String> ls = getModelsAfter2000UsingFor(carList);

        System.out.println(ls.toString());

    }

    // 使用命令式编程来迭代该列表，并获取 2000 年后制造的汽车的名称。然后按年份对这些型号进行升序排序。
    private static List<String> getModelsAfter2000UsingFor(List<Car> cars) {
        List<Car> carsSortedByYear = new ArrayList<>();

        // 提取或过滤出 2000 年后制造的汽车
        for (Car car : cars) {
            if (car.getYear() > 2000) {
                carsSortedByYear.add(car);
            }
        }

        // 按照制造年份对该列表进行升序排序
        Collections.sort(carsSortedByYear, new Comparator<Car>() {
            public int compare(Car car1, Car car2) {
                return new Integer(car1.getYear()).compareTo(car2.getYear());
            }
        });

        // 以获取型号名称，并在一个列表中返回它们
        List<String> models = new ArrayList<>();
        for (Car car : carsSortedByYear) {
            models.add(car.getModel());
        }

        return models;
    }

    // 函数组合和集合管道是函数式编程中常用的两种设计模式
    // 函数式代码比命令式代码更简洁。
    // 函数式代码不会表现出明显的易变性，而且使用了更少的垃圾变量。
    // 第二个方法中使用的函数/方法都是有返回值的表达式。将此方法与 getModelsAfter2000UsingFor 中的
    // Collections.sort 方法进行对比。
    // getModelsAfter2000UsingPipeline 使用了集合管道模式，而且非常富于表达。
    public static List<String> getModelsAfter2000UsingPipeline(List<Car> cars) {
        return cars.stream().filter(car -> car.getYear() > 2000)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getModel)
                .collect(Collectors.toList());

        // Java 8 的惰性计算和函数融合功能（参阅 2014 年的 Java
        // 中的函数编程）可帮助避免在某些情况下创建中间对象。数据在管道中传输时，函数不会使中间对象变得可见或可用。
    }

}
