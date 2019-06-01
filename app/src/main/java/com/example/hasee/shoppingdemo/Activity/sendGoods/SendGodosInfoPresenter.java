package com.example.hasee.shoppingdemo.Activity.sendGoods;

import android.util.Log;

import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.EntityUtils;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/30
 */
public class SendGodosInfoPresenter implements ContentPresenter.ISendgoodsPresenter{
    private ContentPresenter.ISendGoodsView iSendGoodsView;

    public SendGodosInfoPresenter(ContentPresenter.ISendGoodsView iSendGoodsView) {
        this.iSendGoodsView = iSendGoodsView;
    }

    @Override
    public void NotSendGoodsInfo() {
        RetrofitCreate.getShoppingService().getNotSendGoodsInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<EntityUtils<List<SendGoodsBean>>>(){
                            @Override
                            public void onNext(EntityUtils<List<SendGoodsBean>> list) {
                                super.onNext(list);
                                List<SendGoodsBean> goods = list.getResult();
                                iSendGoodsView.OnGetNotSendGoodsInfoSuccess(goods);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iSendGoodsView.OnGetNotSendGoodsInfoFailure(e.toString());
                            }
                        });
    }
}
