package com.example.hasee.shoppingdemo.View.User;

import com.example.hasee.shoppingdemo.Bean.UpDateBean;
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
public class IUpdatePhonePresenter implements ContentPresenter.IUpDatePhoneNumber {
    ContentPresenter.IUpdatePhoneView iUpdatePhoneView;

    public IUpdatePhonePresenter(ContentPresenter.IUpdatePhoneView iUpdatePhoneView) {
        this.iUpdatePhoneView = iUpdatePhoneView;
    }

    @Override
    public void UpdatePhone(String phone) {
        Map<String,String> map =new HashMap<>();
        map.put("phone",phone);
        RetrofitCreate.getShoppingService().UpdatePhone(map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<UpDateBean>(){
                            @Override
                            public void onNext(UpDateBean bean) {
                                super.onNext(bean);
                                iUpdatePhoneView.onUpDatePhoneSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iUpdatePhoneView.pnUpDatePhoneFailure(e.toString());
                            }
                        });
    }
}
