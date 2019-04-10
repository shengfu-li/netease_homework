package com.netease.homework.repository;

import com.netease.homework.repository.ProductPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPOMapper {

    public void insertProduct(ProductPO productPO);
    public void deleteProduct(Integer id);
    public void updateProduct(ProductPO productPO);

    public List<ProductPO> selectAll();
    public ProductPO selectById(Integer id);
    List<ProductPO> selectProductsNotBought(Integer userId);
    List<Integer> selectProductsIdHasBought(Integer userId);
}