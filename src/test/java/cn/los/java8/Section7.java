package cn.los.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import cn.los.entity.Car;
import cn.los.function.JobFuntion;

//函数接口
public class Section7 {

    public static void main(String[] args) {

        // 将 Runnable 的匿名实例传递给构造函数已成为一种传统
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("In another thread");
            }
        });

        thread.start();

        System.out.println("In main");
        // 从 Java 8 开始，可以选择传递 lambda 表达式
        Thread thread2 = new Thread(() -> System.out.println("In another thread2"));

        thread2.start();

        // 函数接口有 3 条重要法则：
        // 一个函数接口只有一个抽象方法。
        // 在 Object 类中属于公共方法的抽象方法不会被视为单一抽象方法。
        // 函数接口可以有默认方法和静态方法。
        // 任何满足单一抽象方法法则的接口，都会被自动视为函数接口。这包括 Runnable 和 Callable
        // 等传统接口，以及您自己构建的自定义接口。

        /* 内置函数接口 */

        // Function<T, R>、Predicate<T> 和 Consumer<T>，它们是在 java.util.function
        // 包中定义的。
        // Stream 的 map 方法接受 Function<T, R> 作为参数。类似地，filter 使用
        // Predicate<T>，forEach 使用 Consumer<T>。该包还有其他函数接口，比如
        // Supplier<T>、BiConsumer<T, U> 和 BiFunction<T, U, R>。

        // 断言型接口,输入一个参数，输出一个boolean类型得返回值。
        predicatesTest();

        // 函数型接口,输入一个类型得参数，输出一个类型得参数，当然两种类型可以一致。
        // IntFunction,int->R
        // LongFunction
        // DoubleFunction
        // IntToDoubleFunction, int->double
        // IntToLongFunction
        // LongToDoubleFunction,
        // LongToIntFunction,
        // DoubleToIntFunction
        // DoubleToLongFunction
        // ToIntFunction, T->int
        // ToDoubleFunction,
        // ToLongFunction
        functionsTest();

        // 供给型接口,只有产出，没人输入，就是只有返回值，没有入参
        SupplierTest();

        // 消费型接口,有参数，无返回值类型的接口。
        ConsumerTest();

        ComparatorTest();

        OptionalTest();

        /* 自定义函数接口 */
        // 1.使用 @FunctionalInterface 注释该接口，这是 Java 8 对自定义函数接口的约定。
        // 2.确保该接口只有一个抽象方法。
        // jdk8之前
        testJobFunction(new JobFuntion() {
            @Override
            public void execute() {
                System.out.println("我是自定义函数接口");
            }
        });
        // 使用lambada表达式 代码更简洁
        testJobFunction(() -> System.out.println("我是自定义函数接口"));

    }

    private static void testJobFunction(JobFuntion jobFuntion) {
        jobFuntion.execute();
    }

    private static void OptionalTest() {
        // Optional 看上去没什么用，和NPE相关的。
        // Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，
        Optional<String> optional = Optional.of("bam");

        optional.isPresent(); // true
        optional.get(); // "bam"
        optional.orElse("fallback"); // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"

    }

    private static void ComparatorTest() {
        // Comparators是从java旧版本升级并增加了一些缺省方法。
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        Comparator<Car> comparator = (p1, p2) -> p1.getModel().compareTo(p2.getModel());

        Car p1 = new Car("John", "Doe", 2017);
        Car p2 = new Car("Alice", "Wonderland", 2016);

        comparator.compare(p1, p2); // > 0
        comparator.reversed().compare(p1, p2); // < 0

    }

    private static void ConsumerTest() {
        // 表示作用在一个入参上的操作，没有返回值。例子 （星球大战）

        Consumer<Car> greeter = (c) -> System.out.println("Hello, " + c.getModel());
        greeter.accept(new Car("Luke", "Skywalker", 2018));

        happy(10000, m -> System.out.println(m));

    }

    private static void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    private static void SupplierTest() {
        // supplier 产生指定类型的一个结果。不同于function，supplier 不接受参数。 类似 一个使用默认构造器的工厂方法。

        Supplier<Car> carSupplier = Car::new;
        Car car = carSupplier.get(); // new Car
        System.out.println(car); // Car [make=null, model=null, year=0]

        List<Integer> list = getNumList(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);

    }

    // 需求：产生指定个整数，并放入集合中
    private static List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    private static void functionsTest() {
        // 接口接收一个参数并产生一个结果。其缺省方法通常被用来链接多个功能一起使用 (compose, andThen)。

        // toInteger 称作函数对象
        // 表示一个方法，接受一个参数，产生一个结果；第一个是入参，第二个是结果
        Function<String, Integer> toInteger = Integer::valueOf;

        // 返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123"); // "123"

        // 这个函数式接口的函数方法是apply, 把函数应用在指定的参数上
        // 如果你想把接受一些输入参数并将对输入参数处理过后的结果返回的功能封装到一个方法内，Function接口是一个不错的选择
        System.out.println(toInteger.apply("123"));

        Function<String, Integer> f1 = (t) -> Integer.valueOf(t) * 10;
        System.out.println(f1.apply("3")); // 30

        // 返回自身
        System.out.println(Function.identity().apply("3")); // 3

        // 先执行 apply 在执行andThen
        System.out.println(f1.andThen((r) -> String.valueOf(r) + ".....").apply("4")); // 40.....

        // 先执行compose里面的函数 再执行本函数的apply
        System.out.println(f1.compose((String r) -> r.substring(1)).apply("a5"));

        // 需求用于处理字符串
        String str = strHandler("huangyichun", s -> s.toUpperCase());
        System.out.println(str);

    }

    private static String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    private static void predicatesTest() {
        // 谓词是只有一个参数的布尔型函数。这个接口包含不同的默认方法，将谓词组成复杂的逻辑组合。
        Predicate<String> predicate = (s) -> s.length() > 0;

        // 检查给定参数的谓词
        Boolean a = predicate.test("foo"); // true
        // 逻辑上相反的谓词
        Boolean b = predicate.negate().test("foo"); // false

        // 引用静态方法
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        System.out.println(nonNull.test(null)); // false
        System.out.println(isNull.test(null)); // true

        // 引用普通方法
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        System.out.println(isEmpty.test("")); // true
        System.out.println(isNotEmpty.test("")); // false

        List<String> list = Arrays.asList("Hello", "Lambda", "Go", "java");
        // 需求:将满足条件的字符串添加到集合中
        list = filterStr(list, s -> s.contains("o"));
        list.forEach(System.out::println);

    }

    private static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> stringList = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str))
                stringList.add(str);
        }
        return stringList;
    }

}
