package com.example.caroline.invoice.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus1 on 2018/4/4.
 */

public class InvoiceInfo implements Serializable{
    private int JLID;
    private String FPDM;
    private String FPHS;
    private String FPHZ;
    private Date LGSJ;
    private int FS;
    private Date FPSJ;
    private int YWBZ;

    public int getJLID() {
        return JLID;
    }

    public void setJLID(int JLID) {
        this.JLID = JLID;
    }

    public String getFPDM() {
        return FPDM;
    }

    public void setFPDM(String FPDM) {
        this.FPDM = FPDM;
    }

    public String getFPHS() {
        return FPHS;
    }

    public void setFPHS(String FPHS) {
        this.FPHS = FPHS;
    }

    public String getFPHZ() {
        return FPHZ;
    }

    public void setFPHZ(String FPHZ) {
        this.FPHZ = FPHZ;
    }

    public Date getLGSJ() {
        return LGSJ;
    }

    public void setLGSJ(Date LGSJ) {
        this.LGSJ = LGSJ;
    }

    public int getFS() {
        return FS;
    }

    public void setFS(int FS) {
        this.FS = FS;
    }

    public Date getFPSJ() {
        return FPSJ;
    }

    public void setFPSJ(Date FPSJ) {
        this.FPSJ = FPSJ;
    }

    public int getYWBZ() {
        return YWBZ;
    }

    public void setYWBZ(int YWBZ) {
        this.YWBZ = YWBZ;
    }


}
