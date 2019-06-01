package com.example.hasee.shoppingdemo.View.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.GoodsInfoActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsInfoBean;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.example.hasee.shoppingdemo.View.Home.adapter.Act_Adapter;
import com.example.hasee.shoppingdemo.View.Home.adapter.Channel_Adapter;
import com.example.hasee.shoppingdemo.View.Home.adapter.Hot_Adapter;
import com.example.hasee.shoppingdemo.View.Home.adapter.Recommend_Adapter;
import com.example.hasee.shoppingdemo.View.Home.adapter.Seckill_Adapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Lmz on 2019/05/12
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter{
    //设置页面上的6种类型
    public static final int BANNER=0; //轮播图类型
    public static final int CHANNEL=1; //频道类型
    public static final int ACT=2; //活动类型
    public static final int SECKILL=3; //秒杀类型
    public static final int RECOMMEND=4; //推荐类型
    public static final int HOT=5; //活动类型
    //设置当前页面的类型  默认为o
    private int currType =0;
    private Context context;
    private HomeBean.ResultBean resul ;
    //初始化布局用的
    private  LayoutInflater inflater;


    public HomeFragmentAdapter(Context context, HomeBean.ResultBean result) {
        this.context =context;
        this.resul =result;
        inflater = LayoutInflater.from(context);
    }

    //重写类型方法
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currType = BANNER;
                break;
            case CHANNEL:
                currType = CHANNEL;
                break;
            case ACT:
                currType = ACT;
                break;
            case SECKILL:
                currType = SECKILL;
                break;
            case RECOMMEND:
                currType = RECOMMEND;
                break;
            case HOT:
                currType = HOT;
                break;
        }
        return currType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == BANNER){
            return new Banner_Vierhoder(inflater.inflate(R.layout.item_home_banner,viewGroup,false));
        }else if (i==CHANNEL){
            return new Channel_Viewhoder(inflater.inflate(R.layout.item_home_channel,viewGroup,false));
        }else if(i==ACT){
            return new Act_ViewHoder(inflater.inflate(R.layout.item_home_act,viewGroup,false));
        }else if(i==SECKILL){
            return new SeckillL_ViewHoder(inflater.inflate(R.layout.item_home_seckill,viewGroup,false));
        }else if(i==RECOMMEND){
            return new Recommend_viewhoder(inflater.inflate(R.layout.item_home_recommend,viewGroup,false));
        }else if(i==HOT){
            return new Hot_viewhoder(inflater.inflate(R.layout.item_home_hot,viewGroup,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == BANNER) {
            Banner_Vierhoder vh = (Banner_Vierhoder) viewHolder;
            vh.setData(resul, vh.item_banner);
        } else if (getItemViewType(i) == CHANNEL) {
            Channel_Viewhoder vh = (Channel_Viewhoder) viewHolder;
            vh.setData(resul.getChannel_info());
        } else if (getItemViewType(i) == ACT) {
            Act_ViewHoder vh = (Act_ViewHoder) viewHolder;
            vh.setData(resul.getAct_info());
        } else if (getItemViewType(i) == SECKILL) {
            SeckillL_ViewHoder vh = (SeckillL_ViewHoder) viewHolder;
            vh.setData(resul.getSeckill_info());
        } else if (getItemViewType(i) == RECOMMEND) {
            Recommend_viewhoder vh = (Recommend_viewhoder) viewHolder;
            vh.setData(resul.getRecommend_info());
        } else if (getItemViewType(i) == HOT) {
            Hot_viewhoder vh = (Hot_viewhoder) viewHolder;
            vh.setData(resul.getHot_info());
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }



    /**
     *  设置banner的ViewHoder
     */
    class Banner_Vierhoder extends RecyclerView.ViewHolder{
        Banner item_banner;
        private HomeBean.ResultBean data;

        public Banner_Vierhoder(@NonNull View itemView) {
            super(itemView);
            item_banner =itemView.findViewById(R.id.item_home_banner);
        }

        //设置banner 数据的方法
        public void setData(HomeBean.ResultBean data, Banner item_banner) {
            this.data = data;
            final List<HomeBean.ResultBean.BannerInfoBean> banner_info = data.getBanner_info();
            List<String> list =new ArrayList<>(); //存放图片路径的集合
            for (int i = 0; i <banner_info.size() ; i++) {
                String image = banner_info.get(i).getImage();
                list.add(Constans.BASE_URl_IMAGE+image);
            }
            item_banner.setImages(list)
                            .setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object path, ImageView imageView) {
                                    Glide.with(context).load(path)
                                            .fitCenter()
                                            .into(imageView);
                                }
                            })
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .setBannerAnimation(Transformer.DepthPage) //设置动画
                            .setDelayTime(2000)
                            .isAutoPlay(true)
                            .start();
        //    .setBannerAnimation(Transformer.Accordion) //原版的动画

            item_banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    HomeBean.ResultBean.BannerInfoBean bean = banner_info.get(position);
                    GoodsInfoBean goods =new GoodsInfoBean(bean.getOption(),"剑网三","35",bean.getImage());
                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra("goods",goods);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });


        }
    }


    /**
     *   设置频道类型的ViewHoder
     */
    class Channel_Viewhoder extends RecyclerView.ViewHolder{
        private GridView gridView;
        private List<HomeBean.ResultBean.ChannelInfoBean> data;
        private Channel_Adapter adapter;
        public Channel_Viewhoder(@NonNull View itemView) {
            super(itemView);
            gridView =itemView.findViewById(R.id.item_home_gride);
        }
        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> data) {
            this.data = data;
            adapter =new Channel_Adapter(context,data);
            gridView.setNumColumns(5);
            gridView.setAdapter(adapter);

        }
    }


    /**
     *   设置acT 的ViewPager
     */
    class Act_ViewHoder extends RecyclerView.ViewHolder{
        private ViewPager viewPager;
        private Act_Adapter act_adapter;
        private List<HomeBean.ResultBean.ActInfoBean> data;

        public Act_ViewHoder(@NonNull View itemView) {
            super(itemView);
            viewPager =itemView.findViewById(R.id.item_home_viewpager);
        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> data) {
            this.data = data;
            act_adapter = new Act_Adapter(context,data);
            viewPager.setPageMargin(20);
            //设置Viewpager 滑动的动画效果
            viewPager.setOffscreenPageLimit(3); //必须大于或等于3
            viewPager.setPageTransformer(true,new RotateDownPageTransformer());
            viewPager.setAdapter(act_adapter);
        }
    }
    /**
     *  设置 秒杀的 viewhoder
     */
    class SeckillL_ViewHoder extends RecyclerView.ViewHolder{
        private TextView item_home_seckill_time,item_home_seckill_more;
        private RecyclerView item_home_seckill_recycler;
        private HomeBean.ResultBean.SeckillInfoBean data;
        //设置倒计时
        private long distance=0;
        private    Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                distance=distance-1000; //减1
                SimpleDateFormat format =new SimpleDateFormat("hh:mm:ss"); //设置显示的格式
                String time = format.format(new Date(distance));//转换格式
                item_home_seckill_time.setText(time); //设置给UI
                handler.removeMessages(0); //移除一下handler
                handler.sendEmptyMessageDelayed(0,1000); //再次发送
                if (distance<=0){ //判断时间是否小于或者0
                    handler.removeCallbacksAndMessages(null); //情况handler
                }
            }
        };

        public SeckillL_ViewHoder(@NonNull View itemView) {
            super(itemView);
            item_home_seckill_more = itemView.findViewById(R.id.item_home_seckill_more);
            item_home_seckill_time =itemView.findViewById(R.id.item_home_seckill_time);
            item_home_seckill_recycler = itemView.findViewById(R.id.item_home_seckill_recyclerview);
        }

        public void setData(HomeBean.ResultBean.SeckillInfoBean data) {
            this.data = data;
            String end_time = data.getEnd_time();
            String start_time = data.getStart_time();
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            item_home_seckill_recycler.setLayoutManager(manager);
            Seckill_Adapter adapter =new Seckill_Adapter(data.getList());
            item_home_seckill_recycler.setAdapter(adapter);

            //设置倒计时  首先获取时间差值
            distance =Integer.valueOf(end_time)-Integer.valueOf(start_time);
            handler.sendEmptyMessageDelayed(0,1000); //通过 handker延时1秒发送

        }
    }
    /**
     * 设置推荐的Viewhoder
     */
    class Recommend_viewhoder extends RecyclerView.ViewHolder{
        private RecyclerView gride;
        private List<HomeBean.ResultBean.RecommendInfoBean> data;


        public Recommend_viewhoder(@NonNull View itemView) {
            super(itemView);
            gride =itemView.findViewById(R.id.item_home_recommend_gride);
        }

        public void setData(List<HomeBean.ResultBean.RecommendInfoBean> data) {
            this.data = data;
            GridLayoutManager manager =new GridLayoutManager(context,3);
            manager.setOrientation(GridLayoutManager.VERTICAL);
            gride.setLayoutManager(manager);
            Recommend_Adapter adapter;
            adapter= new Recommend_Adapter(data);
            gride.setAdapter(adapter);

        }
    }
    /**
     *设置热门的Viewhoder
     */
    class Hot_viewhoder extends RecyclerView.ViewHolder{
        GridView gridView;
        private List<HomeBean.ResultBean.HotInfoBean> data;

        public Hot_viewhoder(@NonNull View itemView) {
            super(itemView);
            gridView =itemView.findViewById(R.id.item_home_hot_grid);
        }

        public void setData(List<HomeBean.ResultBean.HotInfoBean> data) {
            this.data = data;
            Hot_Adapter adapter =new Hot_Adapter(context,data);
            gridView.setAdapter(adapter);
        }
    }
}
