package com.example.hasee.shoppingdemo.Activity.login;

import com.example.hasee.shoppingdemo.Bean.LoginBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/21
 */
public class LoginPresenter implements ContentPresenter.ILoginPresenter {
    private ContentPresenter.ILoginView loginView;

    public LoginPresenter(ContentPresenter.ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void LoginUserInfo(String name, String pwd) {
        Map<String,String> map =new HashMap<>();
        map.put("name",name);
        map.put("password",pwd);
        RetrofitCreate.getShoppingService().LoginUser(map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<LoginBean>(){
                            @Override
                            public void onNext(LoginBean bean) {
                                super.onNext(bean);
                                loginView.onLoginSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                loginView.onLoginFailure(e.toString());
                            }
                        });
    }
}
