package com.android.cai_lai_la.model;


public class ProductClass {
    /**
     * id
     */
    private Integer pcid;

    /**
     * 商品类型名称
     */
    private String classname;
     private String classpic;

    @Override
    public String toString() {
        return "ProductClass{" +
                "pcid=" + pcid +
                ", classname='" + classname + '\'' +
                ", classpic='" + classpic + '\'' +
                '}';
    }

    public String getClasspic() {
        return classpic;
    }

    public void setClasspic(String classpic) {
        this.classpic = classpic;
    }

    /**
     * 获取id
     *
     * @return pcid - id
     */
    public Integer getPcid() {
        return pcid;
    }

    /**
     * 设置id
     *
     * @param pcid id
     */
    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    /**
     * 获取商品类型名称
     *
     * @return className - 商品类型名称
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 设置商品类型名称
     *
     * @param classname 商品类型名称
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }
}