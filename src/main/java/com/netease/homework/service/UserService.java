package com.netease.homework.service;

import com.netease.homework.repository.UserPO;

import java.util.List;

public interface UserService {
    List<UserPO> getUserByName(String name);
    UserPO getUserById(String id);
    String getUserType(String name);
    List<UserPO> getAllUsers();

}
