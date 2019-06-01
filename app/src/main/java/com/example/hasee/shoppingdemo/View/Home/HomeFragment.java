package com.example.hasee.shoppingdemo.View.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Activity.search.SearchActivity;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ContentPresenter.IHomeView {


  //  @BindView(R.id.home_mess)
    TextView homeMess;
    private ImageView topBtn;
    private RecyclerView homeRecycler;
    private EditText search;
    private ContentPresenter.IHomePresenter iHomePresenter;
    private HomeFragmentAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeRecycler = view.findViewById(R.id.home_recycler);
        topBtn = view.findViewById(R.id.home_top);
        homeMess=view.findViewById(R.id.home_mess);
        search =view.findViewById(R.id.home_edit);
        initlistener();
        iHomePresenter = new HomePresenter(this);
        iHomePresenter.getHomeData();
        return view;
    }

    private void initlistener() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onGetHomeDataSuccess(HomeBean homeBean) {
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ChannelInfoBean> gride = result.getChannel_info();
        adapter = new HomeFragmentAdapter(MyApplication.getContext(),result);
        homeRecycler.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(MyApplication.getContext(), 1);
        homeRecycler.setLayoutManager(manager);

        //设置跨度大小的监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                //判断页面显示的多布局为第几个，当显示到是前4个是，隐藏按钮 。显示的是后四个时也就等于说滑动到底部了，就显示按钮
                if (i<=4){
                    topBtn.setVisibility(View.GONE);
                }else {
                    topBtn.setVisibility(View.VISIBLE);
                }
                return 1;
            }
        });

        topBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回顶部
                homeRecycler.scrollToPosition(0);
            }
        });

    }

    @Override
    public void onGetDataFailure(String ErrorMess) {
        Toast.makeText(getContext(), "出现了错误：" + ErrorMess, Toast.LENGTH_SHORT).show();
        System.out.println("错误原因：" + ErrorMess);
    }

    @Override
    public void OnShowLoading() {

    }

    @Override
    public void OnHineLoading() {

    }
}
