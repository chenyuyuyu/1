package com.chenxy.blog.handler;

import com.alibaba.fastjson.JSON;
import com.chenxy.blog.dao.pojo.SysUser;
import com.chenxy.blog.service.LoginService;
import com.chenxy.blog.utils.UserThreadLocal;
import com.chenxy.blog.vo.ErrorCode;
import com.chenxy.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行controller方法(Handler)之前进行执行
        /**
         * 1. 需要判断 请求的接口路径 是否为 HandlerMethod (controller方法)
         * 2. 判断 token是否为空，如果为空 未登录
         * 3. 如果token 不为空，登录验证 loginService checkToken
         * 4. 如果认证成功 放行即可
         */
        //如果不是我们的方法进行放行
        if(!(handler instanceof HandlerMethod)){
            //handler可能是静态资源
            return true;
        }
        String token=request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if(StringUtils.isBlank(token)){
            Result re = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登陆");
            //设置浏览器识别返回的是json
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(re));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if(sysUser==null){
            Result re = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登陆");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(re));
            return false;
        }
        //是登陆状态，放行
        //登陆验证成功，放行
        //怎样在controller中直接获取用户信息？
        UserThreadLocal.put(sysUser);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除ThreadLocal中用完的信息，会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
