package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vukhachoi.weddingmanagement.R;

import java.util.List;

import model.Dichvu;

/**
 * Created by Vu Khac Hoi on 3/14/2017.
 */

public class Adapter_CapNhat_DichVu extends ArrayAdapter<Dichvu> {
    Activity context;
    int resource;
    List<Dichvu> objects;

    public Adapter_CapNhat_DichVu(Activity context, int resource, List<Dichvu> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        ImageView imgHinhDaiDien= (ImageView) row.findViewById(R.id.imgHinhDaiDien);
        TextView txtTenMonAn= (TextView) row.findViewById(R.id.txtTenMonAn);
        TextView txtGiaMonAn=(TextView) row.findViewById(R.id.txtGiaMonAn);
        Dichvu dichvu=this.objects.get(position);
        txtTenMonAn.setText(dichvu.getTendichvu().toString());
        txtGiaMonAn.setText(dichvu.getDongia()+"$");
        imgHinhDaiDien.setImageResource(R.drawable.imgdichvu);
        return row;
    }
}
