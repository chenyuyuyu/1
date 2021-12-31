package com.chenxy.blog.admin.service;

import com.chenxy.blog.admin.pojo.Admin;
import com.chenxy.blog.admin.pojo.Permission;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private AdminService adminService;
    public boolean auth(HttpServletRequest request, Authentication authentication){
        //权限认证，请求路径
        String requestURI = request.getRequestURI();
        log.info("request url:{}",requestURI);
//        log.info("url:{}",request.getRequestURL());
        //true代表放行，false代表拦截
        //获取登录用户的信息
        Object principal = authentication.getPrincipal();
        if(principal==null || "anonymousUser".equals(principal)){
            //未登录
            return false;
        }
        UserDetails userDetails=(UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin= adminService.findAdminByUsername(username);
        if(admin==null){
            return false;
        }
        if(admin.getId()==1){
            //认为是超级管理员root
            return true;
        }
        List<Permission> permissions=adminService.findPermissionsByAdminId(admin.getId());
        requestURI= StringUtils.split(requestURI,'?')[0];
        for (Permission permission : permissions) {
            if(requestURI.equals(permission.getPath())){
                log.info("权限通过");
                return true;
            }
        }

        return false;
    }
}
