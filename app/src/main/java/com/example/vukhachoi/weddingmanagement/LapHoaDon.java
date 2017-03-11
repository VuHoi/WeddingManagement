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
import model.Hoadon;
import model.TiecCuoi;
import sqlite.Databasehelper;

public class LapHoaDon extends AppCompatActivity {

    TabHost tabHost;

    ListView lv_hoadon;
    ArrayAdapter<TiecCuoi>adapterHoaDon;
    ArrayList<TiecCuoi>dsHoaDon;

    ListView lv_hoadon_dathanhtoan;
    ArrayList<Hoadon>dsHoaDon_dathanhtoan;
    ArrayAdapter<Hoadon>adapterHoaDon_dathanhtoan;

    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;

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

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
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
    private void Xulytab1()
    {
        lv_hoadon= (ListView) findViewById(R.id.lv_hoadon);

        dsHoaDon=new ArrayList<>();
        adapterHoaDon=new Adapter_HoaDon(LapHoaDon.this, R.layout.item_hoadon,dsHoaDon);


        Cursor hoadon_dalap=database.rawQuery("select makh from hoadon",null);
        hoadon_dalap.moveToFirst();
        ArrayList<String>dshoadondalap=new ArrayList<>();
        while (!hoadon_dalap.isAfterLast())
        {
            dshoadondalap.add(hoadon_dalap.getString(0));
            hoadon_dalap.moveToNext();
        }
        hoadon_dalap.close();

        Cursor cursor=database.rawQuery("SELECT   makh,tenchure,tencodau,tensanh,ngay FROM THONGTIN",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int flag=1;
            String makh = cursor.getString(0);
            for(String test:dshoadondalap)
            {
                if(makh.equals(test))
                {
                   flag=0;
                }
            }
            if(flag==1)
            {
                String chure = cursor.getString(1);
                String codau = cursor.getString(2);
                String sanh = cursor.getString(3);
                String ngay = cursor.getString(4);
                dsHoaDon.add(new TiecCuoi(makh, chure, codau, sanh, ngay));
                adapterHoaDon.notifyDataSetChanged();
            }
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

        Cursor cursor2=database.rawQuery("select thongtin.makh,tendv,soluong,dongia,tiendatcoc " +
                "from thongtin join datdichvu on thongtin.makh=datdichvu.makh " +
                "join dichvu on dichvu.madatdichvu=datdichvu.madv",null);
        cursor2.moveToFirst();

        while (!cursor2.isAfterLast())
        {
            String makh_dv = cursor2.getString(0);
            String tendv=cursor2.getString(1);
            int datcoc=cursor2.getInt(4);
            int sl=cursor2.getInt(2);
            int dongia=cursor2.getInt(3);
            for (TiecCuoi tc : dsHoaDon)
            {
                if(makh_dv.equals(tc.getMakh()))
                {
                    Dichvu dvtemp=new Dichvu(makh_dv,tendv,sl,dongia);
                    tc.adddv(dvtemp);
                    tc.setTiendatcoc(datcoc);
                }

            }
            cursor2.moveToNext();
        }
        cursor2.close();
    }

    private void Xulytab2()
    {

        lv_hoadon_dathanhtoan= (ListView) findViewById(R.id.lv_hoadon_lichsu);
        dsHoaDon_dathanhtoan=new ArrayList<>();
        adapterHoaDon_dathanhtoan=new ArrayAdapter<Hoadon>(LapHoaDon.this,android.R.layout.simple_list_item_1,dsHoaDon_dathanhtoan);
        lv_hoadon_dathanhtoan.setAdapter(adapterHoaDon_dathanhtoan);
        Cursor cursor=database.rawQuery("select mahd,makh,soluongban,dongia,tiendatcoc,tongtien from hoadon",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahd=cursor.getString(0);
            String makh=cursor.getString(1);
            int sl=cursor.getInt(2);
            int datcoc=cursor.getInt(3);
            int tongtien=cursor.getInt(4);
            dsHoaDon_dathanhtoan.add(new Hoadon(mahd,makh,sl,datcoc,datcoc,tongtien));
            adapterHoaDon_dathanhtoan.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();
    }
    private void addControls() {
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        AddTabhost();
        Xulytab1();
        Xulytab2();



    }
}
