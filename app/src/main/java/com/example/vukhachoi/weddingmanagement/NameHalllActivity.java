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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerNameHallAdapter;
import hall.wedding.management.NameHall;
import sqlite.Databasehelper;

public class NameHalllActivity extends AppCompatActivity {

    RecyclerView rcHallName;
    Toolbar toolbar;
    MenuItem myMenu;
    ListView lv_edit;
    int flag=0;
    View temp;
    ArrayAdapter<String> adapter;
    ArrayList<String> ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_halll);
        addControls();
        addEvents();
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

    @Override
    protected void onResume() {
        lv_edit.setVisibility(View.INVISIBLE);
        super.onResume();
    }

    private void addEvents() {
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==1)
                {
                    lv_edit.setVisibility(View.INVISIBLE);
                    flag=0;
                }
            }
        });
        lv_edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        //Thêm
                        break;
                    case 1:
                        //Xóa
                        break;
                    case 2:
                        //Sửa
                        break;
                }
                lv_edit.setVisibility(View.INVISIBLE);
                flag=0;
            }
        });
    }

    private void addControls() {
        lv_edit= (ListView) findViewById(R.id.lv_edit);
        toolbar= (Toolbar) findViewById(R.id.toolbar_namehall);
        toolbar.setTitle("Thông tin sảnh");
        setSupportActionBar(toolbar);
        ds=new ArrayList<>();
        ds.add("Thêm");
        ds.add("Xóa");
        ds.add("Sửa");
        adapter=new ArrayAdapter<String>(NameHalllActivity.this,android.R.layout.simple_list_item_1,ds);
        lv_edit.setAdapter(adapter);
        temp=findViewById(R.id.activity_name_halll);
    }
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.mane_namehall, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        myMenu = menu.findItem(R.id.edit_menu);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.edit_menu:
                if(flag==0)
                {
                    lv_edit.setVisibility(View.VISIBLE);
                    flag=1;
                }
                else
                {
                    lv_edit.setVisibility(View.INVISIBLE);
                    flag=0;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
