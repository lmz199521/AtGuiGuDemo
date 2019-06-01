package com.example.hasee.shoppingdemo.Activity.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.shoppingdemo.Activity.search.adapter.HotRecommendAdapter;
import com.example.hasee.shoppingdemo.Activity.search.adapter.SearchGoodsAdapter;
import com.example.hasee.shoppingdemo.Activity.search.adapter.SelectTagAdapter;
import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.Presenter.ContentPresenter;
import com.example.hasee.shoppingdemo.R;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ContentPresenter.ISearchView,View.OnClickListener,SelectTagAdapter.ItemClick{
    private ContentPresenter.ISearchPresenter iSearchPresenter;
    private AutoCompleteTextView search_auto_text;
    private ImageView search_img;
    private TextView search_old,search_next;
    private RecyclerView search_old_recyclerview,search_recyclerview;
    private TextView search_hot;
    private RecyclerView search_hot_recyclerview;
    private HotRecommendAdapter hotadapter;
    private SelectTagAdapter tagAdapter;
    private int count=0;
    private SearchGoodsAdapter goodsAdapter;
    private List<String> taglist;
    private List<RecommndBean.ResultBean> listinfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initdata();
        initlistener();
        iSearchPresenter =new SearchPresenter(this);
        iSearchPresenter.GetRecommendData();
    }

    private void initlistener() {

    }

    private void initdata() {
        //推荐页面
        GridLayoutManager manager =new GridLayoutManager(this,3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        search_hot_recyclerview.setLayoutManager(manager);
        hotadapter =new HotRecommendAdapter();
        search_hot_recyclerview.setAdapter(hotadapter);



        //自动提示搜索
        String[] strings =new String[]{"abccc","baac","cccac","ddda","aba","aab","aac","bbba","ccaac","abc","bbc","cddd"};
        ArrayAdapter<String> str =new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,strings);
        search_auto_text.setAdapter(str);

        //搜索历史
        FlexboxLayoutManager flexbox =new FlexboxLayoutManager(this);
        flexbox.setFlexWrap(FlexWrap.WRAP);
        flexbox.setJustifyContent(JustifyContent.FLEX_START);
        search_old_recyclerview.setLayoutManager(flexbox);
        tagAdapter = new SelectTagAdapter();
        tagAdapter.setClick(this);
        search_old_recyclerview.setAdapter(tagAdapter);

        //搜索
        GridLayoutManager searmanger =new GridLayoutManager(this,2);
        searmanger.setOrientation(GridLayoutManager.VERTICAL);
        search_recyclerview.setLayoutManager(searmanger);
        goodsAdapter =new SearchGoodsAdapter();
        search_recyclerview.setAdapter(goodsAdapter);



    }

    private void initView() {
        search_auto_text = (AutoCompleteTextView) findViewById(R.id.search_auto_text);
        search_img = (ImageView) findViewById(R.id.search_img);
        search_img.setOnClickListener(this);
        search_old = (TextView) findViewById(R.id.search_old);
        search_old_recyclerview = (RecyclerView) findViewById(R.id.search_old_recyclerview);
        search_hot = (TextView) findViewById(R.id.search_hot);
        search_hot_recyclerview = (RecyclerView) findViewById(R.id.search_hot_recyclerview);
        search_next = (TextView)findViewById(R.id.search_next);
        search_next.setOnClickListener(this);
        taglist = new ArrayList<>();
        search_recyclerview = findViewById(R.id.search_recyclerview);
    }

    @Override
    public void onRecommendSuccess(RecommndBean bean) {
        listinfo = bean.getResult();
        getGoodsInfo();
    }

    private void getGoodsInfo() {
        RecommndBean.ResultBean goods = listinfo.get(count);
        String url = goods.getUrl();
        iSearchPresenter.GetGoodsInfo(url);
    }

    @Override
    public void onRecommendFailure(String mess) {

    }

    @Override
    public void onRecommendinfoSuccess(JacketBean bean) {
        List<JacketBean.ResultBean> list = bean.getResult();
        List<JacketBean.ResultBean.HotProductListBean> hotlist = list.get(0).getHot_product_list();
        hotadapter.setRefresh(hotlist);
    }

    @Override
    public void onRecommendinfoFailure(String mess) {

    }

    @Override
    public void onSearchGoodsinfoSuccess(SearchBean bean) {
        List<SearchBean.ResultBean.ChildBean> searchList = bean.getResult().get(0).getChild();
        goodsAdapter.setRefresh(searchList);

    }

    @Override
    public void onSearchGoodsinfoFailure(String mess) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_next:
                count++;
                count = count%3;
                getGoodsInfo();
                break;
            case R.id.search_img:
                String info = search_auto_text.getText().toString().trim();
                iSearchPresenter.getSearchGoodsinfo(info);
                taglist.add(info);
                tagAdapter.Refresh(taglist);
                break;
        }
    }

    @Override
    public void ItemInfo(String name) {
        iSearchPresenter.getSearchGoodsinfo(name);
    }
}
