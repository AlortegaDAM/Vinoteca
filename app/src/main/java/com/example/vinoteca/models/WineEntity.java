package com.example.vinoteca.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WineEntity extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String cellar;
    private String denomination;
    private String price;
    private String type;
    private String date;
    private boolean alcoholic;
    private String image;


    public WineEntity() {
    }
    public WineEntity(String image, String name, String cellar, String id){
        this.image=image;
        this.name=name;
        this.cellar=cellar;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name!=null && name.length()>5) {
            this.name = name;
            return true;
        } else {
            return false;
        }

    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }
    public String getCellar(){
        return cellar;
    }
    public boolean setCellar(String cellar){
        if(cellar.length()>6){
            this.cellar= cellar;
            return true;
        }else {
            return false;
        }

    }
    public String getPrice(){
        return price;
    }
    public boolean setPrice(String price){
        if( price!=null && price.matches("[0-9]*")){
            this.price= price;
            return true;
        }else{return false;}
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDenomination() {
        return denomination;
    }

    public boolean setDenomination(String denomination) {
        if( denomination!=null && denomination.length()>6){
            this.denomination= denomination;
            return true;
        }else{
            return false;}
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate(){
        return date;
    }
    public boolean setDate(String format, String value) {
        //v=verified
        boolean result = false;
        Date date = null;
        try {
            SimpleDateFormat data = new SimpleDateFormat(format);
            date = data.parse(value);
            if (value.equals(data.format(date))) {
                result = true;
                this.date = value;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean equals(Object wine){
        boolean result=false;
        if(wine!=null && wine instanceof WineEntity){
            WineEntity wineC=(WineEntity) wine;
            if(id.equals(wineC.getId())){
                result=true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "(Wine:"+ id + "; name:"+ name + "; cellar:"+ cellar + "; denomination:"+ denomination + "; price:"+ price + "; date:"+ date + "; type:"+ type + "; alcoholic:"+ alcoholic +")";
    }
}
