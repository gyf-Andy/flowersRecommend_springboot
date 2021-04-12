package com.flower.service;

import com.flower.entity.User;

public interface UserService {
    /**
     * 添加用户
     * @param user 用户对象
     */
    Integer add(User user);

    /**
     * 修改用户
     * @param user 用户对象
     */
    Integer edit(User user);

    /**
     * 通过用户名查找用户
     * @param username 用户登录名
     */
    User findByUsername(String username);

    /**
     * 用户登录
     * @param user 用户
     */
    User login(User user);


}
