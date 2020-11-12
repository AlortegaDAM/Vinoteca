package com.example.vinoteca.presenters;

import com.example.vinoteca.interfaces.ListInterface;

public class ListPresenter implements ListInterface.Presenter {
    private ListInterface.View view;
    public ListPresenter(ListInterface.View view){
        this.view =view;
    }

    @Override
    public void onClickFloatingButton() {
        //Log.d("");
        view.startFormActivity();
    }
}
