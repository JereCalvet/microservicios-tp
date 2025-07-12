# Funcionalidad de los servicios

* Cada API tiene su respectivo CRUD (Create, Read, Update, Delete).
* La mayoría tiene un seeder para poblar la base de datos con datos de prueba.
* El microservicio de mecánica tiene configuraciones customs (años de garantía según tipo de vehículo) en el application.properties.
* El microservicio de mecánica calcula si el vehiculo esta en garantía según que se configuraron para cada tipo en el archivo application.properties. Este mismo busca la fecha de venta del vehículo en el microservicio de ventas (con la patente), y si la fecha de venta es mayor a la fecha de garantía, se informa que el vehículo no está en garantía.
* El microservicio de ventas tiene configuraciones customs (id de la sucursal que vende) en el application.properties.
* El microservicio de ventas verifica el stock en el almacen de la sucursal que vende, y si no hay stock, verifica en el almacén central. Si no hay stock en ninguno de los dos almacenes, no se puede realizar la venta. Si el stock se encuentra en el almacén central, se realiza la transferencia del stock al almacen de la sucursal que vende, y luego se realiza la venta.
* Las configuraciones de los tiempos de entrega son dinámicas, están en las entidades DeliveryConfig, se obtienen en tiempo de ejecución por el microservicio de stock y se utilizan para calcular los tiempos de entrega. El mismo queda finalmente plasmado en la observacion de la venta.

## Requisitos para ejecutar el proyecto
* Ejecutar el docker-compose.yml para levantar las bases de datos. Los parametros de conexión, ya estan seteados en los application.properties de cada microservicio.

## Datos generados por las seeds

<img width="1733" height="1837" alt="image" src="https://github.com/user-attachments/assets/c208f1c7-117d-423c-8b76-85b02c226847" />

## Diagrama de secuencia del proceso de venta - interaccion entre microservicios

<img width="2566" height="2055" alt="image" src="https://github.com/user-attachments/assets/cc1ad1c9-f2e9-4842-8abc-f593e21342e3" />

## Diagrama de secuencia del proceso de servicio mecánico - interaccion entre microservicios

Calcula si corresponde o no la garantía

<img width="2628" height="972" alt="image" src="https://github.com/user-attachments/assets/2bf4d412-025e-4776-b483-1f13facdc081" />
