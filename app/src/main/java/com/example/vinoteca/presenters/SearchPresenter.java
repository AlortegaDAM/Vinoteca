package com.example.vinoteca.presenters;

import android.util.Log;

import com.example.vinoteca.interfaces.SearchInterface;
import com.example.vinoteca.models.WineModel;

import java.util.ArrayList;

public class SearchPresenter implements SearchInterface.Presenter{
    String TAG = "Vinoteca/SearchPresenter";
    private SearchInterface.View view;
    private WineModel wineM;
    public SearchPresenter(SearchInterface.View view) {this.view=view;
    this.wineM = new WineModel();}
    @Override
    public void onClickSearchButton() {
        Log.d(TAG, "On click SearchImage");
        view.buttonSearch();
    }
    @Override
    public ArrayList<String> getSpinnerValues(){
        return (ArrayList<String>) wineM.getSpinnerValues();
    }
}
