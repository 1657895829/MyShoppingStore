package com.example.shop.bean;

/**
 * 加入购物车返回数据
 */

public class AddCartBean {

    /**
     * msg : 加购成功
     * code : 0
     */

    private String msg;
    private String code;

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
}
