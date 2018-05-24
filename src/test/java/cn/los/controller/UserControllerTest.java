package cn.los.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.los.LosTests;
import cn.los.service.UserService;

public class UserControllerTest extends LosTests {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationConnect;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws JsonProcessingException {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();

    }

    @Test
    public void test2() throws Exception {
        String uri = "/";
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String ls = mvcResult.getResponse().getContentAsString();

        System.out.println("----------" + ls);

        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
    }

    @Test
    public void test3() throws Exception {

        String uri = "/";

    }

}
