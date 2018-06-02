package cn.los.java8;

import static org.junit.Assert.assertEquals;

import java.util.function.Predicate;

/*java 中重要的函数接口*/
public class InterfacesDemo {
    public static void main(String[] args) {
        
        //Predicate<T>
        Predicate<Integer> a=x->x>5;
        boolean result = a.test(2);
        assertEquals(false, result);
        
        //Consumer<T>
        
        //Function<T,R>
        
        //Supplier<T>
        
        //UnarryOperator<T>
        
        //BinarrOperator<T>
       
    }

}
