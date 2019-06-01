package com.example.hasee.shoppingdemo.Activity.search.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lmz on 2019/05/23
 */
public class SelectTagAdapter extends RecyclerView.Adapter<SelectTagAdapter.TagViewhoder>{

    private Context context;
    private List<String> taglist = new ArrayList<>();
    private ItemClick click;
    public void Refresh(List<String> taglist){
        this.taglist =taglist;
        notifyDataSetChanged();
    }

    public void setClick(ItemClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public TagViewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context =viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_text, viewGroup, false);
        return new TagViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewhoder tagViewhoder, final int i) {
            tagViewhoder.textView.setText(taglist.get(i));
        Random random =new Random();
        int i1 = random.nextInt(256);
        int i2 = random.nextInt(256);
        int i3 = random.nextInt(256);
        tagViewhoder.textView.setTextColor(Color.rgb(i1,i2,i3));

        tagViewhoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.ItemInfo(taglist.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return taglist.size();
    }

    class TagViewhoder extends RecyclerView.ViewHolder{
        TextView textView;
        public TagViewhoder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_search_tag);
        }
    }

    public interface ItemClick{
        void ItemInfo(String name);
    }
}
