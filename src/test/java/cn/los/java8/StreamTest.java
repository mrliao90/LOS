package cn.los.java8;

import java.util.ArrayList;
import java.util.List;

import cn.los.common.util.TimeRecorder;
import cn.los.entity.UserEntity;

public class StreamTest {
    
    public static void main(String[] args) {
        
        List<UserEntity> userList = new ArrayList<>();
        
        UserEntity user = null;
        
        for (int i = 1; i <= 500; i++) {
            user = new UserEntity("name"+i,"realname"+i);
            userList.add(user);
        }
        //Stream 就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了，就好比流水从面前流过，一去不复返。
        TimeRecorder recorder = new TimeRecorder();
        recorder.start();
        userList.stream().filter(u->u.getUsername().equals("name1")).forEach(u -> {
            System.out.println(u.getUsername());
        });
        recorder.end();
        
        System.out.print("Stream iterator:");
        System.out.println(recorder.getDuration());

        //parallelStream其实就是一个并行执行的流.它通过默认的ForkJoinPool,可能提高你的多线程任务的速度.
        recorder.start();
        userList.parallelStream().filter(u->u.getUsername().equals("name1")).forEach(u -> {
            System.out.println(u.getUsername());
        });
        recorder.end();
        
        System.out.print("Parallel Stream iterator:");
        System.out.println(recorder.getDuration());

        recorder.start();
        for (UserEntity u : userList) {
            if(u.getUsername().equals("name1")) {
                System.out.println(u.getUsername()); 
            }
            
        }
        recorder.end();
        
        System.out.print("Normal iterator:");
        System.out.println(recorder.getDuration());
        
    }
}
