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
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.vinoteca.R;
import com.example.vinoteca.interfaces.FormInterface;
import com.example.vinoteca.models.WineEntity;
import com.example.vinoteca.models.WineModel;
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

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FormActivity extends AppCompatActivity implements FormInterface.View {
    String TAG = "Vinoteca/views/formActivity";
    private FormInterface.Presenter presenter;
    private Context sContext;
    private boolean neWine;
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
    TextInputLayout spinnerTIL;
    ImageView imageWine;
    Button buttonImage;
    private List<String> type;
    private Spinner spinner ;
    private int Year, Month, Day;
    private Calendar calendar;
    ImageView imageDate;
    private CheckBox checkBox;
    private ArrayAdapter<String> adapter_wine;
    private DatePickerDialog datePickerDialog;
    private String id;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private ConstraintLayout constraintLayoutForm;
    private Uri uri;
    private WineModel winemodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Starting on Create");
        setContentView(R.layout.activity_form);
        sContext = this;
        presenter = new FormPresenter(this);
        wine=new WineEntity();

        neWine=true;
        winemodel= new WineModel();
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
                if(!wine.setName(nameTE.getText().toString())){
                    nameTIL.setError(presenter.getError("WineName"));
                }
                if(!wine.setCellar(cellarTE.getText().toString())){
                    cellarTIL.setError(presenter.getError("WineCellar"));
                }
                if(!wine.setDenomination(denominationTE.getText().toString())){
                    denominationTIL.setError(presenter.getError("WineDenomination"));
                }
                if(!wine.setPrice(priceTE.getText().toString())){priceTIL.setError(presenter.getError("WinePrice"));}
                if(!wine.setType(spinner.getSelectedItem().toString())){spinnerTIL.setError(presenter.getError("WineType"));}
                try {
                    BitmapDrawable wineI = (BitmapDrawable) imageWine.getDrawable();
                    if (wineI == null) {
                        wine.setImage("");
                    } else {
                        Bitmap bitmap = Bitmap.createBitmap(wineI.getBitmap());
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
                        byte[] imageBytes = bytes.toByteArray();
                        wine.setImage(Base64.encodeToString(imageBytes, Base64.DEFAULT));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(wine.setName(nameTE.getText().toString()) && wine.setCellar(cellarTE.getText().toString()) && wine.setDenomination(denominationTE.getText().toString())
                && wine.setPrice(priceTE.getText().toString()) && wine.setType(spinner.getSelectedItem().toString()) && wine.setDate("dd/MM/yyyy", yearTE.getText().toString())) {
                    wine.setAlcoholic(checkBox.isChecked());

                }

                presenter.onClickSaveButton(wine);
            }
        });

        spinnerTIL = findViewById(R.id.spinnerTIL);
        /*spinner = (Spinner) findViewById(R.id.spinner);
        type = new ArrayList<String>();
        type.add(sContext.getResources().getString(R.string.tinto));
        type.add(sContext.getResources().getString(R.string.blanco));
        type.add(sContext.getResources().getString(R.string.rosado));
        type.add(sContext.getResources().getString(R.string.dulce));
        type.add(sContext.getResources().getString(R.string.espumoso));
        adapter_wine = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, type);
        adapter_wine.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_wine);
        ImageView SpinnerAdd = findViewById(R.id.imageViewSpinner);
        SpinnerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "On click SpinnerButtonadd");
                presenter.AddSpinner();
            }
        });*/

        type = new ArrayList<String>();
        type.addAll(presenter.getSpinner());
        adapter_wine = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, type);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter_wine);
        ImageView SpinnerAdd = findViewById(R.id.imageViewSpinner);
        SpinnerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "On click SpinnerButtonadd");
                presenter.AddSpinner();
            }
        });
        wine=new WineEntity();
        checkBox= (CheckBox) findViewById(R.id.checkBox);

        nameTE = findViewById(R.id.nameTE);
        nameTIL = findViewById(R.id.nameTIL);
        nameTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange (View view,boolean hasFocus){
                if (!hasFocus) {
                    Log.d(TAG, "Exit EditTextName");
                    if (wine.setName(nameTE.getText().toString()) == false) {
                        nameTIL.setError(presenter.getError("WineName"));
                    } else {
                        nameTIL.setError("");
                    }
                } else {
                    Log.d(TAG, "Input EditTextName");
                }
            }

            });
        cellarTE = findViewById(R.id.cellarTE);
        cellarTIL = findViewById(R.id.cellarTIL);
        cellarTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange (View view,boolean hasFocus){
                if (!hasFocus) {
                    Log.d(TAG, "Exit EditTextCellar");
                    if (wine.setCellar(cellarTE.getText().toString()) == false) {
                        cellarTIL.setError(presenter.getError("WineCellar"));
                    } else {
                        cellarTIL.setError("");
                    }
                } else {
                    Log.d(TAG, "Input EditTextCellar");
                }
            }

        });
        denominationTE = findViewById(R.id.denominationTE);
        denominationTIL = findViewById(R.id.denominationTIL);
        denominationTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange (View view,boolean hasFocus){
                if (!hasFocus) {
                    Log.d(TAG, "Exit EditTextDenomination");
                    if (wine.setDenomination(denominationTE.getText().toString())==false){
                        denominationTIL.setError(presenter.getError("WineDenomination"));
                    } else {
                        denominationTIL.setError("");
                    }
                    } else {
                    Log.d(TAG, "Input EditTextDenomination");
                }
            }

        });
        priceTE = findViewById(R.id.priceTE);
        priceTIL = findViewById(R.id.priceTIL);
        priceTE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange (View view,boolean hasFocus){
                if (!hasFocus) {
                    Log.d(TAG, "Exit EditTextPrice");
                    if (wine.setPrice(priceTE.getText().toString()) == false) {
                        priceTIL.setError(presenter.getError("WinePrice"));
                    } else {
                        priceTIL.setError("");
                    }
                } else {
                    Log.d(TAG, "Input EditTextPrice");
                }
            }

        });
        yearTE= findViewById(R.id.yearTE);
        yearTIL = findViewById(R.id.yearTIL);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        imageDate= (ImageView) findViewById(R.id.imageDate);
        imageDate.setOnClickListener(new View.OnClickListener() {
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
                        yearTE.setText(day +"/"+ month0 +"/"+ year);
                    }
                }, Year, Month, Day);
                datePickerDialog.show();
            }
        });

        ImageButton delete=(ImageButton)findViewById(R.id.deleteButton);
        if (neWine) {
            delete.setEnabled(false);
        } else {
            toolbar.setTitle(R.string.update);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onclickDeleteImage");
                    presenter.onClickDeleteImage();
                }
            });
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
        /*id=getIntent().getStringExtra("id");
        if (id != null) {
            //estamos en update
            WineEntity wineCharge = presenter.getWineById(id);/*
            nameTE.setText(wineCharge.getName());
            cellarTE.setText(wineCharge.getCellar());
            denominationTE.setText(wineCharge.getDenomination());
            priceTE.setText(wineCharge.getPrice());
            checkBox.setChecked(wineCharge.isAlcoholic());
            yearTE.setText(wineCharge.getDate());
            if(wineCharge.getImage()=="" | wineCharge.getImage()==null){

            }else{
                imageWine.setBackground(null);
                byte[] decodedString = Base64.decode(wine.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageWine.setImageBitmap(decodedByte);
            }
            neWine=false;
        } else {
            //Estamos creando
            neWine= true;
        }*/
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

        int id = item.getItemId();
        Log.d(TAG, "Starting options item selected");

        if (id == R.id.action_search) {
            Log.d(TAG, "Click on Searchmenu");
            presenter.onClickSearchButton();
            return true;
        }

        if (id == R.id.action_settings) {
            Log.d(TAG, "Click on Settingsmenu");
            return true;
        }
        if (id == R.id.action_about) {
            Log.d(TAG, "click on aboutmenu");
            presenter.onClickAboutButton();
        }
        if (id == R.id.action_help) {
            Log.d(TAG, "Click on helpmenu");
            return true;
        }
        if (id == R.id.action_orderBy) {
            Log.d(TAG, "Click on orderbymenu");
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                    /*Se carga la imagen desde un objeto Bitmap*/
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();
                    if (selectedPath != null) {
                        /*Bytes de la imagen*/
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        /*Transforma los bytes-imagen a un Bitmap*/
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                        /*Carga Bitmap en el ImageView*/
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
    public void toast(String text) {
        Toast.makeText(sContext, text, Toast.LENGTH_LONG).show();
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
    public void deleteWine(){
        Log.d(TAG, "Delete Wine");
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(sContext);
        builder.setTitle(R.string.deletedWine);
        builder.setMessage(R.string.sureDelete);
        //Accept
        builder.setPositiveButton(R.string.deletedWine, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickDeleteButton(id);
            }
        });
        //Cancel
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void startAboutActivity() {
        Log.d(TAG,"Starting About Activity");
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }
    @Override
    public void startSearchActivity() {
        Log.d(TAG,"Starting Search Activity");
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }
}

