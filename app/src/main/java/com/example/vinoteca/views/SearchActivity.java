package com.example.vinoteca.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.vinoteca.interfaces.SearchInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.vinoteca.R;
import com.example.vinoteca.presenters.FormPresenter;
import com.example.vinoteca.presenters.SearchPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements SearchInterface.View {
    String TAG = "Vinoteca/SearchActivity";
    private SearchInterface.Presenter presenter;
    private Context sContext;
    int Year, Month, Day ;
    private DatePickerDialog datePickerDialog ;
    Calendar calendar ;
    TextInputEditText sNameTE;
    TextInputEditText sYearTE;
    Spinner spinner;
    ArrayList<String> opcion = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sContext = this;
        presenter= new SearchPresenter(this);
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
        spinner = (Spinner) findViewById(R.id.sSpinner);
        ArrayList<String> opcion =presenter.getSpinnerValues();
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcion));


        sYearTE=findViewById(R.id.sYearTE);
        ImageView sYearImage = (ImageView) findViewById(R.id.sYearImage);
        sYearImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(sContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String day = "" + dayOfMonth;
                        if (dayOfMonth<10) {
                            day = "0" + dayOfMonth;
                        }
                        String month0 = "" + (month + 1);
                        if (month<10) {
                            month0 = "0" + month0;
                        }
                        sYearTE.setText(day +"/"+ month0 +"/"+ year);
                    }
                }, Year, Month, Day);
                datePickerDialog.show();
            }
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Log.d(TAG, "Starting options item selected");

        if (id == R.id.action_search) {
            Log.d(TAG, "Click on Searchmenu");
            return true;
        }

        if (id == R.id.action_settings) {
            Log.d(TAG, "Click on Settingsmenu");
            return true;
        }
        if (id == R.id.action_about) {
            Log.d(TAG, "click on aboutmenu");
            return true;
        }
        if (id == R.id.action_help) {
            Log.d(TAG, "Click on helpmenu");
            presenter.onClickHelpButton();
            return true;
        }
        if (id == R.id.action_orderBy) {
            Log.d(TAG, "Click on orderbymenu");
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Intent intent = getIntent();
        intent.putExtra("name", sNameTE.getText().toString());
        intent.putExtra("date", sYearTE.getText().toString());
        intent.putExtra("spinner", spinner.getSelectedItemId());
        setResult(RESULT_OK, intent);
        finish();
    }
    @Override
    public void startHelpActivityfromSearch(){
        Log.d(TAG,"Starting HelpActivity");
        Intent intent = new Intent(SearchActivity.this, HelpActivity.class);
        intent.putExtra("helper", "search");
        startActivity(intent);
    }
}