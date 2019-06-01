package com.example.hasee.shoppingdemo.View.Type.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.shoppingdemo.Bean.TypeTagBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.View.Type.adapter.Type_tag_adapter;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

/**
 * A simple {@link Fragment} subclass.
 */
public class Type_tag_Fragment extends Fragment implements ContentPresenter.ITypeTagView{

    private RecyclerView recyclerView;
    private ContentPresenter.ITypeTagPresenter presenter;
    private Type_tag_adapter adapter;
    public Type_tag_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_tag, container, false);
        recyclerView =view.findViewById(R.id.type_tag_recyclerview);
        initdata();
        presenter =new TypeTagPresenter(this);
        presenter.getITypeTagData();
        return view;
    }

    private void initdata() {
        FlexboxLayoutManager flexboxLayoutManager=new FlexboxLayoutManager(MyApplication.getContext());
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(flexboxLayoutManager);
        adapter =new Type_tag_adapter();
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onGetTypeTagDataSuccess(TypeTagBean bean) {
        System.out.println("type:"+bean.getResult().size());
        adapter.Refresh(bean.getResult());
    }

    @Override
    public void onGetTypeTagDataFailure(String ErrorMess) {
        System.out.println("type:"+ErrorMess);
    }

    @Override
    public void OnShowLoading() {

    }

    @Override
    public void OnHineLoading() {

    }
}
