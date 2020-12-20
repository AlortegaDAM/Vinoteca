package com.example.vinoteca.views;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.vinoteca.R;
import com.example.vinoteca.interfaces.FormInterface;
import com.example.vinoteca.models.WineEntity;
import com.example.vinoteca.presenters.FormPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity implements FormInterface.View {
    String TAG = "Vinoteca/views/formActivity";
    private FormInterface.Presenter presenter;
    private Context sContext;
    WineEntity wine;
    TextInputLayout nameTIL;
    TextInputEditText nameTE;
    TextInputLayout cellarTIL;
    TextInputEditText cellarTE;
    TextInputLayout denominationTIL;
    TextInputEditText denominationTE;
    TextInputLayout priceTIL;
    TextInputEditText priceTE;
    TextInputLayout yearTIL;
    TextInputEditText yearTE;
    ImageView imageWine;
    Button buttonImage;
    TextView textViewid;
    private List<String> tipo= null;
    private Spinner spinner = null;
    private ArrayAdapter<String> adapter_wine;
    private DatePickerDialog datePickerDialog;
    private String id;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private ConstraintLayout constraintLayoutForm;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting on Create");
        setContentView(R.layout.activity_form);
        sContext = this;
        presenter = new FormPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_form);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "On click Back button");
                    presenter.backToList();
                    onBackPressed();


                }
            });
        } else {
            Log.d(TAG, "Error on charge Toolbar");
        }
        ImageButton button4 = findViewById(R.id.buttonsave);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on  Save button");
                presenter.onClickSaveButton();
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        tipo = new ArrayList<String>();
        tipo.add(sContext.getResources().getString(R.string.tinto));
        tipo.add(sContext.getResources().getString(R.string.blanco));
        tipo.add(sContext.getResources().getString(R.string.rosado));
        tipo.add(sContext.getResources().getString(R.string.dulce));
        tipo.add(sContext.getResources().getString(R.string.espumoso));
        adapter_wine = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tipo);
        adapter_wine.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_wine);
        ImageView SpinnerAdd = findViewById(R.id.imageViewSpinner);
        SpinnerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "On click SpinnerButtonadd");
                presenter.AddSpinner();
            }
        });
        nameTE = findViewById(R.id.nameTE);
        nameTIL = findViewById(R.id.nameTIL);
        nameTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange (View view,boolean hasFocus){
                if (!hasFocus) {
                    Log.d(TAG, "Exit EditText");
                    if (wine.setName(nameTE.getText().toString()) == false) {
                        nameTIL.setError(presenter.getError("WineName"));
                    } else {
                        nameTIL.setError("");
                    }
                } else {
                    Log.d(TAG, "Input EditText");
                }
            }

            });


        textViewid=findViewById(R.id.textViewid);
        id=getIntent().getStringExtra("id");
        if(id!=null){
            textViewid.setText(id);
        }       else{
            //deshabilitar el boton eliminar
        }
        imageWine= findViewById(R.id.imageWine);
        imageWine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "Click on image");
                presenter.onClickImageWine();
            }
        });
        buttonImage=findViewById(R.id.buttonImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Click on delete image");
                presenter.onClickButtonImage();
            }
        });
    }
        @Override
        public void addSpinner () {
            Log.d(TAG, "Adding Fold to spinner");
            String newType = "";
            LayoutInflater layoutActivity = LayoutInflater.from(sContext);
            View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(sContext);
            alertDialog.setView(viewAlertDialog);
            EditText dialogtext = (EditText) viewAlertDialog.findViewById(R.id.dialogText);
            dialogtext.setHint(getResources().getString(R.string.type));
            alertDialog.setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.add),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    if ((dialogtext.getText().toString().equals(newType.toString()))) {
                                        dialogBox.cancel();
                                        spinner.setSelection(adapter_wine.getPosition(dialogtext.getText().toString()));
                                    } else {
                                        adapter_wine.add(dialogtext.getText().toString());
                                        spinner.setSelection(adapter_wine.getPosition(dialogtext.getText().toString()));
                                    }
                                }
                            })
                    .setNegativeButton(getResources().getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    dialogBox.cancel();
                                }
                            })
                    .create()
                    .show();

        }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Starting onStart");
    }
    @Override
    public void backToList() {
        Log.d(TAG,"BackPressed");
        onBackPressed();

    }
    @Override
    public void wineNew(){
        Log.d(TAG, "New wine in process...");
        finish();
}
    @Override
    public void closeActivity() {
        Log.d(TAG,"Finish activity");
        finish();
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

    @Override
    public void showRequestPermission() {
        Log.d(TAG, "Starting showRequestPermission");
        // Permiso denegado
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(FormActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
            // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
            // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
        }else{
            Snackbar.make(constraintLayoutForm, getResources().getString(R.string.permission_denied), Snackbar.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);


                        // Se carga el Bitmap en el ImageView
                        imageWine.setImageBitmap(imageScaled);
                    }
                }
                break;
        }
    }

    @Override
    public void selectImageFromGallery() {
        Log.d(TAG, "Select an image from gallery");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.imageselected)),
                REQUEST_SELECT_IMAGE);
    }
    @Override
    public void cleanImage() {
        Log.d(TAG, "Cleaning image");
        imageWine.setImageBitmap(null);
    }

    @Override
    public void showErrorPermissionDenied() {
        Log.d(TAG, "Show snackbar dont have permisssion");
        Snackbar.make(constraintLayoutForm, getResources().getString(R.string.permission_denied), Snackbar.LENGTH_LONG).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "Starting on RequestPermissionResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT).show();
            presenter.permissionGranted();
        } else {
            Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
            presenter.permissionDenied();
        }
    }

}