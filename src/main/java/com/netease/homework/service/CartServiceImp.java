package com.netease.homework.service;

import com.netease.homework.repository.CartPO;
import com.netease.homework.repository.CartPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "cartService")
public class CartServiceImp implements CartService {

    @Autowired
    private CartPOMapper cartPOMapper;

    @Autowired
    UserService userService;

    @Override
    public List<CartPO> getCartByUsername(String username) {
        return selectByUsername(username);
    }

    @Override
    public void insert(CartPO cartPO) {
        cartPOMapper.insert(cartPO);
    }

    @Override
    public void deleteById(Integer id) {
        cartPOMapper.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndProductId(Integer userId, Integer productId) {
        cartPOMapper.deleteByUserIdAndProductId(userId,productId);
    }

    @Override
    public void updateById(Integer number, Integer id) {
        cartPOMapper.updateById(number,id);
    }

    @Override
    //更新该用户购买的该产品的数量
    public void updateByUsername(CartPO cartPO, String username) {
        Integer id=cartPO.getId();
        cartPOMapper.updateById(cartPO.getNumber(),id);
    }

    @Override
    //寻找该用户购买的全部产品
    public List<CartPO> selectByUsername(String username) {
        Integer userId=userService.getUserByName(username).get(0).getId();
        return cartPOMapper.selectByUserId(userId);
    }

    @Override
    //寻找该用户购买的某个产品
    public CartPO selectByUsername(String username, Integer productId)
    {
        Integer userId=userService.getUserByName(username).get(0).getId();
        return cartPOMapper.selectByUserIdAndProductId(userId,productId);
    }

    @Override
    public CartPO selectById(Integer id) {

        return cartPOMapper.selectById(id);
    }

    @Override
    public List<CartPO> selectAll() {
        return cartPOMapper.selectAll();
    }
}
