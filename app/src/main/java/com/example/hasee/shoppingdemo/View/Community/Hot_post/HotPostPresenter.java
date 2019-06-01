package com.example.hasee.shoppingdemo.View.Community.Hot_post;

import com.example.hasee.shoppingdemo.Bean.HotPostBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/10
 */
public class HotPostPresenter implements ContentPresenter.IHotPostPresenter {

    ContentPresenter.IHostView iHostView;

    public HotPostPresenter(ContentPresenter.IHostView iHostView) {
        this.iHostView = iHostView;
    }
    //这里是获取数据的
    @Override
    public void getHotPostData() {
        RetrofitCreate.getShoppingService().getHotPostInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                //进行中
                                iHostView.OnShowLoading();
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                //完成
                                iHostView.OnHineLoading();
                            }
                        })
                        .subscribe(new Observer<HotPostBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(HotPostBean hotPostBean) {
                                    iHostView.onGetHotPostDataSuccess(hotPostBean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                ErrorUtils.ErrorMess(e);
                                    iHostView.onGetHotPostDataFailure(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }
}
