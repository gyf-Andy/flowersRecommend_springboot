package com.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flower.entity.User;
import com.flower.mapper.UserMapper;
import com.flower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer edit(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));
    }

    @Override
    public User login(User user) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", user.getUsername())
                .eq("password", user.getPassword()));
    }
}
