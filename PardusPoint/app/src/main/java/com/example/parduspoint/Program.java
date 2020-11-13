package com.example.parduspoint;

public class Program {
    String name;
    int image;


    public Program(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;}

    public void setName (String name){
        this.name = name;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }
}