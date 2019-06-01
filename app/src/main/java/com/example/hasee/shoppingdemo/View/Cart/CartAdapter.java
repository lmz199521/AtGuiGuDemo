package com.example.hasee.shoppingdemo.View.Cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingdemo.Bean.GoodsinfoDataBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.example.hasee.shoppingdemo.Utils.Constans;
import com.example.hasee.shoppingdemo.Utils.Database.GoodsinfoDatabaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/17
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHoder>{

    private Context context;
    private List<GoodsinfoDataBean> list;
    private addItemClick addItemClick;
    private SubItemClick subItemClick;
    private  TextView countPrice;
    private CheckBox checkBox,deleteBox;
    private GoodsinfoDatabaseDao dao;
    private GoodsIsNull isNull;

    public CartAdapter(TextView countPrice, CheckBox checkBox,CheckBox deleteBox) {
        this.countPrice = countPrice;
        this.checkBox = checkBox;
        this.deleteBox = deleteBox;

    }

    public void setIsNull(GoodsIsNull isNull) {
        this.isNull = isNull;
    }

    public void Refresh(List<GoodsinfoDataBean> list){
        if (this.list == null){
            this.list =  new ArrayList<>();
        }
        this.list.clear();
        this.list =list;
        CheckAll();
        ShowPrice();
        notifyDataSetChanged();
    }
    public void isChecked(boolean ischeck){
        for (int i=0;i<list.size();i++){
            list.get(i).setCheck(ischeck);
        }
        CheckAll();
        ShowPrice();
        notifyDataSetChanged();
    }
    public void DeleRefresh(){
        notifyDataSetChanged();
    }
    //删除
    public void DeleteGoods(){
        if (list!=null && list.size()>=0){
            for (int i=0;i<list.size();i++){
                GoodsinfoDataBean bean = list.get(i);
                if (bean.isCheck()){
                    dao.DeleteData(bean.getId()+"");
                    list.remove(bean);
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(0,list.size()-i);
                    Log.d("SIZE",""+list.size());
                    i--;
                }
            }
        }else {
            return;
        }
        Log.d("SIZE",""+list.size());
        if (list.size()==0){
            isNull.GoodsIsnull();
        }

    }
    //设置全选和反选
    private void CheckAll(){
        int num =0;
        for (int i=0;i<list.size();i++){
            GoodsinfoDataBean goodsinfoDataBean = list.get(i);

            if (goodsinfoDataBean.isCheck()){
                num++; //选中
            }else {
                //非选中
                checkBox.setChecked(false);
                deleteBox.setChecked(false);
            }
        }
        Log.d("CheckAll","num:"+num+"===size："+list.size());
        if (num == list.size()){
            checkBox.setChecked(true);
            deleteBox.setChecked(true);
        }
    }



    //计算价格并显示
    private void ShowPrice() {
        double Countp=0.0;
        Countp = getCountPrice(Countp);
        countPrice.setText("￥"+Countp);
    }

    private double getCountPrice(double countp) {
        if (list!=null && list.size()>0){
            for (int i=0;i<list.size();i++){
                GoodsinfoDataBean dataBean = list.get(i);
                boolean check = dataBean.isCheck();
            //    Toast.makeText(context, ""+check, Toast.LENGTH_SHORT).show();
                if (check){
                    double price = Double.valueOf(dataBean.getNumber()) * Double.valueOf(dataBean.getPrice());
                    countp += price;
                }
            }
        }
        return countp;
    }

    //设置一个加减的局部刷新方法
    public void NumberRefresh(int item, int goodsNumber){
            if (item>=0 && item<list.size()){
                list.get(item).setNumber(goodsNumber+"");
            }
            notifyItemChanged(item,"ojbk");
    }

    public void setAddItemClick(CartAdapter.addItemClick addItemClick) {
        this.addItemClick = addItemClick;
    }

    public void setSubItemClick(SubItemClick subItemClick) {
        this.subItemClick = subItemClick;
    }

    @NonNull
    @Override
    public CartViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        dao =new GoodsinfoDatabaseDao(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_goshoppingcart_goodsinfo, viewGroup, false);
        return new CartViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHoder vh, final int i) {
        final GoodsinfoDataBean bean = list.get(i);
        Glide.with(context).load(Constans.BASE_URl_IMAGE+bean.getPic())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(vh.pic);
        final String number = bean.getNumber();
        vh.number.setText(number);
        vh.price.setText("￥"+bean.getPrice());
        vh.title.setText(bean.getName());

        vh.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addItemClick.AddBtn(bean.getId(),i);
                    ShowPrice();
            }
        });
        vh.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    subItemClick.SubBtn(bean.getId(),i);
                ShowPrice();
            }
        });
        vh.radioButton.setChecked(bean.isCheck());
        //按钮的点击事件
        vh.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.radioButton.isChecked()){
                    bean.setCheck(true);
                }else {
                    bean.setCheck(false);
                }
                CheckAll();
                ShowPrice();
            }
        });
        //整个Item的点击事件
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setCheck(!bean.isCheck());
                vh.radioButton.setChecked(bean.isCheck());
                CheckAll();
                ShowPrice();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CartViewHoder extends RecyclerView.ViewHolder{
        CheckBox radioButton;
        TextView title,price,number;
        ImageView pic,add,subtract;

        public CartViewHoder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.item_goshoppingcart_radio);
            title = itemView.findViewById(R.id.item_goshoppingcart_title);
            price = itemView.findViewById(R.id.item_goshoppingcart_price);
            number = itemView.findViewById(R.id.item_goshoppingcart_number);
            pic = itemView.findViewById(R.id.item_goshoppingcart_pic);
            add = itemView.findViewById(R.id.item_goshoppingcart_addBtn);
            subtract = itemView.findViewById(R.id.item_goshoppingcart_subtract);
        }
    }

    /**
     *   设置 加减按钮的点击事件，回传回去 商品ID 和 item的下标
     */
    public interface addItemClick{
        void AddBtn(int id,int item);
    }
    public interface SubItemClick{
        void SubBtn(int id,int item);
    }
    public interface GoodsIsNull{
        void GoodsIsnull();
    }

}
