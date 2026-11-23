package com.botilleria.productos.consumer;

import com.botilleria.productos.dto.CarritoEventoRecibidoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumidor Kafka en ms-productos: monitorea la demanda de productos agregados en carrito.eventos.
 * Mantiene stock.queue de RabbitMQ intacta para el descuento transaccional de inventario.
 */
@Component
public class ProductoKafkaConsumer {

    private final ObjectMapper objectMapper;

    public ProductoKafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "${app.kafka.topic-carrito:carrito.eventos}",
            groupId = "${spring.kafka.consumer.group-id:ms-productos-group}",
            autoStartup = "${app.kafka.enabled:true}"
    )
    public void consumirEventoCarrito(String payload) {
        CarritoEventoRecibidoDto evento;
        try {
            evento = objectMapper.readValue(payload, CarritoEventoRecibidoDto.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Payload no es JSON valido en carrito.eventos", e);
        }
        System.out.println("[MS-PRODUCTOS][KAFKA] Demanda detectada: " + evento.getTipoEvento()
                + " | productoId=" + evento.getProductoId()
                + " | cantidad=" + evento.getCantidad());
    }
}
