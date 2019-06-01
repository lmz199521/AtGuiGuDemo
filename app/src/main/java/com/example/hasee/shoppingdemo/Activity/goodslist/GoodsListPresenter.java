package com.example.hasee.shoppingdemo.Activity.goodslist;

import com.example.hasee.shoppingdemo.Bean.GoodsListBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/15
 */
public class GoodsListPresenter implements ContentPresenter.IGoodsListPresenter{

    private ContentPresenter.IGoodsListView iGoodsListView;

    public GoodsListPresenter(ContentPresenter.IGoodsListView iGoodsListView) {
        this.iGoodsListView = iGoodsListView;
    }

    @Override
    public void getGoodsListData(String url) {
        RetrofitCreate.getShoppingService().getGoodsListInfo(url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                iGoodsListView.OnHineLoading();
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                iGoodsListView.OnShowLoading();
                            }
                        })
                        .subscribe(new MVPObserver<GoodsListBean>(){
                            @Override
                            public void onNext(GoodsListBean bean) {
                                iGoodsListView.onGetGoodsListDataSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                ErrorUtils.ErrorMess(e);
                                iGoodsListView.onGetGoodsListDataFailure(e.toString());
                            }
                        });

    }
}
