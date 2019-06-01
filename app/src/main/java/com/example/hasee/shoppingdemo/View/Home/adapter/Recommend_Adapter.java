package com.example.hasee.shoppingdemo.View.Home.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Lmz on 2019/05/13
 */
public class Recommend_Adapter extends RecyclerView.Adapter<Recommend_Adapter.GridView_Viewhoder>{

    private Context context;
    private List<HomeBean.ResultBean.RecommendInfoBean> data = new ArrayList<>();
    public Recommend_Adapter(List<HomeBean.ResultBean.RecommendInfoBean> data) {
        this.data =data;
    }


    @NonNull
    @Override
    public GridView_Viewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommend_gride, viewGroup, false);
        return new GridView_Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridView_Viewhoder vh, int i) {
        final HomeBean.ResultBean.RecommendInfoBean bean = data.get(i);
        vh.tPrice.setText(data.get(i).getCover_price());
            vh.tName.setText(data.get(i).getName());

            Glide.with(context).load(Constans.BASE_URl_IMAGE+data.get(i).getFigure()).placeholder(R.drawable.ic_launcher_background).into(vh.img);
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
        return data.size();
    }

    class GridView_Viewhoder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tName,tPrice;

        public GridView_Viewhoder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.item_home_recommend_gride_img);
            tName =itemView.findViewById(R.id.item_home_recommend_gride_name);
            tPrice =itemView.findViewById(R.id.item_home_recommend_gride_price);
        }
    }
}
