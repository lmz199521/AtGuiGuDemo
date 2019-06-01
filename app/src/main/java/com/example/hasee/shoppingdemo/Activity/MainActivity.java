package com.example.hasee.shoppingdemo.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Activity.autologin.AutoLoginPresenter;
import com.example.hasee.shoppingdemo.Activity.login.LoginActivity;
import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.Bean.LoginBean;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.SharePreUtils;
import com.example.hasee.shoppingdemo.Utils.UserInfoManger;
import com.example.hasee.shoppingdemo.View.Cart.CartFragment;
import com.example.hasee.shoppingdemo.View.Community.CommunityFragment;
import com.example.hasee.shoppingdemo.View.Home.HomeFragment;
import com.example.hasee.shoppingdemo.View.Type.TypeFragment;
import com.example.hasee.shoppingdemo.View.User.UserFragment;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ContentPresenter.IAutoLoginUserView{

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private long lastTime;
    private ContentPresenter.IAutoLoginUserPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String token = UserInfoManger.getInstance().GetToken();
        presenter = new AutoLoginPresenter(this);
        presenter.AutoLogin(token);
        initview();
        initlistener();
        radioGroup.check(R.id.main_radiobtn_home);


    }
    private void initview() {
        frameLayout =findViewById(R.id.main_fragment);
        radioGroup =findViewById(R.id.main_radiogroup);
    }

    private void initlistener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId){
                    case R.id.main_radiobtn_home:
                        fragment =new HomeFragment();
                        break;
                    case R.id.main_radiobtn_type:
                        fragment =new TypeFragment();
                        break;
                    case R.id.main_radiobtn_community:
                        fragment =new CommunityFragment();
                        break;
                    case R.id.main_radiobtn_cart:
                        fragment =new CartFragment();
                        break;
                    case R.id.main_radiobtn_user:
                        fragment = new UserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,fragment).commit();

            }
        });
    }


    //重写退出方法   2秒之内 按两次返回键，就退出
    @Override
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis-lastTime>2000){
            Log.d("onBackPressed","当前时间："+currentTimeMillis+"========上一次的时间:"+lastTime);
            Toast.makeText(MyApplication.getContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            lastTime = currentTimeMillis;
        }else {
            super.onBackPressed(); //退出
        }
    }

    @Override
    public void onAutoLoginSuccess(LoginBean bean) {
        String code = bean.getCode();
        if ("200".equals(code)){
            UserBean userBean = SharePreUtils.ResultToUserBean(bean.getResult());
            UserInfoManger.getInstance().setBean(userBean);
           // UserInfoManger.getInstance().CheckAvatarIsUpData(); //检查是否要更新头像
            Log.d("token","MainActivity====name:"+userBean.getName());
        }else if ("1006".equals(code)){
            Log.d("token","MainActivity====token失效了！");
        }

    }

    @Override
    public void onAutoLoginFailure(String mess) {
        Toast.makeText(this, ""+mess, Toast.LENGTH_SHORT).show();
    }

}
