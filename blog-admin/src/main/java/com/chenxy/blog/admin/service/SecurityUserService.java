package com.chenxy.blog.admin.service;

import com.chenxy.blog.admin.pojo.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class SecurityUserService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}",username);
        //当用户登录时，springsecurity会把请求转发到此
        //根据用户名查找用户，不存在 抛出异常，存在 将用户名 密码，授权列表组成springsecurity的User对象 返回
        Admin adminUser = adminService.findAdminByUsername(username);
        if(adminUser==null){
            //登录失败
            return null;
        }
        UserDetails userDetails=new User(username,adminUser.getPassword(),new ArrayList<>());
        //接下来的认证 由框架完成
        return userDetails;
    }
}
