package model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by billy on 03-Mar-17.
 */

public class TiecCuoi implements Serializable {
    private String makh,codau,chure,sanh,ngay,ca;
    private int check=0;
    private int soban;

    protected TiecCuoi(Parcel in) {
        makh = in.readString();
        codau = in.readString();
        chure = in.readString();
        sanh = in.readString();
        ngay = in.readString();
        ca = in.readString();
        check = in.readInt();
        soban = in.readInt();
    }



    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public TiecCuoi(String makh, String chure, String codau, String sanh, String ngay, String ca, int soban) {
        this.codau = codau;
        this.makh=makh;
        this.chure = chure;
        this.sanh = sanh;
        this.ngay = ngay;
        this.ca = ca;
        this.soban = soban;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getCodau() {
        return codau;
    }

    public void setCodau(String codau) {
        this.codau = codau;
    }

    public String getChure() {
        return chure;
    }

    public void setChure(String chure) {
        this.chure = chure;
    }

    public String getSanh() {
        return sanh;
    }

    public void setSanh(String sanh) {
        this.sanh = sanh;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public int getSoban() {
        return soban;
    }

    public void setSoban(int soban) {
        this.soban = soban;
    }

    public TiecCuoi() {

    }

}
