package com.example.hasee.shoppingdemo.View.Community.Hot_post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/10
 */
public class Hot_Post_item_Adapter extends RecyclerView.Adapter<Hot_Post_item_Adapter.Item_ViewHoder>{

    private Context context;
    private List<String> list =new ArrayList<>();

    public Hot_Post_item_Adapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Item_ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot_post_text, viewGroup, false);
        return new Item_ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_ViewHoder vh, int i) {
            vh.textView.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Item_ViewHoder extends RecyclerView.ViewHolder{
        TextView textView;
        public Item_ViewHoder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.hot_post_item_text);
        }
    }
}
