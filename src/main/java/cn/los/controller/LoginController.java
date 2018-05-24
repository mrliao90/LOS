package cn.los.controller;

import java.util.HashMap;
import java.util.Map;

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
        
        //{code: 20000, data: {token: "admin"}}
        
        Map<String, String> token=new HashMap<>();
        
        token.put("token", "admin");
        
        return new ResultUtil<String>().setData("token=admin");
    }

    
    

}
