package com.example.hasee.shoppingdemo.View.Home;

import com.example.hasee.shoppingdemo.Bean.HomeBean;
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
 * Created by Lmz on 2019/05/12
 * 主页的控制类
 */
public class HomePresenter implements ContentPresenter.IHomePresenter{
    private ContentPresenter.IHomeView iHomeView;

    public HomePresenter(ContentPresenter.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    @Override
    public void getHomeData() {
        RetrofitCreate.getShoppingService().getHomeDataInfo()
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                iHomeView.OnHineLoading();
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                iHomeView.OnShowLoading();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<HomeBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(HomeBean homeBean) {
                                iHomeView.onGetHomeDataSuccess(homeBean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                ErrorUtils.ErrorMess(e);
                                iHomeView.onGetDataFailure(e.toString());


                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }
}
