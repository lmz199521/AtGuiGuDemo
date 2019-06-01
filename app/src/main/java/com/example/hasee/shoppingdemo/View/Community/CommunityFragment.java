package com.example.hasee.shoppingdemo.View.Community;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.View.Community.News_post.News_postFragment;
import com.example.hasee.shoppingdemo.View.Community.Hot_post.HotPostFragment;
import com.example.hasee.shoppingdemo.View.Community.adapter.CommunityAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list =new ArrayList<>();
    private List<String> tagList =new ArrayList<>();
    private CommunityAdapter adapter;
    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        initview(view);
        initdata();
        return view;
    }

    private void initdata() {
        String[] tags = new String[]{"新帖","热帖"};
        tabLayout.addTab(tabLayout.newTab().setText("新帖"));
        tabLayout.addTab(tabLayout.newTab().setText("热帖"));
        list.add(new News_postFragment());
        list.add(new HotPostFragment());
        tagList.add("新帖");
        tagList.add("热帖");
        adapter= new CommunityAdapter(getChildFragmentManager(),list,tagList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    private void initview(View view) {
        tabLayout =view.findViewById(R.id.community_tablayout);
        viewPager = view.findViewById(R.id.community_viewpager);
    }

}
