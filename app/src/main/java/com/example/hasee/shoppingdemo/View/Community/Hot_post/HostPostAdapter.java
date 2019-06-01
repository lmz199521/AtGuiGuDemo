package com.example.hasee.shoppingdemo.View.Community.Hot_post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Bean.HotPostBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/10
 */
public class HostPostAdapter extends RecyclerView.Adapter<HostPostAdapter.Hot_Viewhoder>{

    private Context context;

    private List<HotPostBean.ResultBean> list = new ArrayList<>();
    public void Refresh(List<HotPostBean.ResultBean> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Hot_Viewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_hotpost, viewGroup, false);
        return new Hot_Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hot_Viewhoder vh, int i) {
        HotPostBean.ResultBean bean = list.get(i);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getAvatar())
                .into(vh.avatar);
        vh.username.setText(bean.getUsername());
        vh.title.setText(bean.getSaying());
        vh.like.setText(bean.getLikes());
        vh.mess.setText(bean.getComments());
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getFigure())
                .placeholder(R.drawable.a3)
                .into(vh.img);
        vh.time.setText(bean.getAdd_time());

        List<String> listBean =new ArrayList<>();
        String is_hot = bean.getIs_hot();
        String Is_top = bean.getIs_top();
        String is_essence = bean.getIs_essence();
        if ("1".equals(is_hot)){
            listBean.add("热门");
        }
        if ("1".equals(Is_top)){
            listBean.add("置顶");
        }
        if ("1".equals(is_essence)){
            listBean.add("精华");
        }
        FlexboxLayoutManager manager =new FlexboxLayoutManager(context);
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setJustifyContent(JustifyContent.FLEX_START);
        vh.recyclerView.setLayoutManager(manager);
        Hot_Post_item_Adapter adapter =new Hot_Post_item_Adapter(listBean);
        vh.recyclerView.setAdapter(adapter);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Hot_Viewhoder extends RecyclerView.ViewHolder{
        ImageView img,avatar;
        TextView username,time,title,like,mess;
        RecyclerView recyclerView;
        public Hot_Viewhoder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.hot_post_pic);
            avatar =itemView.findViewById(R.id.hot_post_avatar);
            username=itemView.findViewById(R.id.hot_post_username);
            time =itemView.findViewById(R.id.hot_post_time);
            title =itemView.findViewById(R.id.hot_post_title);
            like =itemView.findViewById(R.id.hot_post_like);
            mess =itemView.findViewById(R.id.hot_post_mess);
            recyclerView =itemView.findViewById(R.id.hot_post_flexbox);
        }
    }
}
