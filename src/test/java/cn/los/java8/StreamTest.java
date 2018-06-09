package cn.los.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.los.common.util.TimeRecorder;
import cn.los.entity.UserEntity;

public class StreamTest {

    public static void main(String[] args) {

        List<UserEntity> userList = new ArrayList<>();
        UserEntity user = null;

        for (int i = 1; i <= 1000000; i++) {
            user = new UserEntity("name" + i);
            userList.add(user);
        }

        TimeRecorder recorder = new TimeRecorder();

        recorder.start();
        List<String> ls2 = new ArrayList<>();

        for (UserEntity u : userList) {
            ls2.add(u.getUsername());
        }

        for (String s : ls2) {
            if (s.equals("name1")) {
                System.out.println(s);
            }
        }
        recorder.end();
        System.out.print("List<UserEntity> =1=> List<String>");
        System.out.println(recorder.getDuration());

        recorder.start();
        List<String> ls = userList.stream().map(u -> u.getUsername()).collect(Collectors.toList());
        for (String s : ls) {
            if (s.equals("name1")) {
                System.out.println(s);
            }
        }
        recorder.end();

        System.out.print("List<UserEntity> =2=> List<String>");
        System.out.println(recorder.getDuration());

        // Stream
        // 就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了，就好比流水从面前流过，一去不复返。

        recorder.start();
        userList.stream().filter(u -> u.getUsername().equals("name1")).forEach(u -> {
            System.out.println(u.getUsername());
        });
        recorder.end();

        System.out.print("Stream iterator:");
        System.out.println(recorder.getDuration());

        // parallelStream其实就是一个并行执行的流.它通过默认的ForkJoinPool,可能提高你的多线程任务的速度.
        recorder.start();
        userList.parallelStream().filter(u -> u.getUsername().equals("name1")).forEach(u -> {
            System.out.println(u.getUsername());
        });
        recorder.end();

        System.out.print("Parallel Stream iterator:");
        System.out.println(recorder.getDuration());

        recorder.start();
        for (UserEntity u : userList) {
            if (u.getUsername().equals("name1")) {
                System.out.println(u.getUsername());
            }

        }
        recorder.end();

        System.out.print("Normal iterator:");
        System.out.println(recorder.getDuration());

    }
}
