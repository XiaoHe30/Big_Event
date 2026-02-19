package com.xiaohe.controller;

import com.xiaohe.pojo.Result;
import com.xiaohe.pojo.User;
import com.xiaohe.service.UserService;
import com.xiaohe.utils.JwtUtil;
import com.xiaohe.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);

        //判断该用户是否存在
        if (loginUser == null){
            return Result.error("用户名错误或不存在");
        }
        //判断密码是否正确

        //登录成功
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
}
