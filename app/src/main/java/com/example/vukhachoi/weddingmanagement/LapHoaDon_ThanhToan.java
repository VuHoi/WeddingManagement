package com.example.vukhachoi.weddingmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import model.TiecCuoi;

public class LapHoaDon_ThanhToan extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don__thanh_toan);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_ThanhToan);
        toolbar.setTitle("Lập hóa đơn");
        setSupportActionBar(toolbar);

        TextView txtSanh_Laphoadon= (TextView) findViewById(R.id.txtSanh_LapHoaDon);
        TextView txtChuRe_LapHoaDon= (TextView) findViewById(R.id.txtChuRe_LapHoaDon);
        TextView txtCoDau_LapHoaDon= (TextView) findViewById(R.id.txtCoDau_LapHoaDon);
        EditText txtSLBan= (EditText) findViewById(R.id.txtSLBan);
        TextView txtDonGiaBan= (TextView) findViewById(R.id.txtDonGiaBan);
        TextView txt_NgayThanhToan= (TextView) findViewById(R.id.txtDonGiaBan);
        TextView txtTongTienBan= (TextView) findViewById(R.id.txtTongTienBan);

        TiecCuoi temp= (TiecCuoi) getIntent().getSerializableExtra("tieccuoi");
        txtChuRe_LapHoaDon.setText(temp.getChure());
        txtSanh_Laphoadon.setText(txtSanh_Laphoadon.getText()+temp.getSanh());
        txtCoDau_LapHoaDon.setText(temp.getCodau());;

    }
}
