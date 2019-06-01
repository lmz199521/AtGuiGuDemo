package com.example.hasee.shoppingdemo.Utils.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lmz on 2019/05/21
 */
public class UserInfoDataBase extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "UserInfo";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PWD = "pwd";
    public static final String AVATAR = "avatar";

    public UserInfoDataBase(Context context) {
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("create table " + TABLE_NAME + "(" + ID + " text primary key," + NAME + " text," + PWD + " text," + AVATAR + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
