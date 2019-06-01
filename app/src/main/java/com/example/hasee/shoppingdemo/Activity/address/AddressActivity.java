package com.example.hasee.shoppingdemo.Activity.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.CheckStatusUtils;
import com.example.hasee.shoppingdemo.Utils.UserInfoManger;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView address_back;
    private LinearLayout address_layout;
    private RecyclerView address_list;
    private TextView address_btn;
    private CheckAddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initView();
        initdata();

    }

    private void initdata() {
        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        address_list.setLayoutManager(manager);
        addressAdapter= new CheckAddressAdapter();
        address_list.setAdapter(addressAdapter);


        UserBean bean = UserInfoManger.getInstance().getBean();
        if (bean!=null){
            String address = (String) bean.getAddress();
            System.out.println(address);
            if (address==null){
                CheckStatusUtils.ToastMess("你还没有设置地址，快去设置吧");
                return;
            }
            System.out.println(address);
            String[] split = address.split(",");
            System.out.println(split[0]+"=="+split[1]+"==="+split[2]);
            addressAdapter.Refresh(split);
        }
    }

    private void initView() {
        address_back = (ImageView) findViewById(R.id.address_back);
        address_back.setOnClickListener(this);
        address_layout = (LinearLayout) findViewById(R.id.addressinfo_layout);
        address_list = (RecyclerView) findViewById(R.id.address_list);
        address_btn = (TextView) findViewById(R.id.address_btn);
        address_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_back:
                onBackPressed();
                break;
            case R.id.address_btn:
                Intent addressInfo  = new Intent(this, Address_InfoActivity.class);
                startActivity(addressInfo);
                break;
        }
    }
}
