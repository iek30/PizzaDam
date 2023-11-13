package com.example.pizzadam;

import java.util.ArrayList;

public class Servicio {

    private static Servicio servicio;
    private ArrayList<Cliente> clientes;
    private Pizza[] pizzas;
    private ArrayList<Pizza> pedido = null;

    private Servicio(){
        this.clientes = DAOUsuarios.getInstance().listadoClientes();
        this.pizzas = DAOPizzas.getInstance().getPizzas();
        this.pedido = new ArrayList<>();
    }

    public void crearPedido(){
        this.pedido = new ArrayList<>();
    }

    public ArrayList<Pizza> getPedido(){
        return this.pedido;
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

    public ArrayList<Cliente> copiaListadoClientes(){
        return new ArrayList<>(clientes);
    }

    public Pizza[] copiaListadoPizzas(){
        return pizzas.clone();
    }

    public boolean comprobarAcceso(String usuario, String contrasena){
        boolean accesoPermitido = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (usuario.equals(clientes.get(i).getUsuario()) && contrasena.equals(clientes.get(i).getContrasena())) accesoPermitido = true;
        }
        return accesoPermitido;
    }

}
