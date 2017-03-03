package com.example.vukhachoi.weddingmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.adapterTraCuu;
import model.TiecCuoi;
import sqlite.DataBase;

public class activityTraCuu extends AppCompatActivity {
    final String DATABASE_NAME="Weeding.sqlite";
    SQLiteDatabase database;

    ListView lvTraCuu;
    ArrayAdapter<TiecCuoi>arrayAdapter;
    ArrayList<TiecCuoi>ds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu);
        addControls();
        addEvents();
    }

    private void addControls() {
        lvTraCuu= (ListView) findViewById(R.id.lvTraCuu);
        ds=new ArrayList<TiecCuoi>();
        arrayAdapter=new adapterTraCuu(activityTraCuu.this,R.layout.info,ds);
        lvTraCuu.setAdapter(arrayAdapter);

        database= DataBase.initDatabase(activityTraCuu.this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT * FROM THONGTIN",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String makh=cursor.getString(0);
            String chure=cursor.getString(1);
            String codau=cursor.getString(2);
            String sanh=cursor.getString(9);
            String ngay=cursor.getString(4);
            String ca=cursor.getString(5);
            int soban=cursor.getInt(7);
            ds.add(new TiecCuoi(makh,chure,codau,sanh,ngay,ca,soban));
            arrayAdapter.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();
  }

    private void addEvents() {

    }
}
