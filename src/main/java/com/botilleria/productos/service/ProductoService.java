package com.botilleria.productos.service;

import com.botilleria.productos.model.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private List<Producto> listaDeProductos = new ArrayList<>();

    public ProductoService() {
        // Precios actualizados a la realidad chilena
        listaDeProductos.add(new Producto(1L, "Pisco Alto del Carmen 35°", 7500, 20, "https://via.placeholder.com/200x200/F39C12/ffffff?text=Pisco"));
        listaDeProductos.add(new Producto(2L, "Cerveza Escudo Lata 470cc", 1500, 150, "https://via.placeholder.com/200x200/F1C40F/000000?text=Cerveza"));
        listaDeProductos.add(new Producto(3L, "Vino Casillero del Diablo", 6500, 35, "https://via.placeholder.com/200x200/8E44AD/ffffff?text=Vino"));
        listaDeProductos.add(new Producto(4L, "Whisky Johnnie Walker Red Label", 18000, 15, "https://via.placeholder.com/200x200/D35400/ffffff?text=Whisky"));
        listaDeProductos.add(new Producto(5L, "Bebida Coca-Cola 2L", 2800, 50, "https://via.placeholder.com/200x200/C0392B/ffffff?text=Bebida"));
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