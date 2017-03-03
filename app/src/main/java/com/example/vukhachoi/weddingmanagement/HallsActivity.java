package com.example.vukhachoi.weddingmanagement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

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

        addControl();
        addEvent();

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
