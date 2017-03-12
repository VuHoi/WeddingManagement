package com.example.vukhachoi.weddingmanagement;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerNameHallAdapter;
import adapter.RecyclerViewItemClickInterface;
import hall.wedding.management.NameHall;
import sqlite.Databasehelper;

public class NameHalllActivity extends AppCompatActivity {
    Databasehelper myDatabase;
    SQLiteDatabase database;
    RecyclerNameHallAdapter adapterHallName;
    RecyclerView rcHallName;
    Button btnxoa;
    List<NameHall> nameHalls;
    Toolbar toolbar;
    MenuItem myMenu;
    ListView lv_edit;
    int flag=0;
    View temp;
    ArrayAdapter<String> adapter;
    ArrayList<String> ds;
    final int THEM=0;
    final int SUA=2;
    final  int XOA=1;
    final  int MACDINH=3;
    int luachon=MACDINH;
    Bundle extras;
    String LoaiSanh;
    String banToiDa;
    String giaToiThieu;
    String TenSanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_halll);
        addControls();
        addEvents();
        myDatabase = new Databasehelper(this);
        extras=getIntent().getExtras();
        LoaiSanh= extras.getString("NameHall");
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();

        Context context;
        context = getApplicationContext();
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager=new GridLayoutManager(context, 2);
        Bundle extras=getIntent().getExtras();
        String data= extras.getString("NameHall");

