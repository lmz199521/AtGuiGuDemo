package com.example.hasee.shoppingdemo.Activity.address;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
public class TwonAdapter extends RecyclerView.Adapter<TwonAdapter.TwonViewhoder>{

    private Context context;
    private List<CityBean.ChildBeanX.ChildBean> child =new ArrayList<>();
    private TwonClick click;
    public void RefreshTwon(List<CityBean.ChildBeanX.ChildBean> child){
        this.child.clear();
        this.child =child;
        notifyDataSetChanged();
    }
    public void ClearList(){
        this.child.clear();
        notifyDataSetChanged();
    }

    public void setClick(TwonClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public TwonViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, viewGroup, false);
        return new TwonViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwonViewhoder twonViewhoder, final int i) {
            twonViewhoder.title.setText(child.get(i).getValue());
            twonViewhoder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.ItemClick(child.get(i).getValue());
                }
            });
    }

    @Override
    public int getItemCount() {
        return child.size();
    }

    class TwonViewhoder extends RecyclerView.ViewHolder{
        TextView title;
        public TwonViewhoder(@NonNull View itemView) {
            super(itemView);
        title =itemView.findViewById(R.id.item_cityTag);
        }
    }
    public interface TwonClick{
        void ItemClick(String twon);
    }
}
