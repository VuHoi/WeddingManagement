package com.example.vukhachoi.weddingmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import adapter.Adapter_HoaDon;
import model.Dichvu;
import model.TiecCuoi;
import sqlite.Databasehelper;

public class LapHoaDon extends AppCompatActivity {

    TabHost tabHost;

    ListView lv_hoadon;
    ArrayAdapter<TiecCuoi>adapterHoaDon;
    ArrayList<TiecCuoi>dsHoaDon;



    ListView lv_hoadonDaThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don);
        addControls();
        addEvents();

    }

    private void addEvents() {
        lv_hoadon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(LapHoaDon.this,LapHoaDon_ThanhToan.class);
                intent.putExtra("tieccuoi",dsHoaDon.get(i));
                startActivity(intent);
            }
        });
    }

    private void AddTabhost()
    {
        tabHost= (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        tab1.setIndicator("Lập hóa đơn");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        tab2.setIndicator("Lịch sử");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        tabHost.setCurrentTab(0);
    }
    private void addControls() {

       AddTabhost();

        lv_hoadon= (ListView) findViewById(R.id.lv_hoadon);
        Databasehelper myDatabase = new Databasehelper(this);
        //myDatabase.db_delete();
        myDatabase.Khoitai();;
        SQLiteDatabase database = myDatabase.getMyDatabase();

        dsHoaDon=new ArrayList<>();
        adapterHoaDon=new Adapter_HoaDon(LapHoaDon.this, R.layout.item_hoadon,dsHoaDon);
        Cursor cursor=database.rawQuery("SELECT   makh,tenchure,tencodau,tensanh,ngay FROM THONGTIN",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String makh = cursor.getString(0);
            String chure = cursor.getString(1);
            String codau = cursor.getString(2);
            String sanh = cursor.getString(3);
            String ngay = cursor.getString(4);
            dsHoaDon.add(new TiecCuoi(makh, chure, codau, sanh, ngay));
            adapterHoaDon.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();
        lv_hoadon.setAdapter(adapterHoaDon);

        //Lấy tiền bàn
        Cursor cursor1=database.rawQuery("select thongtin.makh,sum(dongia),dongiatoithieu" +
                                        " from  sanh join thongtin on sanh.tensanh=thongtin.tensanh join datmonan on thongtin.makh=datmonan.makh join monan on monan.madatmonan=datmonan.mamonan" +
                                        " group by thongtin.makh",null);

        cursor1.moveToFirst();
        while (!cursor1.isAfterLast())
        {
            String makh = cursor1.getString(0);
            int tienan = cursor1.getInt(1)+cursor1.getInt(2);
            for (TiecCuoi tc : dsHoaDon)
            {
                if(makh.equals(tc.getMakh()))
                    tc.setTienban(tienan);

            }
            cursor1.moveToNext();
        }
        cursor1.close();

        Cursor cursor2=database.rawQuery("select thongtin.makh,tendv,soluong,dongia " +
                "from thongtin join datdichvu on thongtin.makh=datdichvu.makh " +
                "join dichvu on dichvu.madatdichvu=datdichvu.madv",null);
        cursor2.moveToFirst();

        while (!cursor2.isAfterLast())
        {
            String makh_dv = cursor2.getString(0);
            String tendv=cursor2.getString(1);
            int sl=cursor2.getInt(2);
            int dongia=cursor2.getInt(3);
            for (TiecCuoi tc : dsHoaDon)
            {
                if(makh_dv.equals(tc.getMakh()))
                {
                    Dichvu dvtemp=new Dichvu(makh_dv,tendv,sl,dongia);
                    tc.adddv(dvtemp);
                }

            }
            cursor2.moveToNext();
        }
        cursor2.close();


    }
}
