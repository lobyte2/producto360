package com.botilleria.productos.model;

public class Producto {
    private Long identificador;
    private String nombre;
    private Integer precio;
    private Integer inventario;
    private String imagen;

    public Producto() {
    }

    public Producto(Long identificador, String nombre, Integer precio, Integer inventario, String imagen) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}