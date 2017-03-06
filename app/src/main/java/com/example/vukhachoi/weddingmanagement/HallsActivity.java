package com.example.vukhachoi.weddingmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerViewAdapter;
import hall.wedding.management.HallDetail;
import sqlite.Databasehelper;

public class HallsActivity extends AppCompatActivity {

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

        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
        List<HallDetail> hallDetail = new ArrayList<>();
        rcListHall.setLayoutManager(recyclerViewLayoutManager);
        rcListHall.setHasFixedSize(true);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(hallDetail,this);
        rcListHall.setAdapter(adapter);
        ContentValues values=new ContentValues();
String data="A5";
        values.put("loaisanh","A");
        values.put("tensanh","A5");
        values.put("sobantoida",110);
        values.put("dongiatoithieu",110);
        values.put("ghichu","khong co gi");
        values.put("tinhtrang",1);


        database.delete("sanh","tensanh=?",new String[]{data});
        Cursor cursor=database.rawQuery("SELECT distinct Loaisanh,tinhtrang FROM Sanh",null);
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
        cursor.close();

        Cursor cursor1=database.rawQuery("SELECT distinct Loaisanh,tinhtrang FROM sanh where tinhtrang=0",null);
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast())
        {
            HallDetail Hall=new HallDetail();
            Hall.setNameHall(cursor1.getString(0));
            Hall.setActive(Boolean.parseBoolean(cursor1.getString(1)));
            hallDetail.add(Hall);
            cursor1.moveToNext();
            adapter.notifyDataSetChanged();
        }
        cursor1.moveToFirst();
        cursor1.close();
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    }
}
