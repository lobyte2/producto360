package com.botilleria.productos.service;

import com.botilleria.productos.model.Producto;
import com.botilleria.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired(required = false)
    private ProductoRepository productoRepository;

    private List<Producto> listaRespaldo = new ArrayList<>();

    public ProductoService() {
        listaRespaldo.add(new Producto(1L, "Pisco Alto del Carmen 35°", 7500, 20, "https://d3w3u0f6pasxxu.cloudfront.net/optimized/12e266cbd209e421a915bf9ad1ca524e-3x.jpeg"));
        listaRespaldo.add(new Producto(2L, "Cerveza Escudo Lata 470cc", 1500, 150, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeWk8thFq5OCe_VfDfQ8_ZGLxEFqbEF9GoocXrEqeLww-p_E-Nz4ccn8my&s=10"));
        listaRespaldo.add(new Producto(3L, "Vino Casillero del Diablo", 6500, 35, "https://conchaytoro.com/wp-content/uploads/2018/07/7X1A7469-editada-min.webp"));
        listaRespaldo.add(new Producto(4L, "Whisky Johnnie Walker Red Label", 18000, 15, "https://santaisabel.vtexassets.com/arquivos/ids/377375/Whisky-Johnnie-Walker-Red-Label-40%C2%B0-750-cc.jpg"));
        listaRespaldo.add(new Producto(5L, "Bebida Coca-Cola 2L", 2800, 50, "https://cugat.cl/wp-content/uploads/2021/04/cugat.cl-bebida-coca-cola-original-desechable-2-5lt.jpg"));
    }

    public List<Producto> obtenerTodos() {
        if (productoRepository != null) {
            try {
                List<Producto> prods = productoRepository.findAll();
                if (!prods.isEmpty()) {
                    return prods;
                }
            } catch (Exception e) {
                System.err.println("Aviso conexion Supabase: " + e.getMessage());
            }
        }
        return listaRespaldo;
    }

    public Producto buscarPorId(Long identificador) {
        if (productoRepository != null) {
            try {
                return productoRepository.findById(identificador).orElse(null);
            } catch (Exception e) {
                System.err.println("Aviso conexion Supabase: " + e.getMessage());
            }
        }
        return listaRespaldo.stream()
                .filter(p -> p.getIdentificador().equals(identificador))
                .findFirst()
                .orElse(null);
    }

    public void descontarStock(Long identificador, Integer cantidad) {
        Producto p = buscarPorId(identificador);
        if (p != null) {
            int nuevoInventario = p.getInventario() - cantidad;
            p.setInventario(nuevoInventario < 0 ? 0 : nuevoInventario);
            
            if (productoRepository != null) {
                try {
                    productoRepository.save(p);
                    System.out.println("Stock guardado en Supabase para " + p.getNombre() + ". Nuevo stock: " + p.getInventario());
                    return;
                } catch (Exception e) {
                    System.err.println("Error al guardar stock en Supabase: " + e.getMessage());
                }
            }
            System.out.println("Stock descontado en memoria para " + p.getNombre() + ". Nuevo stock: " + p.getInventario());
        }
    }
}
