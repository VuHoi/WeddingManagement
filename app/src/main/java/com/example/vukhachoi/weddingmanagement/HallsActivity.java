package com.example.vukhachoi.weddingmanagement;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerViewAdapter;
import hall.wedding.management.HallDetail;

public class HallsActivity extends AppCompatActivity {

    RecyclerView rcListHall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halls);
     //   addData();
        addControl();
        addEvent();

    }

    private void addData() {

//        Context context;
//        RecyclerView.LayoutManager recyclerViewLayoutManager;
//        List<Person> people = new ArrayList<>();
//        people.add(new Person("Long", true));
//        people.add(new Person("My", false));
//        people.add(new Person("Duong", true));
//        people.add(new Person("Duyen", false));
//        people.add(new Person("Long", true));
//        people.add(new Person("My", false));
//        people.add(new Person("Duong", true));
//        people.add(new Person("Duyen", false));
//        context = getApplicationContext();
//        rvItems = (RecyclerView) findViewById(R.id.rv_items);
////        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
//        rvItems.setLayoutManager(recyclerViewLayoutManager);
//        rvItems.setHasFixedSize(true);
//        rvItems.setAdapter(new RecyclerDataAdapter(MainActivity.this, people));
    }


    private void addEvent() {
    }

    private void addControl() {
        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
        List<HallDetail> hallDetail = new ArrayList<>();
        hallDetail.add(new HallDetail("Hall A", true));
        hallDetail.add(new HallDetail("Hall D", false));
        hallDetail.add(new HallDetail("Hall C", true));
        hallDetail.add(new HallDetail("Hall B", true));
        hallDetail.add(new HallDetail("Hall E", false));
        rcListHall= (RecyclerView) findViewById(R.id.rclListHall);
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcListHall.setLayoutManager(recyclerViewLayoutManager);
        rcListHall.setHasFixedSize(true);
        rcListHall.setAdapter(new RecyclerViewAdapter( hallDetail,this));
    }
}
