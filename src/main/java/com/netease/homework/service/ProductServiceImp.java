package com.netease.homework.service;

import com.netease.homework.repository.ProductPO;
import com.netease.homework.repository.ProductPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "productServcie")
public class ProductServiceImp implements ProductServcie {

    @Autowired
    private ProductPOMapper productPOMapper;
    @Autowired
    private UserService userService;

    private Set<Integer> productsIdHasBought;

    @Override
    public List<ProductPO> selectAll() {
        return productPOMapper.selectAll();
    }

    @Override
    public ProductPO selectById(Integer id) {
        return productPOMapper.selectById(id);
    }

    @Override
    public void insert(ProductPO productPO) {
        productPOMapper.insertProduct(productPO);
    }

    @Override
    public List<ProductPO> getProductsNotBought(String username) {
        Integer userId=userService.getUserByName(username).get(0).getId();
        return productPOMapper.selectProductsNotBought(userId);
    }

    @Override
    public Boolean isBoughtByUser(ProductPO productPO,String username) {
        Integer userId=userService.getUserByName(username).get(0).getId();
        //从数据库中读取已经被购买的商品id，有点类似于缓存，可以尝试更换成redis
//        if(productsIdHasBought==null){
            productsIdHasBought=new HashSet<>();
            List<Integer> productIdList=productPOMapper.selectProductsIdHasBought(userId);
            for(Integer id:productIdList){
                productsIdHasBought.add(id);
            }
//        }
        if(productsIdHasBought.contains(productPO.getId()))
            return true;
        else
            return false;
    }

    @Override
    public void deleteProduct(Integer id) {
        productPOMapper.deleteProduct(id);
    }

    @Override
    public void updateProduct(ProductPO productPO) {
        productPOMapper.updateProduct(productPO);
    }
}
