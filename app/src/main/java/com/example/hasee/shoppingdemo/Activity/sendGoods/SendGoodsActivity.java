package com.example.hasee.shoppingdemo.Activity.sendGoods;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;

import java.util.List;

public class SendGoodsActivity extends AppCompatActivity implements View.OnClickListener,ContentPresenter.ISendGoodsView{

    private ImageView sendgoods_back;
    private ViewPager viewPager;
    private ContentPresenter.ISendgoodsPresenter presenter;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_goods);
        initView();
        initdata();
        presenter = new SendGodosInfoPresenter(this);
        presenter.NotSendGoodsInfo();
    }

    private void initdata() {
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true,new CardTransformer());
    }

    private void initView() {
        sendgoods_back = (ImageView) findViewById(R.id.sendgoods_back);
        sendgoods_back.setOnClickListener(this);
        viewPager =findViewById(R.id.sendgoods_viewpager);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.sendgoods_back:
                    onBackPressed();
                    break;
                    default:break;
            }
    }

    @Override
    public void OnGetNotSendGoodsInfoSuccess(List<SendGoodsBean> goods) {
        for (int i=0;i<goods.size();i++){
            SendGoodsBean bean = goods.get(i);
            Log.d("SendGoodsActivity","OnGetNotSendGoodsInfoSuccess"+bean.getBody()+"price:"+bean.getTotalPrice()+"type:"+bean.getSubject());
        }
        adapter =new PagerAdapter(getSupportFragmentManager(),goods);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void OnGetNotSendGoodsInfoFailure(String mess) {
        Log.d("SendGoodsActivity","OnGetNotSendGoodsInfoFailure"+mess);
    }
}
