package com.botilleria.productos;

import com.botilleria.productos.model.Producto;
import com.botilleria.productos.service.ProductoService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductosServiceTest {

    @Test
    public void alObtenerTodos_deberiaRetornarTresProductos() {
        ProductoService servicio = new ProductoService();
        List<Producto> lista = servicio.obtenerTodos();

        assertNotNull(lista);
        assertEquals(3, lista.size());
    }

    @Test
    public void alBuscarPorId_deberiaRetornarElProductoCorrecto() {
        ProductoService servicio = new ProductoService();
        Producto producto = servicio.buscarPorId(1L);

        assertNotNull(producto);
        assertEquals("Pisco Alto del Carmen 35°", producto.getNombre());
    }
}