# MS-Productos

Microservicio encargado de gestionar el catlogo e inventario de la botillera.

## Caractersticas
- Expone endpoints para consultar los productos disponibles.
- Integracin con **RabbitMQ** para descontar el stock de manera asncrona cuando se concreta una venta.
- Se registra en Netflix Eureka.
- Protegido va Microsoft Entra ID (Azure AD).

## Instrucciones de Uso
- Puerto por defecto: 8082
- Ejecutar con mvn spring-boot:run o a travs de docker-compose up.
