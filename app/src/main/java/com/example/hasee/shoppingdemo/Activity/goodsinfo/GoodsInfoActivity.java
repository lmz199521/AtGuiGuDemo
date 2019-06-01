package com.example.hasee.shoppingdemo.Activity.goodsinfo;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.MainActivity;
import com.example.hasee.shoppingdemo.Activity.login.LoginActivity;
import com.example.hasee.shoppingdemo.Bean.GoodsInfoBean;
import com.example.hasee.shoppingdemo.Bean.GoodsListBean;
import com.example.hasee.shoppingdemo.Bean.GoodsinfoDataBean;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.CheckStatusUtils;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.example.hasee.shoppingdemo.Utils.Database.GoodsinfoDatabaseDao;
import com.example.hasee.shoppingdemo.Utils.UserInfoManger;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Observable;

import javax.crypto.interfaces.PBEKey;

public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener,ContentPresenter.ICheckGoodsView {

    private ImageView goodsImg, backImg, subBtn, addBtn, pic;
    private TextView goodsName, goodsPrice, go_cart, number, commit, overBtn, price, name;
    private Button addcart;
    private View parent = null;
    private int count = 1;
    private GoodsinfoDatabaseDao dao;
    private PopupWindow popupWindow;
    private View view;
    private GoodsInfoBean goods;
    private ContentPresenter.ICheckGoodsPresenter presenter;
    private boolean isFast=false;
    private GoodsinfoDataBean good;
    private int goodsCount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        view = getLayoutInflater().inflate(R.layout.item_goodsinfo_cart, null);
        Intent intent = getIntent();
        goods = (GoodsInfoBean) intent.getSerializableExtra("goods");
        initview();
        initdata(goods);
        initlistener();
        dao = new GoodsinfoDatabaseDao(MyApplication.getContext());
        parent = LayoutInflater.from(this).inflate(R.layout.activity_goods_info, null);
        presenter =new IGoodsinfoPresenter(this);
    }

    private void initlistener() {
        backImg.setOnClickListener(this);
        addcart.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        overBtn.setOnClickListener(this);
        commit.setOnClickListener(this);
        go_cart.setOnClickListener(this);

    }

    private void PopWindow() {
        popupWindow = new PopupWindow(MyApplication.getContext());

        popupWindow.setContentView(view);
        popupWindow.setHeight(500);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.goods_bottomlayout), Gravity.BOTTOM, 0, 0);

    }

    private void initdata(final GoodsInfoBean goods) {
        goodsPrice.setText("￥" + goods.getPrice());
        goodsName.setText(goods.getName());
        Glide.with(this).load(Constans.BASE_URl_IMAGE + goods.getPic())
                .placeholder(R.drawable.ic_launcher_background)
                .into(goodsImg);

        price.setText(goods.getPrice());
        name.setText(goods.getName());
        Glide.with(this).load(Constans.BASE_URl_IMAGE + goods.getPic())
                .placeholder(R.drawable.ic_launcher_background)
                .into(pic);

    }

    private void initview() {
        goodsImg = findViewById(R.id.goodsinfo_img);
        goodsName = findViewById(R.id.goodsinfo_name);
        goodsPrice = findViewById(R.id.goodsinfo_price);
        backImg = findViewById(R.id.goodsinfo_top_left_back);
        addcart = findViewById(R.id.goodsinfo_addcart);
        go_cart = findViewById(R.id.goodsinfo_cart);
        subBtn = view.findViewById(R.id.item_goodsinfo_cart_sub_btn);
        addBtn = view.findViewById(R.id.item_goodsinfo_cart_add_btn);
        price = view.findViewById(R.id.item_goodsinfo_cart_price);
        name = view.findViewById(R.id.item_goodsinfo_cart_name);
        pic = view.findViewById(R.id.item_goodsinfo_cart_img);
        overBtn = view.findViewById(R.id.item_goodsinfo_cart_over);
        commit = view.findViewById(R.id.item_goodsinfo_cart_commit);
        number = view.findViewById(R.id.item_goodsinfo_cart_count);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goodsinfo_top_left_back:
                onBackPressed();
                break;
            case R.id.goodsinfo_addcart:
                PopWindow();
                break;
             case R.id.goodsinfo_cart:
                Intent intent = new Intent(GoodsInfoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.item_goodsinfo_cart_sub_btn:
                if (count <= 0) {
                    return;
                }
                count--;
                number.setText("" + count);
                break;
            case R.id.item_goodsinfo_cart_add_btn:
                count++;
                number.setText("" + count);
                break;
            case R.id.item_goodsinfo_cart_commit:
                boolean netWork = CheckStatusUtils.CheckNetWork();
                if (!netWork) {
                    return;
                }
                UserBean bean = UserInfoManger.getInstance().getBean();
                if (bean == null) {
                    CheckStatusUtils.ToastMess("请登录");
                    //当前没有登录。跳转到登录界面
                    Intent toLogin = new Intent(GoodsInfoActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                } else {
                    AddData();
                }
                break;
            case R.id.item_goodsinfo_cart_over:
                popupWindow.dismiss();
                break;
        }
    }

    private void AddData() {
         good = new GoodsinfoDataBean();
        GoodsinfoDataBean bean = dao.SelectGoods(goods.getId());

        if (bean == null) {
            isFast=true;
            String number = this.number.getText().toString();
            int id = goods.getId();
            goodsCount =Integer.parseInt(number);
            CheckGoods(id,number);

        } else {
            isFast=false;
            String s = number.getText().toString();
            String number = bean.getNumber();
            int i = Integer.parseInt(s);
            int i2 = Integer.parseInt(number);
            int i3 = i + i2;
            goodsCount=i3;
            CheckGoods(goods.getId(),i3+"");

        }

    }


    private void CheckGoods(int id,String number){
            presenter.CheckGoodsNumber(id+"",number);
    }

    @Override
    public void onCheckGoodsinfoSuccess(String json) throws JSONException {
        int newCount = Integer.parseInt(json);
        String countMess=goodsCount>newCount?"库存不足，已按最大的库存加入购物车":"添加购物车成功";
        CheckStatusUtils.ToastMess(countMess);
        if (isFast){
            //第一次添加
            good.setName(goods.getName());
            good.setNumber(json);
            good.setPic(goods.getPic());
            good.setPrice(goods.getPrice());
            good.setId(goods.getId());

            dao.AddData(good, UserInfoManger.getInstance().getBean().getName());
            presenter.AddGoodsInfoToServer(goods.getId()+"",json,goods.getName(),goods.getPic());


            popupWindow.dismiss();
        }else {
                //第二次 属于修改
            dao.UpdataData("" + goods.getId(), "" + json);
            presenter.AddGoodsInfoToServer(goods.getId()+"",json,goods.getName(),goods.getPic());
        }

    }

    @Override
    public void onCheckGoodsinfoFailure(String mess) {
        Log.d("check","错误信息"+mess);
    }


    @Override
    public void onAddGoodsToServerSuccess(String json) {
        Log.d("check","onAddGoodsToServerSuccess"+json);
    }

    @Override
    public void onAddGoodsToServerFailure(String mess) {
        Log.d("check","onAddGoodsToServerFailure"+mess);
    }
}
