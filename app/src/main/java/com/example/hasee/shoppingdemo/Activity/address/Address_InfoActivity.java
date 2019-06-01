package com.example.hasee.shoppingdemo.Activity.address;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.CheckStatusUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Address_InfoActivity extends AppCompatActivity implements View.OnClickListener,ProvinceAdapter.ProvinceItemClick,CityAdapter.CityItemClick,TwonAdapter.TwonClick,ContentPresenter.IAddressUpdateView {

    private ImageView address_back;
    private EditText addressinfo_name;
    private EditText addressinfo_phone;
    private TextView addressinfo_checkCity;
    private EditText addressinfo_final;
    private Button addressinfo_commit;
    private PopupWindow popupWindow;
    private List<CityBean> list ;
    private  RecyclerView province,twon,cityrecyc;
    private ProvinceAdapter adapter;
    private CityAdapter cityAdapter;
    private  List<CityBean.ChildBeanX> currProvince;
    private TwonAdapter twonAdapter;
    private StringBuffer sb = new StringBuffer();
    private int currProIndex,currCityIndex;
    private ContentPresenter.IAddressUpdatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__info);

        initView();
        ReadCityInfo();
        AddressPoPwid();
        presenter =new AddressPresenter(this);

    }

    private void ReadCityInfo() {
        String city = InputAssets();
        System.out.println(city);
        try {
            JSONArray jsonArray =new JSONArray(city);
            Log.d("city:",""+jsonArray.length());
            list =new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CityBean cityBean =new CityBean();
                String id = jsonObject.getString("id");
                String value = jsonObject.getString("value");
                List<CityBean.ChildBeanX> child = new ArrayList<>();
                JSONArray citychild = jsonObject.getJSONArray("child");
                for (int j=0;j<citychild.length();j++){
                    JSONObject cityobject = citychild.getJSONObject(j);
                    CityBean.ChildBeanX citybb =new CityBean.ChildBeanX();
                    citybb.setId(cityobject.getString("id"));
                    citybb.setValue(cityobject.getString("value"));
                    JSONArray twonArray = cityobject.getJSONArray("child");

                    List<CityBean.ChildBeanX.ChildBean> twonlist = new ArrayList<>();
                    for (int k=0;k<twonArray.length();k++){
                        CityBean.ChildBeanX.ChildBean twon =new CityBean.ChildBeanX.ChildBean();
                        JSONObject twonArrayJSONObject = twonArray.getJSONObject(k);
                        twon.setId(twonArrayJSONObject.getString("id"));
                        twon.setValue(twonArrayJSONObject.getString("value"));
                        twon.setChild(twonArrayJSONObject.getString("child"));
                        twonlist.add(twon);
                    }
                    citybb.setChild(twonlist);
                    child.add(citybb);
                }
                cityBean.setChild(child);
                cityBean.setId(id);
                cityBean.setValue(value);
                list.add(cityBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        address_back = (ImageView) findViewById(R.id.address_back);
        addressinfo_name = (EditText) findViewById(R.id.addressinfo_name);
        addressinfo_phone = (EditText) findViewById(R.id.addressinfo_phone);
        addressinfo_checkCity = (TextView) findViewById(R.id.addressinfo_checkCity);
        address_back.setOnClickListener(this);
        addressinfo_checkCity.setOnClickListener(this);
        addressinfo_final = (EditText) findViewById(R.id.addressinfo_final);
        addressinfo_commit = (Button) findViewById(R.id.addressinfo_commit);

        addressinfo_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addressinfo_commit:
                String addressName = addressinfo_name.getText().toString().trim();
                String addressPhone = addressinfo_phone.getText().toString().trim();
                boolean isPhone = RegexUtils.isMobileExact(addressPhone);
                if (!isPhone){
                    CheckStatusUtils.ToastMess("手机号不合法");
                    return;
                }
                Toast.makeText(this, "2222", Toast.LENGTH_SHORT).show();
                String myaddress = addressinfo_final.getText().toString().trim();
                StringBuffer addressInfo =new StringBuffer();
                addressInfo.append(addressName+","+addressPhone+","+myaddress);
                Log.d("address","地址是："+addressInfo.toString());
                presenter.UpdateUserAddressInfo(addressInfo.toString()); //向服务器去更新地址信息
                break;
            case R.id.address_back:
                onBackPressed();
                break;
            case R.id.addressinfo_checkCity:
                //在这里选择城市

                boolean showing = popupWindow.isShowing();

                Toast.makeText(this, "状态"+showing, Toast.LENGTH_SHORT).show();
                if (!popupWindow.isShowing()){
                    popupWindow.showAtLocation(Address_InfoActivity.this.findViewById(R.id.address_mmain), Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,0);
                }else {
                    popupWindow.dismiss();
                }
                break;

        }
    }

        private void AddressPoPwid() {



            View view = LayoutInflater.from(this).inflate(R.layout.address_popwid, null);
            popupWindow =new PopupWindow(view);
            popupWindow.setHeight(700);
            WindowManager wm = (WindowManager) this
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            popupWindow.setWidth(width);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE)); //设置颜色
            popupWindow.setOutsideTouchable(true); //设置点击外部消失
            popupWindow.setAnimationStyle(R.style.popwid_style);

            province =view.findViewById(R.id.address_popwid_province);
            cityrecyc =view.findViewById(R.id.address_popwid_city);
            twon =view.findViewById(R.id.address_popwid_twon);
            //省
            LinearLayoutManager provinceManager =new LinearLayoutManager(this);
            provinceManager.setOrientation(LinearLayoutManager.VERTICAL);
            province.setLayoutManager(provinceManager);
            province.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            adapter =new ProvinceAdapter(list);
            adapter.setClick(this);
            province.setAdapter(adapter);
            //市
            LinearLayoutManager cityManager =new LinearLayoutManager(this);
            cityManager.setOrientation(LinearLayoutManager.VERTICAL);
            cityrecyc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            cityrecyc.setLayoutManager(cityManager);
            cityAdapter =new CityAdapter();
            cityAdapter.setCityItemClick(this);
            cityrecyc.setAdapter(cityAdapter);
            //县
            LinearLayoutManager twonManager =new LinearLayoutManager(this);
            twonManager.setOrientation(LinearLayoutManager.VERTICAL);
            twon.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            twon.setLayoutManager(twonManager);
            twonAdapter =new TwonAdapter();
            twonAdapter.setClick(this);
            twon.setAdapter(twonAdapter);

        }
    //获取 城市的JSon串
    private String InputAssets() {
        StringBuffer stringBuffer = new StringBuffer();
        AssetManager assets = getAssets();
        try {
            BufferedReader bf =new BufferedReader(new InputStreamReader(assets.open("content.json")));
            String line;
            while ((line=bf.readLine())!=null){
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("city","长度："+stringBuffer.toString().length());
        Log.d("city","长度："+stringBuffer.toString());
        return stringBuffer.toString();
    }


    @Override
    public void ProvinecItem(int index, String province) {
        sb.delete(0,sb.length());
        cityAdapter.ClearList();
        twonAdapter.ClearList();
        currProIndex =index;
        currProvince = list.get(index).getChild();
        Log.d("city",""+currProvince.size());
        CheckStatusUtils.ToastMess(province);
        cityAdapter.RefreshCity(currProvince);
    }

    @Override
    public void CityClick(int index, String city) {
        currCityIndex =index;
        List<CityBean.ChildBeanX.ChildBean> child = currProvince.get(index).getChild();
        twonAdapter.ClearList();
        CheckStatusUtils.ToastMess(city);
        twonAdapter.RefreshTwon(child);
    }

    @Override
    public void ItemClick(String twon) {
        String value = list.get(currProIndex).getValue();
        sb.append(value+"省/行政区");
        String cityname = currProvince.get(currCityIndex).getValue();
        sb.append(cityname);
        sb.append(twon);
        addressinfo_final.setText(sb.toString());

    }
    // 更新成功
    @Override
    public void OnUpdateSuccess(AddressBean bean) {
           if (bean!=null){
               CheckStatusUtils.ToastMess("添加成功");
               onBackPressed();
           }
    }
    //更新错误
    @Override
    public void OnUpdateFailure(String mess) {
        CheckStatusUtils.ToastMess(mess);
    }
}
