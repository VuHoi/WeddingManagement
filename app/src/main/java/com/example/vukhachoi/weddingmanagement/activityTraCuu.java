package com.example.vukhachoi.weddingmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.adapterTraCuu;
import model.TiecCuoi;
import sqlite.DataBase;

public class activityTraCuu extends AppCompatActivity {
    final String DATABASE_NAME="Weeding.sqlite";
    SQLiteDatabase database;

    ListView lvTraCuu;
    ArrayAdapter<TiecCuoi>arrayAdapter;
    ArrayList<TiecCuoi>ds;

    ArrayList<String> tenvc;
    AutoCompleteTextView txtSearch;
    ArrayAdapter<String>adaptertenvc;

    private Toolbar mToolbar;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        addControls();
        addEvents();
    }

    private void addControls() {
        tenvc=new ArrayList<>();
        adaptertenvc=new ArrayAdapter(activityTraCuu.this,android.R.layout.select_dialog_item,tenvc);

        lvTraCuu= (ListView) findViewById(R.id.lvTraCuu);
        ds=new ArrayList<TiecCuoi>();
        arrayAdapter=new adapterTraCuu(activityTraCuu.this,R.layout.info,ds);
        lvTraCuu.setAdapter(arrayAdapter);

        database= DataBase.initDatabase(activityTraCuu.this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT * FROM THONGTIN",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String makh=cursor.getString(0);
            String chure=cursor.getString(1);
            String codau=cursor.getString(2);
            String sanh=cursor.getString(9);
            String ngay=cursor.getString(4);
            String ca=cursor.getString(5);
            int soban=cursor.getInt(7);
            ds.add(new TiecCuoi(makh,chure,codau,sanh,ngay,ca,soban));
            tenvc.add(chure);
            tenvc.add(codau);
            adaptertenvc.notifyDataSetChanged();
            arrayAdapter.notifyDataSetChanged();
            cursor.moveToNext();
        }
        cursor.close();
  }

    private void addEvents() {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }

        return super.onOptionsItemSelected(item);
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
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            //edtSeach = (EditText)action.getCustomView().findViewById(R.id.edtSearch); //the text editor
            txtSearch= (AutoCompleteTextView) action.getCustomView().findViewById(R.id.txtSearch);
            txtSearch.setThreshold(1);
            txtSearch.setAdapter(adaptertenvc);
            //this is a listener to do a search when the user clicks on search button
//            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                        doSearch();
//                        return true;
//                    }
//                    return false;
//                }
//            });


            txtSearch.requestFocus();

            //open the keyboard focused in the edtSearch
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInput(txtSearch, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
            isSearchOpened = true;
        }
    }

    private void doSearch() {
        //
    }
}

