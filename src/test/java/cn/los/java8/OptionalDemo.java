package cn.los.java8;

import java.util.Optional;
import java.util.function.Consumer;

import cn.los.entity.UserEntity;

/*Optional相当于一个值的容器可以用get方法提取*/
public class OptionalDemo {

    public static void main(String[] args) {

        /* 构造方法 */
        // 要求传入的obj不能是null 值，否则 NullPointerException
        Optional<String> s1 = Optional.of("abc");
        // 以一种智能的，宽容的方式来构建一个Optional实例，来者不拒，传null进到就得到 Optional.empty()
        Optional<String> s2 = Optional.ofNullable(null);
        // 工厂方法 构建空值
        Optional<String> s3 = Optional.empty();

        // 1.存在即返回，无则提供默认值
        System.out.println(s2.orElse(null));

        // 2.存在即返回，无则由函数来产生
        System.out.println(s2.orElseGet(() -> "cc"));// cc 可以写方法返回

        // 3.存在才对他做点什么
        s2.ifPresent(System.out::println);
        s2.ifPresent(s -> System.out.println(s));

        // map 函数 是可能无限级联
        Optional<UserEntity> user = Optional.ofNullable(null);
        String username = user.map(u -> u.getUsername())
                .map(name -> name.toUpperCase()).orElse("gg");

        // 双冒号的使用 就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下。
        Consumer<String> methodParam = OptionalDemo::printValur; // 方法参数
        methodParam.accept(username);

        System.out.println(username);
        // filter 过滤
        UserEntity user2 = new UserEntity();
        user2.setUsername("admin");
        Optional<UserEntity> userOptional = Optional.of(user2);
        userOptional.filter(u -> Optional.ofNullable(u.getUsername()).isPresent())
                .ifPresent(uu -> System.out.println(uu.getUsername()));

        nameValid("");

    }

    // Optional可以用来检验参数的合法性
    public static void nameValid(String name) throws IllegalArgumentException {
        name = Optional.ofNullable(name).filter(x -> x.length() > 0)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username."));
    }

    public static void printValur(String str) {
        System.out.println("print value : " + str);
    }

}
