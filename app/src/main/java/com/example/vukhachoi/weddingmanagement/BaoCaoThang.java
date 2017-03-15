package com.example.vukhachoi.weddingmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.AdapterBaoCao;
import model.BaoCao;
import sqlite.Databasehelper;

public class BaoCaoThang extends AppCompatActivity {
    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;

    ArrayList<BaoCao>dsbc;
    ListView lv_baocao;
    AdapterBaoCao adapterBaoCao;
    TextView txtThang;
    TextView txtDoanhThu;
    TextView txttile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_thang);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        //
        // myDatabase.db_delete();
        addControls();
        addEvents();

    }

    private void addEvents() {

    }

    private void addControls() {
        txtThang= (TextView) findViewById(R.id.txtThang);
        txtDoanhThu= (TextView) findViewById(R.id.txtDoanhThu);
        lv_baocao= (ListView) findViewById(R.id.lv_baocao);
        txttile= (TextView) findViewById(R.id.txttile);

        dsbc=new ArrayList<>();
        adapterBaoCao=new AdapterBaoCao(BaoCaoThang.this,R.layout.item_baocao,dsbc);
        lv_baocao.setAdapter(adapterBaoCao);

        Calendar calendar=Calendar.getInstance();
        DateFormat th = new SimpleDateFormat("MM");
        int thangnay= Integer.parseInt(th.format(calendar.getTime()));
        int thangtruoc=thangnay-1;
        if(thangnay==1)
        {
            thangtruoc=12;
        }


        txtThang.setText(thangnay+"");
        Cursor cursor=database.rawQuery("select ngay,count(mahd),sum(tongtien) from hoadon where thang="+thangnay+" group by thang",null);
        cursor.moveToFirst();
        float doanhthu=0;



        Cursor cursor1=database.rawQuery("select sum(tongtien) from hoadon where thang="+thangtruoc+" group by thang",null);
        cursor.moveToFirst();
        float doanhthutruoc=0;

        while (!cursor1.isAfterLast())
        {
            int tongtien=cursor.getInt(0);
            doanhthutruoc+=tongtien;
            cursor1.moveToNext();
        }
        cursor1.close();

        while (!cursor.isAfterLast())
        {
            int ngay=cursor.getInt(0);
            int sl=cursor.getInt(1);
            int tongtien=cursor.getInt(2);
            doanhthu+=tongtien;
            BaoCao bc=new BaoCao(ngay,sl,tongtien);
            dsbc.add(bc);
            adapterBaoCao.notifyDataSetChanged();
            cursor.moveToNext();
        }
        DecimalFormat x=new DecimalFormat("#.##");
        txtDoanhThu.setText(x.format(doanhthu)+"");
        cursor.close();
        if(doanhthutruoc>0)
        {
            float tl=(doanhthu/doanhthutruoc)*100;
            txttile.setText(x.format(tl)+"");
        }
    }
}
