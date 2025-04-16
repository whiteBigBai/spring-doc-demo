package org.example.springdoc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.springdoc.domain.User;
import org.example.springdoc.mapper.UserMapper;
import org.example.springdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean queryUser(String userName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", "baiLiang");
        queryWrapper.eq("password", "123456");
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.nonNull(user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User queryUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", "baiLiang");
        queryWrapper.eq("password", "123456");
        return userMapper.selectOne(queryWrapper);
    }

}
