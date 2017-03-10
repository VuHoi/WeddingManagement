package com.example.vukhachoi.weddingmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.Adapter_hoadon_dichvu;
import model.Dichvu;
import model.TiecCuoi;

public class LapHoaDon_ThanhToan extends AppCompatActivity {

    TextView txtSanh_Laphoadon;
    TextView txtChuRe_LapHoaDon;
    TextView txtCoDau_LapHoaDon;
    EditText txtSLBan;
    TextView txtDonGiaBan;
    TextView txt_NgayThanhToan;
    TextView txtTongTienBan;
    TiecCuoi temp;
    TextView txttien_dichvu;
    TextView txttien_hoadon;
    TextView txttien_datcoc;
    TextView txttien_conlai;
    ArrayList<Dichvu>dv;
    ListView lv_dv;
    float a=0;
    float tiendv=0;
    ArrayAdapter<Dichvu>adapter;
    DecimalFormat x=new DecimalFormat("#.##");

    Button btnLap;
    Button btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don__thanh_toan);
        addControls();
        addEvents();
    }

    private void addEvents() {

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
           txtSLBan.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               }

               @Override
               public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   txtTongTienBan.setText("Tổng tiền bàn(triệu đồng): ");
                   txttien_hoadon.setText("Tổng tiền hóa đơn(triệu đồng): ");
                   txttien_conlai.setText("Còn lại(triệu đồng): ");
                   if(!txtSLBan.getText().toString().equals("")) {
                       a = ((float)temp.getTienban()/1000000) * Float.parseFloat(txtSLBan.getText().toString());
                       txtTongTienBan.setText(txtTongTienBan.getText().toString() + a);
                       float temp1=(float)a+tiendv;
                       txttien_hoadon.setText(txttien_hoadon.getText().toString()+x.format(temp1));

                       float conlai=temp.getTiendatcoc()-temp1;
                       txttien_conlai.setText(txttien_conlai.getText().toString()+x.format(conlai));
                   }
               }

               @Override
               public void afterTextChanged(Editable editable) {

               }
           });
    }

    private void addControls() {

        temp= (TiecCuoi) getIntent().getSerializableExtra("tieccuoi");
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_ThanhToan);
        toolbar.setTitle("Lập hóa đơn");
        setSupportActionBar(toolbar);

        lv_dv= (ListView) findViewById(R.id.lv_dichvu);
        dv=new ArrayList<>();
        dv=temp.getDv();
        adapter=new Adapter_hoadon_dichvu(LapHoaDon_ThanhToan.this,R.layout.item_hoadon_dichvu,dv);
        lv_dv.setAdapter(adapter);

        btnLap= (Button) findViewById(R.id.btnLap);
        btnHuy= (Button) findViewById(R.id.btnHuy);

        txtSanh_Laphoadon= (TextView) findViewById(R.id.txtSanh_LapHoaDon);
        txtChuRe_LapHoaDon= (TextView) findViewById(R.id.txtChuRe_LapHoaDon);
        txtCoDau_LapHoaDon= (TextView) findViewById(R.id.txtCoDau_LapHoaDon);
        txtSLBan= (EditText) findViewById(R.id.txtSLBan);
        txtDonGiaBan= (TextView) findViewById(R.id.txtDonGiaBan);
        txt_NgayThanhToan= (TextView) findViewById(R.id.txt_NgayThanhToan);
        txtTongTienBan= (TextView) findViewById(R.id.txtTongTienBan);
        txttien_dichvu= (TextView) findViewById(R.id.txttien_dichvu);
        txttien_hoadon= (TextView) findViewById(R.id.txttien_hoadon);
        txttien_datcoc= (TextView) findViewById(R.id.txttien_datcoc);
        txttien_conlai= (TextView) findViewById(R.id.txttien_conlai);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        txt_NgayThanhToan.setText(txt_NgayThanhToan.getText()+date);
        txtChuRe_LapHoaDon.setText(temp.getChure());
        txtSanh_Laphoadon.setText(txtSanh_Laphoadon.getText()+temp.getSanh());
        txtCoDau_LapHoaDon.setText(temp.getCodau());



        for(Dichvu dv:temp.getDv())
        {
            tiendv+=(float)(dv.getDongia()*dv.getSoluong());
        }
        tiendv=(float)tiendv/1000000;


        float tien=(float)temp.getTienban()/1000000;
        txtDonGiaBan.setText(txtDonGiaBan.getText().toString()+tien);

        txttien_dichvu.setText(txttien_dichvu.getText().toString()+x.format(tiendv));


        txttien_datcoc.setText(txttien_datcoc.getText().toString()+temp.getTiendatcoc());


    }
}
