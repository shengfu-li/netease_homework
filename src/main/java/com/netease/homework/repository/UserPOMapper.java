package com.netease.homework.repository;

import com.netease.homework.repository.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPOMapper {
    void insertUser(UserPO userPO);
    void deleteUser(UserPO userPO);
    void updateUser(UserPO userPO);


    List<UserPO> selectByName(String name);
    UserPO selectById(String id);
    List<UserPO> selectAll();
    String getUserType(String name);
}