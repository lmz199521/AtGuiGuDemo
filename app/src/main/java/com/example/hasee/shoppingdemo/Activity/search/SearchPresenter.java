package com.example.hasee.shoppingdemo.Activity.search;

import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lmz on 2019/05/23
 */
public class SearchPresenter implements ContentPresenter.ISearchPresenter{
    ContentPresenter.ISearchView iSearchView;

    public SearchPresenter(ContentPresenter.ISearchView iSearchView) {
        this.iSearchView = iSearchView;
    }

    @Override
    public void GetRecommendData() {
        RetrofitCreate.getShoppingService().getRecommendGoodsInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<RecommndBean>(){
                            @Override
                            public void onNext(RecommndBean bean) {
                                super.onNext(bean);
                                iSearchView.onRecommendSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iSearchView.onRecommendFailure(e.toString());
                            }
                        });
    }

    @Override
    public void GetGoodsInfo(String url) {
        String newUrl="/atguigu/json/"+url;
        RetrofitCreate.getShoppingService().getTypeGoodsInfo(newUrl)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<JacketBean>(){
                            @Override
                            public void onNext(JacketBean bean) {
                                super.onNext(bean);
                                iSearchView.onRecommendinfoSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iSearchView.onRecommendinfoFailure(e.toString());
                            }
                        });
    }

    @Override
    public void getSearchGoodsinfo(String name) {
        RetrofitCreate.getShoppingService().getSearchGoodsInfo(name)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new MVPObserver<SearchBean>(){
                            @Override
                            public void onNext(SearchBean bean) {
                                super.onNext(bean);
                                iSearchView.onSearchGoodsinfoSuccess(bean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ErrorUtils.ErrorMess(e);
                                iSearchView.onSearchGoodsinfoFailure(e.toString());
                            }
                        });
    }
}
