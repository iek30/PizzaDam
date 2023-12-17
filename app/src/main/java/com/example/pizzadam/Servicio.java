package com.example.pizzadam;

import java.util.ArrayList;

public class Servicio {

    private static Servicio servicio;
    private Pizza[] pizzas;
    private ArrayList<Pizza> pedido = null;
    private ArrayList<String> ultima = null;

    private Servicio(){
        this.pizzas = DAOPizzas.getInstance().getPizzas();
        this.pedido = new ArrayList<>();
        this.ultima = new ArrayList<>();
    }

    public void crearPedido(){
        this.pedido = new ArrayList<>();
    }

    public ArrayList<Pizza> getPedido(){
        return this.pedido;
    }

    public void crearUltima(){
        this.ultima = new ArrayList<>();
    }

    public ArrayList<String> getUltima(){
        return this.ultima;
    }

    public double calcularTotal(){
        double total = 0;
        for(Pizza pizza: this.pedido){
            total = total + pizza.getPrecio();
        }
        return total;
    }


    public static Servicio getInstance(){
        if (servicio == null) servicio = new Servicio();
        return servicio;
    }


    public Pizza[] copiaListadoPizzas(){
        return pizzas.clone();
    }

    public double obtenerPrecio(String frase) {

        String[] palabras = frase.split("\\s+");

        for (String palabra : palabras) {
            switch (palabra.toUpperCase()) {
                case "PEQUEÑA":
                    return 7.25;
                case "MEDIANA":
                    return 10.75;
                case "FAMILIAR":
                    return 18.55;
            }
        }

        return 0.0;
    }

}
