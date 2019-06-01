package com.example.hasee.shoppingdemo.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.MyApplication;

/**
 * Created by Lmz on 2019/05/24
 *  检查 状态工具类
 *  1. 创建单例
 *  2. 检查网络的方法
 */
public class CheckStatusUtils {
    private static  CheckStatusUtils instance;
    private static ConnectivityManager manager;
    public CheckStatusUtils(Context context) {
            manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public static CheckStatusUtils getInstance(Context context) {
        CheckStatusUtils utils =instance;
        if (utils== null){
                synchronized (CheckStatusUtils.class){
                    utils =instance;
                    if (utils == null){
                        utils =new CheckStatusUtils(context.getApplicationContext());
                        instance =utils;
                    }
                }
        }
        return utils;
    }

    /**
     * 网络检查的方法，判断是否由网
     */

    public static boolean CheckNetWork(){
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo!=null){
            boolean infoAvailable = networkInfo.isAvailable();
            if (infoAvailable){
                Log.d("network","当前状态有网络");
                return true;
            }else {
                Log.d("network","当前状态没有网络");
                Toast t =Toast.makeText(MyApplication.getContext(), "当前网络连接断了", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,100);
                t.show();
                return false;
            }
        }else {
            Toast toast= Toast.makeText(MyApplication.getContext(), "当前网络连接断了,请确保有网络在进行操作", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,100);
            toast.show();
            Log.d("network","当前状态以断开网络");
            return false;
        }
    }

    /**
     * 检查是否为wifi网络
     */
    public static boolean CheckNetWorkWifi(){
        return  false;
    }


    /**
     * 吐司 工具类
     */
    public static void ToastMess(String mess){
        Toast t =Toast.makeText(MyApplication.getContext(), mess, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,100);
        t.show();
    }





}
