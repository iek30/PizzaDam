package com.example.pizzadam;

import java.util.ArrayList;
import java.util.Arrays;

public class DAOPizzas {
    private static DAOPizzas dao = null;
    private static Pizza[] pizzas = null;

    private DAOPizzas(){}

    public static DAOPizzas getInstance(){
        if (dao == null && pizzas == null) {
            dao = new DAOPizzas();
            pizzas = null;
        }
        return dao;
    }

    public Pizza[] getPizzas(){

        ArrayList<String> ingredientes1 = new ArrayList<>();
        ingredientes1.add("Carbonara");
        ingredientes1.add("Pollo");
        ingredientes1.add("Champiñones");
        ingredientes1.add("Cebolla");
        ingredientes1.add("Bacon");

        ArrayList<String> ingredientes2 = new ArrayList<>();
        ingredientes2.add("Tomate");
        ingredientes2.add("Chicharrones");
        ingredientes2.add("Gaucha");
        ingredientes2.add("Ternera");

        ArrayList<String> ingredientes3 = new ArrayList<>();
        ingredientes3.add("Tomate");
        ingredientes3.add("Jalapeño");
        ingredientes3.add("Salsa Valentina");
        ingredientes3.add("Ternera");
        ingredientes3.add("Bacon");

        ArrayList<String> ingredientes4 = new ArrayList<>();
        ingredientes4.add("Tomate");
        ingredientes4.add("Patatas");
        ingredientes4.add("Pollo");
        ingredientes4.add("Salsa Blanca");

        Pizza[] listData = new Pizza[]{
                new Pizza("Pizza Carbonara", R.drawable.carbonara, 10, ingredientes1),
                new Pizza("Pizza Chicharruners", R.drawable.chicharruners, 10, ingredientes2),
                        new Pizza("Pizza Mexicana", R.drawable.mexicana, 10, ingredientes3),
                        new Pizza("Pizza Ranchera", R.drawable.ranchera, 10, ingredientes4),
                        new Pizza("Pizza al gusto", R.drawable.gusto, 10, new ArrayList<>())
        };

        return listData;
    }
}
