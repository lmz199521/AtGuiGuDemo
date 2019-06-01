package com.example.hasee.shoppingdemo.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.hasee.shoppingdemo.Bean.User;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Utils.SharePreUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Lmz on 2019/05/21
 */
public class UserInfoManger {
    //在内存当中缓存用户的信息， 避免每次都去数据库查找
    private static  UserInfoManger instance =new UserInfoManger(); //设置成全局是因为用户信息用该是全局范围使用的
    private UserBean bean;
    private User user;
    private  String avatarPath;

    public UserInfoManger() {
    }

    public static UserInfoManger getInstance() {
        return instance;
    }

    public static void setInstance(UserInfoManger instance) {
        UserInfoManger.instance = instance;
    }

    public UserBean getBean() {
        return bean;
    }

    public void setBean(UserBean bean) {
        this.bean = bean;
    }

    public void SavePhone(String phone){
        bean.setPhone(phone);
    }
    public String getPhone(){
       return (String) bean.getPhone();
    }

    public void SaveAvatar(String avatar){
        bean.setAvatar(avatar);
    }
    public String getAvatar(){
        return (String) bean.getAvatar();
    }


    //创建一个保存用户头像的缓存方法
    private void setAvatarCachePath(){
        //设置缓存头像的目录，该目录当程序卸载的时候可以跟随程序删除，不会产生垃圾文件
        avatarPath = MyApplication.getContext().getExternalCacheDir()+"/avatar/";
        File file =new File(avatarPath);
        if (file.exists()){
            //判断是否有这个文件夹， 如果没有就去创建
            file.mkdir();
        }
    }


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    /**
     * 读取token。如果当前有用户登录，accountManger保存了用户信息，直接从缓存用户信息中读取token.
     * 如果当前没有登录的用户，即accountManager没有维护当前用户信息，那么token我们需要从sp中读取。如果sp中
     * 没有token，则返回空。
     * @return
     */
    public void saveToken(String token){
        SharePreUtils.saveToken(token);
        bean.setToken(token);
    }
    public String GetToken(){
        if (bean!=null){
            return bean.getToken();
        }else {
            String token =SharePreUtils.getToken();
            if (TextUtils.isEmpty(token)){
                return null;
            }else {
                return token;
            }
        }
    }

    public void CliearToken(){
            bean.setToken("");
    }


    //设置一个 保存本地缓存的类
    public boolean CheckAvatarIsUpData(){
        if (bean==null)
            return false;
        //第一步，获取服务端的文件名
        String tempName =user.getAvatar();
        Log.d("avatar","服务端文件名："+tempName);
        //拆解下从服务器获取的文件名，得到最原始的名字     1558593277925.jpg
        final String fileName = tempName.substring(tempName.lastIndexOf("/") + 1, tempName.length());
        Log.d("avatar","拆解之后的文件名："+fileName);
        //第二步获取本地存储的文件名
        File[] files= new File(getAvatar()).listFiles();
        if(files!=null && files.length>0){
              for (int i=0;i<files.length;i++){
                 String localName= files[i].getName();
                  Log.d("avatar","本地的一些文件名："+localName);
                 if (localName.equals(fileName)){
                     return false; //不需要下载了
                 }
              }
        }
        //第三步，如果上面的文件没有，那我们就去网络上下载下来。
        String ImagerUrl =Constans.BASE_URl_IMAGE+user.getAvatar();
        Log.d("avatar","文件的URL："+ImagerUrl);
        RetrofitCreate.getShoppingService().DownLoadImager(ImagerUrl)
                        .observeOn(Schedulers.io()) //读取文件放入子线程执行
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody body) {
                                  //在这里读取流
                                byte[] bytes =new byte[1024];
                                InputStream inputStream = body.byteStream();
                                String savePath = getAvatarPath()+fileName;
                                FileOutputStream outputStream =null;
                                try {
                                    outputStream = new FileOutputStream(savePath);
                                    while (inputStream.read(bytes)!=-1){
                                        outputStream.write(bytes);
                                    }
                                outputStream.flush();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }finally {
                                    if (inputStream!=null){
                                        try {
                                            inputStream.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    if (outputStream!=null){
                                        try {
                                            outputStream.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }


                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
        return true;
    }

}
