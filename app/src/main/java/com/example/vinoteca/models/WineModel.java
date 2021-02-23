package com.example.vinoteca.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class WineModel {

    public ArrayList<WineEntity> getAllSumarize() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<WineEntity> result = realm.where(WineEntity.class).findAll();
        Log.d("Realm find items:", "" + result.size());

        ArrayList<WineEntity> wineList = new ArrayList<>();
        wineList.addAll(realm.copyFromRealm(result));
        realm.commitTransaction();
        realm.close();
        ArrayList<WineEntity> wineListSummarize = new ArrayList<>();
        for (WineEntity wine : wineList) {
            WineEntity neWine= new WineEntity();
            neWine.setId(wine.getId());
            neWine.setName(wine.getName());
            neWine.setCellar(wine.getCellar());
            neWine.setImage(wine.getImage());

            wineListSummarize.add(neWine);

        }
        return wineListSummarize;
    }

    public boolean insert(WineEntity wine) {
        boolean create = false;
        Realm realm = Realm.getDefaultInstance();
        wine.setId(UUID.randomUUID().toString());
        if (wine != null) {
            try {
                realm.beginTransaction();
                realm.copyToRealm(wine);
                realm.commitTransaction();
                realm.close();
                create = true;
            } catch (Exception error) {

            }
        }
        return create;
    }

    public boolean deleteWine(String id) {
        boolean delete = false;
        Realm realm = Realm.getDefaultInstance();
        if (id != null) {
            try {
                realm.beginTransaction();
                WineEntity wineR = realm.where(WineEntity.class)
                        .equalTo("id", id)
                        .findFirst();

                wineR.deleteFromRealm();
                realm.commitTransaction();
                realm.close();
                delete = true;
            } catch (Exception e) {

            }
        }
        return delete;
    }

    public boolean updateWine(WineEntity wine) {
        boolean update = false;
        Realm realm = Realm.getDefaultInstance();
        if (wine != null) {
            try {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(wine);
                realm.commitTransaction();
                update = true;
                realm.close();
            } catch (Exception e) {

            }
        }
        return update;
    }

    public WineEntity getWineById(String id) {
        WineEntity wine = null;
        Realm realm = Realm.getDefaultInstance();
        if (id != null) {
            try {
                realm.beginTransaction();
                wine = realm.where(WineEntity.class).equalTo("id", id).findFirst();
                realm.commitTransaction();
                realm.close();
            } catch (Exception e) {

            }

        }
        return wine;
    }

    public List<String> getSpinnerValues() {
        List<String> spinner = new ArrayList<>();
        List<WineEntity> listWines = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            RealmResults<WineEntity> result = realm.where(WineEntity.class).distinct("type").findAll();
            listWines.addAll(realm.copyFromRealm(result));
            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {

        }
        for (WineEntity wine : listWines) {
            if(wine.getType()!=null) {
                spinner.add(wine.getType());
            }
        }

        return spinner;
    }
}


