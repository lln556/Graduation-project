package com.android.cai_lai_la.model;


public class DiscountBelong {
    /**
     * 外键，用户id
     */
    private Integer uid;

    /**
     * 外键，折扣id
     */
    private Integer did;

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
     * 获取外键，折扣id
     *
     * @return did - 外键，折扣id
     */
    public Integer getDid() {
        return did;
    }

    @Override
    public String toString() {
        return "DiscountBelong{" +
                "uid=" + uid +
                ", did=" + did +
                '}';
    }

    /**
     * 设置外键，折扣id
     *
     * @param did 外键，折扣id
     */
    public void setDid(Integer did) {
        this.did = did;
    }
}