package com.example.hasee.shoppingdemo.View.Home.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.GoodsInfoActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsInfoBean;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Lmz on 2019/05/12
 */
public class Seckill_Adapter extends RecyclerView.Adapter<Seckill_Adapter.Recycler_ViewHoder>{

    private Context context;
    private List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list;
    public Seckill_Adapter(List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list =list;


    }

    @NonNull
    @Override
    public Recycler_ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_seckill_recyclerview, viewGroup, false);
        return new Recycler_ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_ViewHoder vh, int i) {
        final HomeBean.ResultBean.SeckillInfoBean.ListBean bean = list.get(i);
        vh.newprice.setText(bean.getCover_price());
        vh.oldprice.setText(bean.getOrigin_price());
        vh.oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getFigure()).into(vh.img);
        //textView1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInfoBean goods =new GoodsInfoBean(Integer.parseInt(bean.getProduct_id()),bean.getName(),bean.getCover_price(),bean.getFigure());
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("goods",goods);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Recycler_ViewHoder extends RecyclerView.ViewHolder{
        TextView newprice,oldprice;
        ImageView img;
        public Recycler_ViewHoder(@NonNull View itemView) {
            super(itemView);
            newprice =itemView.findViewById(R.id.item_home_seckill_newprice);
            oldprice =itemView.findViewById(R.id.item_home_seckill_oldprice);
            img =itemView.findViewById(R.id.item_home_seckill_img);
        }
    }
}
