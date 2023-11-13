package com.example.pizzadam;

import java.util.ArrayList;

public class DAOUsuarios {

    private static DAOUsuarios dao = null;
    private static ArrayList<Cliente> clientes;

    private DAOUsuarios(){}

    public static DAOUsuarios getInstance(){
        if (dao == null){
            dao = new DAOUsuarios();
            clientes = new ArrayList<>();
        }
        return dao;
    }

    public ArrayList<Cliente> listadoClientes(){
        clientes.add(new Cliente("user","user"));
        return clientes;
    }

}
