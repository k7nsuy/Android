package com.example.funkiepumkinapplication.Map;

import java.io.Serializable;

public class Shop implements Serializable {
    private Integer shopId;
    private String shopName;
    private String shopAddress;
    private String shopTel;
    private String shopLat;//위도
    private String shopLng;//경도

    public Shop(String shopName, String shopAddress, String shopTel) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopTel = shopTel;

    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopLat() {
        return shopLat;
    }

    public void setShopLat(String shopLat) {
        this.shopLat = shopLat;
    }

    public String getShopLng() {
        return shopLng;
    }

    public void setShopLng(String shopLng) {
        this.shopLng = shopLng;
    }
}
