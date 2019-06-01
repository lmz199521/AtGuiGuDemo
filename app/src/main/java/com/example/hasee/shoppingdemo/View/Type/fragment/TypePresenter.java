package com.example.hasee.shoppingdemo.View.Type.fragment;

import com.example.hasee.shoppingdemo.Bean.TypeBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/14
 */
public class TypePresenter implements ContentPresenter.ITypePresenter {
    ContentPresenter.ITypeView iTypeView;

    public TypePresenter(ContentPresenter.ITypeView iTypeView) {
        this.iTypeView = iTypeView;
    }

    @Override
    public void getITypeData() {
        RetrofitCreate.getShoppingService().getTypeInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<TypeBean>(){
                            @Override
                            public void onNext(TypeBean bean) {
                                iTypeView.onGetTypeDataSuccess(bean);
                            }
                            @Override
                            public void onError(Throwable e) {
                                iTypeView.onGetTypeFailure(e.toString());
                            }
                        });
    }
}
