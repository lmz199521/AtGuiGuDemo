package com.example.hasee.shoppingdemo.Activity.sendGoods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

/**
 * Created by Lmz on 2019/05/30
 */
public class CardFragment extends Fragment{

    private View rootView;
    private TextView goodsnumber,goodsprice;
    private ImageView goodsPic;
    private SendGoodsBean info;
    public static CardFragment newInstance(SendGoodsBean bean){
            CardFragment cardFragment =new CardFragment();
            Bundle bundle =new Bundle();
            bundle.putSerializable("info",bean);
            cardFragment.setArguments(bundle); //fragment之间传递参数
            return cardFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.item_cardview_sendgoods,container,false);
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(rootView);
        info = (SendGoodsBean) getArguments().getSerializable("info");
        goodsprice.setText("订单价格："+info.getTotalPrice());
    }
    private void initview(View rootView) {
        goodsnumber =rootView.findViewById(R.id.sendgoods_goodsnumber);
        goodsprice = rootView.findViewById(R.id.sendgoods_price);
        goodsPic =rootView.findViewById(R.id.sendgoods_goodpic);
    }
}
