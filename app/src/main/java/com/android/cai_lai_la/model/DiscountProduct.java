package com.android.cai_lai_la.model;

import java.io.Serializable;
import java.util.Date;

public class DiscountProduct extends Product implements Serializable {

    /**
     * 优惠比例
     */
    private Float discountrate;

    /**
     * 优惠描述
     */
    private String describe;


    public Float getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(Float discountrate) {
        this.discountrate = discountrate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
