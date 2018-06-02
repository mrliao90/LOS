package cn.los.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.los.common.util.ResultUtil;
import cn.los.common.vo.Result;
import cn.los.entity.UserEntity;

@RestController
@RequestMapping("login")
public class LoginController {
    
    @RequestMapping(value="", method = RequestMethod.POST)
    public Result<String> Login(@RequestBody UserEntity user) {
        return new ResultUtil<String>().setData2("admin","admin");
    }

    
    

}
