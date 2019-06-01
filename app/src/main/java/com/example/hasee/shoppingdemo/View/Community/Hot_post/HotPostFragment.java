package com.example.hasee.shoppingdemo.View.Community.Hot_post;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Bean.HotPostBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotPostFragment extends Fragment implements ContentPresenter.IHostView{
    private ContentPresenter.IHotPostPresenter postPresenter;
    private RecyclerView recyclerView;
    private HostPostAdapter adapter;

    public HotPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot_post, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        recyclerView =view.findViewById(R.id.hotpost_Recyclerview);
        //设置热门的布局管理者
        LinearLayoutManager hotpostManger =new LinearLayoutManager(MyApplication.getContext());
        hotpostManger.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(hotpostManger);
        adapter =new HostPostAdapter();
        recyclerView.setAdapter(adapter);
        postPresenter = new HotPostPresenter(this);
        postPresenter.getHotPostData();

    }

    @Override
    public void onGetHotPostDataSuccess(HotPostBean hotPostBean) {
        List<HotPostBean.ResultBean> result = hotPostBean.getResult();
        adapter.Refresh(result);
    }

    @Override
    public void onGetHotPostDataFailure(String ErrorMess) {
        Toast.makeText(getContext(), "出现了错误："+ErrorMess, Toast.LENGTH_SHORT).show();
        System.out.println("错误原因："+ErrorMess);
    }

    @Override
    public void OnShowLoading() {

    }

    @Override
    public void OnHineLoading() {

    }
}
