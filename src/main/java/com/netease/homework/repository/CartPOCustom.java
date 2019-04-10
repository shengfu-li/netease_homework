package com.netease.homework.repository;

public class CartPOCustom {
    private CartPO cartPO;
    private String productName;
    private int price;

    public CartPO getCartPO() {
        return cartPO;
    }

    public void setCartPO(CartPO cartPO) {
        this.cartPO = cartPO;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
