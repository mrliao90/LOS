package cn.los.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.ibm.com/developerworks/cn/java/j-java8idioms1/index.html
//https://www.ibm.com/developerworks/cn/views/global/libraryview.jsp?sort_by=&show_abstract=true&show_all=&search_flag=&contentarea_by=%E6%89%80%E6%9C%89%E4%B8%93%E5%8C%BA&search_by=Java+8+%E4%B9%A0%E6%83%AF%E7%94%A8%E8%AF%AD&product_by=-1&topic_by=-1&type_by=%E6%89%80%E6%9C%89%E7%B1%BB%E5%88%AB&ibm-search=%E6%90%9C%E7%B4%A2
public class Section1 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo",
                "Darla", "Marlin", "Jacques");

        findNemo(names);
        findNemo2(names);
        useMap();
    }

    // 这是一个命令式编程的程序 — 许多 Java 开发人员最熟悉的格式 — 所以您需要定义程序的每一步：
    // 告诉它迭代每个元素，比较值，设置 flag 变量，然后跳出循环。
    // 命令式格式为您提供了完全的控制权，这有时是件好事。
    // 而另一方面，您需要执行所有工作。在许多情况下，可以减少工作量来提高效率。
    private static void findNemo(List<String> names) {
        // 首先初始化一个可变的flag 变量，也称为垃圾（garbage）变量
        boolean found = false;
        // names 列表中循环，一次处理一个元素
        for (String name : names) {
            // 它检查获得的名称是否与它寻找的值
            // 如果值匹配，那么它会将 flag 变量设置为 true，并通知控制流“跳出”循环。
            if (name.equals("Nemo")) {
                found = true;
                break;
            }
        }

        if (found)
            System.out.println("Found Nemo");
        else
            System.out.println("Sorry, Nemo not found");
    }

    // 声明式编程意味着，您仍会告诉程序要做什么，但将实现细节留给底层的函数库。
    // 首先请注意，这个版本中没有任何垃圾变量。您也没有将精力浪费在对集合的循环处理上，而是使用内置的 contains() 方法
    // 来完成工作。您仍然需要告诉程序要做什么 — 检查集合是否包含我们寻找的值 — 但将细节留给底层的方法。
    // 在命令式示例中，您控制着迭代，而且程序完全按照要求来操作。在声明式版本中，您无需关心工作如何完成，只要它完成即可。
    // contains() 的实现可能不同，但只要结果符合预期，您可能就会很开心。花更少的精力获得同样的结果。
    // 训练自己以声明式编程思考，这将大大简化向 Java
    // 中的函数式编程的过渡。为什么呢？因为函数式编程是以声明式为基础而建立的。声明式思考试图逐步从命令式编程过渡到函数式编程。
    public static void findNemo2(List<String> names) {
        if (names.contains("Nemo"))
            System.out.println("Found Nemo");
        else
            System.out.println("Sorry, Nemo not found");
    }

    // 尽管函数式格式的编程始终是声明式的，但简单地使用声明式编程并不等于函数式编程。这是因为函数式编程合并了声明式方法与高阶函数。
    // Java 中的高阶函数
    // 在 Java中，要将对象传递给方法，在方法内创建对象，并从方法中返回对象。
    // ，可以将函可以对函数执行同样的操作。也就是说数传递给方法，在方法内创建函数，并从方法返回函数。
    // 在此上下文中，方法 是类的一部分 — 静态或实例 —
    // 但函数对于方法而言是本地函数，不能特意与类或实例关联。可以接收、创建或返回函数的函数或方法被视为高阶函数。
    public static void useMap() {

        // 包含一个网站的页面访问次数
        Map<String, Integer> pageVisits = new HashMap<>();

        String page = "https://agiledeveloper.com";

        incrementPageVisit(pageVisits, page);
        incrementPageVisit2(pageVisits, page);

        System.out.println(pageVisits.get(page));

    }

    // 使用命令式格式编写的：它的职责是递增给定页面的计数，将该计数存储在 Map
    // 中。该方法不知道给定页面是否有计数，所以它首先会检查是否存在计数。如果不存在，那么它会插入一个 “0”
    // 作为该页面的计数。然后获得该计数，递增它，并将新值存储在 Map 中。
    private static void incrementPageVisit(Map<String, Integer> pageVisits, String page) {
        if (!pageVisits.containsKey(page)) {
            pageVisits.put(page, 0);
        }

        pageVisits.put(page, pageVisits.get(page) + 1);

    }

    // 声明式思考要求您将此方法的设计思路从 “如何做” 转变为 “做什么”。当调用方法 incrementPageVisit()
    // 时，您希望将给定页面的计数初始化为 1 或将运行值递增 1。这就是做什么。
    // 因为您在执行声明式编程，所以下一步是扫描 JDK 库，查找 Map 接口中可以完成您的目标的方法 — 也就是说，寻找一个知道如何
    // 完成给定任务的内置方法。
    // 事实证明，merge() 方法能完美实现您的目标。
    private static void incrementPageVisit2(Map<String, Integer> pageVisits, String page) {
        // page 作为第一个参数传递给 merge()：该键的值应该更新。第二个参数是给该键分配的初始值，如果Map
        // 中不存在该键（在本例中，该值为 “1”）。第三个参数（一个拉姆达表达式）接收 map 中该键对应的值作为其参数，并且将该值作为变量传递给
        // merge 方法中的第二个参数。 这个拉姆达表达式返回的是它的参数的和，实际上就是递增计数。
        pageVisits.merge(page, 1, (oldValue, value) -> oldValue + value);

    }

}
