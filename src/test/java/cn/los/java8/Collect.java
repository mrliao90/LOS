package cn.los.java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collect {
    
    public static void main(String[] args) {
        //collect(toList) 方法由 Stream 里的值生成一个列表，是一个及早值操作
        List<String> list = Stream.of("a","b","c","d","e") //由列表生成一个Stream
                                  .collect(Collectors.toList());//进行Stream 操作，继而是collect操作，由Stream生成列表
        //使用断言判断结果是否和预期的一致
        assertEquals(Arrays.asList("a","b","c","d","e"), list);
        
        List<String> list2 = Stream.of("a","b","c","d","e")
                .map(s->s.toUpperCase()).collect(Collectors.toList());
        
        assertEquals(Arrays.asList("A","B","C","D","E"), list2);
        
        
        BinaryOperator<Integer> accumulator = (x,y)-> x+y;
        
        int sum = accumulator.apply(0, 1);
        
        
        
        assertEquals(1, sum);
        
        
        
        
        
        
    }

}
