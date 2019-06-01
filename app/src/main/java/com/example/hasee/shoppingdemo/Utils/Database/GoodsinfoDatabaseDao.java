package com.example.hasee.shoppingdemo.Utils.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Bean.GoodsinfoDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/16
 */
public class GoodsinfoDatabaseDao {

    private Context context;
    private GoodsinfoDatabase database;
    private SQLiteDatabase db = null;

    public GoodsinfoDatabaseDao(Context context) {
        this.context = context;
        database = new GoodsinfoDatabase(context);
        db = database.getReadableDatabase();
    }

    //增
    public void AddData(GoodsinfoDataBean bean, String name) {

        ContentValues values = new ContentValues();
        values.put(GoodsinfoDatabase.GOODS_ID, bean.getId());
        values.put(GoodsinfoDatabase.GOODS_NAME, bean.getName());
        values.put(GoodsinfoDatabase.GOODS_NUMBER, bean.getNumber());
        values.put(GoodsinfoDatabase.GOODS_PIC, bean.getPic());
        values.put(GoodsinfoDatabase.GOODS_PRICE, bean.getPrice());
        values.put(GoodsinfoDatabase.USER_NAME, name);
        db.insert(GoodsinfoDatabase.TABLE_NAME, null, values);
    }

    // 删
    public void DeleteData(String id) {

        //根据ID 删除了数据
        db.delete(GoodsinfoDatabase.TABLE_NAME, GoodsinfoDatabase.GOODS_ID + "=?", new String[]{id});
    }

    // 改 //通过id 修改时数据
    public void UpdataData(String id, String number) {

        ContentValues values = new ContentValues();
        values.put(GoodsinfoDatabase.GOODS_NUMBER, number);
        db.update(GoodsinfoDatabase.TABLE_NAME, values, GoodsinfoDatabase.GOODS_ID + "=?", new String[]{id});
    }

    // 查
    public List<GoodsinfoDataBean> SelectData(String userName) {
        Cursor cursor = db.rawQuery("select * from " + GoodsinfoDatabase.TABLE_NAME + " where " + GoodsinfoDatabase.USER_NAME + "=?", new String[]{userName});

        // Cursor cursor = db.rawQuery("select * from " + GoodsinfoDatabase.TABLE_NAME, null);
        List<GoodsinfoDataBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_ID));
            String name = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_NAME));
            String price = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_PRICE));
            String pic = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_PIC));
            String number = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_NUMBER));
            GoodsinfoDataBean bean = new GoodsinfoDataBean(id, name, price, pic, number);
            list.add(bean);
        }
        return list;
    }

    //查询单个
    public GoodsinfoDataBean SelectGoods(int id) {
        Cursor cursor = db.rawQuery("select * from " + GoodsinfoDatabase.TABLE_NAME + " where _id = ?", new String[]{id + ""});

        GoodsinfoDataBean bean = null;
        while (cursor.moveToNext()) {
            int idd = cursor.getInt(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_ID));
            String name = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_NAME));
            String price = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_PRICE));
            String pic = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_PIC));
            String number = cursor.getString(cursor.getColumnIndex(GoodsinfoDatabase.GOODS_NUMBER));
            bean = new GoodsinfoDataBean(idd, name, price, pic, number);
        }

        return bean;
    }
}
