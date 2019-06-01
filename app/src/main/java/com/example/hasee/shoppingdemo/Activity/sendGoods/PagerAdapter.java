package com.example.hasee.shoppingdemo.Activity.sendGoods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/30
 */
public class PagerAdapter extends FragmentStatePagerAdapter{
    List<SendGoodsBean> list;

    public PagerAdapter(FragmentManager fm,List<SendGoodsBean> list) {
        super(fm);
        this.list =list;
    }

    @Override
    public Fragment getItem(int i) {
        return CardFragment.newInstance(list.get(i));
    }

    @Override
    public int getCount() {
        return this.list.size();
    }
}
