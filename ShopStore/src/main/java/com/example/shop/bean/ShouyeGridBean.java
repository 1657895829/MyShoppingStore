package com.example.shop.bean;

import java.util.List;

/**
 * 九宫格数据
 */
public class ShouyeGridBean {

    /**
     * msg :
     * code : 0
     * data : [{"cid":1,"createtime":"2017-10-10T19:41:39","icon":"http://120.27.23.105/images/category/shop.png","ishome":1,"name":"京东超市"},{"cid":2,"createtime":"2017-10-10T19:41:39","icon":"http://120.27.23.105/images/category/qqg.png","ishome":1,"name":"全球购"},{"cid":3,"createtime":"2017-10-10T19:45:11","icon":"http://120.27.23.105/images/category/phone.png","ishome":1,"name":"手机数码"},{"cid":5,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/man.png","ishome":1,"name":"男装"},{"cid":6,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/girl.png","ishome":1,"name":"女装"},{"cid":7,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/manshoe.png","ishome":1,"name":"男鞋"},{"cid":8,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/girlshoe.png","ishome":1,"name":"女鞋"},{"cid":9,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/shirt.png","ishome":1,"name":"内衣配饰"},{"cid":10,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/bag.png","ishome":1,"name":"箱包手袋"},{"cid":11,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/beauty.png","ishome":1,"name":"美妆个护"},{"cid":12,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/jewel.png","ishome":1,"name":"钟表珠宝"},{"cid":13,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/luxury.png","ishome":1,"name":"奢侈品"},{"cid":14,"createtime":"2017-10-10T20:12:03","icon":"http://120.27.23.105/images/category/computer.png","ishome":1,"name":"电脑办公"},{"cid":15,"createtime":"2017-09-29T10:13:48","icon":"http://120.27.23.105/images/icon.png","ishome":0,"name":"家用电器"},{"cid":16,"createtime":"2017-09-29T10:13:48","icon":"http://120.27.23.105/images/icon.png","ishome":0,"name":"食品生鲜"},{"cid":17,"createtime":"2017-09-29T10:13:48","icon":"http://120.27.23.105/images/icon.png","ishome":0,"name":"酒水饮料"},{"cid":18,"createtime":"2017-09-29T10:13:48","icon":"http://120.27.23.105/images/icon.png","ishome":0,"name":"母婴童装"},{"cid":19,"createtime":"2017-09-29T10:13:48","icon":"http://120.27.23.105/images/icon.png","ishome":0,"name":"玩具乐器"},{"cid":20,"createtime":"2017-09-29T10:13:48","icon":"http://120.27.23.105/images/icon.png","ishome":0,"name":"医药保健"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 1
         * createtime : 2017-10-10T19:41:39
         * icon : http://120.27.23.105/images/category/shop.png
         * ishome : 1
         * name : 京东超市
         */

        private int cid;
        private String createtime;
        private String icon;
        private int ishome;
        private String name;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getIshome() {
            return ishome;
        }

        public void setIshome(int ishome) {
            this.ishome = ishome;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
