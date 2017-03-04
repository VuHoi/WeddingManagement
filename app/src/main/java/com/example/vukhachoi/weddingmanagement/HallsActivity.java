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

import adapter.RecyclerViewAdapter;
import hall.wedding.management.HallDetail;
import sqlite.DataBase;

public class HallsActivity extends AppCompatActivity {
    SQLiteDatabase database;
    final String DATABASE_NAME="Weeding.sqlite";
    RecyclerView rcListHall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halls);

        addControl();
        addEvent();

    }


    private void addEvent() {

    }

    private void addControl() {
        rcListHall= (RecyclerView) findViewById(R.id.rclListHall);
        database= DataBase.initDatabase(HallsActivity.this,DATABASE_NAME);
        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
        List<HallDetail> hallDetail = new ArrayList<>();
        rcListHall.setLayoutManager(recyclerViewLayoutManager);
        rcListHall.setHasFixedSize(true);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(hallDetail,this);
        rcListHall.setAdapter(adapter);

        Cursor cursor=database.rawQuery("SELECT distinct Loaisanh,tinhtrang FROM sanh where tinhtrang=1",null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast())
        {
            HallDetail Hall=new HallDetail();
            Hall.setNameHall(cursor.getString(0));
            Hall.setActive(Boolean.parseBoolean(cursor.getString(1)));
            hallDetail.add(Hall);
            cursor.moveToNext();
            adapter.notifyDataSetChanged();
        }

       cursor=database.rawQuery("SELECT distinct Loaisanh,tinhtrang FROM sanh where tinhtrang=0",null);
        while (!cursor.isAfterLast())
        {
            HallDetail Hall=new HallDetail();
            Hall.setNameHall(cursor.getString(0));
            Hall.setActive(Boolean.parseBoolean(cursor.getString(1)));
            hallDetail.add(Hall);
            cursor.moveToNext();
            adapter.notifyDataSetChanged();
        }
        cursor.moveToFirst();
        cursor.close();
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    }
}
