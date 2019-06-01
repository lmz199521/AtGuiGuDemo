package com.example.hasee.shoppingdemo;

import android.app.Application;
import android.content.Context;

import com.example.hasee.shoppingdemo.Utils.CheckStatusUtils;
import com.example.hasee.shoppingdemo.Utils.CrashMess;
import com.example.hasee.shoppingdemo.Utils.SharePreUtils;

import java.util.List;

/**
 * Created by Lmz on 2019/05/09
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context =this;
        CrashMess.getInstance(this);
        SharePreUtils.init(this);
        CheckStatusUtils.getInstance(this);


    }
    public static Context getContext(){
        return context;
    }
}
