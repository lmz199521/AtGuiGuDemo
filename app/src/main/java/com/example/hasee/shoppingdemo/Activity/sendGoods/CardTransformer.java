package com.example.hasee.shoppingdemo.Activity.sendGoods;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import retrofit2.http.POST;

/**
 * Created by Lmz on 2019/05/30
 */
public class CardTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position<0){
            page.setTranslationX(-position*page.getWidth());
            //调整位置
            float scale =(page.getWidth()+40* position)/page.getWidth();
            page.setScaleY(scale);
            page.setScaleX(scale);
            page.setTranslationZ(position);
            page.setTranslationY(-position*40);
        }
    }
}
