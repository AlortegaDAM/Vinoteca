package com.example.vinoteca.interfaces;

public interface FormInterface {


    public interface View {
        void wineNew();
        void addSpinner();
        void backToList();
        void closeActivity();

        void showRequestPermission();

        void selectImageFromGallery();

        void showErrorPermissionDenied();
        void cleanImage();
    }

    public interface Presenter {
        void onClickSaveButton();
        String getError(String error_code);
        void AddSpinner();
        void backToList();

        void onClickImageWine();
        void onClickButtonImage();
        void permissionGranted();

        void permissionDenied();
    }
}
