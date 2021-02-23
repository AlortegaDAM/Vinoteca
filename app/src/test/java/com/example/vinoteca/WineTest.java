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
}
