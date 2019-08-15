package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-15
 * @Version 1.0
 */
public class AdvertisementWithNum {
    private  Advertisement advertisement;
    private  Integer number ;

    @Override
    public String toString() {
        return "AdvertisementWithNum{" +
                "advertisement=" + advertisement +
                ", number=" + number +
                '}';
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
