package com.example.hasee.shoppingdemo.Activity.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hasee.shoppingdemo.Bean.RegisterBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;

public class RegisterActivity extends AppCompatActivity implements ContentPresenter.IRegisterView, View.OnClickListener {


    private EditText register_pwd;
    private EditText register_user;
    private Button register_commit;
    private ContentPresenter.IRegisterPresenter iRegisterPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        iRegisterPresenter =new RegisterPresenter(this);
    }



    private void initView() {
        register_pwd = (EditText) findViewById(R.id.register_pwd);
        register_user = (EditText) findViewById(R.id.register_user);
        register_commit = (Button) findViewById(R.id.register_commit);

        register_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_commit:
                String name = register_user.getText().toString().trim();
                String pwd = register_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(name) ||TextUtils.isEmpty(pwd) ){
                    Toast.makeText(this, "输入不可为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    iRegisterPresenter.RegisterUserInfo(name,pwd);
                }
                break;
        }
    }


    @Override
    public void onRegisterSuccess(RegisterBean bean) {
        String result = bean.getResult();
        if ("注册成功".equals(result)){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegisterFailure(String mess) {

    }

}
