package com.example.hasee.shoppingdemo.View.Community.News_post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Bean.NewPostBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lmz on 2019/05/15
 */
public class NewPostAdapter extends RecyclerView.Adapter<NewPostAdapter.NewPostViewhoder>{

    private Context context;
    private List<NewPostBean.ResultBean> beans =  new ArrayList<>();
    private List<String> comment_list;
    public void Refresh(List<NewPostBean.ResultBean> beans){
        this.beans =beans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewPostViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_newpost, viewGroup, false);
        return new NewPostViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewPostViewhoder vh, int i) {
        NewPostBean.ResultBean bean = beans.get(i);

        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getAvatar())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(vh.avatar);
        vh.username.setText(bean.getUsername());
        vh.title.setText(bean.getSaying());
        vh.like.setText(bean.getLikes());
        vh.mess.setText(bean.getIs_essence());
        vh.time.setText(bean.getAdd_time());
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getFigure())
                .placeholder(R.drawable.ic_launcher_background)
                .into(vh.img);
        comment_list= bean.getComment_list();
        if (comment_list!=null && comment_list.size()>0){
            vh.danmakuView.setVisibility(View.VISIBLE);
                List<IDanmakuItem> list =new ArrayList<>();
              for (int j=0;j<comment_list.size();j++){
                    IDanmakuItem iDanmakuItem =new DanmakuItem(context,comment_list.get(j),vh.danmakuView.getWidth());
                    list.add(iDanmakuItem);
              }
            Collections.shuffle(comment_list);
            vh.danmakuView.addItem(list,true);
            vh.danmakuView.show();
        }else {
            vh.danmakuView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class NewPostViewhoder extends RecyclerView.ViewHolder{
        ImageView img,avatar;
        TextView username,time,title,like,mess;
        DanmakuView danmakuView;
        public NewPostViewhoder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.new_post_pic);
            avatar =itemView.findViewById(R.id.new_post_avatar);
            username=itemView.findViewById(R.id.new_post_username);
            time =itemView.findViewById(R.id.new_post_time);
            title =itemView.findViewById(R.id.new_post_title);
            like =itemView.findViewById(R.id.new_post_like);
            mess =itemView.findViewById(R.id.new_post_mess);
            danmakuView = itemView.findViewById(R.id.new_post_danmaku);

        }
    }
}
