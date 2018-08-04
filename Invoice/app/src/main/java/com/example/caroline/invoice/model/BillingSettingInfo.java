package com.example.caroline.invoice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/4/9.
 */

public class BillingSettingInfo {
    private List<KpexoptionInfo> kps;
    private int Kpzlid;
    private String Kpzlmc;
    private String Kpzldm;
    private int Gsds;
    private String Fpzldm;
    private String Hyfldm;
    private int kpoption;

    public BillingSettingInfo(){
        kps=new ArrayList<KpexoptionInfo>();
    }
    public int getKpoption() {
        return kpoption;
    }

    public void setKpoption(int kpoption) {
        this.kpoption = kpoption;
    }



    public List<KpexoptionInfo> getKps() {
        return kps;
    }

    public void setKps(List<KpexoptionInfo> kps) {
        this.kps = kps;
    }
    public int getKpzlid() {
        return Kpzlid;
    }

    public void setKpzlid(int kpzlid) {
        Kpzlid = kpzlid;
    }

    public String getKpzlmc() {
        return Kpzlmc;
    }

    public void setKpzlmc(String kpzlmc) {
        Kpzlmc = kpzlmc;
    }

    public String getKpzldm() {
        return Kpzldm;
    }

    public void setKpzldm(String kpzldm) {
        Kpzldm = kpzldm;
    }

    public int getGsds() {
        return Gsds;
    }

    public void setGsds(int gsds) {
        Gsds = gsds;
    }

    public String getFpzldm() {
        return Fpzldm;
    }

    public void setFpzldm(String fpzldm) {
        Fpzldm = fpzldm;
    }

    public String getHyfldm() {
        return Hyfldm;
    }

    public void setHyfldm(String hyfldm) {
        Hyfldm = hyfldm;
    }


}
