package cn.los.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.los.entity.UserEntity;

/*方法引用*/
public class MethodReference {

    public static void main(String[] args) {

        // 标准语法：Classname::methodName

        UserEntity user = new UserEntity("admin");

        List<UserEntity> list = new ArrayList<>();
        list.add(user);
        // 使用Lambda 调用参数
        list.stream().map(u -> u.getUsername()).collect(Collectors.toList());
        // 使用方法引用
        list.stream().map(UserEntity::getUsername).collect(Collectors.toList());

    }

}
