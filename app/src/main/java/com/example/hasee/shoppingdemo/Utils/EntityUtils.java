package com.example.hasee.shoppingdemo.Utils;

/**
 * Created by Lmz on 2019/05/14
 *  定义一个自定义的Bean类
 */
public class EntityUtils<T> {
    private String code;
    private String msg;
    private  T result;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
