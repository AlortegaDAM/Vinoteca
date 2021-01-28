package com.example.vinoteca.models;

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
        if (name!=null) {
            this.name = name;
            return true;
        } else {
            return false;
        }

    }
    public String getCellar(){
        return cellar;
    }
    public boolean setCellar(String cellar){
        if(cellar.length()<8){
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
        if( price!=null){
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
        if( denomination!=null){
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
}
