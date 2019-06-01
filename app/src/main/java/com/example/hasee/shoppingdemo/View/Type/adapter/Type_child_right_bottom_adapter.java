package com.example.hasee.shoppingdemo.View.Type.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Lmz on 2019/05/14
 */
public class Type_child_right_bottom_adapter extends RecyclerView.Adapter<Type_child_right_bottom_adapter.BottomViewHoder>{

    private Context context;
    private List<JacketBean.ResultBean.ChildBean> data = new ArrayList<>();
    public Type_child_right_bottom_adapter(Context context, List<JacketBean.ResultBean.ChildBean> data) {
        this.context =context;
        this.data = data;
    }

    @NonNull
    @Override
    public BottomViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_child_right_top, viewGroup, false);
        return new BottomViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomViewHoder vh, int i) {
        final JacketBean.ResultBean.ChildBean bean = data.get(i);
        vh.price.setText(bean.getName());
        vh.price.setTextColor(Color.BLACK);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getPic())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(vh.img);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInfoBean goods =new GoodsInfoBean(Integer.parseInt(bean.getP_catalog_id()),bean.getName(),"26",bean.getPic());
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("goods",goods);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BottomViewHoder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView price;

        public BottomViewHoder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.item_type_child_hot_pic);
            price=itemView.findViewById(R.id.item_type_child_hot_price);
        }
    }
}