btnxoa= (Button) findViewById(R.id.btnxoa);
        nameHalls = new ArrayList<>();
        Cursor cursor =database.rawQuery("Select * from sanh where loaisanh=? ",new String[]{data});
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            NameHall nameHall=new NameHall();
            nameHall.setActive(Boolean.parseBoolean(cursor.getString(5)));
            nameHall.setBanToiDa(Integer.parseInt(cursor.getString(2)));
            nameHall.setGiaToiThieu(Integer.parseInt(cursor.getString(3)));
            nameHall.setNamehall(cursor.getString(1));
            nameHall.setVisible(false);
            nameHall.setCheck(false);
            nameHalls.add(nameHall);
            if(cursor.getString(5).equals("1"))
            {
                nameHall.setImgActive(R.drawable.red);
            }
            else
            {
                nameHall.setImgActive(R.drawable.active);
            }
            cursor.moveToNext();
        }

        cursor.close();
        rcHallName= (RecyclerView) findViewById(R.id.rcHallName);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcHallName.setLayoutManager(recyclerViewLayoutManager);
        rcHallName.setHasFixedSize(true);
        adapterHallName =new RecyclerNameHallAdapter(nameHalls,this);
        rcHallName.setAdapter(adapterHallName);

        adapterHallName.setOnItemClickListener(new RecyclerViewItemClickInterface() {
            @Override
            public void onItemclick(View v, NameHall viewModel) {
                if(luachon==MACDINH)
                {
                    Intent intent=new Intent(NameHalllActivity.this, DetailWeddingActivity.class);
                    intent.putExtra("Tensanh",viewModel.getNamehall().toString());
                    v.getContext().startActivity(intent);
                }
//                else if(luachon==XOA) {
//                    try {
//
////                        ContentValues values = new ContentValues();
//
////                        database.delete("Sanh", "Loaisanh=? and TenSanh=?", new String[]{LoaiSanh, viewModel.getNamehall().toString()});
//                        Toast.makeText(NameHalllActivity.this, "Đã xóa " + viewModel.getNamehall().toString(), Toast.LENGTH_SHORT).show();
//                    }catch (SQLiteConstraintException SQLe)
//                    {
//                        Toast.makeText(NameHalllActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                }
                else if(luachon==SUA)
                {
                    giaToiThieu=viewModel.getGiaToiThieu()+"";
                    banToiDa=viewModel.getBanToiDa()+"";
                    TenSanh=viewModel.getNamehall()+"";
                    showDialog(SUA);
                }
            }
        });


        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterHallName.remove();

            }
        });
    }





    @Override
    protected void onResume() {
        lv_edit.setVisibility(View.INVISIBLE);

        super.onResume();
    }

    private void addEvents() {
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==1)
                {
                    lv_edit.setVisibility(View.INVISIBLE);
                    flag=0;
                }
            }
        });
        lv_edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        luachon=THEM;
                        showDialog(-1);
                        break;
                    case 1:
                        //Xóa
                        luachon=XOA;
                        showDialog(-1);

                        break;
                    case 2:
                        //Sửa
                        luachon=SUA;
                        showDialog(-1);
                        break;
                    case 3:
                        luachon=MACDINH;
                }

                lv_edit.setVisibility(View.INVISIBLE);
                flag=0;
            }
        });
    }

    private void addControls() {
        lv_edit= (ListView) findViewById(R.id.lv_edit);
        toolbar= (Toolbar) findViewById(R.id.toolbar_namehall);
        toolbar.setTitle("Thông tin sảnh");
        setSupportActionBar(toolbar);
        ds=new ArrayList<>();
        ds.add("Thêm");
        ds.add("Xóa");
        ds.add("Sửa");
        ds.add("Mặc định");
        adapter=new ArrayAdapter<String>(NameHalllActivity.this,android.R.layout.simple_list_item_1,ds);
        lv_edit.setAdapter(adapter);
        temp=findViewById(R.id.activity_name_halll);
    }
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.mane_namehall, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        myMenu = menu.findItem(R.id.edit_menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.edit_menu:
                if(flag==0)
                {
                    lv_edit.setVisibility(View.VISIBLE);
                    flag=1;
                }
                else
                {
                    lv_edit.setVisibility(View.INVISIBLE);
                    flag=0;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog dialog =null;
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogview;
        AlertDialog.Builder dialogbuilder;
        switch (id)
        {
            case -1:
                dialogview = inflater.inflate(R.layout.dialog_login, null);
                dialogbuilder = new AlertDialog.Builder(this);
                dialogbuilder.setView(dialogview);
                dialogbuilder.setView(dialogview);
                dialog = dialogbuilder.create();
                
                break;
            case THEM:
                dialogview = inflater.inflate(R.layout.dialog_them_sua_sanh, null);
                dialogbuilder = new AlertDialog.Builder(this);
                dialogbuilder.setView(dialogview);
                dialogbuilder.setView(dialogview);
                dialog = dialogbuilder.create();
                luachon=MACDINH;
                break;
            case SUA:
                dialogview = inflater.inflate(R.layout.dialog_them_sua_sanh, null);
                dialogbuilder = new AlertDialog.Builder(this);
                dialogbuilder.setView(dialogview);
                dialogbuilder.setView(dialogview);
                dialog = dialogbuilder.create();
                break;


        }

        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        final AlertDialog alertDialog;
        Button btnThem;
        Button btnSua;
        final    EditText edtgiatoithieu;
        final   EditText edtbantoida;
        final    EditText edtTensanh;
        switch (id) {
            case -1:
                //dang nhap
                alertDialog = (AlertDialog) dialog;
                Button btndangnhap= (Button) alertDialog.findViewById(R.id.btndangnhap);

                final EditText edtpass= (EditText) alertDialog.findViewById(R.id.edtpass);
                btndangnhap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                        switch (luachon)
                        {
                            case THEM :
                                showDialog(THEM);
                                luachon=MACDINH;
                                break;

                            case -1:   luachon=MACDINH; break;
                            case XOA:
                                for(NameHall hall:nameHalls)
                                {
                                    hall.setVisible(true);
                                }
                                adapterHallName.notifyDataSetChanged();
                                break;
                        }

                    }
                });

                break;
            case THEM:
                //them sanh
                alertDialog = (AlertDialog) dialog;
                btnThem= (Button) alertDialog.findViewById(R.id.btnThem);
                btnSua= (Button) alertDialog.findViewById(R.id.btnSua);
                btnSua.setVisibility(View.INVISIBLE);
                edtgiatoithieu= (EditText) alertDialog.findViewById(R.id.edtgiatoithieu);
                edtbantoida= (EditText) alertDialog.findViewById(R.id.edtbantoida);
                edtTensanh= (EditText) alertDialog.findViewById(R.id.edttensanh);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues values=new ContentValues();

                        if(edtTensanh.getText().toString().isEmpty()||edtbantoida.getText().toString().isEmpty()||edtgiatoithieu.getText().toString().isEmpty() ) {

                            Toast.makeText(NameHalllActivity.this, "Không được bỏ trông các trường", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try {
                                values.put("LoaiSanh", LoaiSanh);
                                values.put("TenSanh", edtTensanh.getText().toString());
                                values.put("SoBanToiDa", edtbantoida.getText().toString());
                                values.put("DonGiaToiThieu", edtgiatoithieu.getText().toString());
                                values.put("GhiChu", "");
                                values.put("TinhTrang", "0");
                                database.insertWithOnConflict("Sanh", null, values, SQLiteDatabase.CONFLICT_FAIL);
                                Toast.makeText(NameHalllActivity.this, "Thêm sảnh thành công", Toast.LENGTH_SHORT).show();
                                recreate();
                                alertDialog.dismiss();
                            } catch (SQLiteConstraintException SQLe) {
                                Toast.makeText(NameHalllActivity.this, "Thêm sảnh thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                break;
            case SUA:
                //sua sanh
                alertDialog = (AlertDialog) dialog;
                btnThem= (Button) alertDialog.findViewById(R.id.btnThem);
                btnSua= (Button) alertDialog.findViewById(R.id.btnSua);
                btnThem.setVisibility(View.INVISIBLE);
                edtgiatoithieu= (EditText) alertDialog.findViewById(R.id.edtgiatoithieu);
                edtbantoida= (EditText) alertDialog.findViewById(R.id.edtbantoida);
                edtTensanh= (EditText) alertDialog.findViewById(R.id.edttensanh);
                edtbantoida.setText(banToiDa);
                edtgiatoithieu.setText(giaToiThieu);
                edtTensanh.setText(TenSanh);
                edtTensanh.setEnabled(false);
                edtTensanh.setKeyListener(null);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues values=new ContentValues();
                        Bundle extras=getIntent().getExtras();
                        String Loaisanh= extras.getString("NameHall");
                        if(edtTensanh.getText().toString().isEmpty()||edtbantoida.getText().toString().isEmpty()||edtgiatoithieu.getText().toString().isEmpty() ) {

                            Toast.makeText(NameHalllActivity.this, "Không được bỏ trông các trường", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            try {

                                values.put("TenSanh", edtTensanh.getText().toString());
                                values.put("SoBanToiDa", edtbantoida.getText().toString());
                                values.put("DonGiaToiThieu", edtgiatoithieu.getText().toString());

                                database.updateWithOnConflict("Sanh", values, "TenSanh=? and LoaiSanh=?", new String[]{edtTensanh.getText().toString(), Loaisanh}, SQLiteDatabase.CONFLICT_FAIL);
                                Toast.makeText(NameHalllActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                recreate();
                                alertDialog.dismiss();
                            } catch (SQLiteConstraintException SQLe) {
                                Toast.makeText(NameHalllActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
              luachon=MACDINH;
        }
    }
}
