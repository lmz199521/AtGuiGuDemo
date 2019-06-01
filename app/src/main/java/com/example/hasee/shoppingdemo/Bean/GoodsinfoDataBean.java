package com.example.hasee.shoppingdemo.Bean;

/**
 * Created by Lmz on 2019/05/16
 */
public class GoodsinfoDataBean {

    int id;
    String name;
    String price;
    String pic;
    String number;
    boolean isCheck = true;

    public GoodsinfoDataBean() {
    }

    public GoodsinfoDataBean(int id, String name, String price, String pic, String number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic = pic;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
