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

import adapter.RecyclerNameHallAdapter;
import hall.wedding.management.NameHall;
import sqlite.Databasehelper;

public class NameHalllActivity extends AppCompatActivity {

    RecyclerView rcHallName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_halll);
        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
        Bundle extras=getIntent().getExtras();
        String data= extras.getString("NameHall");
        ContentValues values=new ContentValues();

        List<NameHall> nameHalls = new ArrayList<>();
        Cursor cursor =database.rawQuery("Select * from sanh where loaisanh=? ",new String[]{data});
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            NameHall nameHall=new NameHall();
            nameHall.setActive(Boolean.parseBoolean(cursor.getString(5)));
            nameHall.setBanToiDa(Integer.parseInt(cursor.getString(2)));
            nameHall.setGiaToiThieu(Integer.parseInt(cursor.getString(3)));
            nameHall.setNamehall(cursor.getString(1));

            nameHalls.add(nameHall);
            if(cursor.getString(5).equals("1"))
            {
                nameHall.setImgActive(R.drawable.active);
            }
            else
            {
                nameHall.setImgActive(R.drawable.red);
            }
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
