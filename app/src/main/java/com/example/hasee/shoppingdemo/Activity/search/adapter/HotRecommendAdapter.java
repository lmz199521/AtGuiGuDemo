package com.example.hasee.shoppingdemo.Activity.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/23
 */
public class HotRecommendAdapter extends RecyclerView.Adapter<HotRecommendAdapter.Hot_Recommend_Viewhoder>{

    private Context context;
    private List<JacketBean.ResultBean.HotProductListBean> list = new ArrayList<>();



    public void setRefresh(List<JacketBean.ResultBean.HotProductListBean> list) {
        this.list=list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public Hot_Recommend_Viewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_hot_recommend, viewGroup, false);
        return new Hot_Recommend_Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hot_Recommend_Viewhoder vh, int i) {

        JacketBean.ResultBean.HotProductListBean bean = list.get(i);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getFigure()).into(vh.imageView);
        vh.textView.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class Hot_Recommend_Viewhoder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public Hot_Recommend_Viewhoder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_search_hot_recommend_img);
            textView =itemView.findViewById(R.id.item_search_hot_recommend_name);
        }
    }
}
