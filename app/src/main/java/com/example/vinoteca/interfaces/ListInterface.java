package com.example.vinoteca.interfaces;

import com.example.vinoteca.models.WineEntity;

import java.util.ArrayList;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startSearchActivity();
        void startAboutActivity();
        void startFormActivity(String id);
        void removeRecyclerViewItem(int id);
        void showToast(String error);
    }

    public interface Presenter{
        void onClickFloatingButton();
        void onClickAboutButton();
        void onClickSearchButton();
        void onClickRecyclerViewItem(String id);
        void onSwipeRecyclerViewItem(int id, WineEntity wine);
        void wineCharge();
        ArrayList<WineEntity> getAllSummarize();
    }
}
