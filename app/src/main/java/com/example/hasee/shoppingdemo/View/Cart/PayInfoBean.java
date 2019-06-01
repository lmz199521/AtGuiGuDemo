package com.example.hasee.shoppingdemo.View.Cart;

import java.util.Map;

/**
 * Created by Lmz on 2019/05/28
 */
public class PayInfoBean {

    String OutTradeNo;
    Map<String,String> result;

    public PayInfoBean() {
    }

    public PayInfoBean(String outTradeNo, Map<String, String> result) {
        OutTradeNo = outTradeNo;
        this.result = result;
    }

    public String getOutTradeNo() {
        return OutTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        OutTradeNo = outTradeNo;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
