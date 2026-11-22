package com.botilleria.productos.service;

import com.botilleria.productos.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class KafkaEventProducer {

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.kafka.enabled:true}")
    private boolean kafkaEnabled;

    @Value("${app.kafka.topic-productos:productos.eventos}")
    private String topicProductos;

    public void publicarProductoVisto(String usuarioId, Producto producto) {
        if (!kafkaEnabled || kafkaTemplate == null || producto == null) {
            return;
        }
        try {
            Map<String, Object> evento = new LinkedHashMap<>();
            evento.put("tipoEvento", "PRODUCTO_VISTO");
            evento.put("origen", "ms-productos");
            evento.put("usuarioId", (usuarioId != null && !usuarioId.isBlank()) ? usuarioId : "anonimo");
            evento.put("productoId", producto.getIdentificador());
            evento.put("productoNombre", producto.getNombre());
            evento.put("precio", producto.getPrecio());
            evento.put("inventario", producto.getInventario());
            evento.put("timestamp", Instant.now().toString());

            String json = objectMapper.writeValueAsString(evento);
            kafkaTemplate.send(topicProductos, String.valueOf(producto.getIdentificador()), json);
        } catch (Exception e) {
            System.err.println("[ms-productos] Aviso Kafka (no bloqueante): " + e.getMessage());
        }
    }

    public void publicarBusqueda(String usuarioId, String query, int resultados) {
        if (!kafkaEnabled || kafkaTemplate == null) {
            return;
        }
        try {
            Map<String, Object> evento = new LinkedHashMap<>();
            evento.put("tipoEvento", "BUSQUEDA_PRODUCTO");
            evento.put("origen", "ms-productos");
            evento.put("usuarioId", (usuarioId != null && !usuarioId.isBlank()) ? usuarioId : "anonimo");
            evento.put("productoNombre", query);
            evento.put("resultados", resultados);
            evento.put("timestamp", Instant.now().toString());

            String json = objectMapper.writeValueAsString(evento);
            kafkaTemplate.send(topicProductos, "busqueda", json);
        } catch (Exception e) {
            System.err.println("[ms-productos] Aviso Kafka busqueda (no bloqueante): " + e.getMessage());
        }
    }
}
