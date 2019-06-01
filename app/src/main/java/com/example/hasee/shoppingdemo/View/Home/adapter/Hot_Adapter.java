package com.example.hasee.shoppingdemo.View.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.GoodsInfoActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsInfoBean;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Lmz on 2019/05/13
 */
public class Hot_Adapter extends BaseAdapter {
    private Context context;
    private List<HomeBean.ResultBean.HotInfoBean> data =new ArrayList<>();
    public Hot_Adapter(Context context, List<HomeBean.ResultBean.HotInfoBean> data) {
            this.context =context;
            this.data =data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewhoder vh;
        if (convertView == null){
            vh =new GridViewhoder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_hot_grid,parent,false);
            vh.img=convertView.findViewById(R.id.item_home_hot_image);
            vh.tName=convertView.findViewById(R.id.item_home_hot_name);
            vh.tPrice =convertView.findViewById(R.id.item_home_hot_price);
            convertView.setTag(vh);
        }else {
            vh = (GridViewhoder) convertView.getTag();
        }
        final HomeBean.ResultBean.HotInfoBean bean = data.get(position);
        vh.tPrice.setText("￥"+data.get(position).getCover_price());
        vh.tName.setText(data.get(position).getName());
        Glide.with(context).load(Constans.BASE_URl_IMAGE+data.get(position).getFigure()).placeholder(R.drawable.ic_launcher_background).into(vh.img);

        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInfoBean goods =new GoodsInfoBean(Integer.parseInt(bean.getProduct_id()),bean.getName(),bean.getCover_price(),bean.getFigure());
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("goods",goods);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class GridViewhoder {
        ImageView img;
        TextView tName,tPrice;
    }
}
