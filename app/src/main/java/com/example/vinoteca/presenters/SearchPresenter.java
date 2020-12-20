package com.example.vinoteca.presenters;

import android.util.Log;

import com.example.vinoteca.interfaces.SearchInterface;

public class SearchPresenter implements SearchInterface.Presenter{
    String TAG = "Vinoteca/SearchPresenter";
    private SearchInterface.View view;
    public SearchPresenter(SearchInterface.View view) {this.view=view; }
    @Override
    public void onClickSearchButton() {
        Log.d(TAG, "On click SearchImage");
        view.buttonSearch();
    }
}
