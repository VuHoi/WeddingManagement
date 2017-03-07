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

        //ds = (ArrayList<TiecCuoi>) getIntent().getSerializableExtra("dsdamcuoi");
//        ArrayList<TiecCuoi> test= getIntent().getParcelableExtra("dsdamcuoi");
//        if(test.size()==0)
//        Toast.makeText(LapHoaDon.this,"deo co",Toast.LENGTH_LONG).show();

//        lv_tc= (ListView) findViewById(R.id.lv_tc);
//        adapter= new ArrayAdapter<TiecCuoi>(LapHoaDon.this,android.R.layout.simple_list_item_1,ds);
//        lv_tc.setAdapter(adapter);
    }
}
