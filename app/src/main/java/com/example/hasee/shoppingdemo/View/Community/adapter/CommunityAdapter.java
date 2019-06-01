package com.example.hasee.shoppingdemo.View.Community.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/15
 */
public class CommunityAdapter extends FragmentPagerAdapter{
    private  List<Fragment> list =new ArrayList<>();
    private List<String> tagList = new ArrayList<>();
    public CommunityAdapter(FragmentManager fm, List<Fragment> list, List<String> tagList) {
        super(fm);
        this.list =list;
        this.tagList =tagList;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tagList.get(position);
    }
}
