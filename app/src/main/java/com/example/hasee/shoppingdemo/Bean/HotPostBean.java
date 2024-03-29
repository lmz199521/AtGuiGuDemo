package com.example.hasee.shoppingdemo.Bean;

import java.util.List;

/**
 * Created by Lmz on 2019/05/10
 * 热门的Bean
 */
public class HotPostBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"add_time":"1478848148","avatar":"/ugc/user/avatar/14611224500881026.jpeg","comment_list":["一定要双十一那天发帖么？_(:з」∠)_"],"comments":"1","figure":"/ugc/post/img/201611/14788481411252582.jpeg","is_essence":"1","is_hot":"1","is_like":"0","is_top":"1","likes":"2","post_id":"2616","saying":"【购物节活动】从今天起！民那桑在社区晒出购物节期间购买的订单～活动期间每天抽一位用户免单哦！！是免！单！哦！快来晒出你的订单吧！","user_id":"246715","username":"飞天小谷"},{"add_time":"1478681505","avatar":"/ugc/user/avatar/14651924470798522.png","comment_list":["然而看一眼，真没想要买的\u2026\u2026","回复尚硅谷首席神秘官：猴！","回复胖成球：这次会送，另外也会上架售卖","这个有卖吗！还是只有这次送呢？","好可爱！！！"],"comments":"5","figure":"/ugc/post/img/201611/14786815026026582.png","is_essence":"0","is_hot":"0","is_like":"0","is_top":"1","likes":"3","post_id":"2602","saying":"2333双十一马上到啦，小仓给您们带来惊喜了哦：凡是在10号18点到13号24点之间购买次元仓自营发货的订单都赠送萌萌哒小仓贴纸哦！","user_id":"90437","username":"尚硅谷首席惊喜官"},{"add_time":"1475140120","avatar":"/ugc/user/avatar/14651924470798522.png","comment_list":["666","回复尚硅谷首席铲屎官：wom","回复尚硅谷首席铲屎官：wom","ilcd","回复 ＠夕夕夕凄：亲 通过首页的\u201c小谷陪你过国庆\u201d的banner进去就可以参加大转盘抽奖了*^_^*","哎找不到大转盘哎","回复 ＠小鸟～萌萌哒：大转盘9月30日18点上线哦","大转盘在哪里找呀？～～～","回复尚硅谷哒小神棍：满减是可以配合优惠券一起叠加使用的","回复尚硅谷哒小神棍：国庆我们有满减，这个才是重头啦，优惠券这次国庆不是主角！","优惠券的力度减小了，有些商品除了小仓自营的商品不划算。就算有免单的机会也不一定能抽到啊！"],"comments":"11","figure":"/ugc/post/img/201609/14751401203006663.png","is_essence":"1","is_hot":"1","is_like":"0","is_top":"1","likes":"18","post_id":"2345","saying":"特大惊喜：#谷の盛典# #小谷陪你过国庆# 国庆攻略大曝光！小谷教您买买买！0元备战黄金周~30日前海量优惠券免！费！领！假期福利满天飞~全场满减搭配优惠券！爆款直降白菜价！","user_id":"90437","username":"尚硅谷首席惊喜官"},{"add_time":"1474625262","avatar":"/ugc/user/avatar/14651924470798522.png","comment_list":["啦啦","啦啦","吃土快乐，祝我快乐","猝不及防一口土\u2026\u2026","吃土愉快","啊啊啊啊啊啊啊疯掉了！！！！！猝不及防啊啊啊"],"comments":"7","figure":"/ugc/post/img/201609/14746252609717297.png","is_essence":"1","is_hot":"1","is_like":"0","is_top":"1","likes":"15","post_id":"2313","saying":"惊喜不断，新爆款、爆款、爆款，限时预定哦！","user_id":"90437","username":"尚硅谷首席惊喜官"},{"add_time":"1473674779","avatar":"/ugc/user/avatar/14651924470798522.png","comment_list":["第二！","我第一～～哈哈哈～～沙发～"],"comments":"2","figure":"/ugc/post/img/201609/14736747792594015.png","is_essence":"1","is_hot":"1","is_like":"0","is_top":"1","likes":"22","post_id":"2199","saying":"尚硅谷的新品-流烟昔泠新款套装【轻梦泽】今晚八点准时在次元仓首发，买买买2333\u2026","user_id":"90437","username":"尚硅谷首席惊喜官"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * add_time : 1478848148
         * avatar : /ugc/user/avatar/14611224500881026.jpeg
         * comment_list : ["一定要双十一那天发帖么？_(:з」∠)_"]
         * comments : 1
         * figure : /ugc/post/img/201611/14788481411252582.jpeg
         * is_essence : 1
         * is_hot : 1
         * is_like : 0
         * is_top : 1
         * likes : 2
         * post_id : 2616
         * saying : 【购物节活动】从今天起！民那桑在社区晒出购物节期间购买的订单～活动期间每天抽一位用户免单哦！！是免！单！哦！快来晒出你的订单吧！
         * user_id : 246715
         * username : 飞天小谷
         */

        private String add_time;
        private String avatar;
        private String comments;
        private String figure;
        private String is_essence;
        private String is_hot;
        private String is_like;
        private String is_top;
        private String likes;
        private String post_id;
        private String saying;
        private String user_id;
        private String username;
        private List<String> comment_list;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getIs_essence() {
            return is_essence;
        }

        public void setIs_essence(String is_essence) {
            this.is_essence = is_essence;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getSaying() {
            return saying;
        }

        public void setSaying(String saying) {
            this.saying = saying;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<String> comment_list) {
            this.comment_list = comment_list;
        }
    }
}
