package com.android.cai_lai_la.model;


public class Discount {
    /**
     * id
     */
    private Integer did;

    /**
     * 优惠金额（money和rate一般只取一个）
     */
    private Float discountmoney;

    /**
     * 优惠比例
     */
    private Float discountrate;

    /**
     * 优惠名称
     */
    private String discountname;

    /**
     * 优惠描述
     */
    private String describe;

    /**
     * 获取id
     *
     * @return did - id
     */
    public Integer getDid() {
        return did;
    }

    /**
     * 设置id
     *
     * @param did id
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * 获取优惠金额（money和rate一般只取一个）
     *
     * @return discountMoney - 优惠金额（money和rate一般只取一个）
     */
    public Float getDiscountmoney() {
        return discountmoney;
    }

    /**
     * 设置优惠金额（money和rate一般只取一个）
     *
     * @param discountmoney 优惠金额（money和rate一般只取一个）
     */
    public void setDiscountmoney(Float discountmoney) {
        this.discountmoney = discountmoney;
    }

    /**
     * 获取优惠比例
     *
     * @return discountRate - 优惠比例
     */
    public Float getDiscountrate() {
        return discountrate;
    }

    /**
     * 设置优惠比例
     *
     * @param discountrate 优惠比例
     */
    public void setDiscountrate(Float discountrate) {
        this.discountrate = discountrate;
    }

    /**
     * 获取优惠名称
     *
     * @return discountName - 优惠名称
     */
    public String getDiscountname() {
        return discountname;
    }

    /**
     * 设置优惠名称
     *
     * @param discountname 优惠名称
     */
    public void setDiscountname(String discountname) {
        this.discountname = discountname;
    }

    /**
     * 获取优惠描述
     *
     * @return describe - 优惠描述
     */
    public String getDescribe() {
        return describe;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "did=" + did +
                ", discountmoney=" + discountmoney +
                ", discountrate=" + discountrate +
                ", discountname='" + discountname + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    /**
     * 设置优惠描述
     *
     * @param describe 优惠描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}