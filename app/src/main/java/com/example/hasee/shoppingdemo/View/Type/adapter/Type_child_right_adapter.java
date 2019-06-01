package com.example.hasee.shoppingdemo.View.Type.adapter;

import android.content.Context;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Lmz on 2019/05/14
 */
public class Type_child_right_adapter extends RecyclerView.Adapter{

        public static final int HOT_LIST=0;
        public static final int TYPE_GRID=1;
        private int currType =0;
        private List<JacketBean.ResultBean> result =new ArrayList<>();
        private Context context;
         //初始化布局用的
          private  LayoutInflater inflater;

    public Type_child_right_adapter(Context context,List<JacketBean.ResultBean> result) {
        this.context =context;
        this.result =result;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        switch (position){
            case HOT_LIST:
                currType = HOT_LIST;
                break;
            case TYPE_GRID:
                currType =TYPE_GRID;
                break;
        }
        return currType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==HOT_LIST){
            View view = inflater.inflate(R.layout.item_type_child_toplistview, viewGroup, false);
            return new Hot_ListView_ViewHoder(view);
        }else if (i==TYPE_GRID){
            View view = inflater.inflate(R.layout.item_type_child_bottomlistview, viewGroup, false);
            return new Bottom_ViewHoder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == HOT_LIST) {
            Hot_ListView_ViewHoder vh = (Hot_ListView_ViewHoder) viewHolder;
            vh.setData(result.get(0).getHot_product_list());
        } else if (getItemViewType(i) == TYPE_GRID) {
            Bottom_ViewHoder vh = (Bottom_ViewHoder) viewHolder;
            vh.setData(result.get(0).getChild());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class Hot_ListView_ViewHoder extends RecyclerView.ViewHolder{
        private RecyclerView listView ;
        private List<JacketBean.ResultBean.HotProductListBean> data;
        private Type_child_right_top_adapter top_adapter;
        public Hot_ListView_ViewHoder(@NonNull View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.item_type_child_hot_listview);
        }

        public void setData(List<JacketBean.ResultBean.HotProductListBean> data) {
            this.data = data;
            LinearLayoutManager manager =new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            listView.setLayoutManager(manager);
            top_adapter =new Type_child_right_top_adapter(context,data);
            listView.setAdapter(top_adapter);

        }
    }
    class Bottom_ViewHoder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView ;
        private List<JacketBean.ResultBean.ChildBean> data;
        private Type_child_right_bottom_adapter adapter;
        public Bottom_ViewHoder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.item_type_child_bottom_recycler);
        }

        public void setData(List<JacketBean.ResultBean.ChildBean> data) {
            this.data = data;
            GridLayoutManager manager =new GridLayoutManager(context,3);
            manager.setOrientation(GridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            adapter =new Type_child_right_bottom_adapter(context,data);
            recyclerView.setAdapter(adapter);
        }
    }
}
