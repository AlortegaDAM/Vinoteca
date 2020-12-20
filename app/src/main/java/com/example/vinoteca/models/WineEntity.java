package com.example.vinoteca.models;

public class WineEntity {

    private String id;
    private String name;
    private String cellar;
    private String denomination;
    private double price;
    private String type;
    private String image;


    public WineEntity() {
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
    public double getPrice(){
        return price;
    }
    public boolean setPrice(Double price){
        if( price!=null){
            this.price=price;
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

    public void setDenomination(String denomination) {
        this.denomination = denomination;
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
