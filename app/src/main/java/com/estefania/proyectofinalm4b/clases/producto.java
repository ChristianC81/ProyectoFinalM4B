package com.estefania.proyectofinalm4b.clases;

public class producto {

    private String imagen;
    private String prod_codigo;
    private String prod_descripcion;
    private String prod_tipo;
    private String prod_img;
    private String prod_nombre;
    private Integer prod_id;
    private Integer stock;
    private Integer id_producto;

    private double prod_preciounitario;

    public producto() {
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getProd_codigo() {
        return prod_codigo;
    }

    public void setProd_codigo(String prod_codigo) {
        this.prod_codigo = prod_codigo;
    }

    public String getProd_descripcion() {
        return prod_descripcion;
    }

    public void setProd_descripcion(String prod_descripcion) {
        this.prod_descripcion = prod_descripcion;
    }

    public String getProd_tipo() {
        return prod_tipo;
    }

    public void setProd_tipo(String prod_tipo) {
        this.prod_tipo = prod_tipo;
    }

    public String getProd_img() {
        return prod_img;
    }

    public void setProd_img(String prod_img) {
        this.prod_img = prod_img;
    }

    public String getProd_nombre() { return prod_nombre;
    }

    public void setProd_nombre(String prod_nombre) {
        this.prod_nombre = prod_nombre;
    }

    public Integer getProd_id() {
        return prod_id;
    }

    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public double getProd_preciounitario() {
        return prod_preciounitario;
    }

    public void setProd_preciounitario(double prod_preciounitario) {
        this.prod_preciounitario = prod_preciounitario;
    }
}
