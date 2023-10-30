package com.example.pizzadam;

import java.util.ArrayList;

public class Pizza {

    private Integer idImagen;
    private String nombre;
    private ArrayList<String> ingredientes;
    private double precio;

    public Pizza(Integer idImagen, String nombre, ArrayList<String> ingredientes, double precio) {
        this.idImagen = idImagen;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "idImagen=" + idImagen +
                ", nombre='" + nombre + '\'' +
                ", ingredientes=" + ingredientes +
                ", precio=" + precio +
                '}';
    }
}
