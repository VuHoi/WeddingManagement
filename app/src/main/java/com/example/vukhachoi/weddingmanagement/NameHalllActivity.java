package com.example.vukhachoi.weddingmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerNameHallAdapter;
import hall.wedding.management.NameHall;
import sqlite.DataBase;

public class NameHalllActivity extends AppCompatActivity {
    SQLiteDatabase database;
    final String DATABASE_NAME="Weeding.sqlite";
    RecyclerView rcHallName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database= DataBase.initDatabase(NameHalllActivity.this,DATABASE_NAME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_halll);
        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
        List<NameHall> nameHalls = new ArrayList<>();
      Cursor cursor =database.rawQuery("Select * from sanh",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            NameHall nameHall=new NameHall();
            nameHall.setActive(Boolean.parseBoolean(cursor.getString(5)));
            nameHall.setBanToiDa(Integer.parseInt(cursor.getString(2)));
            nameHall.setGiaToiThieu(Integer.parseInt(cursor.getString(3)));
            nameHalls.add(nameHall);
            cursor.moveToNext();
        }
        cursor.close();
        rcHallName= (RecyclerView) findViewById(R.id.rcHallName);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcHallName.setLayoutManager(recyclerViewLayoutManager);
        rcHallName.setHasFixedSize(true);
        rcHallName.setAdapter(new RecyclerNameHallAdapter( nameHalls,this));

    }
}
