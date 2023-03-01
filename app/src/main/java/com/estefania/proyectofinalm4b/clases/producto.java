package com.estefania.proyectofinalm4b.clases;

public class producto {

    private String prod_nombre;
    private String prod_tipo;
    private String prod_descripcion;
    private double prod_preciounitario;

    public producto() {
    }

    public String getProd_nombre() {
        return prod_nombre;
    }

    public void setProd_nombre(String prod_nombre) {
        this.prod_nombre = prod_nombre;
    }

    public String getProd_tipo() {
        return prod_tipo;
    }

    public void setProd_tipo(String prod_tipo) {
        this.prod_tipo = prod_tipo;
    }

    public String getProd_descripcion() {
        return prod_descripcion;
    }

    public void setProd_descripcion(String prod_descripcion) {
        this.prod_descripcion = prod_descripcion;
    }

    public double getProd_preciounitario() {
        return prod_preciounitario;
    }

    public void setProd_preciounitario(double prod_preciounitario) {
        this.prod_preciounitario = prod_preciounitario;
    }
}
