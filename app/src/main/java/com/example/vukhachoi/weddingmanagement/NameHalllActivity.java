package com.example.vukhachoi.weddingmanagement;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerNameHallAdapter;
import adapter.RecyclerViewAdapter;
import hall.wedding.management.HallDetail;

public class NameHalllActivity extends AppCompatActivity {

    RecyclerView rcHallName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_halll);
        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 1);
        List<HallDetail> hallDetail = new ArrayList<>();
        hallDetail.add(new HallDetail("Hall A", true));
        hallDetail.add(new HallDetail("Hall D", false));
        hallDetail.add(new HallDetail("Hall C", true));
        hallDetail.add(new HallDetail("Hall B", true));
        hallDetail.add(new HallDetail("Hall E", false));
        rcHallName= (RecyclerView) findViewById(R.id.rcHallName);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcHallName.setLayoutManager(recyclerViewLayoutManager);
        rcHallName.setHasFixedSize(true);
        rcHallName.setAdapter(new RecyclerNameHallAdapter( hallDetail,this));

    }
}
