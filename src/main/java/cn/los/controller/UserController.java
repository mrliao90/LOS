package cn.los.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.los.base.BaseController;
import cn.los.base.BaseService;
import cn.los.entity.UserEntity;
import cn.los.service.UserService;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserEntity, String> {

    @Autowired
    UserService userService;

    @Override
    public BaseService getService() {
        return userService;

    }

}
