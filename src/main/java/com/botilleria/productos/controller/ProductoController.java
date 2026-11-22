package com.botilleria.productos.controller;

import com.botilleria.productos.model.Producto;
import com.botilleria.productos.service.KafkaEventProducer;
import com.botilleria.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicioDeProducto;

    @Autowired(required = false)
    private KafkaEventProducer kafkaEventProducer;

    @GetMapping
    public ResponseEntity<List<Producto>> listarCatalogo() {
        return ResponseEntity.ok(servicioDeProducto.obtenerTodos());
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<Producto> obtenerDetalle(
            @PathVariable Long identificador,
            @RequestParam(required = false) String usuarioId,
            Authentication authentication) {
        Producto productoEncontrado = servicioDeProducto.buscarPorId(identificador);
        if (productoEncontrado != null) {
            if (kafkaEventProducer != null) {
                kafkaEventProducer.publicarProductoVisto(resolverUsuario(usuarioId, authentication), productoEncontrado);
            }
            return ResponseEntity.ok(productoEncontrado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductos(
            @RequestParam String q,
            @RequestParam(required = false) String usuarioId,
            Authentication authentication) {
        String termino = q == null ? "" : q.trim().toLowerCase(Locale.ROOT);
        List<Producto> filtrados = servicioDeProducto.obtenerTodos().stream()
                .filter(p -> p.getNombre() != null && p.getNombre().toLowerCase(Locale.ROOT).contains(termino))
                .toList();
        if (kafkaEventProducer != null && !termino.isEmpty()) {
            kafkaEventProducer.publicarBusqueda(resolverUsuario(usuarioId, authentication), q.trim(), filtrados.size());
        }
        return ResponseEntity.ok(filtrados);
    }

    private String resolverUsuario(String usuarioParam, Authentication auth) {
        if (usuarioParam != null && !usuarioParam.isBlank()) {
            return usuarioParam;
        }
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            String pref = jwt.getClaimAsString("preferred_username");
            if (pref != null && !pref.isBlank()) return pref;
            String email = jwt.getClaimAsString("email");
            if (email != null && !email.isBlank()) return email;
            String sub = jwt.getSubject();
            if (sub != null && !sub.isBlank()) return sub;
        }
        return "1";
    }
}
