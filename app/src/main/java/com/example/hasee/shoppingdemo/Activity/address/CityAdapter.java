package com.example.hasee.shoppingdemo.Activity.address;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/29
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewhoder>{

    private Context context;
    private List<CityBean.ChildBeanX> list = new ArrayList<>();
    private CityItemClick cityItemClick;
    public void RefreshCity(List<CityBean.ChildBeanX> list){
        this.list =list;
        notifyDataSetChanged();
    }
    public void ClearList(){
        this.list.clear();
        notifyDataSetChanged();
    }

    public void setCityItemClick(CityItemClick cityItemClick) {
        this.cityItemClick = cityItemClick;
    }

    @NonNull
    @Override
    public CityViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, viewGroup, false);
        return new CityViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewhoder cityViewhoder, final int i) {
        Log.d("adapter",""+list.get(i).getValue());
            cityViewhoder.title.setText(list.get(i).getValue());
            cityViewhoder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cityItemClick.CityClick(i,list.get(i).getValue());
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CityViewhoder extends RecyclerView.ViewHolder{
        TextView title;
        public CityViewhoder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.item_cityTag);
        }
    }

    public interface CityItemClick{
        void CityClick(int index,String city);
    }
}
