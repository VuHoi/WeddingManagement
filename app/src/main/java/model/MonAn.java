package model;

/**
 * Created by Vu Khac Hoi on 3/5/2017.
 */

public class MonAn {
    private  String TenMonAn;
    private int Gia;
    private String MaKH;

    public String getTenSanh() {
        return TenSanh;
    }

    public void setTenSanh(String tenSanh) {
        TenSanh = tenSanh;
    }

    private  String TenSanh;
    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }



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


}
