package com.example.pizzadam;

import java.util.ArrayList;

public class Servicio {

    private ArrayList<Cliente> clientes;
    private Pizza[] pizzas;

    public Servicio(){
        this.clientes = DAOUsuarios.getInstance().listadoClientes();
        this.pizzas = DAOPizzas.getInstance().getPizzas();
    }

    public ArrayList<Cliente> copiaListadoClientes(){
        return new ArrayList<>(this.clientes);
    }

    public Pizza[] copiaListadoPizzas(){
        return this.pizzas.clone();
    }

    public boolean comprobarAcceso(String usuario, String contrasena){
        boolean accesoPermitido = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (usuario.equals(clientes.get(i).getUsuario()) && contrasena.equals(clientes.get(i).getContrasena())) accesoPermitido = true;
        }
        return accesoPermitido;
    }

}
