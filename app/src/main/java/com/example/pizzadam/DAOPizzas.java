package com.example.pizzadam;

import java.util.ArrayList;

public class DAOPizzas {
    private static DAOPizzas dao = null;
    private static ArrayList<MyListData> pizzas = null;

    private DAOPizzas(){}

    public static DAOPizzas getInstance(){
        if (dao == null && pizzas == null) {
            dao = new DAOPizzas();
            pizzas = new ArrayList<>();
        }
        return dao;
    }

    public ArrayList<MyListData> getPizzas(){

        return null;
    }
}
