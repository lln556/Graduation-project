package com.android.cai_lai_la.model;


public class HistoryBrowser {
    /**
     * 外键，用户id
     */
    private Integer uid;

    /**
     * 外键，商品id
     */
    private Integer pid;

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

    @Override
    public String toString() {
        return "HistoryBrowser{" +
                "uid=" + uid +
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