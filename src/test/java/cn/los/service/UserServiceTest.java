package cn.los.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.los.LosTests;
import cn.los.entity.UserEntity;

public class UserServiceTest extends LosTests {

    @Autowired
    UserService userService;

    @Test
    public void test2() {

        List<UserEntity> ls = userService.finAll();

        Assert.assertTrue("数据一致", ls.size() > 0);

    }

}
