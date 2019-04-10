package com.netease.homework.service;

import com.netease.homework.repository.UserPO;
import com.netease.homework.repository.UserPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImp implements UserService{
    @Autowired
    private  UserPOMapper userPOMapper;


    @Override
    public List<UserPO> getUserByName(String name) {
        return userPOMapper.selectByName(name);
    }

    @Override
    public UserPO getUserById(String id) {
        return userPOMapper.selectById(id);
    }

    @Override
    public String getUserType(String name) {
        return userPOMapper.getUserType(name);
    }

    @Override
    public List<UserPO> getAllUsers() {
        return userPOMapper.selectAll();
    }
}
