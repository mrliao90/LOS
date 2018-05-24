package cn.los.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.los.entity.UserEntity;
import cn.los.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<UserEntity> getBookList() {

        return userService.finAll();

    }

}
