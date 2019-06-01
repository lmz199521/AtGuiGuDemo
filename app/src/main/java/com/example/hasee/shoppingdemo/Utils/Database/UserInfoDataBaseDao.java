package com.example.hasee.shoppingdemo.Utils.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hasee.shoppingdemo.Bean.UserBean;

/**
 * Created by Lmz on 2019/05/21
 */
public class UserInfoDataBaseDao {
       private Context context;
       private UserInfoDataBase dataBase;

    public UserInfoDataBaseDao(Context context) {
        this.context = context;
        dataBase = new UserInfoDataBase(context);
    }

    //增加用户
    public void AddUser(UserBean bean){
        SQLiteDatabase db = dataBase.getReadableDatabase();
        ContentValues values =new ContentValues();
        values.put(UserInfoDataBase.ID,bean.getId());
        values.put(UserInfoDataBase.NAME,bean.getName());
        values.put(UserInfoDataBase.PWD,bean.getPassword());
        values.put(UserInfoDataBase.AVATAR,(String) bean.getAvatar());
        db.insert(UserInfoDataBase.TABLE_NAME,null,values);
    }
    // 删
    public void DeleteUser(String id){
        SQLiteDatabase db = dataBase.getReadableDatabase();
        db.delete(UserInfoDataBase.TABLE_NAME,UserInfoDataBase.ID+"=?",new String[]{id});
    }
    // 改
    public void UpDateUser(UserBean bean){
        SQLiteDatabase db = dataBase.getReadableDatabase();
        ContentValues values =new ContentValues();
        values.put(UserInfoDataBase.ID,bean.getId());
        values.put(UserInfoDataBase.NAME,bean.getName());
        values.put(UserInfoDataBase.PWD,bean.getPassword());
        values.put(UserInfoDataBase.AVATAR,(String) bean.getAvatar());
        db.replace(UserInfoDataBase.TABLE_NAME,null,values);
    }
    // 查
    public UserBean SelectUser(){
        SQLiteDatabase db = dataBase.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserInfoDataBase.TABLE_NAME, null);
        UserBean bean = null;
        while (cursor.moveToNext()){
            bean =new UserBean();
            String id = cursor.getString(cursor.getColumnIndex(UserInfoDataBase.ID));
            String name = cursor.getString(cursor.getColumnIndex(UserInfoDataBase.NAME));
            String pwd = cursor.getString(cursor.getColumnIndex(UserInfoDataBase.PWD));
            String avatar = cursor.getString(cursor.getColumnIndex(UserInfoDataBase.AVATAR));
            bean.setId(id);
            bean.setName(name);
            bean.setPassword(pwd);
            bean.setAvatar(avatar);
        }
        db.close();
        return bean;

    }

}
