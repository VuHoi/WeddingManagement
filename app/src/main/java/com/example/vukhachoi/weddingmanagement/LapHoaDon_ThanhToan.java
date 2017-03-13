package com.example.vukhachoi.weddingmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.Adapter_hoadon_dichvu;
import model.Dichvu;
import model.TiecCuoi;
import sqlite.Databasehelper;

import static com.example.vukhachoi.weddingmanagement.R.id.txtSearch;

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
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Button btnLap;
    Button btnHuy;
    Databasehelper myDatabase = new Databasehelper(this);

    private Toolbar mToolbar;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    AutoCompleteTextView txtSearch;

    ArrayList<String> dskh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don__thanh_toan);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Tra cứu");
        setSupportActionBar(mToolbar);
        addControls();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }
    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtSearch.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_search));

            isSearchOpened = false;
            recreate();
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title


            txtSearch= (AutoCompleteTextView) action.getCustomView().findViewById(R.id.txtSearch);
            txtSearch.setThreshold(1);
            txtSearch.setAdapter(adaptertenvc);



            txtSearch.requestFocus();

            txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String temp=txtSearch.getText().toString();
                    for(int a=0;a<ds.size();a++)
                    {
                        if(ds.get(a).getCodau().equals(temp) || ds.get(a).getChure().equals(temp))
                        {
                            TiecCuoi tc=ds.get(a);
                            ds.remove(a);
                            tc.setCheck(1);
                            ds.add(0,tc);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
            isSearchOpened = true;
        }
    }
    private void addEvents() {

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDatabase.Khoitai();;
                SQLiteDatabase database = myDatabase.getMyDatabase();
                ContentValues values=new ContentValues();
                values.put("Makh",temp.getMakh());
                values.put("Soluongban",Integer.parseInt(txtSLBan.getText().toString()));
                values.put("Dongia",temp.getTienban());
                values.put("Tiendatcoc",temp.getTiendatcoc());
                values.put("Tongtien",a+tiendv);
                values.put("Nghd",df.format(Calendar.getInstance().getTime()));
                database.insert("hoadon",null,values);
                Toast.makeText(LapHoaDon_ThanhToan.this,"Lập hóa đon thành công",Toast.LENGTH_LONG).show();
                finish();
            }
        });
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
                       txtTongTienBan.setText(txtTongTienBan.getText().toString() + x.format(a));
                       float temp1=(float)a+tiendv;
                       txttien_hoadon.setText(txttien_hoadon.getText().toString()+x.format(temp1));

                       float conlai=(temp.getTiendatcoc()-temp1*1000000)/1000000;
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


        txttien_datcoc.setText(txttien_datcoc.getText().toString()+(float)temp.getTiendatcoc()/1000000);


    }
}
