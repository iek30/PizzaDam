package com.example.pizzadam;

import java.util.ArrayList;

public class MyListData {
    private String name;
    private int imgId;
    private double precio;
    private ArrayList<String> ingredientes;

    public MyListData(String name, int imgId, double precio, ArrayList<String> ingredientes) {
        this.name = name;
        this.imgId = imgId;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
