package com.example.hasee.shoppingdemo.Bean;

import java.util.List;

/**
 * Created by Lmz on 2019/05/14
 */
public class TypeBean {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"tag_name":"小裙子","uri":"SKIRT_URL.json"},{"tag_name":"上衣","uri":"JACKET_URL.json"},{"tag_name":"下装","uri":"PANTS_URL.json"},{"tag_name":"外套","uri":"OVERCOAT_URL.json"},{"tag_name":"配件","uri":"ACCESSORY_URL.json"},{"tag_name":"包包","uri":"BAG_URL.json"},{"tag_name":"装扮","uri":"DRESS_UP_URL.json"},{"tag_name":"居家宅品","uri":"HOME_PRODUCTS_URL.json"},{"tag_name":"办公文具","uri":"STATIONERY_URL.json"},{"tag_name":"数码周边","uri":"DIGIT_URL.json"},{"tag_name":"游戏专区","uri":"GAME_URL.json"}]
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
         * tag_name : 小裙子
         * uri : SKIRT_URL.json
         */

        private String tag_name;
        private String uri;

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
