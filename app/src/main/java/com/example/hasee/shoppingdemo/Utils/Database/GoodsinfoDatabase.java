package com.example.hasee.shoppingdemo.Utils.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lmz on 2019/05/16
 */
public class GoodsinfoDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "goodsinfo";
    public static final String GOODS_ID = "_id";
    public static final String GOODS_NAME = "name";
    public static final String GOODS_PRICE = "price";
    public static final String GOODS_PIC = "pic";
    public static final String GOODS_NUMBER = "number";
    public static final String USER_NAME = "userName";

    public GoodsinfoDatabase(Context context) {
        super(context, "goods.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + GOODS_ID + " integer primary key ," + GOODS_NAME + " text,"
                + GOODS_PRICE + " text," + GOODS_PIC + " text," + GOODS_NUMBER + " text," + USER_NAME + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
