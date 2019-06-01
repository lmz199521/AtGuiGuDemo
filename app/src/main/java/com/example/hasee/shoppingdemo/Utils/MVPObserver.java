package com.example.hasee.shoppingdemo.Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * Created by Lmz on 2019/05/14
 *  自定义 观察者方法 供RXjava 方法用。做到代码复用 较少不必要的代码。
 */
public class MVPObserver<T>  implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        ErrorUtils.ErrorMess(e);
    }

    @Override
    public void onComplete() {

    }
}
