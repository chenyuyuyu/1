package com.chenxy.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class LoginUserVo {
    //与页面交互
//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String account;
    private String nickname;
    private String avatar;
}
