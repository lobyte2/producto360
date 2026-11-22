package com.botilleria.productos.dto;

public class ProductoEventoDto {
    private String tipoEvento;
    private String origen;
    private String usuarioId;
    private Long productoId;
    private String productoNombre;
    private Integer precio;
    private Integer inventario;
    private Integer resultados;
    private String timestamp;

    public ProductoEventoDto() {}

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }

    public Integer getInventario() { return inventario; }
    public void setInventario(Integer inventario) { this.inventario = inventario; }

    public Integer getResultados() { return resultados; }
    public void setResultados(Integer resultados) { this.resultados = resultados; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
