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
import com.example.hasee.shoppingdemo.Activity.goodslist.GoodsListInfoActivity;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.List;

/**
 * Created by Lmz on 2019/05/12
 */
public class Channel_Adapter extends BaseAdapter{
    private Context context;
    private  List<HomeBean.ResultBean.ChannelInfoBean> data;
    public Channel_Adapter(Context context, List<HomeBean.ResultBean.ChannelInfoBean> data) {
        this.context =context;
        this.data=data;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        GridViewgoder vh;
        if (convertView == null){
            vh =new GridViewgoder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_gride,parent,false);
            vh.img =convertView.findViewById(R.id.item_home_gride_img);
            vh.textView=convertView.findViewById(R.id.item_home_gride_name);
            convertView.setTag(vh);
        }else {
            vh = (GridViewgoder) convertView.getTag();
        }
        String name = data.get(position).getChannel_name();
        vh.textView.setText(name);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+data.get(position).getImage()).into(vh.img);

        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channel_id = data.get(position).getValue().getChannel_id();
                int anInt = Integer.parseInt(channel_id);
                Intent intent =new Intent(context, GoodsListInfoActivity.class);
                intent.putExtra("list_id",anInt);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        });


        return convertView;
    }
    class GridViewgoder {
        ImageView img;
        TextView textView;
    }


}
