package com.chenxy.blog.controller;

import com.chenxy.blog.service.LoginService;
import com.chenxy.blog.vo.Result;
import com.chenxy.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    //后端传递多个参数，前端只选用其需要的参数就可以
    @PostMapping
    public Result regist(@RequestBody LoginParam loginParam){
        //sso单点登录，后期如果把注册功能提出去（单独的服务，可以独立提供接口服务）
        return loginService.register(loginParam);
    }
}
