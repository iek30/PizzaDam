package com.example.pizzadam;

import java.util.ArrayList;

public class Servicio {

    private static Servicio servicio;
    private ArrayList<Cliente> clientes;
    private Pizza[] pizzas;
    private ArrayList<Pizza> pedido = null;
    private ArrayList<String> ultima = null;

    private Servicio(){
        this.clientes = DAOUsuarios.getInstance().listadoClientes();
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
