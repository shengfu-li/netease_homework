package com.netease.homework.mybatisGenerator;

import com.netease.homework.mybatisGenerator.CartPO;
import java.util.List;

public interface CartPOMapper {
    int insert(CartPO record);

    List<CartPO> selectAll();
}