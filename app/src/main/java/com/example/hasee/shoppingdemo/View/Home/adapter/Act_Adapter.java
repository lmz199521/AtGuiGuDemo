package com.example.hasee.shoppingdemo.View.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.GoodsInfoActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsInfoBean;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Lmz on 2019/05/12
 */
public class Act_Adapter  extends PagerAdapter{
    private Context context;
    private List<HomeBean.ResultBean.ActInfoBean> data;

    public Act_Adapter(Context context, List<HomeBean.ResultBean.ActInfoBean> data) {
        this.context =context;
        this.data =data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view ==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView img=new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+data.get(position).getIcon_url()).into(img);
        //添加到容器
        container.addView(img);
   //     System.out.println("uri"+Constans.BASE_URl_IMAGE+data.get(position).getIcon_url());
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  GoodsInfoBean goods =new GoodsInfoBean(position,"剑网三","35",data.get(position).getIcon_url());
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("goods",goods);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/
                Toast.makeText(context, "没有这个商品了", Toast.LENGTH_SHORT).show();
            }
        });
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
