package com.botilleria.productos.controller;


import com.botilleria.productos.model.Producto;
import com.botilleria.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicioDeProducto;

    @GetMapping
    public ResponseEntity<List<Producto>> listarCatalogo() {
        return ResponseEntity.ok(servicioDeProducto.obtenerTodos());
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<Producto> obtenerDetalle(@PathVariable Long identificador) {
        Producto productoEncontrado = servicioDeProducto.buscarPorId(identificador);
        if (productoEncontrado != null) {
            return ResponseEntity.ok(productoEncontrado);
        }
        return ResponseEntity.notFound().build();
    }
}