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

import com.example.vukhachoi.weddingmanagement.R;

import java.util.List;

import model.Dichvu;
import sqlite.Databasehelper;

/**
 * Created by Vu Khac Hoi on 3/5/2017.
 */

public class AdapterDichVu extends ArrayAdapter<Dichvu> {
    Activity context; int resource; List<Dichvu> objects;
    Databasehelper myDatabase;
    SQLiteDatabase database;
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
        myDatabase = new Databasehelper(getContext());
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        LayoutInflater inflater=this.context.getLayoutInflater();
        Row=inflater.inflate(this.resource,null);
        TextView txtTendichvu= (TextView) Row.findViewById(R.id.txtTendichvu);
        TextView txtGiaDichVu= (TextView) Row.findViewById(R.id.txtGiaDichvu);
        final TextView txtSoLuong= (TextView) Row.findViewById(R.id.txtSoLuong);
        CheckBox ckb= (CheckBox) Row.findViewById(R.id.ckbLuachon);
        final Dichvu dichvu=this.objects.get(position);
        txtTendichvu.setText(dichvu.getTendichvu().toString());
        txtSoLuong.setText(dichvu.getSoluong()+"");
        txtGiaDichVu.setText(dichvu.getDongia()+"");
        try
        {
            Cursor cursorCheck=database.rawQuery("select * from DatDichVu join Dichvu on MaDatDichVu=MaDV where TenDV=? and  Tensanh=?",new String[]{dichvu.getTendichvu().toString(),dichvu.getTenSanh().toString()});
            cursorCheck.moveToFirst();
            if(cursorCheck.getString(0)!=null&&cursorCheck.getString(3).equals(dichvu.getTenSanh())){
                ckb.setChecked(true);
                txtSoLuong.setText(cursorCheck.getString(2).toString());
            }
        }catch (Exception e)
        {

        }


        ckb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor= database.rawQuery("select Madatdichvu from DichVu where TenDV=?",new String[]{dichvu.getTendichvu().toString()});
                cursor.moveToFirst();
                if(((CheckBox)v).isChecked())
                {
                    ContentValues values=new ContentValues();
                    values.put("MaKH",dichvu.getMaKH());
                    values.put("MaDv",cursor.getString(0));
                    values.put("SoLuong",txtSoLuong.getText().toString());
                    values.put("TenSanh",dichvu.getTenSanh());
                    database.insert("DatDichVu",null,values);
                }
                else
                {
                    database.delete("DatDichVu","Tensanh=? and MaDv=?",new String[]{dichvu.getTenSanh(), cursor.getString(0).toString()});
                }
            }
        });


        return Row;
    }
}
