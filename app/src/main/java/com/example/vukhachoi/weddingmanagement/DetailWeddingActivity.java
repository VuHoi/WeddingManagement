package com.example.vukhachoi.weddingmanagement;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.AdapterMonAn;
import model.MonAn;
import sqlite.Databasehelper;

public class DetailWeddingActivity extends AppCompatActivity {
ListView lsvMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wedding);
        addControl();
        addEvent();


    }

    private void addControl() {
        lsvMonAn= (ListView) findViewById(R.id.lsv_MonAn);

        Databasehelper myDatabase = new Databasehelper(this);

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
        List<MonAn>lsv= new ArrayList<>();

        Cursor cursor=database.rawQuery("SELECT * FROM monan",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            MonAn monAn=new MonAn();
            monAn.setTenMonAn(cursor.getString(1).toString());
            monAn.setGia(Integer.parseInt(cursor.getString(2).toString()));
            lsv.add(monAn);
            cursor.moveToNext();

        }


        cursor.close();
        AdapterMonAn adapter=new AdapterMonAn(DetailWeddingActivity.this,R.layout.item_monan,lsv);
        lsvMonAn.setAdapter(adapter);

    }


    private void addEvent() {

    }
}
