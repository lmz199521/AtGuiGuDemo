package com.example.hasee.shoppingdemo.Activity.address;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

/**
 * Created by Lmz on 2019/05/29
 */
public class CheckAddressAdapter extends RecyclerView.Adapter<CheckAddressAdapter.CheckAddress>{

    private Context context;
    private String[] split = new String[]{};
    public void Refresh(String[] split){
        this.split =split;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CheckAddress onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_address_list, viewGroup, false);
        return new CheckAddress(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckAddress vh, int i) {
        vh.addressName.setText(split[0]);
        vh.addressPhone.setText(split[1]);
        vh.AddressInfo.setText(split[2]);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class CheckAddress extends RecyclerView.ViewHolder{
        TextView addressName,addressPhone,AddressInfo;
        public CheckAddress(@NonNull View itemView) {
            super(itemView);
            addressName =itemView.findViewById(R.id.item_address_name);
            addressPhone =itemView.findViewById(R.id.item_address_phone);
            AddressInfo =itemView.findViewById(R.id.item_address_home);
        }
    }
}
