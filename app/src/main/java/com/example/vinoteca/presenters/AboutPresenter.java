package com.example.vinoteca.presenters;

import com.example.vinoteca.interfaces.AboutInterface;

public class AboutPresenter implements AboutInterface.Presenter {
    String TAG="Vinoteca/AboutPresenter";
    private AboutInterface.View view;
    public AboutPresenter(AboutInterface.View view){this.view=view;}
}
