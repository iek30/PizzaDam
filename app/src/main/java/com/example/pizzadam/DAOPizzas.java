package com.example.pizzadam;

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

        Pizza[] listData = new Pizza[]{
                new Pizza("Pizza Carbonara", R.drawable.carbonara, 12.7, null),
                new Pizza("Pizza Chicharruners", R.drawable.chicharruners, 12.7, null),
                new Pizza("Pizza Mexicana", R.drawable.mexicana, 12.7, null),
                new Pizza("Pizza Ranchera", R.drawable.ranchera, 12.7, null),
                new Pizza("Pizza al gusto", R.drawable.ranchera, 12.7, null)
        };

        return listData;
    }
}
