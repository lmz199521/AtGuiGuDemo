package com.example.hasee.shoppingdemo.View.User;

import android.media.MediaPlayer;

import com.example.hasee.shoppingdemo.Bean.LogoutBean;
import com.example.hasee.shoppingdemo.Bean.UpLoadImageBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.Utils.ErrorUtils;
import com.example.hasee.shoppingdemo.Utils.MVPObserver;
import com.example.hasee.shoppingdemo.Utils.RetrofitCreate;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Lmz on 2019/05/22
 */
public class IUploadImagePresenter implements ContentPresenter.IUploadImagePresenter{
    ContentPresenter.IUploadImageView iUploadImageView;

    public IUploadImagePresenter(ContentPresenter.IUploadImageView iUploadImageView) {
        this.iUploadImageView = iUploadImageView;
    }

    @Override
    public void Uploadimage(File file) {
        //创建上传文件的请求体
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
        //创建上传文件的part 参数
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);

        RetrofitCreate.getShoppingService().UploadImager(part)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new MVPObserver<UpLoadImageBean>(){
                                @Override
                                public void onNext(UpLoadImageBean bean) {
                                    super.onNext(bean);
                                    iUploadImageView.onUploadSuccess(bean);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    ErrorUtils.ErrorMess(e);
                                    iUploadImageView.onUploadFailuer(e.toString());
                                }
                            });
    }

    @Override
    public void LogoutUser() {
            RetrofitCreate.getShoppingService().LogOutUser()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new MVPObserver<LogoutBean>(){
                                @Override
                                public void onNext(LogoutBean bean) {
                                    super.onNext(bean);
                                    iUploadImageView.onLogoutSuccess(bean);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    ErrorUtils.ErrorMess(e);
                                    iUploadImageView.onLogoutFailure(e.toString());
                                }
                            });
    }
}
