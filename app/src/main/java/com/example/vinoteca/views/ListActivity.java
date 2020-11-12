package com.example.vinoteca.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vinoteca.R;
import com.example.vinoteca.interfaces.ListInterface;
import com.example.vinoteca.presenters.ListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListActivity extends AppCompatActivity implements ListInterface.View {
    String TAG = "Vinoteca/views/listActivity";
    private ListInterface.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting on create");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter= new ListPresenter( this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on  Floating button");
                presenter.onClickFloatingButton();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "Starting on create options menu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d(TAG, "Starting options item selected");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void startFormActivity(){
        Log.d(TAG,"Starting FormActivity");
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);

    }

}