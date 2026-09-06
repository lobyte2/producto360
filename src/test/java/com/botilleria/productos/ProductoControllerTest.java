package com.botilleria.productos;

import com.botilleria.productos.controller.ProductoController;
import com.botilleria.productos.controller.ProductoController;
import com.botilleria.productos.model.Producto;
import com.botilleria.productos.service.ProductoService;
import com.botilleria.productos.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService servicioDeProducto;

    @Test
    public void listarCatalogo_DeberiaRetornarListaDeProductos() throws Exception {
        Producto p1 = new Producto(1L, "Pisco Alto del Carmen 35°", 5500, 20);
        Mockito.when(servicioDeProducto.obtenerTodos()).thenReturn(List.of(p1));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pisco Alto del Carmen 35°"));
    }
}