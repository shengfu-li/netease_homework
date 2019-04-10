package com.netease.homework.repository;

public class ProductPOCustom {
    private ProductPO productPO;
    //增加一个属性，用于判断该商品是否已经被购买
    //1表示已经购买，2表示未购买
    private String hasBought;

    public ProductPO getProductPO() {
        return productPO;
    }

    public void setProductPO(ProductPO productPO) {
        this.productPO = productPO;
    }

    public String getHasBought() {
        return hasBought;
    }

    public void setHasBought(String hasBought) {
        this.hasBought = hasBought;
    }



}
