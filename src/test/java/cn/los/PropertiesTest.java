package cn.los;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class PropertiesTest extends LosTests{
    
    @Value("${los.test}")
    private String testStr;
     
    @Test
    public void test2() throws Exception {
        System.out.println("testStr : " + testStr);
    }

}
 