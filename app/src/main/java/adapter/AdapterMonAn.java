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

import model.MonAn;

/**
 * Created by Vu Khac Hoi on 3/5/2017.
 */

public class AdapterMonAn extends ArrayAdapter<MonAn> {
    Activity context; int resource; List<MonAn> objects;

    public AdapterMonAn(Activity context, int resource, List<MonAn> objects) {
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
        TextView txtMonAn= (TextView) Row.findViewById(R.id.txtMonan);
        TextView txtGia= (TextView) Row.findViewById(R.id.txtGia);
        MonAn monAN=this.objects.get(position);
        txtMonAn.setText(monAN.getTenMonAn().toString());
        txtGia.setText(monAN.getGia()+"");
        return Row;
    }
}
