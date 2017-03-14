package model;

/**
 * Created by billy on 13-Mar-17.
 */

public class BaoCao {
    private int ngay,thang,sl,doanhthu;
    private float tile;

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }

    public float getTile() {
        return tile;
    }

    public void setTile(float tile) {
        this.tile = tile;
    }

    public BaoCao(int ngay, int thang, int sl, int doanhthu, float tile) {

        this.ngay = ngay;
        this.thang = thang;
        this.sl = sl;
        this.doanhthu = doanhthu;
        this.tile = tile;
    }
}
