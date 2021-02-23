package com.example.vinoteca;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vinoteca.models.WineEntity;
import com.example.vinoteca.models.WineModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class WineRTest {

    private WineModel wineModel;

    @Before
    public void setUp(){
        wineModel=new WineModel();
    }

    @Test
    public void createDatabase(){
        ArrayList<WineEntity> wines = wineModel.getAllSumarize();
        int sizeBefore = wines.size();

        WineEntity wine=new WineEntity();
        wine.setName("Hay que borrar uno");
        wine.setAlcoholic(true);
        wine.setType("tinto");
        wine.setPrice("12");
        wine.setCellar("Casa paco");

        wineModel.insert(wine);

        wines=wineModel.getAllSumarize();
        int sizeAfter=wines.size();
        assertEquals(sizeAfter, sizeBefore +1);

    }
}
