package com.android.cai_lai_la.model;


public class DiscountTo {
    /**
     * 外键，折扣id
     */
    private Integer did;

    /**
     * 外键，商品id
     */
    private Integer pid;

    /**
     * 获取外键，折扣id
     *
     * @return did - 外键，折扣id
     */
    public Integer getDid() {
        return did;
    }

    /**
     * 设置外键，折扣id
     *
     * @param did 外键，折扣id
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * 获取外键，商品id
     *
     * @return pid - 外键，商品id
     */
    public Integer getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "DiscountTo{" +
                "did=" + did +
                ", pid=" + pid +
                '}';
    }

    /**
     * 设置外键，商品id
     *
     * @param pid 外键，商品id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }
}