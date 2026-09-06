package com.botilleria.productos.model;

public class Producto {
    private Long identificador;
    private String nombre;
    private Integer precio;
    private Integer inventario;

    public Producto() {
    }

    public Producto(Long identificador, String nombre, Integer precio, Integer inventario) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getInventario() {
        return inventario;
    }

    public void setInventario(Integer inventario) {
        this.inventario = inventario;
    }
}