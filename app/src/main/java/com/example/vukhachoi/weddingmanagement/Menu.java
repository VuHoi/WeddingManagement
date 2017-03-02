package com.example.vukhachoi.weddingmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
    ImageView imgTiepNhan,imgDatTiec,imgTraCuu,imgHoaDon,imgBaoCao,imgThayDoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        addControls();
        addEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void addEvents() {
        HoatAnh();

    }

    private void addControls() {
        imgTiepNhan= (ImageView) findViewById(R.id.imgTiepNhan);
        imgDatTiec= (ImageView) findViewById(R.id.imgDatTiec);
        imgTraCuu= (ImageView) findViewById(R.id.imgTraCuu);
        imgHoaDon= (ImageView) findViewById(R.id.imgHoaDon);
        imgBaoCao= (ImageView) findViewById(R.id.imgBaoCao);
        imgThayDoi= (ImageView) findViewById(R.id.imgThayDoi);

    }
    private void HoatAnh()
    {
        ThayDoiAnh(imgTiepNhan,R.drawable.sanh2);
        ThayDoiAnh(imgDatTiec,R.drawable.tieccuoi2);
        ThayDoiAnh(imgTraCuu,R.drawable.tiec2);
        ThayDoiAnh(imgHoaDon,R.drawable.hoadon2);
        ThayDoiAnh(imgBaoCao,R.drawable.baocao2);
        ThayDoiAnh(imgThayDoi,R.drawable.thaydoi2);
        ThayDoiAnh(imgDatTiec,R.drawable.tieccuoi2);

    }

    private void ThayDoiAnh(final ImageView id1, final int id2)
    {
        id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id1.setImageResource(id2);
                Intent intent=new Intent(Menu.this,HallsActivity.class);
                startActivity(intent);
            }
        });
    }
}
