package com.example.vinoteca.presenters;

import com.example.vinoteca.interfaces.FormInterface;

public class FormPresenter implements FormInterface.Presenter {
    private FormInterface.View view;
    public FormPresenter(FormInterface.View view){
        this.view =view;
    }

    @Override
    public void onClickSaveButton() {
        //es solo implementando los metodos necesarios
    }
}
