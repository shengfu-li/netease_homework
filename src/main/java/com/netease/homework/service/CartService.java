package com.netease.homework.service;

import com.netease.homework.repository.CartPO;

import java.util.List;

public interface CartService {

    public List<CartPO> getCartByUsername(String username);

    public void insert(CartPO cartPO);
    public void deleteById(Integer id);
    public void deleteByUserIdAndProductId(Integer userId,Integer productId);
    public void updateById(Integer number,Integer id);
    public void updateByUsername(CartPO cartPO,String username);
    public List<CartPO> selectByUsername(String username);
    public CartPO selectByUsername(String username,Integer productId);
    public CartPO selectById(Integer id);
    public List<CartPO> selectAll();
    

}
