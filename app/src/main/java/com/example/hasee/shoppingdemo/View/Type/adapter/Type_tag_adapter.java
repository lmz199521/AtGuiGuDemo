package com.example.hasee.shoppingdemo.View.Type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.Bean.TypeTagBean;
import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lmz on 2019/05/14
 */
public class Type_tag_adapter extends RecyclerView.Adapter<Type_tag_adapter.Tag_Viewhoder>{
    private Context context;
    private List<TypeTagBean.ResultBean> result = new ArrayList<>();
    public void Refresh(List<TypeTagBean.ResultBean> result){
        this.result =result;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Tag_Viewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_type_tag, viewGroup, false);
        return new Tag_Viewhoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Tag_Viewhoder vh, int i) {
        TypeTagBean.ResultBean bean = result.get(i);
        vh.title.setText(bean.getName());
        Random random =new Random();
        int i1 = random.nextInt(256);
        int i2 = random.nextInt(256);
        int i3 = random.nextInt(256);
        vh.title.setTextColor(Color.rgb(i1,i2,i3));

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class Tag_Viewhoder extends RecyclerView.ViewHolder{
        TextView title;
        public Tag_Viewhoder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.item_type_tag_title);
        }
    }
}
