package com.chenxy.blog.handler;

import com.chenxy.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//对加了@controller注解的方法进行拦截处理 Aop的实现
@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json数据，否则返回页面
    public Result doException(Exception ex){
        //打印异常的堆栈信息，指明错误原因
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}
