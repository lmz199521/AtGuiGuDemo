package com.example.hasee.shoppingdemo.View.Type.fragment;

import com.example.hasee.shoppingdemo.Bean.JacketBean;
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
 * Created by Lmz on 2019/05/14
 */
public class TypeInfoPresenter implements ContentPresenter.ITypeInfoPresenter {
    private ContentPresenter.ItypeInfoView itypeInfoView;

    public TypeInfoPresenter(ContentPresenter.ItypeInfoView itypeInfoView) {
        this.itypeInfoView = itypeInfoView;
    }

    @Override
    public void getTypeInfoData(String url) {
        RetrofitCreate.getShoppingService().getTypeGoodsInfo(url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                itypeInfoView.OnHineLoading();
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                itypeInfoView.OnShowLoading();
                            }
                        })
                        .subscribe(new MVPObserver<JacketBean>(){
                            @Override
                            public void onNext(JacketBean jacketBean) {
                                itypeInfoView.onGetTypeInfoDataSuccess(jacketBean);

                            }

                            @Override
                            public void onError(Throwable e) {
                                ErrorUtils.ErrorMess(e);
                                itypeInfoView.onGetTypeInfoFailure(e.toString());
                            }
                        });
    }
}
