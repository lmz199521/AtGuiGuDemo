package com.example.hasee.shoppingdemo.View.Cart;

import android.util.Log;

import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.EntityUtils;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Created by Lmz on 2019/05/24
 */
public class ShoppingPresenter implements ContentPresenter.IShoppingCartPresenter{
    private ContentPresenter.IShoppingCartView iShoppingCartView;

    public ShoppingPresenter(ContentPresenter.IShoppingCartView iShoppingCartView) {
        this.iShoppingCartView = iShoppingCartView;
    }

    @Override
    public void CommitGoods(String subject, String totalPrice, JSONArray json) {
        JSONObject jsonObject =new JSONObject();
        try {
            jsonObject.put("subject",subject);
            jsonObject.put("totalPrice",totalPrice);
            jsonObject.put("body",json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("CommitGoods",jsonObject.toString());

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        RetrofitCreate.getShoppingService().CommitShoppingCart(body)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<EntityUtils<OrderInfoBean>>(){
                            @Override
                            public void onNext(EntityUtils<OrderInfoBean> stringEntityUtils) {
                                super.onNext(stringEntityUtils);
                                OrderInfoBean result = stringEntityUtils.getResult();

                               iShoppingCartView.onCommitGoodsSuccess(result);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iShoppingCartView.onCommitGoodsFailure(e.toString());
                            }
                        });


    }

    @Override
    public void GetDataByServer() {
                RetrofitCreate.getShoppingService().getShoppingCartByServer()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new MVPObserver<EntityUtils<String>>(){
                                    @Override
                                    public void onNext(EntityUtils<String> json) {
                                        super.onNext(json);
                                        String s = json.toString();
                                        String result = json.getResult();

                                        Gson gson =new Gson();
                                        JsonParser jsonParser =new JsonParser();
                                        JsonArray asJsonArray = jsonParser.parse(result).getAsJsonArray();
                                        ArrayList<ShoopingGoodsBean> list =new ArrayList<>();
                                        for (JsonElement bean:asJsonArray){
                                            ShoopingGoodsBean goodsBean = gson.fromJson(bean, ShoopingGoodsBean.class);
                                            list.add(goodsBean);
                                        }

                                        iShoppingCartView.onGetDataSuccess(list);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        super.onError(e);
                                        ErrorUtils.ErrorMess(e);
                                        iShoppingCartView.onGetDataFailure(e.toString());
                                    }
                                });
    }

    @Override
    public void getFirmServerPayInfo(String outTradeNo, String resultContent, boolean isTrue) {
                JSONObject jsonObject =new JSONObject();
        try {
            jsonObject.put("outTradeNo",outTradeNo);
            jsonObject.put("result",resultContent);
            jsonObject.put("clientPayResult",isTrue);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; chatset=utf-8"), jsonObject.toString());
        RetrofitCreate.getShoppingService().getFirmServerPayInfo(body)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new MVPObserver<EntityUtils<Boolean>>(){
                                @Override
                                public void onNext(EntityUtils<Boolean> booleanEntityUtils) {
                                    super.onNext(booleanEntityUtils);
                                    iShoppingCartView.onFirmServerSuccess(booleanEntityUtils.getResult());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    ErrorUtils.ErrorMess(e);
                                    iShoppingCartView.onFirmServerFailure(e.toString());
                                }
                            });


    }
}
