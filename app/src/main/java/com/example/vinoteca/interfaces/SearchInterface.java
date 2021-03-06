package com.example.vinoteca.interfaces;

import java.util.ArrayList;

public interface SearchInterface {
    public interface Presenter {
        void onClickSearchButton();
        public ArrayList<String> getSpinnerValues();

        void onClickHelpButton();
    }

    public interface View {
        void buttonSearch();

        void startHelpActivityfromSearch();
    }
}
