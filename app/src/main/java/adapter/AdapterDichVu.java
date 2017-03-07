package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vukhachoi.weddingmanagement.R;

import java.util.List;

import model.Dichvu;

/**
 * Created by Vu Khac Hoi on 3/5/2017.
 */

public class AdapterDichVu extends ArrayAdapter<Dichvu> {
    Activity context; int resource; List<Dichvu> objects;

    public AdapterDichVu(Activity context, int resource, List<Dichvu> objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View Row;
        LayoutInflater inflater=this.context.getLayoutInflater();
        Row=inflater.inflate(this.resource,null);
        TextView txtTendichvu= (TextView) Row.findViewById(R.id.txtTendichvu);
        TextView txtGiaDichVu= (TextView) Row.findViewById(R.id.txtGiaDichvu);
        TextView txtSoLuong= (TextView) Row.findViewById(R.id.txtSoLuong);
        Dichvu dichvu=this.objects.get(position);
txtTendichvu.setText(dichvu.getTendichvu().toString());
        txtSoLuong.setText(dichvu.getSoluong()+"");
        txtGiaDichVu.setText(dichvu.getDongia()+"");
        return Row;
    }
}
