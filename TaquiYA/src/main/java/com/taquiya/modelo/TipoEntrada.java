package com.taquiya.modelo;

public class TipoEntrada {

    private String idTipo;
    private String nombre;
    private double precio;

    public TipoEntrada(String idTipo, String nombre, double precio) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - " + precio + " €";
    }

}
