package com.xiaohe.controller;

import com.xiaohe.pojo.Result;
import com.xiaohe.pojo.User;
import com.xiaohe.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        //查询用户
        User u = userService.findByUserName(username);
        //注册用户
        if (u == null){
            //没有被占用，执行注册逻辑
            userService.register(username, password);
            return Result.success();
        }else {
            //被占用，返回信息
            return Result.error("用户名已被占用！");
        }
    }
}
