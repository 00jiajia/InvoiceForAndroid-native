package com.example.caroline.invoice.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus1 on 2018/3/20.
 */

public class DrawerInfo implements Serializable {
    private int KPRID;
    private String DLM;
    private String PASSWORD;
    private String XM;
    private byte[] TX;
    private Date MMCZRQ;
    private Date CJRQ;
    private Date JYRQ;
    private Date SCRQ;
    private int KPRJS;
    private int MMWTID;
    private String MMWTDA;

    public int getKPRID() {
        return KPRID;
    }

    public void setKPRID(int KPRID) {
        this.KPRID = KPRID;
    }

    public String getDLM() {
        return DLM;
    }

    public void setDLM(String DLM) {
        this.DLM = DLM;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public byte[] getTX() {
        return TX;
    }

    public void setTX(byte[] TX) {
        this.TX = TX;
    }

    public Date getMMCZRQ() {
        return MMCZRQ;
    }

    public void setMMCZRQ(Date MMCZRQ) {
        this.MMCZRQ = MMCZRQ;
    }

    public Date getCJRQ() {
        return CJRQ;
    }

    public void setCJRQ(Date CJRQ) {
        this.CJRQ = CJRQ;
    }

    public Date getJYRQ() {
        return JYRQ;
    }

    public void setJYRQ(Date JYRQ) {
        this.JYRQ = JYRQ;
    }

    public Date getSCRQ() {
        return SCRQ;
    }

    public void setSCRQ(Date SCRQ) {
        this.SCRQ = SCRQ;
    }

    public int getKPRJS() {
        return KPRJS;
    }

    public void setKPRJS(int KPRJS) {
        this.KPRJS = KPRJS;
    }

    public int getMMWTID() {
        return MMWTID;
    }

    public void setMMWTID(int MMWTID) {
        this.MMWTID = MMWTID;
    }

    public String getMMWTDA() {
        return MMWTDA;
    }

    public void setMMWTDA(String MMWTDA) {
        this.MMWTDA = MMWTDA;
    }

}
