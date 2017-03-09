package com.example.vukhachoi.weddingmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
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

    ArrayList<Dichvu>dv;
    ListView lv_dv;
    ArrayAdapter<Dichvu>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don__thanh_toan);
        addControls();
        addEvents();
    }

    private void addEvents() {

           txtSLBan.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               }

               @Override
               public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   txtTongTienBan.setText("Tổng tiền bàn: ");
                   if(!txtSLBan.getText().toString().equals("")) {
                       int a = temp.getTienban() * Integer.parseInt(txtSLBan.getText().toString());
                       String b = a + "";
                       txtTongTienBan.setText(txtTongTienBan.getText() + b);
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
//        Dichvu temp1=new Dichvu("xam",1,2);
//        Dichvu temp2=new Dichvu("xi",2,4);
//        dv.add(temp1);
//        dv.add(temp2);
        dv=temp.getDv();
        adapter=new Adapter_hoadon_dichvu(LapHoaDon_ThanhToan.this,R.layout.item_hoadon_dichvu,dv);
        lv_dv.setAdapter(adapter);

        txtSanh_Laphoadon= (TextView) findViewById(R.id.txtSanh_LapHoaDon);
        txtChuRe_LapHoaDon= (TextView) findViewById(R.id.txtChuRe_LapHoaDon);
        txtCoDau_LapHoaDon= (TextView) findViewById(R.id.txtCoDau_LapHoaDon);
        txtSLBan= (EditText) findViewById(R.id.txtSLBan);
        txtDonGiaBan= (TextView) findViewById(R.id.txtDonGiaBan);
        txt_NgayThanhToan= (TextView) findViewById(R.id.txt_NgayThanhToan);
        txtTongTienBan= (TextView) findViewById(R.id.txtTongTienBan);


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        txt_NgayThanhToan.setText(txt_NgayThanhToan.getText()+date);

        txtChuRe_LapHoaDon.setText(temp.getChure());
        txtSanh_Laphoadon.setText(txtSanh_Laphoadon.getText()+temp.getSanh());
        String tien=temp.getTienban()+"";
        txtDonGiaBan.setText(txtDonGiaBan.getText()+tien);
        txtCoDau_LapHoaDon.setText(temp.getCodau());;

    }
}
