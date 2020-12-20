package com.example.vinoteca.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.vinoteca.interfaces.SearchInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.vinoteca.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements SearchInterface.View {
    String TAG = "Vinoteca/SearchActivity";
    private SearchInterface.Presenter presenter;
    private Context sContext;
    int Year, Month, Day ;
    private DatePickerDialog datePickerDialog ;
    Calendar calendar ;
    ArrayList<String> opcion = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sContext = this;
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.searchActivity);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "click on back button");
                    onBackPressed();
                }
            });
        } else {
            Log.d(TAG, "Error on load Toolbar");
        }
        Spinner spinner = (Spinner) findViewById(R.id.sSpinner);
        ArrayList<String> opcion = new ArrayList<>();
        opcion.add("Criterio de búsqueda");
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcion));


        Button Sbutton= findViewById(R.id.buttonS);
        Sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "On click Search Button");
                presenter.onClickSearchButton();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "OptionsMenu");
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
    @Override
    public void buttonSearch() {
        Log.d(TAG,"Click on ImageSearch");
        finish();
    }
}