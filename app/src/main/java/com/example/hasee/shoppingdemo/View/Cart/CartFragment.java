package com.example.hasee.shoppingdemo.View.Cart;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.IGoodsinfoPresenter;
import com.example.hasee.shoppingdemo.Bean.GoodsinfoDataBean;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.CheckStatusUtils;
import com.example.hasee.shoppingdemo.Utils.Database.GoodsinfoDatabaseDao;
import com.example.hasee.shoppingdemo.Utils.UserInfoManger;
import com.example.hasee.shoppingdemo.View.Home.HomeFragment;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartAdapter.SubItemClick, CartAdapter.addItemClick, CartAdapter.GoodsIsNull,ContentPresenter.ICheckGoodsView,ContentPresenter.IShoppingCartView {


    private TextView shoppingcart_top_right_img,shoppingcart_get_coimmit;
    private ImageView shoppingcart_top_left_img;
    private RecyclerView shoppingcart_recyclervice;
    private TextView shoppingcart_home;
    private ConstraintLayout shoppingcart_layout, shoppingcart_constraint;
    private GoodsinfoDatabaseDao dao;
    private CartAdapter adapter;
    private boolean flag = true;
    private PopupWindow popupWindow;
    private TextView CountPrice, deleteText;
    private CheckBox checkBox, deledtbox;
    private View popView;
    private ContentPresenter.ICheckGoodsPresenter presenter;
    private int GoodId=0;
    private int GoodIndex=0;
    private int nowCount=0;
    private ContentPresenter.IShoppingCartPresenter iShoppingCartPresenter;

    public CartFragment() {
        // Required empty public constructor
        dao = new GoodsinfoDatabaseDao(MyApplication.getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        initView(view);
        initdata();
        initlistener();
        presenter =new IGoodsinfoPresenter(this);
        iShoppingCartPresenter = new ShoppingPresenter(this);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);  //配置沙箱环境   不配置这一句 无法唤醒支付宝页面
        return view;
    }

    private void initlistener() {
        shoppingcart_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new HomeFragment()).commit();
            }
        });
        shoppingcart_top_right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                String tt = flag ? "编辑" : "完成";
                if (flag) {
                    popupWindow.dismiss();
                } else {
                    PoPWid();
                }
                shoppingcart_top_right_img.setText(tt);

            }
        });

        shoppingcart_get_coimmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countprice = CountPrice.getText().toString().trim();
                String substring = countprice.substring(1);
                Log.d("price",""+substring);
                double price = Double.parseDouble(substring);
                Log.d("price",""+price);
                if (price==0){
                    CheckStatusUtils.ToastMess("请选择商品");
                }else {
                    iShoppingCartPresenter.GetDataByServer();
                }
            }
        });
    }

    private void PoPWid() {

        popupWindow = new PopupWindow(MyApplication.getContext());
        popupWindow.setContentView(popView);
        popupWindow.setHeight(120);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(getActivity().findViewById(R.id.shoppingcart_get_layout), Gravity.BOTTOM, 0, 100);


    }

    private void initdata() {
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        shoppingcart_recyclervice.setLayoutManager(manager);
        adapter = new CartAdapter(CountPrice, checkBox, deledtbox);
        shoppingcart_recyclervice.setAdapter(adapter);
        adapter.setAddItemClick(this);
        adapter.setSubItemClick(this);
        adapter.setIsNull(this);
        ShowShopping();


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    adapter.isChecked(true);
                } else {
                    adapter.isChecked(false);
                }
            }
        });
        deledtbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deledtbox.isChecked()) {
                    adapter.isChecked(true);
                } else {
                    adapter.isChecked(false);
                }
            }
        });
        deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.DeleteGoods();
            }
        });
    }

    private void ShowShopping() {
        UserBean bean = UserInfoManger.getInstance().getBean();
        if (bean != null) {
            List<GoodsinfoDataBean> list = dao.SelectData(bean.getName());
            if (list.size() == 0) {
                //展示 买买买 的推荐页面
                GoneCart("购物车空无一物");
            } else {
                shoppingcart_layout.setVisibility(View.GONE);
                shoppingcart_constraint.setVisibility(View.VISIBLE);
                shoppingcart_top_right_img.setVisibility(View.VISIBLE);
                adapter.Refresh(list);
            }
        } else {
            //展示 买买买 的推荐页面
            GoneCart("购物车空无一物");
        }
    }

    //展示隐藏购物车，展示买买买的推荐页面
    private void GoneCart(String str) {
        Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT).show();
        shoppingcart_constraint.setVisibility(View.GONE);
        shoppingcart_layout.setVisibility(View.VISIBLE);
        shoppingcart_top_right_img.setVisibility(View.GONE);

    }

    private void initView(View view) {
        shoppingcart_top_right_img = (TextView) view.findViewById(R.id.shoppingcart_top_right_img);
        shoppingcart_top_left_img = (ImageView) view.findViewById(R.id.shoppingcart_top_left_img);
        shoppingcart_recyclervice = (RecyclerView) view.findViewById(R.id.shoppingcart_recyclervice);
        shoppingcart_get_coimmit=(TextView)view.findViewById(R.id.shoppingcart_get_coimmit);

        shoppingcart_home = (TextView) view.findViewById(R.id.shoppingcart_home);
        shoppingcart_layout = (ConstraintLayout) view.findViewById(R.id.shoppingcart_layout);
        shoppingcart_constraint = (ConstraintLayout) view.findViewById(R.id.shoppingcart_constraint);
        CountPrice = view.findViewById(R.id.shoppingcart_get_price);
        checkBox = view.findViewById(R.id.shoppingcart_get_all);
        checkBox.setChecked(true);
        popView = getLayoutInflater().inflate(R.layout.item_shopping_goods_operation, null);
        deledtbox = popView.findViewById(R.id.item_shopping_goods_opration_all);
        deledtbox.setChecked(false);
        deleteText = popView.findViewById(R.id.item_shopping_goods_opration_delete);


    }

    @Override
    public void AddBtn(int id, int i) {
        GoodsinfoDataBean goods = dao.SelectGoods(id);
        int goodsNumber = Integer.parseInt(goods.getNumber());
        goodsNumber++;
        GoodId=id;
        GoodIndex=i;
        nowCount =goodsNumber;
        presenter.CheckGoodsNumber(id+"",goodsNumber+"");


    }

    @Override
    public void SubBtn(int id, int i) {
        //    Toast.makeText(MyApplication.context, "减了", Toast.LENGTH_SHORT).show();
        GoodsinfoDataBean goods = dao.SelectGoods(id);
        int goodsNumber = Integer.parseInt(goods.getNumber());
        if (goodsNumber > 1) {
            goodsNumber--;
            dao.UpdataData("" + id, "" + goodsNumber);
            adapter.NumberRefresh(i, goodsNumber);
        } else {
            Toast.makeText(MyApplication.getContext(), "已经减不动了", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void GoodsIsnull() {
        GoneCart("购物车已经被清空了，快去买买买吧");
        if (popupWindow != null) {
            popupWindow.dismiss();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TACH", "获得了焦点");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TACH", "失去了焦点");
        if (popupWindow != null) {
            popupWindow.dismiss();
        }

    }

    @Override
    public void onCheckGoodsinfoSuccess(String json) {
        int newCount = Integer.parseInt(json);
        String countMess=nowCount>newCount?"库存不足，加不动了":"增加成功";
        CheckStatusUtils.ToastMess(countMess);
        dao.UpdataData("" + GoodId, "" + newCount);
        adapter.NumberRefresh(GoodIndex, newCount);
    }

    @Override
    public void onCheckGoodsinfoFailure(String mess) {

    }

    @Override
    public void onAddGoodsToServerSuccess(String json) {

    }

    @Override
    public void onAddGoodsToServerFailure(String mess) {

    }

    @Override
    public void onCommitGoodsSuccess( OrderInfoBean result) {
        Log.d("onCommitGoodsSuccess","onCommitGoodsSuccess：num==="+result.getOutTradeNo());
        Log.d("onCommitGoodsSuccess","onCommitGoodsSuccess：详情==="+result.getOrderInfo());
        CheckStatusUtils.ToastMess("提交成功了");
        PayGoods(result);
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    PayInfoBean bean = (PayInfoBean) msg.obj;
                    Log.d("cartCommit","Result"+bean.getResult());
                    Log.d("cartCommit","OutTradeNo"+bean.getOutTradeNo());
                    Map<String,String> map=bean.getResult();
                    String resultContent = map.get("result");
                    String resultStatus = map.get("resultStatus");
                    boolean payresultIsOk=false;

                    if (resultStatus.equals("9000")){
                        payresultIsOk=true;
                    }
                    //去服务端拿支付结果
                    iShoppingCartPresenter.getFirmServerPayInfo(bean.getOutTradeNo(),resultContent,payresultIsOk);
                    break;
                default:break;
            }
        }
    };

    /**
     *  调用支付宝接口 支付商品
     * @param result   商品详情
     */
    private void PayGoods(final OrderInfoBean result) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask task = new PayTask(getActivity());
                Map<String,String> res = task.payV2(result.getOrderInfo(),true);
                PayInfoBean bean =new PayInfoBean(result.getOutTradeNo(),res);
                Message msg =new Message();
                msg.what =1;
                msg.obj=bean;
                mHandler.sendMessage(msg);

            }
        };
        //必须异步调用
        Thread thread =new Thread(runnable);
        thread.start();
    }

    @Override
    public void onCommitGoodsFailure(String mess) {
        Log.d("jsonarray","onCommitGoodsFailure："+mess);
    }



    @Override
    public void onGetDataSuccess(ArrayList<ShoopingGoodsBean> list) {




        Log.d("jsonarray","购物车商品数量："+list.size());
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<list.size();i++){
            ShoopingGoodsBean bean = list.get(i);

            dao.DeleteData(bean.getProductId());
            JSONObject object =new JSONObject();
            try {
                object.put("productNum",bean.getProductNum());
                object.put("productName",bean.getProductName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(object);
        }

        String countprice = CountPrice.getText().toString().trim();
        String substring = countprice.substring(1);
        double price = Double.parseDouble(substring);
        iShoppingCartPresenter.CommitGoods("buy",""+price,jsonArray);

    }

    @Override
    public void onGetDataFailure(String mess) {
        Log.d("jsonarray","错误："+mess);
    }

    @Override
    public void onFirmServerSuccess(boolean isTrue) {
        Log.d("onFirmServerSuccess","mess"+isTrue);
        if (isTrue){
            Toast.makeText(MyApplication.getContext(), "支付成功", Toast.LENGTH_SHORT).show();
            adapter.DeleRefresh();
        }
    }

    @Override
    public void onFirmServerFailure(String mess) {
        Log.d("onFirmServerSuccess","错误："+mess);

    }
}
