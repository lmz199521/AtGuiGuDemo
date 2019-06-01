package com.example.hasee.shoppingdemo.View.Type.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.Bean.TypeBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.example.hasee.shoppingdemo.View.Type.adapter.Type_child_adapter;
import com.example.hasee.shoppingdemo.View.Type.adapter.Type_child_right_adapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Type_child_Fragment extends Fragment implements ContentPresenter.ITypeView,Type_child_adapter.TypeItemClick,ContentPresenter.ItypeInfoView{

    private ContentPresenter.ITypePresenter presenter;
    private ContentPresenter.ITypeInfoPresenter infoPresenter;
    private RecyclerView leftR,rightR;
    private Type_child_adapter leftadapter;
    private Type_child_right_adapter right_adapter;
    public Type_child_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_child, container, false);
        initview(view);


        presenter =new TypePresenter(this);
        infoPresenter =new TypeInfoPresenter(this);
        presenter.getITypeData();
        return view;
    }
    private void initview(View view){
        leftR =view.findViewById(R.id.type_child_left_recyclerview);
        LinearLayoutManager leftm =new LinearLayoutManager(MyApplication.getContext());
        leftm.setOrientation(LinearLayoutManager.VERTICAL);
        leftR.setLayoutManager(leftm);
        leftR.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        leftadapter = new Type_child_adapter();
        leftadapter.setClick(this);
        leftR.setAdapter(leftadapter);

        rightR = view.findViewById(R.id.type_child_right_recyclerview);
        GridLayoutManager rightmanger =new GridLayoutManager(MyApplication.getContext(),1);
        rightmanger.setOrientation(GridLayoutManager.VERTICAL);
        rightR.setLayoutManager(rightmanger);



    }

    @Override
    public void onGetTypeDataSuccess(TypeBean bean) {
        List<TypeBean.ResultBean> beanlist = bean.getResult();
        System.out.println("type:"+beanlist.size());
        leftadapter.Refresh(beanlist);
    }

    @Override
    public void onGetTypeFailure(String errorMess) {
        Toast.makeText(MyApplication.getContext(), "左侧列表数据请求失败.错误原因："+errorMess, Toast.LENGTH_SHORT).show();
        System.out.println("左侧列表数据请求失败.错误原因："+errorMess);
    }

    @Override
    public void ItemClick(String url) {
            String GoodsUrl = Constans.BASE_URL_JSON+url;
            infoPresenter.getTypeInfoData(GoodsUrl);
    }

    /**
     *   获取到了右侧数据详情
     * @param bean
     */
    @Override
    public void onGetTypeInfoDataSuccess(JacketBean bean) {
        List<JacketBean.ResultBean> result = bean.getResult();
        System.out.println("right_info"+result.size());
      //  right_adapter.Refresh(result);
      right_adapter =new Type_child_right_adapter(MyApplication.getContext(),result);
        rightR.setAdapter(right_adapter);

    }

    @Override
    public void onGetTypeInfoFailure(String errorMess) {
        Toast.makeText(MyApplication.getContext(), "右侧数据请求失败.错误原因："+errorMess, Toast.LENGTH_SHORT).show();
        System.out.println("右侧数据请求失败.错误原因："+errorMess);
    }

    @Override
    public void OnShowLoading() {

    }

    @Override
    public void OnHineLoading() {

    }
}
