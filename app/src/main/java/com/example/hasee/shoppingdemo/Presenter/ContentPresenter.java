package com.example.hasee.shoppingdemo.Presenter;

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
import com.example.hasee.shoppingdemo.Bean.RegisterBean;
import com.example.hasee.shoppingdemo.Bean.TypeBean;
import com.example.hasee.shoppingdemo.Bean.TypeTagBean;
import com.example.hasee.shoppingdemo.Bean.UpDateBean;
import com.example.hasee.shoppingdemo.Bean.UpLoadImageBean;
import com.example.hasee.shoppingdemo.View.BaseView;
import com.example.hasee.shoppingdemo.View.Cart.OrderInfoBean;
import com.example.hasee.shoppingdemo.View.Cart.ShoopingGoodsBean;

import org.json.JSONArray;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmz on 2019/05/10
 * 定义接口契约的总类
 */
public class ContentPresenter {


    //设置获取主页面数据接口
    public interface IHomePresenter {
        void getHomeData();
    }

    public interface IHomeView extends BaseView {
        void onGetHomeDataSuccess(HomeBean homeBean);

        void onGetDataFailure(String ErrorMess);
    }

    //设置分类页面的数据接口
    public interface ITypeTagPresenter {
        void getITypeTagData();
    }

    public interface ITypeTagView extends BaseView {
        void onGetTypeTagDataSuccess(TypeTagBean bean);

        void onGetTypeTagDataFailure(String ErrorMess);
    }

    //设置分类页面的分类数据的接口
    public interface ITypePresenter {
        void getITypeData();
    }

    public interface ITypeView extends BaseView {
        void onGetTypeDataSuccess(TypeBean bean);

        void onGetTypeFailure(String errorMess);
    }

    //设置分类页面商品详情接口
    public interface ITypeInfoPresenter {
        void getTypeInfoData(String url);
    }

    public interface ItypeInfoView extends BaseView {
        void onGetTypeInfoDataSuccess(JacketBean bean);

        void onGetTypeInfoFailure(String errorMess);
    }

    //设置获取热门页面接口
    public interface IHotPostPresenter {
        void getHotPostData();
    }

    public interface IHostView extends BaseView {
        void onGetHotPostDataSuccess(HotPostBean hotPostBean);

        void onGetHotPostDataFailure(String ErrorMess);
    }

    //获取发现页面 新帖 页面接口
    public interface INewsPostPresenter {
        void getNewsPostData();
    }

    public interface INewsPostView extends BaseView {
        void onGetNewsPostDataSuccess(NewPostBean bean);

        void onGetNewPostDataFailure(String ErrorMess);
    }

    //商品详情列表页面 接口
    public interface IGoodsListPresenter {
        void getGoodsListData(String url);
    }

    public interface IGoodsListView extends BaseView {
        void onGetGoodsListDataSuccess(GoodsListBean bean);

        void onGetGoodsListDataFailure(String ErrorMess);
    }

    //注册页面
    public interface IRegisterPresenter {
        void RegisterUserInfo(String name, String pwd);

    }

    public interface IRegisterView {
        void onRegisterSuccess(RegisterBean bean);

        void onRegisterFailure(String mess);
    }

    //登录界面
    public interface ILoginPresenter {
        void LoginUserInfo(String name, String pwd);
    }

    public interface ILoginView {
        void onLoginSuccess(LoginBean bean);

        void onLoginFailure(String mess);
    }

    //更新手机号
    public interface IUpDatePhoneNumber {
        void UpdatePhone(String phone);
    }

    public interface IUpdatePhoneView {
        void onUpDatePhoneSuccess(UpDateBean bean);

        void pnUpDatePhoneFailure(String mess);
    }

    //更新金钱
    public interface IUpDateMoneyNumber {
        void UpdateMoney(String money);
    }

    public interface IUpdateMoneyView {
        void onUpDateMoneySuccess(UpDateBean bean);

        void pnUpDateMoneyFailure(String mess);
    }

    //自动登录
    public interface IAutoLoginUserPresenter {
        void AutoLogin(String token);
    }

    public interface IAutoLoginUserView {
        void onAutoLoginSuccess(LoginBean bean);

        void onAutoLoginFailure(String mess);
    }

    //上传头像
    public interface IUploadImagePresenter {
        void Uploadimage(File file);

        //退出登录
        void LogoutUser();
    }

    public interface IUploadImageView {
        void onUploadSuccess(UpLoadImageBean bean);

        void onUploadFailuer(String mess);

        //退出
        void onLogoutSuccess(LogoutBean bean);

        void onLogoutFailure(String mess);
    }

    //搜索页面
    public interface ISearchPresenter {
        void GetRecommendData();

        //推荐商品详情
        void GetGoodsInfo(String url);

        //搜索
        void getSearchGoodsinfo(String name);
    }

    public interface ISearchView {
        void onRecommendSuccess(RecommndBean bean);

        void onRecommendFailure(String mess);

        //推荐商品详情
        void onRecommendinfoSuccess(JacketBean bean);

        void onRecommendinfoFailure(String mess);

        //搜索
        void onSearchGoodsinfoSuccess(SearchBean bean);

        void onSearchGoodsinfoFailure(String mess);
    }

    //商品购物车 接口
    public interface ICheckGoodsPresenter{
        void CheckGoodsNumber(String id,String number);
        //向服务器发送输入添加到购物车
        void AddGoodsInfoToServer(String productId,String productNum,String productName,String url) throws JSONException;
    }
    public interface ICheckGoodsView{
        void onCheckGoodsinfoSuccess(String json) throws JSONException;
        void onCheckGoodsinfoFailure(String mess);

        void onAddGoodsToServerSuccess(String json);
        void onAddGoodsToServerFailure(String mess);
    }
    /**
     *  购物车提交
     */
    public interface IShoppingCartPresenter{
        void CommitGoods(String subject, String totalPrice, JSONArray json);

        void GetDataByServer();

        void getFirmServerPayInfo(String outTradeNo,String resultContent,boolean isTrue);
    }
    public interface IShoppingCartView{
        void onCommitGoodsSuccess(OrderInfoBean result);
        void onCommitGoodsFailure(String mess);

        void onGetDataSuccess(ArrayList<ShoopingGoodsBean> list);
        void onGetDataFailure(String mess);

        void onFirmServerSuccess(boolean isTrue);
        void onFirmServerFailure(String mess);
    }
    // 更新收货地址
    public interface IAddressUpdatePresenter{
        void UpdateUserAddressInfo(String address);
    }
    public interface IAddressUpdateView{
        void OnUpdateSuccess(AddressBean bean);
        void OnUpdateFailure(String mess);
    }
    //查看待发货
    public interface ISendgoodsPresenter{
        void NotSendGoodsInfo();
    }
    public interface ISendGoodsView{
        void OnGetNotSendGoodsInfoSuccess(List<SendGoodsBean> goods);
        void OnGetNotSendGoodsInfoFailure(String mess);
    }


}
