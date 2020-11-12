package com.example.vinoteca.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.vinoteca.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {
String TAG="Vinoteca/views/logoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Starting on create");
        setContentView(R.layout.activity_logo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Starting onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Starting onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Starting onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Starting onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Starting onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Starting onDestroy");
    }
}
