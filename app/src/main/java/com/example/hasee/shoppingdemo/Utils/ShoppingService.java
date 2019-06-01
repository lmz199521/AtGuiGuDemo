package com.example.hasee.shoppingdemo.Utils;

import com.example.hasee.shoppingdemo.Activity.address.AddressBean;
import com.example.hasee.shoppingdemo.Activity.search.RecommndBean;
import com.example.hasee.shoppingdemo.Activity.search.SearchBean;
import com.example.hasee.shoppingdemo.Activity.sendGoods.SendGoodsBean;
import com.example.hasee.shoppingdemo.Bean.GoodsListBean;
import com.example.hasee.shoppingdemo.Bean.HomeBean;
import com.example.hasee.shoppingdemo.Bean.HotPostBean;
import com.example.hasee.shoppingdemo.Bean.JacketBean;
import com.example.hasee.shoppingdemo.Bean.LoginBean;
import com.example.hasee.shoppingdemo.Bean.LogoutBean;
import com.example.hasee.shoppingdemo.Bean.NewPostBean;
import com.example.hasee.shoppingdemo.Bean.UpDateBean;
import com.example.hasee.shoppingdemo.Bean.RegisterBean;
import com.example.hasee.shoppingdemo.Bean.TypeBean;
import com.example.hasee.shoppingdemo.Bean.TypeTagBean;
import com.example.hasee.shoppingdemo.Bean.UpLoadImageBean;
import com.example.hasee.shoppingdemo.View.Cart.OrderInfoBean;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Lmz on 2019/05/09
 * 网络API接口类
 */
public interface ShoppingService {

        //上衣的请求
    @GET
    Observable<JacketBean> getJacketInfo(@Url String uri);
    //   http://169.254.206.13:8080/atguigu/json/HOT_POST_URL.json
    //热门的请求
    @GET(Constans.HOT_POST_URL)
    Observable<HotPostBean> getHotPostInfo();

    //主页请求
    //http://169.254.206.13:8080/atguigu/json/HOME_URL.json
    @GET(Constans.HOME_URL)
    Observable<HomeBean> getHomeDataInfo();

    //分类标签请求
    // http://169.254.206.13:8080/atguigu/json/TAG_URL.json
    @GET(Constans.TAG_URL)
    Observable<TypeTagBean> getTypeTagInfo();
    //分类页面分类请求
    //  http://169.254.206.13:8080/atguigu/json/type_url.json
    @GET(Constans.TYPE_URL)
    Observable<TypeBean> getTypeInfo();
    //分类页面详情 请求
    @GET()
    Observable<JacketBean> getTypeGoodsInfo(@Url String url);
    //发现页面 新帖 请求
    @GET(Constans.NEW_POST_URL)
    Observable<NewPostBean> getCommunityNewPostInfo();
    //商品详情列表 页面请求
    @GET()
    Observable<GoodsListBean> getGoodsListInfo(@Url String url);

    //注册
    @POST("register")
    @FormUrlEncoded //表单
    Observable<RegisterBean> RegisterUser(@FieldMap Map<String,String> map) ;
    //登录
    @POST("login")
    @FormUrlEncoded //表单
    Observable<LoginBean> LoginUser(@FieldMap Map<String,String> map) ;
    //更新手机号
    @POST("updatePhone")
    @FormUrlEncoded // 表单
    Observable<UpDateBean> UpdatePhone(@FieldMap Map<String,String> map);
    //更新金钱 因为json 串一样， 所以共用一个
    @POST("updateMoney")
    @FormUrlEncoded // 表单
    Observable<UpDateBean> UpdateMoney(@FieldMap Map<String,String> map);
    //自动登录
    @POST("autoLogin")
    @FormUrlEncoded // 表单
    Observable<LoginBean> AutoLoginUser(@FieldMap Map<String,String> map);
    /**
     *  上传头像
     */
    @Multipart
    @POST("upload")
    Observable<UpLoadImageBean> UploadImager(@Part MultipartBody.Part file);

    /**
     *    下载头像    使用Streaming 可以避免oom
     * @param avatarUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> DownLoadImager(@Url String avatarUrl);


    //退出
    @POST("logout")
    Observable<LogoutBean> LogOutUser();
    //搜索页面的推荐接口
    @GET("getRecommend")
    Observable<RecommndBean> getRecommendGoodsInfo();
    //搜索
    @GET("search")
    Observable<SearchBean> getSearchGoodsInfo(@Query("name")String name);

    /**
     *   checkOneProductInventory
     *   检查服务端的库存问题
     */
    @POST("checkOneProductInventory")
    @FormUrlEncoded
    Observable<EntityUtils<String>> CheckGoodsNumber(@FieldMap Map<String,String> map);

    /**
     *   给服务器购物车 添加数据
     * addOneProduct
     */
    @POST("addOneProduct")
    Observable<EntityUtils<String>> AddGoodsToServer(@Body RequestBody body);

    /**
     * 购买接口
     * getOrderInfo
     */

    @POST("getOrderInfo")
    Observable<EntityUtils<OrderInfoBean>> CommitShoppingCart(@Body RequestBody body);

    /**
     *  获取服务器购物车的数据
     *  getShortcartProducts
     */

    @GET("getShortcartProducts")
    Observable<EntityUtils<String>> getShoppingCartByServer();

    /**
     * 服务端验签接口
     * post   confirmServerPayResult
     */
    @POST("confirmServerPayResult")
    Observable<EntityUtils<Boolean>> getFirmServerPayInfo(@Body RequestBody body);

    /**
     * 更新地址接口
     */
    @POST("updateAddress")
    @FormUrlEncoded // 表单
    Observable<AddressBean> UpdateAddressInfo(@FieldMap Map<String,String> map);
    /**
     * 查看代发货
     */
    @GET("findForSend")
    Observable<EntityUtils<List<SendGoodsBean>>> getNotSendGoodsInfo();
}
