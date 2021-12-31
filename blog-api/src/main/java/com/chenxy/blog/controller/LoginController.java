package com.chenxy.blog.controller;

import com.chenxy.blog.service.LoginService;
import com.chenxy.blog.vo.Result;
import com.chenxy.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    //@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    //而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。
    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登录 验证用户 访问用户表
        return loginService.login(loginParam);

    }
}
