package adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vukhachoi.weddingmanagement.R;

import java.util.List;

import model.MonAn;
import sqlite.Databasehelper;

/**
 * Created by Vu Khac Hoi on 3/5/2017.
 */

public class AdapterMonAn extends ArrayAdapter<MonAn> {
    Activity context; int resource; List<MonAn> objects;
    Databasehelper myDatabase;
    SQLiteDatabase database;
    public AdapterMonAn(Activity context, int resource, List<MonAn> objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        myDatabase = new Databasehelper(getContext());
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        View Row;
        LayoutInflater inflater=this.context.getLayoutInflater();
        Row=inflater.inflate(this.resource,null);
        TextView txtMonAn= (TextView) Row.findViewById(R.id.txtMonan);
        TextView txtGia= (TextView) Row.findViewById(R.id.txtGia);
        CheckBox ckb= (CheckBox) Row.findViewById(R.id.ckbLuachon);

        final MonAn monAN=this.objects.get(position);
        txtMonAn.setText(monAN.getTenMonAn().toString());
        txtGia.setText(monAN.getGia()+"");
try
{

    Cursor cursor1= database.rawQuery("select MaMonAn,MaKH from DatMonAn ",null);
        cursor1.moveToFirst();
        Toast.makeText(context, cursor1.getString(0).toString(), Toast.LENGTH_SHORT).show();
    cursor1.moveToNext();
    Toast.makeText(context, cursor1.getString(1).toString(), Toast.LENGTH_SHORT).show();
    Toast.makeText(context, cursor1.getString(0).toString(), Toast.LENGTH_SHORT).show();
}catch (Exception e)
{

}


        ckb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Cursor cursor= database.rawQuery("select MaDatMonAn from MonAn where Tenmonan=?",new String[]{monAN.getTenMonAn()});
        cursor.moveToFirst();
        if(((CheckBox)v).isChecked())
        {
            ContentValues values=new ContentValues();
            values.put("MaKH",monAN.getMaKH());
            values.put("MaMonAn",cursor.getString(0));
            database.insert("DatMonAn",null,values);
        }
        else
        {
            database.delete("DatMonAn","MaKH=? and MaMonAn=?",new String[]{monAN.getMaKH(), cursor.getString(0).toString()});
        }
    }
});

        return Row;
    }
}
