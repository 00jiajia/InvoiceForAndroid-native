package com.example.caroline.invoice.model;

/**
 * Created by asus1 on 2018/3/20.
 */

public class BasicInfo {
    private String NSRMC;
    //纳税人识别码
    private String NSRSBM;
    //主机标志
    private String ZJBZ;

    //行业分类
    private int HYFL;
    //开票种类
    private int KPZL;

    public String getNSRMC() {
        return NSRMC;
    }

    public void setNSRMC(String NSRMC) {
        this.NSRMC = NSRMC;
    }

    public String getNSRSBM() {
        return NSRSBM;
    }

    public void setNSRSBM(String NSRSBM) {
        this.NSRSBM = NSRSBM;
    }

    public String getZJBZ() {
        return ZJBZ;
    }

    public void setZJBZ(String ZJBZ) {
        this.ZJBZ = ZJBZ;
    }

    public int getHYFL() {
        return HYFL;
    }

    public void setHYFL(int HYFL) {
        this.HYFL = HYFL;
    }

    public int getKPZL() {
        return KPZL;
    }

    public void setKPZL(int KPZL) {
        this.KPZL = KPZL;
    }



}
