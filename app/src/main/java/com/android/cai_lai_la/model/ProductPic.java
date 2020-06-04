package com.android.cai_lai_la.model;


public class ProductPic {
    /**
     * id
     */
    private Integer picid;

    /**
     * 外键，商品id
     */
    private Integer pid;

    /**
     * 图片路径
     */
    private String pidpath;

    /**
     * 获取id
     *
     * @return picid - id
     */
    public Integer getPicid() {
        return picid;
    }

    /**
     * 设置id
     *
     * @param picid id
     */
    public void setPicid(Integer picid) {
        this.picid = picid;
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
     * 获取图片路径
     *
     * @return pidPath - 图片路径
     */
    public String getPidpath() {
        return pidpath;
    }

    @Override
    public String toString() {
        return "ProductPic{" +
                "picid=" + picid +
                ", pid=" + pid +
                ", pidpath='" + pidpath + '\'' +
                '}';
    }

    /**
     * 设置图片路径
     *
     * @param pidpath 图片路径
     */
    public void setPidpath(String pidpath) {
        this.pidpath = pidpath;
    }
}