package com.botilleria.productos.service;

import com.botilleria.productos.model.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private List<Producto> listaDeProductos = new ArrayList<>();

    public ProductoService() {
        // Poblamos la lista en memoria al iniciar el servicio
        listaDeProductos.add(new Producto(1L, "Pisco Alto del Carmen 35°", 5500, 20));
        listaDeProductos.add(new Producto(2L, "Cerveza Escudo Lata 470cc", 1200, 150));
        listaDeProductos.add(new Producto(3L, "Vino Casillero del Diablo", 4990, 35));
    }

    public List<Producto> obtenerTodos() {
        return listaDeProductos;
    }

    public Producto buscarPorId(Long identificador) {
        return listaDeProductos.stream()
                .filter(p -> p.getIdentificador().equals(identificador))
                .findFirst()
                .orElse(null);
    }
}