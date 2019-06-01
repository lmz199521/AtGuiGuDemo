package com.example.hasee.shoppingdemo.Activity.address;

import java.util.List;

/**
 * Created by Lmz on 2019/05/28
 */
public class CityBean {

    /**
     * id : 1
     * value : 北京
     * child : [{"id":"2800","value":"海淀区","child":[{"id":"2848","value":"三环以内","child":""},{"id":"2849","value":"三环到四环之间","child":""},{"id":"2850","value":"四环到五环之间","child":""},{"id":"2851","value":"五环到六环之间","child":""},{"id":"2852","value":"六环以外","child":""},{"id":"4134","value":"西三旗","child":""},{"id":"4209","value":"西二旗","child":""}]},{"id":"2801","value":"西城区","child":[{"id":"2827","value":"内环到二环里","child":""},{"id":"2853","value":"二环到三环","child":""}]},{"id":"2802","value":"东城区","child":[{"id":"2821","value":"内环到三环里","child":""}]},{"id":"2803","value":"崇文区","child":[{"id":"2829","value":"一环到二环","child":""},{"id":"2842","value":"二环到三环","child":""}]},{"id":"2804","value":"宣武区","child":[{"id":"2828","value":"内环到三环里","child":""}]},{"id":"2805","value":"丰台区","child":[{"id":"2832","value":"四环到五环之间","child":""},{"id":"2854","value":"二环到三环","child":""},{"id":"2855","value":"三环到四环之间","child":""},{"id":"34544","value":"五环到六环之间","child":""},{"id":"34545","value":"六环之外","child":""}]},{"id":"2806","value":"石景山区","child":[{"id":"2831","value":"四环到五环内","child":""},{"id":"4187","value":"石景山城区","child":""},{"id":"4188","value":"八大处科技园区","child":""}]},{"id":"2807","value":"门头沟","child":[{"id":"51552","value":"城区","child":""},{"id":"51553","value":"龙泉镇","child":""},{"id":"51554","value":"永定镇","child":""},{"id":"51555","value":"大台镇","child":""},{"id":"51556","value":"潭柘寺镇","child":""},{"id":"51557","value":"王平镇","child":""},{"id":"51558","value":"军庄镇","child":""},{"id":"51559","value":"妙峰山镇","child":""},{"id":"51560","value":"雁翅镇","child":""},{"id":"51561","value":"斋堂镇","child":""},{"id":"51562","value":"清水镇","child":""}]},{"id":"2808","value":"房山区","child":[{"id":"51528","value":"城区","child":""},{"id":"51529","value":"大安山乡","child":""},{"id":"51530","value":"大石窝镇","child":""},{"id":"51531","value":"窦店镇","child":""},{"id":"51532","value":"佛子庄乡","child":""},{"id":"51534","value":"韩村河镇","child":""},{"id":"51535","value":"河北镇","child":""},{"id":"51536","value":"良乡镇","child":""},{"id":"51537","value":"琉璃河镇","child":""},{"id":"51538","value":"南窖乡","child":""},{"id":"51539","value":"蒲洼乡","child":""},{"id":"51540","value":"青龙湖镇","child":""},{"id":"51541","value":"十渡镇","child":""},{"id":"51542","value":"石楼镇","child":""},{"id":"51543","value":"史家营乡","child":""},{"id":"51544","value":"霞云岭乡","child":""},{"id":"51545","value":"新镇","child":""},{"id":"51546","value":"阎村镇","child":""},{"id":"51547","value":"燕山地区","child":""},{"id":"51548","value":"张坊镇","child":""},{"id":"51549","value":"长沟镇","child":""},{"id":"51550","value":"长阳镇","child":""},{"id":"51551","value":"周口店镇","child":""}]},{"id":"2809","value":"通州区","child":[{"id":"51216","value":"六环内（马驹桥镇）","child":""},{"id":"51217","value":"六环外（马驹桥镇）","child":""},{"id":"51218","value":"永顺镇","child":""},{"id":"51219","value":"梨园镇","child":""},{"id":"51220","value":"宋庄镇","child":""},{"id":"51221","value":"漷县镇","child":""},{"id":"51222","value":"张家湾镇","child":""},{"id":"51223","value":"西集镇","child":""},{"id":"51224","value":"永乐店镇","child":""},{"id":"51225","value":"潞城镇","child":""},{"id":"51226","value":"台湖镇","child":""},{"id":"51227","value":"于家务乡","child":""},{"id":"51228","value":"中仓街道","child":""},{"id":"51229","value":"新华街道","child":""},{"id":"51230","value":"玉桥街道","child":""},{"id":"51231","value":"北苑街道","child":""},{"id":"51232","value":"次渠镇","child":""}]},{"id":"2810","value":"大兴区","child":[{"id":"4194","value":"四环至五环之间","child":""},{"id":"4205","value":"六环以外","child":""},{"id":"51081","value":"亦庄经济开发区","child":""},{"id":"6501","value":"五环至六环之间","child":""}]},{"id":"2812","value":"顺义区","child":[{"id":"51125","value":"北石槽镇","child":""},{"id":"51126","value":"北务镇","child":""},{"id":"51127","value":"北小营镇","child":""},{"id":"51128","value":"大孙各庄镇","child":""},{"id":"51129","value":"高丽营镇","child":""},{"id":"51130","value":"光明街道","child":""},{"id":"51131","value":"后沙峪地区","child":""},{"id":"51132","value":"空港街道","child":""},{"id":"51133","value":"李桥镇","child":""},{"id":"51134","value":"李遂镇","child":""},{"id":"51135","value":"龙湾屯镇","child":""},{"id":"51136","value":"马坡地区","child":""},{"id":"51137","value":"木林镇","child":""},{"id":"51138","value":"南彩镇","child":""},{"id":"51139","value":"南法信地区","child":""},{"id":"51140","value":"牛栏山地区","child":""},{"id":"51141","value":"仁和地区","child":""},{"id":"51142","value":"胜利街道","child":""},{"id":"51143","value":"石园街道","child":""},{"id":"51144","value":"双丰街道","child":""},{"id":"51145","value":"天竺地区","child":""},{"id":"51146","value":"旺泉街道","child":""},{"id":"51147","value":"杨镇地区","child":""},{"id":"51148","value":"张镇","child":""},{"id":"51149","value":"赵全营镇","child":""}]},{"id":"2814","value":"怀柔区","child":[{"id":"2847","value":"郊区","child":""},{"id":"6115","value":"城区以内","child":""}]},{"id":"2816","value":"密云区","child":[{"id":"2862","value":"城区以外","child":""},{"id":"6667","value":"城区","child":""}]},{"id":"2901","value":"昌平区","child":[{"id":"2906","value":"城区以外","child":""},{"id":"4135","value":"六环以内","child":""},{"id":"4136","value":"城区","child":""}]},{"id":"2953","value":"平谷区","child":[{"id":"2954","value":"城区以外","child":""},{"id":"6666","value":"城区","child":""}]},{"id":"3065","value":"延庆县","child":[{"id":"51505","value":"延庆镇","child":""},{"id":"51506","value":"城区","child":""},{"id":"51507","value":"康庄镇","child":""},{"id":"51508","value":"八达岭镇","child":""},{"id":"51509","value":"永宁镇","child":""},{"id":"51510","value":"旧县镇","child":""},{"id":"51511","value":"张山营镇","child":""},{"id":"51512","value":"四海镇","child":""},{"id":"51513","value":"千家店镇","child":""},{"id":"51514","value":"沈家营镇","child":""},{"id":"51515","value":"大榆树镇","child":""},{"id":"51516","value":"井庄镇","child":""},{"id":"51517","value":"大庄科乡","child":""},{"id":"51518","value":"刘斌堡乡","child":""},{"id":"51519","value":"香营乡","child":""},{"id":"51520","value":"珍珠泉乡","child":""}]},{"id":"72","value":"朝阳区","child":[{"id":"2799","value":"三环以内","child":""},{"id":"2819","value":"三环到四环之间","child":""},{"id":"2839","value":"四环到五环之间","child":""},{"id":"2840","value":"五环到六环之间","child":""},{"id":"4137","value":"管庄","child":""},{"id":"4139","value":"北苑","child":""},{"id":"4211","value":"定福庄","child":""}]}]
     */

    private String id;
    private String value;
    private List<ChildBeanX> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ChildBeanX> getChild() {
        return child;
    }

    public void setChild(List<ChildBeanX> child) {
        this.child = child;
    }

    public static class ChildBeanX {
        /**
         * id : 2800
         * value : 海淀区
         * child : [{"id":"2848","value":"三环以内","child":""},{"id":"2849","value":"三环到四环之间","child":""},{"id":"2850","value":"四环到五环之间","child":""},{"id":"2851","value":"五环到六环之间","child":""},{"id":"2852","value":"六环以外","child":""},{"id":"4134","value":"西三旗","child":""},{"id":"4209","value":"西二旗","child":""}]
         */

        private String id;
        private String value;
        private List<ChildBean> child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * id : 2848
             * value : 三环以内
             * child :
             */

            private String id;
            private String value;
            private String child;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getChild() {
                return child;
            }

            public void setChild(String child) {
                this.child = child;
            }
        }
    }
}
