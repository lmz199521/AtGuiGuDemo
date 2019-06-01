package com.example.hasee.shoppingdemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hasee.shoppingdemo.Bean.LoginBean;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.MyApplication;

/**
 * Created by Lmz on 2019/05/21
 */
public class SharePreUtils {
        private static  final String name ="user";
        private static SharedPreferences sp;
        public static void init(Context context){
            sp = MyApplication.getContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        }

        public static void saveToken(String token){
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(Constans.TOKEN,token).apply(); //apply 和commit 效果一样
        }
        public static String getToken(){
            String token = sp.getString(Constans.TOKEN, "");
            return token;
        }



    public static UserBean ResultToUserBean(LoginBean.ResultBean result) {
            if (result==null){
                return null;
            }
        UserBean userBean = new UserBean();
        userBean.setAvatar(result.getAvatar()); //头像
        userBean.setPassword(result.getPassword());//密码
        userBean.setName(result.getName());//名字
        userBean.setId(result.getId());//ID
        userBean.setPhone(result.getPhone());//电话
        userBean.setToken(result.getToken());//token
        userBean.setAddress(result.getAddress());//地址
        userBean.setEmail(result.getEmail());//邮箱
        userBean.setMoney(result.getMoney());//金币
        userBean.setPoint(result.getPoint());//积分
        return userBean;
    }

}
