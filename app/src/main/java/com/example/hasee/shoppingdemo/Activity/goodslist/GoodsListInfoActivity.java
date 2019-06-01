package com.example.hasee.shoppingdemo.Activity.goodslist;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Activity.MainActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsListBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;

import java.util.List;

public class GoodsListInfoActivity extends AppCompatActivity implements ContentPresenter.IGoodsListView{

    private ContentPresenter.IGoodsListPresenter presenter;
    private RecyclerView recyclerView;
    private ImageView backimg,homeImg;
    private GoodsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_info);
        Intent intent =getIntent();
        int listId = intent.getIntExtra("list_id", 8);
        presenter = new GoodsListPresenter(this);
        initview();
        initlistener();



        GetData(listId);

    }

    private void initlistener() {
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoodsListInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initview() {
        recyclerView =findViewById(R.id.goods_list_recyclerview);
        backimg =findViewById(R.id.goods_top_left_back);
        homeImg =findViewById(R.id.goods_top_right_img);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        DividerItemDecoration vd =new DividerItemDecoration(MyApplication.getContext(),DividerItemDecoration.VERTICAL);
        vd.setDrawable(ContextCompat.getDrawable(MyApplication.getContext(),R.drawable.item_decoration));
        recyclerView.addItemDecoration(vd);

        DividerItemDecoration vh=new DividerItemDecoration(MyApplication.getContext(),DividerItemDecoration.HORIZONTAL);
        vh.setDrawable(ContextCompat.getDrawable(MyApplication.getContext(),R.drawable.item_decoration));
        recyclerView.addItemDecoration(vh);
        adapter =new GoodsListAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void GetData(int id){
        String url ="";
        switch (id){
            case 8:
                url="CLOSE_STORE.json";
                break;//服饰
            case 4:
                url="GAME_STORE.json";
                break;//游戏
            case 3:
                url="COMIC_STORE.json";
                break;//动漫
            case 5:
                url="COSPLAY_STORE.json";
                break;//装扮
            case 6:
                url="GUFENG_STORE.json";
                break;//古风
            case 9:
                url="STICK_STORE.json";
                break;//漫展票务
            case 10:
                url="FOOD_STORE.json";
                break;// 零食
            case 11:
                url="WENJU_STORE.json";
                break;//文具
            case 12:
                url="SHOUSHI_STORE.json";
                break;//首饰
            case 13:
                url="COMIC_STORE.json";
                break;//更多
        }

        presenter.getGoodsListData(Constans.BASE_URL_JSON+url);
    }

    @Override
    public void onGetGoodsListDataSuccess(GoodsListBean bean) {
        GoodsListBean.ResultBean goods = bean.getResult();
        List<GoodsListBean.ResultBean.PageDataBean> data = goods.getPage_data();

        adapter.Refresh(data);
    }

    @Override
    public void onGetGoodsListDataFailure(String ErrorMess) {

    }

    @Override
    public void OnShowLoading() {

    }

    @Override
    public void OnHineLoading() {

    }
}
