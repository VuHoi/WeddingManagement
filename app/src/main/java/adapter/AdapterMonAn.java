package adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.vukhachoi.weddingmanagement.R;

import java.io.IOException;
import java.util.List;

import model.MonAn;
import sqlite.Databasehelper;

import static android.R.attr.value;

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
        CheckBox ckb= (CheckBox) Row.findViewById(R.id.ckbLuachon);

        final MonAn monAN=this.objects.get(position);
        txtMonAn.setText(monAN.getTenMonAn().toString());
        txtGia.setText(monAN.getGia()+"");

ckb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(((CheckBox)v).isChecked())
        {
            Databasehelper myDatabase = new Databasehelper(getContext());

            try {
                myDatabase.createDatabase();

            } catch (IOException ioe) {

                throw new Error("Unable to create database");
            }

            try {
                myDatabase.openDatabase();

            }catch(SQLException sqle){

                throw sqle;
            }
          SQLiteDatabase database = myDatabase.getMyDatabase();
         Cursor cursor= database.rawQuery("select Madatmonan from MonAn where Tenmonan=?",new String[]{monAN.getTenMonAn()});
          cursor.moveToFirst();

            ContentValues values=new ContentValues();
            values.put("MaKH",monAN.getMaKH());
            values.put("Madatdichvu",cursor.getString(0));
            values.put("Madatdichvu","");
            database.insert("dattiec",null,values);
        }
    }
});

        return Row;
    }
}
