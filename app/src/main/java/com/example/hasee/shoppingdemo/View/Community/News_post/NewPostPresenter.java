package com.example.hasee.shoppingdemo.View.Community.News_post;

import com.example.hasee.shoppingdemo.Bean.NewPostBean;
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
 * Created by Lmz on 2019/05/15
 */
public class NewPostPresenter implements ContentPresenter.INewsPostPresenter{
    private ContentPresenter.INewsPostView iNewsPostView;

    public NewPostPresenter(ContentPresenter.INewsPostView iNewsPostView) {
        this.iNewsPostView = iNewsPostView;
    }

    @Override
    public void getNewsPostData() {
        RetrofitCreate.getShoppingService().getCommunityNewPostInfo()
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                iNewsPostView.OnHineLoading();
                            }
                        })
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                iNewsPostView.OnShowLoading();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<NewPostBean>(){
                            @Override
                            public void onNext(NewPostBean newPostBean) {
                                iNewsPostView.onGetNewsPostDataSuccess(newPostBean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                ErrorUtils.ErrorMess(e);
                                iNewsPostView.onGetNewPostDataFailure(e.toString());
                            }
                        });
    }
}
