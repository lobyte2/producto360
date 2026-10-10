package com.botilleria.productos.consumer;

import com.botilleria.productos.service.ProductoService;
import com.botilleria.productos.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.List;

@Component
public class StockConsumer {

    @Autowired
    private ProductoService productoService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void descontarInventario(Map<String, Object> ordenData) {
        System.out.println("Evento recibido en Productos. Descontando stock...");
        
        List<Map<String, Object>> items = (List<Map<String, Object>>) ordenData.get("items");
        if (items != null) {
            for (Map<String, Object> item : items) {
                // RabbitMQ Jackson converter turns numbers to Integers or Longs depending on size
                Long productoId = Long.valueOf(item.get("productoId").toString());
                Integer cantidad = Integer.valueOf(item.get("cantidad").toString());
                
                productoService.descontarStock(productoId, cantidad);
            }
        }
    }
}
