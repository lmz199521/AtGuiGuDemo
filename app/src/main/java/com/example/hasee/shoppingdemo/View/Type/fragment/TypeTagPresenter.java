package com.example.hasee.shoppingdemo.View.Type.fragment;

import com.example.hasee.shoppingdemo.Bean.TypeTagBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.EntityUtils;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.FuncationUtils;
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
public class TypeTagPresenter implements ContentPresenter.ITypeTagPresenter {
    private ContentPresenter.ITypeTagView iTypeTagView;

    public TypeTagPresenter(ContentPresenter.ITypeTagView iTypeTagView) {
        this.iTypeTagView = iTypeTagView;
    }

    @Override
    public void getITypeTagData() {
        RetrofitCreate.getShoppingService().getTypeTagInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception { //运行完成
                        iTypeTagView.OnHineLoading();//运行完成结束进度条
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception { //代码正在运行
                        iTypeTagView.OnShowLoading(); //展示进度条
                    }
                })
              //  .map(new FuncationUtils<EntityUtils<TypeTagBean>, tttBean>()) //转换类型
                .subscribe(new MVPObserver<TypeTagBean>() {
                               @Override
                               public void onNext(TypeTagBean bean) {
                                   iTypeTagView.onGetTypeTagDataSuccess(bean);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   ErrorUtils.ErrorMess(e);
                                   iTypeTagView.onGetTypeTagDataFailure(e.toString());
                               }
                           }
                );
    }

}

