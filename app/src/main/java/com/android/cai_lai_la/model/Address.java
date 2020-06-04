package com.android.cai_lai_la.model;


public class Address {
    /**
     * id
     */
    private Integer aid;

    /**
     * 外键，用户id
     */
    private Integer uid;

    /**
     * 收货人
     */
    private String addrname;

    /**
     * 收货人电话
     */
    private String addrtell;

    /**
     * 省
     */
    private String sheng;

    /**
     * 市
     */
    private String shi;

    /**
     * 区
     */
    private String qu;

    /**
     * 详细地址
     */
    private String detailaddr;

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", addrname='" + addrname + '\'' +
                ", addrtell='" + addrtell + '\'' +
                ", sheng='" + sheng + '\'' +
                ", shi='" + shi + '\'' +
                ", qu='" + qu + '\'' +
                ", detailaddr='" + detailaddr + '\'' +
                ", psotnum='" + psotnum + '\'' +
                '}';
    }

    /**
     * 邮编
     */
    private String psotnum;

    /**
     * 获取id
     *
     * @return aid - id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置id
     *
     * @param aid id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
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
     * 获取收货人
     *
     * @return addrName - 收货人
     */
    public String getAddrname() {
        return addrname;
    }

    /**
     * 设置收货人
     *
     * @param addrname 收货人
     */
    public void setAddrname(String addrname) {
        this.addrname = addrname;
    }

    /**
     * 获取收货人电话
     *
     * @return addrtell - 收货人电话
     */
    public String getAddrtell() {
        return addrtell;
    }

    /**
     * 设置收货人电话
     *
     * @param addrtell 收货人电话
     */
    public void setAddrtell(String addrtell) {
        this.addrtell = addrtell;
    }

    /**
     * 获取省
     *
     * @return sheng - 省
     */
    public String getSheng() {
        return sheng;
    }

    /**
     * 设置省
     *
     * @param sheng 省
     */
    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    /**
     * 获取市
     *
     * @return shi - 市
     */
    public String getShi() {
        return shi;
    }

    /**
     * 设置市
     *
     * @param shi 市
     */
    public void setShi(String shi) {
        this.shi = shi;
    }

    /**
     * 获取区
     *
     * @return qu - 区
     */
    public String getQu() {
        return qu;
    }

    /**
     * 设置区
     *
     * @param qu 区
     */
    public void setQu(String qu) {
        this.qu = qu;
    }

    /**
     * 获取详细地址
     *
     * @return detailAddr - 详细地址
     */
    public String getDetailaddr() {
        return detailaddr;
    }

    /**
     * 设置详细地址
     *
     * @param detailaddr 详细地址
     */
    public void setDetailaddr(String detailaddr) {
        this.detailaddr = detailaddr;
    }

    /**
     * 获取邮编
     *
     * @return psotNum - 邮编
     */
    public String getPsotnum() {
        return psotnum;
    }

    /**
     * 设置邮编
     *
     * @param psotnum 邮编
     */
    public void setPsotnum(String psotnum) {
        this.psotnum = psotnum;
    }
}