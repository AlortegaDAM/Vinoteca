package com.example.vinoteca;

import com.example.vinoteca.models.WineEntity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WineTest {
    private WineEntity wine;


    @Before
    public void setUp(){
        this.wine=new WineEntity();
    }

    @Test
    public void wineName_isCorrect(){
        assertEquals(true, this.wine.setName("Paola"));
        assertEquals("Paola", this.wine.getName());
        assertEquals("paola", this.wine.getName());

    }
    @Test
    public void wineCellar_isCorrect(){
        assertEquals(false, this.wine.setCellar("ha"));
        assertEquals(true, this.wine.setCellar("Bodegas La paz"));
        assertEquals("Bodegas La paz", this.wine.getCellar());
    }
    @Test
    public void winePrice_isCorrect(){
        assertEquals(false, this.wine.setPrice("La cara"));
        assertEquals(true, this.wine.setPrice("932"));
        assertEquals(932, this.wine.getPrice());

    }
    @Test
    public void wineDate_isCorrect(){

        assertEquals(false, this.wine.setDate("dd/MM/yyyy", "32/13/2070"));
        assertEquals(true, this.wine.setDate("dd/MM/yyyy", "03/03/1988"));
        assertEquals(false, this.wine.setDate("dd/MM/yyyy", "04/3/1988"));
    }
    @Test
    public void wineDenomination_isCorrect(){

        assertEquals(false, this.wine.setDenomination("La"));
        assertEquals(true, this.wine.setDenomination("La Rioja"));
        assertEquals(false, this.wine.setDenomination("15"));
    }
}
