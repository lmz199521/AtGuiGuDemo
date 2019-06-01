package com.example.hasee.shoppingdemo.Activity.goodsinfo;

import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.EntityUtils;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Lmz on 2019/05/24
 */
public class IGoodsinfoPresenter implements ContentPresenter.ICheckGoodsPresenter {

    private ContentPresenter.ICheckGoodsView iCheckGoodsView;

    public IGoodsinfoPresenter(ContentPresenter.ICheckGoodsView iCheckGoodsView) {
        this.iCheckGoodsView = iCheckGoodsView;
    }

    /**
     *   检查库存是否充足
     * @param id
     * @param number
     */
    @Override
    public void CheckGoodsNumber(final String id, String number) {
        Map<String, String> map = new HashMap<>();
        map.put("productId", "" + id);
        map.put("productNum", number);
        RetrofitCreate.getShoppingService().CheckGoodsNumber(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MVPObserver<EntityUtils<String>>(){
                    @Override
                    public void onNext(EntityUtils<String> stringEntityUtils) {
                        super.onNext(stringEntityUtils);
                        String result = stringEntityUtils.getResult();
                        try {
                            iCheckGoodsView.onCheckGoodsinfoSuccess(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        iCheckGoodsView.onCheckGoodsinfoFailure(e.toString());
                    }
                });
    }

    @Override
    public void AddGoodsInfoToServer(String productId, String productNum, String productName, String url){

        JSONObject jsonObject =new JSONObject();

        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productNum",productNum);
            jsonObject.put("productName",productName);
            jsonObject.put("url",url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        RetrofitCreate.getShoppingService().AddGoodsToServer(body)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<EntityUtils<String>>(){
                            @Override
                            public void onNext(EntityUtils<String> stringEntityUtils) {
                                super.onNext(stringEntityUtils);
                                String result = stringEntityUtils.getResult();
                                iCheckGoodsView.onAddGoodsToServerSuccess(result);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iCheckGoodsView.onAddGoodsToServerFailure(e.toString());
                            }
                        });
    }
}
