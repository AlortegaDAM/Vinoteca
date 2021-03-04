package com.example.vinoteca.presenters;

import android.util.Log;

import com.example.vinoteca.interfaces.HelpInterface;

public class HelpPresenter implements HelpInterface.Presenter {

    String TAG="Vinoteca/Presenters/HelpPresenter";

    private HelpInterface.View view;

    public HelpPresenter(HelpInterface.View view){
        this.view=view;
    }

    @Override
    public void error(){
        Log.d(TAG,"HelpPresenter error method");
        view.errorConnection();
    }

}
