package com.example.hasee.shoppingdemo.Utils;
import android.util.Log;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lmz on 2019/05/21
 * 设置token拦截器
 */
public class TokenInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest =null;
        String s = UserInfoManger.getInstance().GetToken();
        Log.d("token","TokenInterceptor=========token:"+s);
        if (UserInfoManger.getInstance().GetToken()!=null){
            newRequest =request.newBuilder().addHeader(Constans.TOKEN,UserInfoManger.getInstance().GetToken()).build();
            return chain.proceed(newRequest);
        }else {
            return chain.proceed(request);
        }

    }
}
