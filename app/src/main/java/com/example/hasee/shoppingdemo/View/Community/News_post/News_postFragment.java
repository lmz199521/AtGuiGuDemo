package com.example.hasee.shoppingdemo.View.Community.News_post;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.shoppingdemo.Bean.NewPostBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class News_postFragment extends Fragment implements ContentPresenter.INewsPostView{

    private ContentPresenter.INewsPostPresenter postPresenter;
    private RecyclerView recyclerView;
    private NewPostAdapter adapter;
    public News_postFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_post, container, false);
        recyclerView =view.findViewById(R.id.community_newpost_recyclerview);
        LinearLayoutManager manager =new LinearLayoutManager(MyApplication.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter =new NewPostAdapter();
        recyclerView.setAdapter(adapter);



        //获取数据
        postPresenter =new NewPostPresenter(this);
        postPresenter.getNewsPostData();
        return view;
    }

    @Override
    public void onGetNewsPostDataSuccess(NewPostBean bean) {
        List<NewPostBean.ResultBean> beans = bean.getResult();
        adapter.Refresh(beans);
    }

    @Override
    public void onGetNewPostDataFailure(String ErrorMess) {
        System.out.println("新帖页面==错误信息："+ErrorMess);
    }

    @Override
    public void OnShowLoading() {

    }

    @Override
    public void OnHineLoading() {

    }
}
