# Funcionalidad de los servicios

* Cada API tiene su respectivo CRUD (Create, Read, Update, Delete).
* La mayoría tiene un seeder para poblar la base de datos con datos de prueba.
* El microservicio de mecánica tiene configuraciones customs (años de garantía según tipo de vehículo) en el application.properties.
* El microservicio de ventas tiene configuraciones customs (id de la sucursal que vende) en el application.properties.
* El microservicio de ventas verifica el stock en el almacen de la sucursal que vende, y si no hay stock, verifica en el almacén central. Si no hay stock en ninguno de los dos almacenes, no se puede realizar la venta. Si el stock se encuentra en el almacén central, se realiza la transferencia del stock al almacen de la sucursal que vende, y luego se realiza la venta.
* Las configuraciones de los tiempos de entrega son dinámicas, están en las entidades DeliveryConfig, se obtienen en tiempo de ejecución por el microservicio de stock y se utilizan para calcular los tiempos de entrega. El mismo queda finalmente plasmado en la observacion de la venta.