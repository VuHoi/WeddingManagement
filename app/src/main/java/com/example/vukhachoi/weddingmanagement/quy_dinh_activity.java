package com.example.vukhachoi.weddingmanagement;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.Adapter_CapNhat;
import adapter.Adapter_CapNhat_DichVu;
import model.Dichvu;
import model.MonAn;
import sqlite.Databasehelper;

public class quy_dinh_activity extends AppCompatActivity {
    TabHost tabHost;
    TextView txtnoidung;
    Switch swcquydinh;
    Databasehelper myDatabase;
    SQLiteDatabase database;
    final int THEM=0,XOA=1,SUA=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quy_dinh);
        addControl();
        addEvent();
        AddTabhost();
    }

    private void addControl() {
        //addcontrol myDatabase = new Databasehelper(this);
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
        ListView lsvDichvu= (ListView) findViewById(R.id.lsv_CapNhatDichVu);
        List<MonAn> listMonAn=new ArrayList<>();
        List<Dichvu> listDichVu=new ArrayList<>();
        //adapter mon an
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
        //adapter dich vu
        Cursor cursordichvu=database.rawQuery("select TenDV,DonGia from dichvu",null);
        cursordichvu.moveToFirst();
        while (!cursordichvu.isAfterLast())
        {
            Dichvu m=new Dichvu();
            m.setTendichvu(cursordichvu.getString(0).toString());
            m.setDongia(Integer.parseInt(cursordichvu.getString(1).toString()));

            listDichVu.add(m);
            cursordichvu.moveToNext();
        }
        cursordichvu.close();
        Adapter_CapNhat_DichVu adapterDIchVu=new Adapter_CapNhat_DichVu(quy_dinh_activity.this,R.layout.item_cap_nhat_mon_an,listDichVu);
        lsvDichvu.setAdapter(adapterDIchVu);
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


    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog dialog =null;
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogview;
        AlertDialog.Builder dialogbuilder;
        switch (id)
        {
            case THEM:
                dialogview = inflater.inflate(R.layout.dialog_login, null);
                dialogbuilder = new AlertDialog.Builder(this);
                dialogbuilder.setView(dialogview);

                dialog = dialogbuilder.create();

                break;
        }


        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        final AlertDialog alertDialog;

        switch(id)
        {
            case THEM:


                break;
        }


    }

    //addtabhost
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
