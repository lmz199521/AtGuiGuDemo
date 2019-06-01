package com.example.hasee.shoppingdemo.View.Type;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.View.Type.fragment.Type_child_Fragment;
import com.example.hasee.shoppingdemo.View.Type.fragment.Type_tag_Fragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends Fragment {

    private SegmentTabLayout tabLayout;
    private FrameLayout frameLayout;
    private ImageView img;

    public TypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type, container, false);
        initview(view);
        initdata();
        return view;
    }

    private void initdata() {
        final String[] tag ={"分类","标签"};
        tabLayout.setTabData(tag);//设置标签
        tabLayout.setIndicatorColor(Color.RED); //选中的颜色
        tabLayout.setTabPadding(25.0f);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(MyApplication.getContext(), "选中了"+tag[position], Toast.LENGTH_SHORT).show();
                Fragment fragment =null;
                switch (position){
                    case 0:
                        fragment =new Type_child_Fragment();
                        break;
                    case 1:
                        fragment = new Type_tag_Fragment();
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.type_framelayout,fragment).commit();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //设置默认选中的标签页面
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.type_framelayout,new Type_child_Fragment()).commit();

    }

    private void initview(View view) {
        tabLayout =view.findViewById(R.id.type_tablayout);
        frameLayout =view.findViewById(R.id.type_framelayout);
        img =view.findViewById(R.id.type_img);
    }

}
