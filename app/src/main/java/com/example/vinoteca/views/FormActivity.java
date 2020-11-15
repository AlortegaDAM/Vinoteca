package com.example.vinoteca.views;

import android.os.Bundle;

import com.example.vinoteca.R;
import com.example.vinoteca.interfaces.FormInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FormActivity extends AppCompatActivity implements FormInterface.View {
    String TAG = "Vinoteca/views/formActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting on Create");
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Menú/NuevoVino");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Asignar la acción necesaria. En este caso "volver atrás"
                    onBackPressed();
                }
            });
        } else {
            Log.d("SobreNosotros", "Error al cargar toolbar");
        }

        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] tipo = {"Tinto","Blanco","Rosado","Dulce","Espumoso","Añadir más opciones..."};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo));*/
    }
    @Override
    public void startFormActivity() {
        //SOLO LLAMADA A LA FUNCION PARA QUE NO DE FALLO,
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