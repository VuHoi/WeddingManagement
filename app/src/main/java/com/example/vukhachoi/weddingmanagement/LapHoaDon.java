package com.example.vukhachoi.weddingmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import model.TiecCuoi;

public class LapHoaDon extends AppCompatActivity {


    ListView lv_tc;
    ArrayAdapter<TiecCuoi>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don);
        addControls();
        addEvents();

    }

    private void addEvents() {

    }

    private void addControls() {

//        ArrayList<TiecCuoi> test= (ArrayList<TiecCuoi>) getIntent().getSerializableExtra("Contact_list");
//
//
//        lv_tc= (ListView) findViewById(R.id.lv_tc);
//        adapter= new ArrayAdapter<TiecCuoi>(LapHoaDon.this,android.R.layout.simple_list_item_1,test);
//        lv_tc.setAdapter(adapter);
    }
}
