package com.example.vinoteca.presenters;

import android.content.Intent;
import android.util.Log;

import com.example.vinoteca.interfaces.ListInterface;
import com.example.vinoteca.views.FormActivity;

public class ListPresenter implements ListInterface.Presenter {
    private ListInterface.View view;
    String TAG = "Vinoteca/ListPresenter";

    public ListPresenter(ListInterface.View view) {
        this.view = view;
    }

    @Override
    public void onClickSearchButton() {
        Log.d(TAG, "On click searchButton");
        view.startSearchActivity();
    }

    @Override
    public void onClickFloatingButton() {
        Log.d(TAG, "On click floatingButton");
        view.startFormActivity();
    }

    @Override
    public void onClickAboutButton() {
        Log.d(TAG, "On click aboutButton");
        view.startAboutActivity();
    }

    @Override
    public void onClickRecyclerViewItem(String id) {
        Log.d(TAG, "click on item of recyclerview to edit");
        view.startFormActivity(id);

    }

    @Override
    public void onSwipeRecyclerViewItem(int id) {
        Log.d(TAG, "on swipeRecyclerViewitem");
        // Decirle al modelo que borre id
        //.. luego en la Unidad 5

        // Decirle al RV que lo elimino
        view.removeRecyclerViewItem(id);
    }
    //public ArrayList<ContactEntity> getAllSummarize();
}
