package model;

/**
 * Created by Vu Khac Hoi on 3/5/2017.
 */

public class MonAn {
    private  String TenMonAn;


    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    private String MaKH;

    public MonAn() {
    }

    public MonAn(String tenMonAn, int gia) {

        TenMonAn = tenMonAn;
        Gia = gia;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    private int Gia;
}
