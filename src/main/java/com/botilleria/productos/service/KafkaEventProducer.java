package com.botilleria.productos.service;

import com.botilleria.productos.dto.ProductoEventoDto;
import com.botilleria.productos.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

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
            ProductoEventoDto evento = new ProductoEventoDto();
            evento.setTipoEvento("PRODUCTO_VISTO");
            evento.setOrigen("ms-productos");
            evento.setUsuarioId((usuarioId != null && !usuarioId.isBlank()) ? usuarioId : "anonimo");
            evento.setProductoId(producto.getIdentificador());
            evento.setProductoNombre(producto.getNombre());
            evento.setPrecio(producto.getPrecio());
            evento.setInventario(producto.getInventario());
            evento.setTimestamp(Instant.now().toString());

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
            ProductoEventoDto evento = new ProductoEventoDto();
            evento.setTipoEvento("BUSQUEDA_PRODUCTO");
            evento.setOrigen("ms-productos");
            evento.setUsuarioId((usuarioId != null && !usuarioId.isBlank()) ? usuarioId : "anonimo");
            evento.setProductoNombre(query);
            evento.setResultados(resultados);
            evento.setTimestamp(Instant.now().toString());

            String json = objectMapper.writeValueAsString(evento);
            kafkaTemplate.send(topicProductos, "busqueda", json);
        } catch (Exception e) {
            System.err.println("[ms-productos] Aviso Kafka busqueda (no bloqueante): " + e.getMessage());
        }
    }
}
