package com.example.hasee.shoppingdemo.Activity.register;

import com.example.hasee.shoppingdemo.Bean.RegisterBean;
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
public class RegisterPresenter implements ContentPresenter.IRegisterPresenter {
    ContentPresenter.IRegisterView iRegisterView;

    public RegisterPresenter(ContentPresenter.IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void RegisterUserInfo(String name, String pwd) {

        Map<String,String> map =new HashMap<>();
        map.put("name",name);
        map.put("password",pwd);
        RetrofitCreate.getShoppingService().RegisterUser(map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<RegisterBean>(){
                            @Override
                            public void onNext(RegisterBean bean) {
                                super.onNext(bean);
                                iRegisterView.onRegisterSuccess(bean);
                            }
                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iRegisterView.onRegisterFailure(e.toString());
                            }
                        });
    }
}
