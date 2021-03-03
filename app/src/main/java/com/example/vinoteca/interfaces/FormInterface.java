package com.example.vinoteca.interfaces;

import com.example.vinoteca.models.WineEntity;

import java.util.List;

public interface FormInterface {


    public interface View {
        void wineNew();
        void addSpinner();
        void backToList();
        void showRequestPermission();
        void selectImageFromGallery();
        void showErrorPermissionDenied();
        void cleanImage();
        void deleteWine();

        void toast(String string);

        void startAboutActivity();

        void startSearchActivity();
    }

    public interface Presenter {
        void onClickSearchButton();

        void onClickAboutButton();

        void onClickSaveButton(WineEntity wine);
        String getError(String error_code);
        void AddSpinner();
        void backToList();
        void onClickImageWine();
        void onClickButtonImage();
        void permissionGranted();
        void permissionDenied();
        List<String> getSpinner();
        void onClickDeleteButton(String id);
        WineEntity getWineById(String id);
        void onClickDeleteImage();
    }
}
