package com.example.vinoteca.presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.vinoteca.R;
import com.example.vinoteca.interfaces.FormInterface;
import com.example.vinoteca.models.WineEntity;
import com.example.vinoteca.models.WineModel;
import com.example.vinoteca.views.MyApplication;

import java.util.List;

public class FormPresenter implements FormInterface.Presenter {
    private FormInterface.View view;
    String TAG="Vinoteca/FormPresenter";
    private WineModel wineR;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    public FormPresenter(FormInterface.View view){
        this.view =view;
        wineR =new WineModel();
    }



    @Override
    public void onClickSaveButton(WineEntity wine) {
        Log.d(TAG,"On click save");
        if(wineR.insert(wine)){
            view.closeActivity();
        }else
        view.wineNew();
    }

    @Override
    public String getError(String error_code) {
        String error_msg="";
        switch(error_code){

            case "WineName":
                error_msg= MyApplication.getContext().getResources().getString(R.string.name_error);
                break;
            case "WineCellar":
                error_msg= MyApplication.getContext().getResources().getString(R.string.cellar_error);
                break;
            case "WineDenomination":
                error_msg= MyApplication.getContext().getResources().getString(R.string.denomination_error);
                break;
            case "WinePrice":
                error_msg= MyApplication.getContext().getResources().getString(R.string.price_error);
                break;
        }
        return error_msg;
    }
    @Override
    public void AddSpinner() {
        Log.d(TAG,"On click AddSpinner");
        view.addSpinner();
    }

    @Override
    public void backToList() {
        Log.d(TAG, "on click Back Button");
        view.backToList();
    }


    @Override
    public void onClickImageWine() {
        Log.d(TAG, "onClickImage");
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d(TAG, "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);
        if(WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED){
            view.showRequestPermission();
        }else{
            Log.d(TAG, "Have permission, go to select image from gallery");
            // Con permisos
            view.selectImageFromGallery();
        }
    }

    @Override
    public void onClickButtonImage() {
        Log.d(TAG, "start on click buttonimage");
        view.cleanImage();
    }

    @Override
    public void permissionGranted() {

        Log.d(TAG, "permissionGranted");
        view.selectImageFromGallery();
    }

    @Override
    public void permissionDenied() {
        Log.d(TAG, "permission denied");
        view.showErrorPermissionDenied();
    }
    @Override
    public List<String> getSpinner() {
        return wineR.getSpinnerValues();
    }
}
