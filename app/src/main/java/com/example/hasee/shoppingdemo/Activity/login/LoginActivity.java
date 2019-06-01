package com.example.hasee.shoppingdemo.Activity.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Activity.MainActivity;
import com.example.hasee.shoppingdemo.Activity.register.RegisterActivity;
import com.example.hasee.shoppingdemo.Bean.LoginBean;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.UserInfoManger;
import com.example.hasee.shoppingdemo.Utils.SharePreUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ContentPresenter.ILoginView {

    private EditText login_pwd;
    private EditText login_user;
    private Button login_commit;
    private TextView login_to_register;
    private ContentPresenter.ILoginPresenter iLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        iLoginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_user = (EditText) findViewById(R.id.login_user);
        login_commit = (Button) findViewById(R.id.login_commit);
        login_to_register = (TextView) findViewById(R.id.login_to_register);
        login_commit.setOnClickListener(this);
        login_to_register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //设置下划线
        login_to_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_commit:
                String name = login_user.getText().toString().trim();
                String pwd = login_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "输入不可以为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    iLoginPresenter.LoginUserInfo(name,pwd);
                }
                break;
            case R.id.login_to_register:
                Intent intent  = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onLoginSuccess(LoginBean bean) {
        LoginBean.ResultBean result = bean.getResult();
        UserBean userBean = SharePreUtils.ResultToUserBean(result);
        UserInfoManger.getInstance().setBean(userBean);
        UserInfoManger.getInstance().saveToken(result.getToken()); //保存token
     //   UserInfoManger.getInstance().CheckAvatarIsUpData(); //检查是否要更新头像
        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onLoginFailure(String mess) {
        Log.d("LoginActivity","错误："+mess);
    }
}
