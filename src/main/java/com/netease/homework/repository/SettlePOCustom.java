package com.netease.homework.repository;

import javafx.scene.input.DataFormat;

public class SettlePOCustom {
    private  SettlePO settlePO;
    private String imageURL;
    private String productName;

    //dateTime属性是为了对Date类型的日期做一些处理，只保留年/月/日/时/分/秒
    private String dateTime;

    //imageHttpURL表示该图片是网络图片
    private String imageHttpURL;
    public String getImageHttpURL() {
        return imageHttpURL;
    }

    public void setImageHttpURL(String imageHttpURL) {
        this.imageHttpURL = imageHttpURL;
    }


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public SettlePO getSettlePO() {
        return settlePO;
    }

    public void setSettlePO(SettlePO settlePO) {
        this.settlePO = settlePO;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
