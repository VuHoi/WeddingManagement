package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vukhachoi.weddingmanagement.R;

import java.util.List;

import model.MonAn;

/**
 * Created by Vu Khac Hoi on 3/14/2017.
 */

public class Adapter_CapNhat extends ArrayAdapter<MonAn> {

    Activity context;
    int resource;
    List<MonAn> objects;

    public Adapter_CapNhat(Activity context, int resource, List<MonAn> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        ImageView imgHinhDaiDien= (ImageView) row.findViewById(R.id.imgHinhDaiDien);
        TextView txtTenMonAn= (TextView) row.findViewById(R.id.txtTenMonAn);
        TextView txtGiaMonAn=(TextView) row.findViewById(R.id.txtGiaMonAn);
        MonAn monAn=this.objects.get(position);
        txtTenMonAn.setText(monAn.getTenMonAn().toString());
        txtGiaMonAn.setText(monAn.getGia()+"$");
        imgHinhDaiDien.setImageResource(R.drawable.imgmonan);
        return row;
    }
}
