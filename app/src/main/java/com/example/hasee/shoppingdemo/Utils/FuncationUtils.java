package com.example.hasee.shoppingdemo.Utils;

import io.reactivex.functions.Function;

/**
 * Created by Lmz on 2019/05/14
 *  function 工具类。 用于RxJava 获取数据后 返回类型。
 */
public class FuncationUtils<R extends EntityUtils<T>,T> implements Function<R,T> {
    @Override
    public T apply(R bean) {
        if (bean.getCode().equals("200")){
            return bean.getResult();
        }else if (bean.getResult() == null){
            throw  new RuntimeException("数据为空");
        }else {
            throw  new RuntimeException("code:"+bean.getCode()+"errorMess:"+bean.getMsg());
        }
    }
}
