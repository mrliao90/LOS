package cn.los.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.los.LosTests;
import cn.los.entity.UserEntity;
import cn.los.repository.UserRepository;

public class UserRepositoryTest extends LosTests {

    @Autowired
    UserRepository dao;

    @Test
    public void test1() throws Exception {
        try {
            UserEntity s = dao.getOne("1");
            System.out.println(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test2() throws Exception {
        
        UserEntity u = new UserEntity();
        u.setUsername("liao2");
        u.setRealname("廖俊荣2");
        u.setPassword("1234562");
        
        dao.save(u);
        
    }

}
