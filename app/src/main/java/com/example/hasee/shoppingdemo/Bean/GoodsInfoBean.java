package com.example.hasee.shoppingdemo.Bean;

import java.io.Serializable;

/**
 * Created by Lmz on 2019/05/15
 */
public class GoodsInfoBean implements Serializable{
    int id;
    String name;
    String price;
    String pic;

    public GoodsInfoBean(int id, String name, String price, String pic) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic = pic;
    }

    public GoodsInfoBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
