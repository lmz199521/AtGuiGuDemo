package com.example.hasee.shoppingdemo.Activity.goodslist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.GoodsInfoActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsInfoBean;
import com.example.hasee.shoppingdemo.Bean.GoodsListBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/15
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListViewhoder>{

    private Context context;
    private List<GoodsListBean.ResultBean.PageDataBean> list =new ArrayList<>();
    public void Refresh(List<GoodsListBean.ResultBean.PageDataBean> list){
        this.list =list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsListViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods_list, viewGroup, false);
        return new GoodsListViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsListViewhoder viewhoder, int i) {

        final GoodsListBean.ResultBean.PageDataBean bean = list.get(i);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getFigure())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(viewhoder.pic);
        viewhoder.price.setText("￥"+bean.getCover_price());
        viewhoder.title.setText(bean.getName());

        viewhoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+bean.getName(), Toast.LENGTH_SHORT).show();
                GoodsInfoBean goods =new GoodsInfoBean(Integer.parseInt(bean.getP_catalog_id()),bean.getName(),bean.getCover_price(),bean.getFigure());
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("goods",goods);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GoodsListViewhoder extends RecyclerView.ViewHolder{
        ImageView pic;
        TextView title,price;
        public GoodsListViewhoder(@NonNull View itemView) {
            super(itemView);
            pic =itemView.findViewById(R.id.item_goods_list_pic);
            title =itemView.findViewById(R.id.item_goods_list_title);
            price =itemView.findViewById(R.id.item_goods_list_price);
        }
    }
}
