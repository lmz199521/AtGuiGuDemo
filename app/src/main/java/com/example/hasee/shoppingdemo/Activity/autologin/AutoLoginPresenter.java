package com.example.hasee.shoppingdemo.Activity.autologin;

import android.util.Log;

import com.example.hasee.shoppingdemo.Bean.LoginBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/22
 */
public class AutoLoginPresenter implements ContentPresenter.IAutoLoginUserPresenter {
    private ContentPresenter.IAutoLoginUserView iAutoLoginUserView;

    public AutoLoginPresenter(ContentPresenter.IAutoLoginUserView iAutoLoginUserView) {
        this.iAutoLoginUserView = iAutoLoginUserView;
    }

    @Override
    public void AutoLogin(String token) {
        Map<String,String> map =new HashMap<>();
        map.put("token",token);
        RetrofitCreate.getShoppingService().AutoLoginUser(map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<LoginBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginBean bean) {
                                Log.d("token","AutoLoginPresenter"+bean.getMessage());
                                iAutoLoginUserView.onAutoLoginSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                    ErrorUtils.ErrorMess(e);
                                    iAutoLoginUserView.onAutoLoginFailure(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }
}
