package com.example.hasee.shoppingdemo.View.Type.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.Bean.TypeBean;
import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/14
 */
public class Type_child_adapter extends RecyclerView.Adapter<Type_child_adapter.Type_Viewhoder>{

    private Context context;
    private List<TypeBean.ResultBean> beanlist = new ArrayList<>();
    private TypeItemClick click;
    public void Refresh(List<TypeBean.ResultBean> beanlist){
        this.beanlist =beanlist;
        notifyDataSetChanged();
    }

    public void setClick(TypeItemClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public Type_Viewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_child, viewGroup, false);
        return new Type_Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Type_Viewhoder viewhoder, final int i) {
            viewhoder.goodsName.setText(beanlist.get(i).getTag_name());
            viewhoder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 click.ItemClick(beanlist.get(i).getUri());
                }
            });

    }

    @Override
    public int getItemCount() {
        return beanlist.size();
    }

    class Type_Viewhoder extends RecyclerView.ViewHolder{
        TextView goodsName;
        public Type_Viewhoder(@NonNull View itemView) {
            super(itemView);
            goodsName =itemView.findViewById(R.id.item_type_child_name);
        }
    }

    public interface TypeItemClick{
        void ItemClick(String url);
    }
}
