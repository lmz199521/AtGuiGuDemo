package com.example.hasee.shoppingdemo.Activity.address;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

import java.util.List;

/**
 * Created by Lmz on 2019/05/29
 */
public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ProvinceViewhoder>{
    private List<CityBean> list;
    private Context context;
    private ProvinceItemClick click;
    public ProvinceAdapter(List<CityBean> list) {
        this.list=list;
        }


    public void setClick(ProvinceItemClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public ProvinceViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, viewGroup, false);
        return new ProvinceViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceViewhoder provinceViewhoder, final int i) {
        provinceViewhoder.title.setText(list.get(i).getValue());

        provinceViewhoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.ProvinecItem(i,list.get(i).getValue());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProvinceViewhoder extends RecyclerView.ViewHolder{
        TextView title;
        public ProvinceViewhoder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.item_cityTag);
        }
    }

    public interface ProvinceItemClick{
        void ProvinecItem(int index,String province);
    }
}
