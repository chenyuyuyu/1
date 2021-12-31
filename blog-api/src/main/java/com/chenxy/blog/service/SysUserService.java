package com.chenxy.blog.service;

import com.chenxy.blog.dao.pojo.SysUser;
import com.chenxy.blog.vo.Result;
import com.chenxy.blog.vo.UserVo;

public interface SysUserService {
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 查询用户信息
     * @param toUid
     * @return
     */
    UserVo findUserVoById(Long toUid);
}
