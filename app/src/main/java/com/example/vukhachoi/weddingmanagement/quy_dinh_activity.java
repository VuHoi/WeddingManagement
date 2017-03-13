package com.example.vukhachoi.weddingmanagement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.Adapter_CapNhat;
import model.MonAn;
import sqlite.Databasehelper;

public class quy_dinh_activity extends AppCompatActivity {
    TabHost tabHost;
    TextView txtnoidung;
    Switch swcquydinh;
    Databasehelper myDatabase;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quy_dinh);
        addControl();
        addEvent();
        AddTabhost();
    }

    private void addControl() {
        myDatabase = new Databasehelper(this);
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        tabHost= (TabHost) findViewById(R.id.tabhost);
txtnoidung= (TextView) findViewById(R.id.txtNoiDung);
        txtnoidung.setText("     Quốc có quốc pháp, gia có gia quy, bất kể loại hình kinh doanh của bạn là nhà hàng, quán ăn hay quán cafe, cũng đều cần có một bộ nội quy thật chuẩn và phù hợp với thực tế nhà hàng của bạn. Nó không những giúp nhà hàng của bạn tránh khỏi những rắc rối vụn vặt trong hoạt động thường ngày mà còn giúp nhân viên của bạn có cái nhìn tổng quan về quyền lợi và trách nhiệm của mình trong hoạt động công tác tại nhà hàng.Nhà hàng sẽ áp dụng những hình thức thưởng phạt thích hợp cho những cặp cô dâu chú rể khi đặt tiệc cưới tại nhà hàng . Đơn giá thanh toán các dich vụ của nhà hàng se được tính theo đơn giá trong phiếu đặt tiệc cưới. Ngày thanh toán trùng với ngày đãi tiệc, thanh toán trễ phạt 1% ngày. " );
        swcquydinh= (Switch) findViewById(R.id.swcquydinh);
        Cursor c=database.rawQuery("select DatQuyDinh from quydinh",null);
        c.moveToFirst();
        if(c.getString(0).toString().equals("1"))
        swcquydinh.setChecked(true);
        else swcquydinh.setChecked(false);
        c.close();


        //tab cap nhat
        ListView lsvMonAn= (ListView) findViewById(R.id.lsv_CapNhatMonAn);
        List<MonAn> listMonAn=new ArrayList<>();
Cursor cursor=database.rawQuery("select TenMonAn,DonGia from MonAn",null);
cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            MonAn m=new MonAn();
            m.setTenMonAn(cursor.getString(0).toString());
            m.setGia(Integer.parseInt(cursor.getString(1).toString()));

            listMonAn.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        Adapter_CapNhat adapter=new Adapter_CapNhat(quy_dinh_activity.this,R.layout.item_cap_nhat_mon_an,listMonAn);
        lsvMonAn.setAdapter(adapter);
    }

    private void addEvent() {
        swcquydinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put("DatQuyDinh",((Switch)v).isChecked());
                database.update("QuyDinh",values,null,null);
                if(((Switch)v).isChecked())
                Toast.makeText(quy_dinh_activity.this, "Đã đặt hình phạt", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(quy_dinh_activity.this, "Đã hủy hình phạt", Toast.LENGTH_SHORT).show();


            }
        });
    }



    private void AddTabhost()
    {

        tabHost.setup();
        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        tab1.setIndicator("Quy Định");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        tab2.setIndicator("Cập Nhật");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

//        tabHost.setCurrentTab(0);
    }
}
