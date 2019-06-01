package com.example.hasee.shoppingdemo.View.User;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Activity.address.AddressActivity;
import com.example.hasee.shoppingdemo.Activity.goodsinfo.GoodsInfoActivity;
import com.example.hasee.shoppingdemo.Activity.login.LoginActivity;
import com.example.hasee.shoppingdemo.Activity.sendGoods.SendGoodsActivity;
import com.example.hasee.shoppingdemo.Bean.LogoutBean;
import com.example.hasee.shoppingdemo.Bean.UpDateBean;
import com.example.hasee.shoppingdemo.Bean.UpLoadImageBean;
import com.example.hasee.shoppingdemo.Bean.User;
import com.example.hasee.shoppingdemo.Bean.UserBean;
import com.example.hasee.shoppingdemo.MyApplication;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.CheckStatusUtils;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.example.hasee.shoppingdemo.Utils.UserInfoManger;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener,ContentPresenter.IUpdatePhoneView,ContentPresenter.IUpdateMoneyView,ContentPresenter.IUploadImageView{


    private ImageButton user_avatar;
    private TextView user_username;
    private TextView user_phone;
    private TextView user_money,user_location,user_sendGoods;
    private Button logoutBtn;
    private ContentPresenter.IUpDatePhoneNumber iUpDatePhoneNumber;
    private ContentPresenter.IUpDateMoneyNumber iUpDateMoneyNumber;
    private ContentPresenter.IUploadImagePresenter imagePresenter;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        iUpDatePhoneNumber = new IUpdatePhonePresenter(this);
        iUpDateMoneyNumber =new IUpdateMoneyPresenter(this);
        imagePresenter = new IUploadImagePresenter(this);
        UserInfo();
        return view;
    }

    private void initView(View view) {
        user_avatar = (ImageButton) view.findViewById(R.id.user_avatar);
        user_username = (TextView) view.findViewById(R.id.user_username);
        user_username.setOnClickListener(this);
        user_avatar.setOnClickListener(this);
        user_phone = (TextView) view.findViewById(R.id.user_phone);
        user_phone.setOnClickListener(this);
        user_money = (TextView) view.findViewById(R.id.user_money);
        user_money.setOnClickListener(this);
        logoutBtn = (Button)view.findViewById(R.id.user_logout);
        logoutBtn.setOnClickListener(this);
        user_location =(TextView)view.findViewById(R.id.user_location);
        user_location.setOnClickListener(this);
        user_sendGoods = (TextView)view.findViewById(R.id.user_sendGoods);
        user_sendGoods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_avatar:
                String s = UserInfoManger.getInstance().GetToken();
                Intent avatarIntent =new Intent(Intent.ACTION_PICK);
                avatarIntent.setType("image/*");
                startActivityForResult(avatarIntent,111);
                break;
            case R.id.user_username:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.user_phone:
                AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity());
                final EditText editText = new EditText(getActivity());
                dialog.setTitle("请输入要修改的手机号")
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String phone = editText.getText().toString();
                                Log.d("dia","手机号："+phone);
                                iUpDatePhoneNumber.UpdatePhone(phone);

                            }
                        })
                        .setNegativeButton("取消",null).show();

                break;
            case R.id.user_money:
                AlertDialog.Builder dialog2 =new AlertDialog.Builder(getActivity());
                final EditText editText1 = new EditText(getActivity());
                dialog2.setTitle("请输入充值的金额")
                        .setView(editText1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String money = editText1.getText().toString();
                                Log.d("dia","金额："+money);
                                iUpDateMoneyNumber.UpdateMoney(money);

                            }
                        })
                        .setNegativeButton("取消",null).show();

                break;
            case R.id.user_logout:
                UserBean bean = UserInfoManger.getInstance().getBean();
                if (bean==null){
                    CheckStatusUtils.ToastMess("你还没登录呢，退出个屁");
                    return;
                }
                imagePresenter.LogoutUser();
                break;


            case R.id.user_location:
                UserBean bean2= UserInfoManger.getInstance().getBean();
                if (bean2==null){
                    CheckStatusUtils.ToastMess("更改地址请先登录！！");
                    return;
                }
                Intent userToAddress = new Intent(getActivity(), AddressActivity.class);
                startActivity(userToAddress);
                break;
            case R.id.user_sendGoods:
                UserBean sendGoods= UserInfoManger.getInstance().getBean();
                if (sendGoods==null){
                    CheckStatusUtils.ToastMess("请登录");
                    return;
                }
                Intent send =new Intent(getActivity(), SendGoodsActivity.class);
                startActivity(send);
                break;

            default:break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
     //   UserInfo();
    }

    private void UserInfo() {

        UserBean bean = UserInfoManger.getInstance().getBean();
        if (bean != null) {//获取用户名
            user_username.setText(bean.getName() + "");
            if (bean.getMoney()!=null ){
                user_money.setText("我的瓜子       "+bean.getMoney());
            }
            if (bean.getPhone()!=null){
                user_phone.setText("手机号         "+bean.getPhone());
            }
            if (bean.getAvatar()!=null){
                Log.d("token","网络获取图片路径"+bean.getAvatar());
                Glide.with(MyApplication.getContext()).load(Constans.BASE_URl_IMAGE+bean.getAvatar())
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(user_avatar);

            }


        }
    }

    @Override
    public void onUpDatePhoneSuccess(UpDateBean bean) {
        Log.d("token","UserFragment=========phone："+bean.getResult());
        UserInfoManger.getInstance().SavePhone(bean.getResult());
        user_phone.setText("手机号         "+bean.getResult());
    }

    @Override
    public void pnUpDatePhoneFailure(String mess) {

    }

    @Override
    public void onUpDateMoneySuccess(UpDateBean bean) {
            user_money.setText("我的瓜子       "+bean.getResult());
    }

    @Override
    public void pnUpDateMoneyFailure(String mess) {
        Toast.makeText(MyApplication.getContext(), ""+mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode ==111 && data!=null){
            Uri uri = data.getData();
            ContentResolver resolver = getActivity().getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
            cursor.moveToFirst();
            String path =cursor.getString(cursor.getColumnIndex("_data"));
            File file =new File(path);
            imagePresenter.Uploadimage(file);

        }



    }

    @Override
    public void onUploadSuccess(UpLoadImageBean bean) {
            UserInfoManger.getInstance().SaveAvatar(bean.getResult()); // 保存头像
            Glide.with(MyApplication.getContext()).load(Constans.BASE_URl_IMAGE+bean.getResult())
                                .placeholder(R.drawable.tblogo)
                                .into(user_avatar);

    }

    @Override
    public void onUploadFailuer(String mess) {
        Log.d("token","上传图片失败了---》："+mess);
    }

    /**
     *   退出成功，把 token 和userBen 都置为空
     * @param bean
     */
    @Override
    public void onLogoutSuccess(LogoutBean bean) {
                Log.d("token","登录状态："+bean.getResult());
        UserInfoManger.getInstance().saveToken("");
        UserInfoManger.getInstance().setBean(null);
        getActivity().finish();

    }

    @Override
    public void onLogoutFailure(String mess) {
        Log.d("token","状态："+mess);
    }
}
