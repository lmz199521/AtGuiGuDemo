package com.example.hasee.shoppingdemo.Activity.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.search.SearchBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/23
 */
public class SearchGoodsAdapter extends RecyclerView.Adapter<SearchGoodsAdapter.SearchGoodsViewhoder>{
    private Context context;
    private List<SearchBean.ResultBean.ChildBean> refresh = new ArrayList<>();

    public void setRefresh(List<SearchBean.ResultBean.ChildBean> refresh) {
        this.refresh.clear();
        this.refresh = refresh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchGoodsViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_goodsinfo, viewGroup, false);
        return new SearchGoodsViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchGoodsViewhoder vh, int i) {
        SearchBean.ResultBean.ChildBean bean = refresh.get(i);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getPic()).placeholder(R.drawable.ic_launcher_background)
                                .into(vh.img);
        vh.name.setText(bean.getName());


    }

    @Override
    public int getItemCount() {
        return refresh.size();
    }



    class SearchGoodsViewhoder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;

        public SearchGoodsViewhoder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.item_search_goods_img);
            name =itemView.findViewById(R.id.item_search_goods_name);

        }
    }
}
