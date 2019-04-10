package com.netease.homework.mybatisGenerator;

import com.netease.homework.mybatisGenerator.UserPO;
import java.util.List;

public interface UserPOMapper {
    int insert(UserPO record);

    List<UserPO> selectAll();
}