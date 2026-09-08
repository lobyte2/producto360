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
        listaDeProductos.add(new Producto(1L, "Pisco Alto del Carmen 35°", 7500, 20, "https://d3w3u0f6pasxxu.cloudfront.net/optimized/12e266cbd209e421a915bf9ad1ca524e-3x.jpeg"));
        listaDeProductos.add(new Producto(2L, "Cerveza Escudo Lata 470cc", 1500, 150, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeWk8thFq5OCe_VfDfQ8_ZGLxEFqbEF9GoocXrEqeLww-p_E-Nz4ccn8my&s=10"));
        listaDeProductos.add(new Producto(3L, "Vino Casillero del Diablo", 6500, 35, "https://conchaytoro.com/wp-content/uploads/2018/07/7X1A7469-editada-min.webp"));
        listaDeProductos.add(new Producto(4L, "Whisky Johnnie Walker Red Label", 18000, 15, "https://santaisabel.vtexassets.com/arquivos/ids/377375/Whisky-Johnnie-Walker-Red-Label-40%C2%B0-750-cc.jpg?v=638520760716300000"));
        listaDeProductos.add(new Producto(5L, "Bebida Coca-Cola 2L", 2800, 50, "https://cugat.cl/wp-content/uploads/2021/04/cugat.cl-bebida-coca-cola-original-desechable-2-5lt.jpg"));
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