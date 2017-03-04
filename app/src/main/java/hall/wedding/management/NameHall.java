package hall.wedding.management;

/**
 * Created by Vu Khac Hoi on 3/4/2017.
 */

public class NameHall {
    private  int BanToiDa;

    public NameHall() {
    }

    public NameHall(int banToiDa, boolean isActive, int giaToiThieu) {
        BanToiDa = banToiDa;
        this.isActive = isActive;
        GiaToiThieu = giaToiThieu;
    }

    public boolean isActive() {

        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private  boolean isActive;

    public int getBanToiDa() {
        return BanToiDa;
    }

    public void setBanToiDa(int banToiDa) {
        BanToiDa = banToiDa;
    }

    public int getGiaToiThieu() {
        return GiaToiThieu;
    }

    public void setGiaToiThieu(int giaToiThieu) {
        GiaToiThieu = giaToiThieu;
    }

    private  int GiaToiThieu;
}
