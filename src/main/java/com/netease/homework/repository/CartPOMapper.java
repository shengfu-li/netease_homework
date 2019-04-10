package com.netease.homework.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartPOMapper {
    int insert(CartPO record);

    void deleteById(Integer id);
    void deleteByUserIdAndProductId(@Param("userId") Integer userId,@Param("productId") Integer productId);
    void updateById(@Param("number") Integer number,@Param("id") Integer id);
    List<CartPO> selectAll();
    List<CartPO> selectByUserId(Integer userId);
    CartPO selectByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
    CartPO selectById(Integer id);
}