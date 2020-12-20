package com.example.vinoteca.interfaces;

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
        void onSwipeRecyclerViewItem(int id);
    }
}
