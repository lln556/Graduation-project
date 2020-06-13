package com.android.cai_lai_la.model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    /**
     * id
     */
    private Integer pid;

    /**
     * 外键，类型id
     */
    private Integer pcid;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品副标题
     */
    private String subtitle;

    /**
     * 原价
     */
    private Float originalprice;

    /**
     * 现价
     */
    private Float currentprice;

    /**
     * 库存
     */
    private Integer storenum;

    /**
     * 生产日期
     */
    private Date manudate;

    /**
     * 保质期
     */
    private Float lifetime;

    /**
     * 商品规格（ml、kg之类的）
     */
    private String specificationi;

    /**
     * 预计到达时间
     */
    private Date arrivaltime;

    /**
     * 获取id
     *
     * @return pid - id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置id
     *
     * @param pid id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取外键，类型id
     *
     * @return pcid - 外键，类型id
     */
    public Integer getPcid() {
        return pcid;
    }

    /**
     * 设置外键，类型id
     *
     * @param pcid 外键，类型id
     */
    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    /**
     * 获取商品标题
     *
     * @return title - 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品标题
     *
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品副标题
     *
     * @return subTitle - 商品副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置商品副标题
     *
     * @param subtitle 商品副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取原价
     *
     * @return originalPrice - 原价
     */
    public Float getOriginalprice() {
        return originalprice;
    }

    /**
     * 设置原价
     *
     * @param originalprice 原价
     */
    public void setOriginalprice(Float originalprice) {
        this.originalprice = originalprice;
    }

    /**
     * 获取现价
     *
     * @return currentPrice - 现价
     */
    public Float getCurrentprice() {
        return currentprice;
    }

    /**
     * 设置现价
     *
     * @param currentprice 现价
     */
    public void setCurrentprice(Float currentprice) {
        this.currentprice = currentprice;
    }

    /**
     * 获取库存
     *
     * @return storeNum - 库存
     */
    public Integer getStorenum() {
        return storenum;
    }

    /**
     * 设置库存
     *
     * @param storenum 库存
     */
    public void setStorenum(Integer storenum) {
        this.storenum = storenum;
    }

    /**
     * 获取生产日期
     *
     * @return manuDate - 生产日期
     */
    public Date getManudate() {
        return manudate;
    }

    /**
     * 设置生产日期
     *
     * @param manudate 生产日期
     */
    public void setManudate(Date manudate) {
        this.manudate = manudate;
    }

    /**
     * 获取保质期
     *
     * @return lifeTime - 保质期
     */
    public Float getLifetime() {
        return lifetime;
    }

    /**
     * 设置保质期
     *
     * @param lifetime 保质期
     */
    public void setLifetime(Float lifetime) {
        this.lifetime = lifetime;
    }

    /**
     * 获取商品规格（ml、kg之类的）
     *
     * @return specificationi - 商品规格（ml、kg之类的）
     */
    public String getSpecificationi() {
        return specificationi;
    }

    /**
     * 设置商品规格（ml、kg之类的）
     *
     * @param specificationi 商品规格（ml、kg之类的）
     */
    public void setSpecificationi(String specificationi) {
        this.specificationi = specificationi;
    }

    /**
     * 获取预计到达时间
     *
     * @return arrivalTime - 预计到达时间
     */
    public Date getArrivaltime() {
        return arrivaltime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pcid=" + pcid +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", originalprice=" + originalprice +
                ", currentprice=" + currentprice +
                ", storenum=" + storenum +
                ", manudate=" + manudate +
                ", lifetime=" + lifetime +
                ", specificationi='" + specificationi + '\'' +
                ", arrivaltime=" + arrivaltime +
                '}';
    }

    /**
     * 设置预计到达时间
     *
     * @param arrivaltime 预计到达时间
     */
    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }
}