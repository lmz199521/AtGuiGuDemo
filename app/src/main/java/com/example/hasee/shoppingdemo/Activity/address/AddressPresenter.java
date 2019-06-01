package com.example.hasee.shoppingdemo.Activity.address;

import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/29
 */
public class AddressPresenter implements ContentPresenter.IAddressUpdatePresenter{
    ContentPresenter.IAddressUpdateView iAddressUpdateView;

    public AddressPresenter(ContentPresenter.IAddressUpdateView iAddressUpdateView) {
        this.iAddressUpdateView = iAddressUpdateView;
    }

    @Override
    public void UpdateUserAddressInfo(String address) {
        Map<String,String> map = new HashMap<>();
        map.put("address",address);
        RetrofitCreate.getShoppingService().UpdateAddressInfo(map)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<AddressBean>(){
                            @Override
                            public void onNext(AddressBean bean) {
                                super.onNext(bean);
                                iAddressUpdateView.OnUpdateSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iAddressUpdateView.OnUpdateFailure(e.toString());
                            }
                        });
    }
}
