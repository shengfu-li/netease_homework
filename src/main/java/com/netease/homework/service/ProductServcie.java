package com.netease.homework.service;

import com.netease.homework.repository.ProductPO;

import java.util.List;

public interface ProductServcie {
    List<ProductPO> selectAll();
    ProductPO selectById(Integer id);
    void insert(ProductPO productPO);
    List<ProductPO> getProductsNotBought(String username);
    Boolean isBoughtByUser(ProductPO productPO,String username);
    void deleteProduct(Integer id);
    void updateProduct(ProductPO productPO);
}
