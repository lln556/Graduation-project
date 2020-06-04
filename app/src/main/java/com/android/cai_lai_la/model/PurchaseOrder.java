package com.android.cai_lai_la.model;


import java.util.Date;

public class PurchaseOrder {
    /**
     * id
     */
    private Integer oid;

    /**
     * 外键，用户id
     */
    private Integer uid;

    /**
     * 外键，商品id
     */
    private Integer pid;

    /**
     * 成交价格
     */
    private Float currentPrice;

    /**
     * 成交时间
     */
    private Date orderTime;

    /**
     * 数目
     */
    private Integer num;

    /**
     * 获取id
     *
     * @return oid - id
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * 设置id
     *
     * @param oid id
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * 获取外键，用户id
     *
     * @return uid - 外键，用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置外键，用户id
     *
     * @param uid 外键，用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取外键，商品id
     *
     * @return pid - 外键，商品id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置外键，商品id
     *
     * @param pid 外键，商品id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取成交价格
     *
     * @return current_price - 成交价格
     */
    public Float getCurrentPrice() {
        return currentPrice;
    }

    /**
     * 设置成交价格
     *
     * @param currentPrice 成交价格
     */
    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * 获取成交时间
     *
     * @return order_time - 成交时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 设置成交时间
     *
     * @param orderTime 成交时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取数目
     *
     * @return num - 数目
     */
    public Integer getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", currentPrice=" + currentPrice +
                ", orderTime=" + orderTime +
                ", num=" + num +
                '}';
    }

    /**
     * 设置数目
     *
     * @param num 数目
     */
    public void setNum(Integer num) {
        this.num = num;
    }
}