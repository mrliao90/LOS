package cn.los.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//提倡使用有帮助的编码
public class Section4 {

    // 保持每行代码都简短紧凑是一种不错的做法，但是走极端可能导致代码变得生硬难读。要提高表达能力，可以问自己代码是否容易理解。要提高可读性，可采用
    // Java 8 垂直对齐各点的约定。使用这些简单技巧，就能创建出简洁、富于表达且可读的函数式代码。
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Jill", "Nate", "Kara", "Kim", "Jullie", "Paul",
                "Peter");

        System.out.println(
                names.stream()
                        .filter(name -> name.length() == 4)
                        .collect(Collectors.joining(", ")));

        List<String> subList = new ArrayList<>();
        for (String name : names) {
            if (name.length() == 4)
                subList.add(name);
        }

        StringBuilder namesOfLength4 = new StringBuilder();
        for (int i = 0; i < subList.size() - 1; i++) {
            namesOfLength4.append(subList.get(i));
            namesOfLength4.append(", ");
        }

        if (subList.size() > 1)
            namesOfLength4.append(subList.get(subList.size() - 1));

        System.out.println(namesOfLength4);

    }

}
